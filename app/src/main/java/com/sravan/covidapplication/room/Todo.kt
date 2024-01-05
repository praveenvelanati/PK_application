package com.sravan.covidapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(@ColumnInfo("filename")val title: String, @ColumnInfo("timeStamp") val timeStamp: String) {

    @PrimaryKey(autoGenerate = true) var id = 0
}