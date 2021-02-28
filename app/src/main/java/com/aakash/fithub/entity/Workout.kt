package com.aakash.fithub.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Workout (
        @PrimaryKey(autoGenerate = false)
        val _id: String = "",
        val wname: String?=null,
        val program: String?= null,
        val link: String?=null,
        val photo:String?=null
        )