package com.aakash.fithub.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aakash.fithub.entity.User


//room db
@Dao
interface UserDAO {
    @Insert
    suspend fun RegisterActivity(user: User)

    @Query("Delete from User")
    suspend fun logout()



//    @Query("select * from User where email=(:email) and password=(:password)")
//    suspend fun checkUser(email: String, password: String)
}
