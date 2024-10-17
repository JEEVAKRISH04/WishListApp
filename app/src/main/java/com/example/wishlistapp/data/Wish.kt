package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "wish-name")
    val name: String="",

    @ColumnInfo(name = "wish-position")
    val position: String="",

    @ColumnInfo(name = "wish-contactNumber")
    val contactNumber: String="",

    @ColumnInfo(name = "wish-genders")
    val genders: String="",

    @ColumnInfo(name = "wish-Department")
    val Department: String=""
)

object DummyWish{
    val wishList = listOf(
        Wish(
            name ="jeeva k",
            position ="android developer intern",
            contactNumber ="1234567890",
            genders ="male",
            Department =  "Android Development"),
        Wish(
            name ="kannan s",
            position ="web developer intern",
            contactNumber ="0987654321",
            genders ="male",
            Department =  "Web Development"),
        Wish(
            name ="sanjay a",
            position ="web developer",
            contactNumber ="0987654321",
            genders ="male",
            Department =  "Web Development"),
        Wish(
            name ="indrish",
            position ="web developer",
            contactNumber ="1234567890",
            genders ="male",
            Department =  "Web Development")
    )
}
