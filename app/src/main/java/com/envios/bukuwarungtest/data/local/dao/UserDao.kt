package com.envios.bukuwarungtest.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.envios.bukuwarungtest.data.local.model.User
import io.reactivex.Flowable

interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)


    @Query("SELECT * FROM user_table")
    fun getAllCategories(): Flowable<List<User>>

    @Delete
    fun delete(user: User)
}