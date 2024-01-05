package com.sravan.covidapplication.models

import com.google.gson.annotations.SerializedName

data class Towns(
    val id: Int,
    @SerializedName("district_id") val districtId: Int,
    val code: String,
    val name: String,
    val status: String
) {
}

