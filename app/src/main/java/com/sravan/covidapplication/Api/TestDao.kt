package com.sravan.covidapplication.Api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sravan.covidapplication.models.EntryData

@Dao
interface TestDao {

    @Query("SELECT * FROM data")
    suspend fun getAllData(): ArrayList<EntryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entryDataList: ArrayList<EntryData>)

}