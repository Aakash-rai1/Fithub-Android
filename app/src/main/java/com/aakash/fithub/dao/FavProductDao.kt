package com.aakash.fithub.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aakash.fithub.entity.ForFavProduct

@Dao
interface FavProductDao {
    @Insert
    suspend fun AddProdcut(list:List<ForFavProduct>?)

    @Query("select * from forfavproduct")
    suspend fun getproduct(): List<ForFavProduct>?

    @Query("delete from forfavproduct")
    suspend fun dropTable()
}