package com.fakestore.Room


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "products_table")
@Parcelize
data class ProductEntity(
//    @PrimaryKey(autoGenerate = true) val id: Number,
    @PrimaryKey(autoGenerate = true)  val id: Int,
    val title: String,
    //val price: Number,
    val category: String,
    val description: String,
    val image: String
):Parcelable{

}
