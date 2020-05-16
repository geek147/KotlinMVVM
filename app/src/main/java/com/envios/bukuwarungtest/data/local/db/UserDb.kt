package com.envios.bukuwarungtest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.envios.bukuwarungtest.data.local.dao.UserDao
import com.envios.bukuwarungtest.data.local.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao(): UserDao


}