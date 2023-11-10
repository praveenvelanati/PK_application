package com.sravan.covidapplication.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class UserProfile(var uId: String,
                       @ServerTimestamp
                       var createdAt: Date?=null,
                       @ServerTimestamp
                       var updatedAt: Date?=null,
                       var photoUrl: String="",
                       var emailId: String="",
                       @get:PropertyName("user_name")
                       @set:PropertyName("user_name")
                       var userName: String="",
                       var token :String="") : java.io.Serializable {

    constructor(): this("", null,null,"","","","")

}
