package com.sravan.covidapplication.models

import androidx.room.Entity

@Entity(tableName = "data")
data class EntryData(val API:String, val Description: String, val Category: String)
