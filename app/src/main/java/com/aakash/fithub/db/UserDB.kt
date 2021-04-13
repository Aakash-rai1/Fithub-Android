package com.aakash.fithub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aakash.fithub.dao.FavProductDao
import com.aakash.fithub.dao.UserDAO
import com.aakash.fithub.dao.WorkoutDAO
import com.aakash.fithub.entity.ForFavProduct
import com.aakash.fithub.entity.User
import com.aakash.fithub.entity.Workout


@Database(
entities =[(User::class), (Workout::class),(ForFavProduct::class)],
version = 4
)

abstract class

UserDB:RoomDatabase() {
abstract fun getUserDAO():UserDAO
abstract fun getWorkOutDAO():WorkoutDAO
abstract fun getFavDao(): FavProductDao



companion object{
@Volatile
    private var instance:UserDB?= null
    fun getInstance(context: Context):UserDB{
        if(instance==null){
            synchronized(UserDB::class){
                instance=buildDatabase(context)
            }
        }
        return instance!!
    }

    private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "UserDB"
            ).build()
}
}