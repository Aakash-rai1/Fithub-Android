package com.aakash.fithub.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aakash.fithub.entity.Workout

@Dao
interface WorkoutDAO {

    @Query("Delete from Workout")
    suspend fun deleteWorkOutData()
    @Insert
    suspend fun  insertWorkOutData(workout: MutableList<Workout>?)
}
