package com.nauka.myexpenses

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.animation.TweenBuilder
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.futured.donut.compose.DonutProgress
import app.futured.donut.compose.data.DonutConfig
import app.futured.donut.compose.data.DonutModel
import app.futured.donut.compose.data.DonutSection
import com.nauka.myexpenses.Adapters.PurchasesViewAdapter
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.AllViewModel.PurchasesTableViewModel
import com.nauka.myexpenses.Fragments.AddCheckFragment
import com.nauka.myexpenses.ProgressBar.PercentProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.delete_dialog.view.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    private lateinit var model: PurchasesTableViewModel
    var context: Context? = null
    private var addCheckFragment: AddCheckFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        addCheckFragment = AddCheckFragment()



        addСheckButton.setOnClickListener {
            addCheckFragment?.show(supportFragmentManager, "addCheck")
        }


        context = this

        model = ViewModelProvider(this).get(PurchasesTableViewModel::class.java)
        /*В переменные permissionStatus/permissionStatus read записываются данные для сверки
        есть ли разрешение чтения/записи во внутреннюю память*/
        val permissionStatus = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionStatusread = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val WRITE_EXTERNAL_STORAGE = 100

        //Проверка на наличие разрешений на чтение/запись во внутреннуюю память
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                WRITE_EXTERNAL_STORAGE
            )
        }

        if (permissionStatusread != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                WRITE_EXTERNAL_STORAGE
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        /*model.allPurchasesTable.observe(this, androidx.lifecycle.Observer { purchasesTable ->
            recyclerView.adapter = PurchasesViewAdapter(purchasesTable, onCardClick = {
                doAsync { model.allPurchasesTable }
            }, del = context as MainActivity)
        })*/

        model.allToday.observe(this, androidx.lifecycle.Observer { purchasesTable ->
            recyclerView.adapter = PurchasesViewAdapter(purchasesTable, onCardClick = {
                doAsync { model.allToday }
            }, del = context as MainActivity)
        })



        model.allPurchasesTable.observe(this, androidx.lifecycle.Observer { purchases ->
            mounthSumm.text =
                purchases.sumBy(PurchasesTable::summ).toString()
            val prog = PercentProgressBar().Progresses(summPotrch, progressBar2, mounthSumm)
            chip4.text =
                "Всего открытых счетов: " + purchases.sumBy(PurchasesTable::summ).toString()
        })





        model.allToday.observe(this, androidx.lifecycle.Observer { purchasesTable ->
            recyclerView.adapter = PurchasesViewAdapter(purchasesTable, onCardClick = {
                delAlertDialog(it)
                //doAsync { model.delete(it) }
            }, del = context as MainActivity)
        })

    }

    fun delAlertDialog(del: PurchasesTable) {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this@MainActivity)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.delete_dialog, null)
        builder.setView(dialogLayout)

        val yesBtn = dialogLayout.yesButton
        val noBtn = dialogLayout.noButton
        builder.setCancelable(false)

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()

        yesBtn.setOnClickListener {
            doAsync { model.delete(del) }
            dialog.dismiss()

        }
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}