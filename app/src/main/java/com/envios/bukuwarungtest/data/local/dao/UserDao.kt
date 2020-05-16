package com.envios.bukuwarungtest.data.local.dao

import androidx.room.*
import com.envios.bukuwarungtest.data.local.model.User
import io.reactivex.Flowable

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)


    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flowable<List<User>>

    @Delete
    fun delete(user: User)
}