package com.aakash.fithub.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val _id: String? = null,
    val fname: String? = null,
    val lname: String? = null,
    val age: Int? = null,
    val gender: String? = null,
    val height: String? = null,
    val weight: String?= null,
    val email: String? = null,
    val password: String? = null,
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeValue(age)
        parcel.writeString(gender)
        parcel.writeString(height)
        parcel.writeString(weight)
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}