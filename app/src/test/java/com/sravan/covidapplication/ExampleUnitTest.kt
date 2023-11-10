package com.sravan.covidapplication

import com.sravan.covidapplication.Api.TestApi
import com.sravan.covidapplication.Repository.EntryRepository
import com.sravan.covidapplication.UIModel.EntryViewModel
import com.sravan.covidapplication.models.Entry
import com.sravan.covidapplication.models.Profile
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var SUT: EntryViewModel
    lateinit var profile: Profile
    lateinit var entryRepository: EntryRepository
    var testApi = UnitTestApi()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun profile_Status_Should_be_true(){

        entryRepository = EntryRepository(testApi)
        SUT = EntryViewModel(entryRepository)
        SUT.updateProfile(isOnline = true)
        assertTrue(SUT.profile.isOnline)
    }
    @Test
    fun car_engine_span() {
        var engine1 = Engine()
        engine1.name = "India"
        val life = engine1.Country().start()
        assertTrue(life == 2)
        engine1.name = "Japan"
        val life2 = engine1.Country().start()
        assertTrue(life == 5)
        //var car1 = Car()
    }

}

class UnitTestApi :TestApi{

    override suspend fun getEntryData(): Response<Entry> {
        TODO("Not yet implemented")
    }

}

class Engine() {
   // val numOfMotors: Int = 4
    var name: String = ""
    inner class Country {

        fun start() : Int {
            if ( name == "India") {
                return 2
            } else if (name == "Japan") {
                return 5
            } else {
                return  3
            }
        }
    }


}

class Car {
    val engine = Engine()

    fun startEngine() {
        engine.Country().start()
    }
}



