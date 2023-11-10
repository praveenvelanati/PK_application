package com.sravan.covidapplication.Utils

import org.junit.Assert
import org.junit.Test

class RegistrationUtilTest{

   @Test
   fun testRegistrationWithEmptyField(){

       val result = RegistrationUtil.validRegistrationInput("sravan@gmail.com","","kumar")
       Assert.assertFalse(result)
   }

    @Test
    fun testRegistrationWithCompleteFields(){

        val result = RegistrationUtil.validRegistrationInput("sravan@gmail.com", "kumar","kumar")
        Assert.assertTrue(result)
    }

    @Test
    fun testRegistrationWithExistingUsers(){

        val result = RegistrationUtil.validRegistrationInput("Rohan@gmail.com","kumar","kumar")
        Assert.assertFalse(result)
    }

    @Test
    fun testRegistrationWithMisMatchedPasswords(){

        val result = RegistrationUtil.validRegistrationInput("sravan@gmail.com","kumar", "velanati")
        Assert.assertFalse(result)
    }

    @Test
    fun testRegistrationWithInsufficientPasswordLength(){

        val result = RegistrationUtil.validRegistrationInput("sravan@gmail.com","he","he")
        Assert.assertFalse(result)
    }

    @Test
    fun testRegistrationWithInCorrectEmailFormat(){

        val result = RegistrationUtil.validRegistrationInput("sravan@.com","kumar", "kumar")
        Assert.assertFalse(result)
    }



}