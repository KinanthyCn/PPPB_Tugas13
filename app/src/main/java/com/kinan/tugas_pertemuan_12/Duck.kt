package com.kinan.tugas_pertemuan_12

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Duck(
    val id: String = "",
    val title : String,
    val description : String,
    val message : String,
    val url : String

)
