package com.sravan.covidapplication.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import com.sravan.covidapplication.Utils.Constants.PREFERENCE_NAME
import java.util.regex.Pattern

object Constants {

//    const val BASE_URL = "https://api.publicapis.org/"
   const val BASE_URL= "https://api.foursquare.com/v3/"

    const val USER_TOKEN = "user_token"
    const val PREFS_TOKEN_FILE = "prefs_token_file"
    const val PREFERENCE_NAME = "chatting"
    const val FCM_TOKEN = "fcm_token"
    const val USERS = "Users"
    const val NAME = "user_name"
    const val PHOTO = "photoUrl"
    const val TOKEN = "token"


    fun getTextFromEdittext(editText: EditText): String {

        return editText.text.trim().toString()
    }

    fun isValidEmail(email: CharSequence): Boolean {
        var isValid = true
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            isValid = false
        }
        return isValid
    }

    // custom function to switch visibility of views
    fun toggleViews(showView: View, hideView: View) {
        showView.visibility = View.VISIBLE
        hideView.visibility = View.GONE
    }

    // custom function to switch between activities
    fun switchActivities(currentActivity: Activity, targetActivity: Class<*>) {
        val intent = Intent(currentActivity, targetActivity)
        currentActivity.startActivity(intent)
        currentActivity.finish()
    }
}

class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    // add more functions as needed
}