package com.sravan.covidapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    @ColumnInfo(name = "title")
    val noteTitle: String,

    @ColumnInfo(name = "description")
    val notesDescription: String,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String,

    @PrimaryKey(autoGenerate = true) var id: Int = 0

) {


}
