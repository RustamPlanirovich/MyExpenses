package com.nauka.myexpenses.Fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.AllViewModel.PurchasesTableViewModel
import com.nauka.myexpenses.R
import kotlinx.android.synthetic.main.add_check_fragment.*
import org.jetbrains.anko.doAsync
import java.text.SimpleDateFormat
import java.util.*

class AddCheckFragment : DialogFragment() {

    private var addCheckFragmentView: View? = null
    private var calendar: Calendar? = null
    private var currentDate = 0
    private var currentMonth = 0
    private var currentYear = 0
    private var currentDateMillis: Long = 0
    private lateinit var model: PurchasesTableViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addCheckFragmentView = inflater.inflate(
            R.layout.add_check_fragment,
            container, false
        )

        return addCheckFragmentView
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        oneTime()
        model = ViewModelProvider(this).get(PurchasesTableViewModel::class.java)

        dateEditText.setOnTouchListener(object : View.OnTouchListener {
            var mContext = dateEditText.context

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_UP) {
                    val datePickerDialog = DatePickerDialog(mContext,
                        { view, year, month, dayOfMonth ->

                            //Устанавливаем в Calendar выбранную дату
                            calendar?.set(year,month,dayOfMonth)

                            oneTime()
                        }, currentYear, currentMonth, currentDate
                    )
                    datePickerDialog.show()
                }
                return false
            }
        })

        addCheckButton.setOnClickListener {
            doAsync {
                model.insert(
                    PurchasesTable(
                        0,
                        currentDateMillis,
                        checkSummEditText.text.toString().toInt(),
                        "0",
                        currentDate.toString(),
                        (currentMonth+1).toString(),
                        currentYear.toString(),
                        typePalSpinner?.selectedItem.toString(),
                        categoryPalSpinner?.selectedItem.toString()
                    )
                )
            }
            dismiss()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun oneTime() {
        currentDate = calendar!!.get(Calendar.DAY_OF_MONTH)
        //Инициализация Month
        currentMonth = calendar!!.get(Calendar.MONTH)
        //Инициализация Year
        currentYear = calendar!!.get(Calendar.YEAR)
        //Получаем текущуюю дату в миллисекундах
        currentDateMillis = calendar!!.timeInMillis
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dayOfMounh = dateFormat.format(currentDateMillis)
        //Вставлеяем в editText2 полученную с класса calendar текущую дату
        dateEditText.setText(dayOfMounh)


    }
}