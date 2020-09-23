package com.nauka.myexpenses.AllDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nauka.myexpenses.AllEntity.MountBallance
import com.nauka.myexpenses.AllEntity.PurchasesTable

@Dao
interface MountBallanceDAO {
    @Query("SELECT * FROM mountBallance ORDER BY id DESC")
    fun mountBallanceAll(): LiveData<List<MountBallance>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mountBallance: MountBallance)

    @Delete()
    fun delete(mountBallance: MountBallance)
}