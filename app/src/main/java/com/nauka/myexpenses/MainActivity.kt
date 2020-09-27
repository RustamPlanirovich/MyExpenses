package com.nauka.myexpenses

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.PopupMenu
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nauka.myexpenses.Adapters.PurchasesViewAdapter
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.AllViewModel.PurchasesTableViewModel
import com.nauka.myexpenses.Fragments.AddCheckFragment
import com.nauka.myexpenses.ProgressBar.PercentProgressBar
import com.nauka.myexpenses.R.layout.activity_main
import com.nauka.myexpenses.R.layout.delete_dialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.first.*
import kotlinx.android.synthetic.main.five.*
import kotlinx.android.synthetic.main.third.*
import kotlinx.android.synthetic.main.two.*
import org.jetbrains.anko.doAsync
import com.nauka.myexpenses.R.layout.*
import com.nauka.myexpenses.R.menu.*
import com.nauka.myexpenses.R.id.*
import kotlinx.android.synthetic.main.choice_of_subject.*


class MainActivity : AppCompatActivity() {
    private lateinit var model: PurchasesTableViewModel
    var context: Context? = null
    private var addCheckFragment: AddCheckFragment? = null
    private lateinit var viecomponent: ViewComponent
    private lateinit var choiceTheme: ChoiceTheme
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        //Скрываем ActionBar
        supportActionBar?.hide()
        //получаем Context MainActivity
        context = this

        /*Инициализируем AddChtckFragment который отвечает за
        добавление нового чека*/
        addCheckFragment = AddCheckFragment()
        /*Инициализация ViewComponent который отвечает за видимость
        основных элементов главного экрана*/
        viecomponent = ViewComponent(context as MainActivity)
        /*Инициализируем MoneyConvert который отвечает за формирование строки
        * с буквой валюты*/
        val money = MoneyConvert()
        choiceTheme = ChoiceTheme(context)
        choiceTheme.loadStateRadioButton()
        /*Инициализирем метод views в котором происходит включение
        * выключение видимости элементов главного экрана*/
        views()
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        /*Инициализация меню и его работа*/
        val popupMenu = PopupMenu(this, menu)
        popupMenu.inflate(menu_main)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                panel -> {
                    val intent = Intent(this, PanelSetting::class.java)
                    startActivity(intent)
                    true
                }
                darkmode -> {
                    val alertDialog = Dialog(this)
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    alertDialog.setContentView(choice_of_subject)
                    alertDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


                    val white = alertDialog.white
                    val black = alertDialog.black
                    val system = alertDialog.system

//                    white.isChecked = choiceTheme.white()
//                    black.isChecked = choiceTheme.black()
//                    system.isChecked = choiceTheme.system()

                    white.setOnClickListener {
                        choiceTheme.saveStateRadioButton(
                            context as MainActivity,
                            white.text.toString()
                        )
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    black.setOnClickListener {
                        choiceTheme.saveStateRadioButton(
                            context as MainActivity,
                            black.text.toString()
                        )
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    system.setOnClickListener {
                        choiceTheme.saveStateRadioButton(
                            context as MainActivity,
                            system.text.toString()
                        )
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    alertDialog.show()

                    true
                }
                setting -> {
                    val intent = Intent(this, GeneralSetting::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        //При клике на textView(menu) открывается меню
        menu.setOnClickListener {
            popupMenu.show()
        }


        /*При скроле главного экрана и достижения его конца,
        * addСheckButton, bottom_appbar скрываются, а когда переходим
        * чуть выше то отображаются*/
        scrollView.viewTreeObserver
            .addOnScrollChangedListener {
                if (scrollView != null) {
                    if (scrollView.getChildAt(0)
                            .bottom <= scrollView.height + scrollView.scrollY
                    ) {
                        //scroll view is at bottom
                        addСheckButton.hide()
                        bottom_appbar.performHide()
                    } else {
                        //scroll view is not at bottom
                        addСheckButton.show()
                        bottom_appbar.performShow()
                    }
                }
            }

        //Вызов фрагмента для добавления нового чека
        addСheckButton.setOnClickListener {
            addCheckFragment?.show(supportFragmentManager, "addCheck")
        }

        //Инициализируем ViewModel.
        model = ViewModelProvider(this).get(PurchasesTableViewModel::class.java)

        //В переменные permissionStatus/permissionStatus read записываются данные для сверки
        //есть ли разрешение чтения/записи во внутреннюю память
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

        //Привязываем linerLayout для RecyclerView
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
            mounthSumm.text = money.moneyConvert(purchases.sumBy(PurchasesTable::summ))
            val prog = PercentProgressBar().Progresses(
                summPotrch,
                progressBar2,
                purchases.sumBy(PurchasesTable::summ)
            )
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

    /*Инициализируем диалог для удаления выбранной записи из БД*/
    fun delAlertDialog(del: PurchasesTable) {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this@MainActivity)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(delete_dialog, null)
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

    /*Метод в котором происходит включение или выключение
    * элементов главного экрана*/
    fun views() {
        include2.visibility = viecomponent.include2()
        include3.visibility = viecomponent.include3()
        include4.visibility = viecomponent.include4()
        include5.visibility = viecomponent.include5()
        include6.visibility = viecomponent.include6()
        include7.visibility = viecomponent.include7()
        include8.visibility = viecomponent.include8()
    }

    override fun onResume() {
        super.onResume()
        views()
    }

}