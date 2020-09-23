package com.nauka.myexpenses.AllViewModel

import android.app.Application
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nauka.myexpenses.AllDao.PurchasesTableDAO
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.MainActivity
import com.nauka.myexpenses.Singelton.RoomSingelton
import java.util.*


class PurchasesTableViewModel(application: Application) : AndroidViewModel(application) {

    var calendar = Calendar.getInstance()
    var currentDate = calendar!!.get(Calendar.DAY_OF_MONTH)

    private val dbPurchasesTable: RoomSingelton = RoomSingelton.getInstance(application)
    internal val allPurchasesTable: LiveData<List<PurchasesTable>> = dbPurchasesTable.purchasesDao()
        .purchasesTableAll()

    internal val allToday: LiveData<List<PurchasesTable>> = dbPurchasesTable.purchasesDao()
        .purchasesToday(currentDate)

    fun insert(purchasesTable: PurchasesTable) {
        dbPurchasesTable.purchasesDao().insert(purchasesTable)
    }

    fun delete(purchasesTable: PurchasesTable) {
        dbPurchasesTable.purchasesDao().delete(purchasesTable)
    }

    fun update(purchasesTable: PurchasesTable) {
        dbPurchasesTable.purchasesDao().update(purchasesTable)

    }
}

