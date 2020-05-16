package com.envios.bukuwarungtest.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User(

    @PrimaryKey var id: Int? = null,
    var avatar: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null

) : Parcelable