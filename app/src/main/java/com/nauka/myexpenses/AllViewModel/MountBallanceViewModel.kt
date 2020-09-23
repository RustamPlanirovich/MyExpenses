package com.nauka.myexpenses.AllViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nauka.myexpenses.AllEntity.MountBallance
import com.nauka.myexpenses.Singelton.RoomSingelton

class MountBallanceViewModel(application: Application):AndroidViewModel(application) {
    private val dbMount: RoomSingelton = RoomSingelton.getInstance(application)
    internal val allMountBallance : LiveData<List<MountBallance>> = dbMount.mountballanceDao().mountBallanceAll()

    fun insert(mountBallance: MountBallance){
        dbMount.mountballanceDao().insert(mountBallance)
    }

    fun delete(mountBallance: MountBallance){
        dbMount.mountballanceDao().delete(mountBallance)
    }
}