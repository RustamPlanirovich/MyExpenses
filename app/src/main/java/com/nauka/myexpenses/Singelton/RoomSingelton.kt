package com.nauka.myexpenses.Singelton

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nauka.myexpenses.AllDao.CategoriesDAO
import com.nauka.myexpenses.AllDao.MountBallanceDAO
import com.nauka.myexpenses.AllDao.PurchasesTableDAO
import com.nauka.myexpenses.AllDao.WalletsDAO
import com.nauka.myexpenses.AllEntity.Categories
import com.nauka.myexpenses.AllEntity.MountBallance
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.AllEntity.Wallets

@Database(entities = [PurchasesTable::class,MountBallance::class,
Wallets::class,Categories::class], version = 1, exportSchema = false)
abstract class RoomSingelton: RoomDatabase() {
    abstract fun purchasesDao(): PurchasesTableDAO
    abstract fun mountballanceDao(): MountBallanceDAO
    abstract fun walletsDao(): WalletsDAO
    abstract fun categoriesDao(): CategoriesDAO

    companion object{
        private var INSTANCE: RoomSingelton? = null
        fun getInstance(context: Context): RoomSingelton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingelton::class.java,
                    "purchasesDB")
                    .build()
            }
            return INSTANCE as RoomSingelton
        }
    }
}