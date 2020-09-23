package com.nauka.myexpenses.AllDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nauka.myexpenses.AllEntity.MountBallance
import com.nauka.myexpenses.AllEntity.Wallets

@Dao
interface WalletsDAO {
    @Query("SELECT * FROM wallets ORDER BY id DESC")
    fun walletsAll(): LiveData<List<Wallets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wallets: Wallets)

    @Delete()
    fun delete(wallets: Wallets)
}