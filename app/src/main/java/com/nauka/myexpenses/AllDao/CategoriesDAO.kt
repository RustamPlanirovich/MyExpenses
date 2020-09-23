package com.nauka.myexpenses.AllDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nauka.myexpenses.AllEntity.Categories
import com.nauka.myexpenses.AllEntity.PurchasesTable

@Dao
interface CategoriesDAO {
    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun categoriesAll(): LiveData<List<Categories>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: Categories)

    @Delete()
    fun delete(categories: Categories)
}