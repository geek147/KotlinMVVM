package com.envios.bukuwarungtest.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.envios.bukuwarungtest.data.local.dao.UserDao
import com.envios.bukuwarungtest.data.local.db.DatabaseFactory.Companion.DB_VERSION
import com.envios.bukuwarungtest.data.local.model.User

@Database(entities = [User::class], version = DB_VERSION, exportSchema = false)
abstract class DatabaseFactory : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "user.db"
        @Volatile
        private var INSTANCE: DatabaseFactory? = null

        fun getInstance(context: Context): DatabaseFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, DatabaseFactory::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        //In case app has to migrate to a new DBDao
        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}