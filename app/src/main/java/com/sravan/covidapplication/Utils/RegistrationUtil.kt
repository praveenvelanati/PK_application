package com.sravan.covidapplication.Utils

import java.util.regex.Pattern

object RegistrationUtil {

    private val existingUsers = listOf("Rahul@gmail.com", "Rohan@gmail.com")

    /**
     * The test cases will pass if..
     * ...username/password/confirmPassword is not empty
     * ...password is at least 2 digits
     * ...password matches the confirm Password
     * ...username is not taken
     */
    fun validRegistrationInput(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // write conditions along with their return statement
        // if username / password / confirm password are empty return false
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false
        }

        //validate username format
        if (!emailValidation(email)){
            return false
        }

        // if username exists in the existingUser list return false
        if (email in existingUsers) {
            return false
        }
        // if password does not matches confirm password return false
        if (password != confirmPassword) {
            return false
        }
        // if digit count of the password is less than 2 return false
        if (password.length <= 4) {
            return false
        }
        return true
    }

    private fun emailValidation(email: String): Boolean {

        val emailAddressPattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailAddressPattern.matcher(email).matches()
    }
}
