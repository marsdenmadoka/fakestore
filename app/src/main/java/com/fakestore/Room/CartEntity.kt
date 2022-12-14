package com.fakestore.Room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "cartItems")
@Parcelize
data class CartEntity(
    @PrimaryKey(autoGenerate = true)  val id: Int=0,
    val title: String ?,
    val price: Double ?,
    val category: String ?,
    val image: String ?
): Parcelable