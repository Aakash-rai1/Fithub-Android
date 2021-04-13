package com.aakash.fithub.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForFavProduct(
    @PrimaryKey( autoGenerate = true) val primaryKey:Int=0,
    val _id:String?=null,
    val wname: String?=null,
    val program: String?= null,
    val link: String?=null,
    val image:String?=null
) {
}