package com.envios.bukuwarungtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey var id: Int? = null,
    var avatar: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null

)