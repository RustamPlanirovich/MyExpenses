package com.nauka.myexpenses.AllViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nauka.myexpenses.AllEntity.Wallets
import com.nauka.myexpenses.Singelton.RoomSingelton

class WalletsViewModel(application: Application):AndroidViewModel(application) {
    private val dbWallets : RoomSingelton = RoomSingelton.getInstance(application)
    internal val allWallets : LiveData<List<Wallets>> = dbWallets.walletsDao().walletsAll()

    fun insert(wallets: Wallets){
        dbWallets.walletsDao().insert(wallets)
    }

    fun delete(wallets: Wallets){
        dbWallets.walletsDao().delete(wallets)
    }
}