package com.nauka.myexpenses.AllDao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.nauka.myexpenses.AllEntity.PurchasesTable


@Dao
interface PurchasesTableDAO {
    @Query("SELECT * FROM purchasesTable ORDER BY id DESC")
    fun purchasesTableAll(): LiveData<List<PurchasesTable>>

    @Query("SELECT SUM(summ) FROM purchasesTable")
    fun purchasesSum(): LiveData<Int>

    @Query("SELECT * FROM purchasesTable WHERE day=:dayw ORDER BY day;")
    fun purchasesToday(dayw: Int): LiveData<List<PurchasesTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(purchasesTable: PurchasesTable)

    @Delete()
    fun delete(purchasesTable: PurchasesTable)

    @Update()
    fun update(purchasesTable: PurchasesTable)
}