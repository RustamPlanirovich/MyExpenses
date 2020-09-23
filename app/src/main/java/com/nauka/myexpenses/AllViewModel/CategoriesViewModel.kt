package com.nauka.myexpenses.AllViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nauka.myexpenses.AllEntity.Categories
import com.nauka.myexpenses.Singelton.RoomSingelton

class CategoriesViewModel(application: Application):AndroidViewModel(application) {
    private val db: RoomSingelton = RoomSingelton.getInstance(application)
    internal val allCategory: LiveData<List<Categories>> = db.categoriesDao().categoriesAll()

    fun insert(categories: Categories){
        db.categoriesDao().insert(categories)
    }
    fun delete(categories: Categories){
        db.categoriesDao().delete(categories)
    }
}