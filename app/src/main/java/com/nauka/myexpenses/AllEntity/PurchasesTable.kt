package com.nauka.myexpenses.AllEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "purchasesTable")
data class PurchasesTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var purchases: Long,
    var summ: Int,
    var mountballance: String,
    var day: String,
    var mount: String,
    var year: String,
    var cashtype: String,
    var category: String
)

@Entity(tableName = "mountBallance")
data class MountBallance(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var mountballance: String,
    var mount: String
)

@Entity(tableName = "wallets")
data class Wallets(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var purse: String,
    var type: String
)

@Entity(tableName = "categories")
data class Categories(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var categoryName: String
)