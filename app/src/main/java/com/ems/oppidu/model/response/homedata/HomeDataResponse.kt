package com.ems.oppidu.model.response.homedata


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeDataResponse(
    @field:SerializedName("Response")
    val response: String? = null,

    @field:SerializedName("Message")
    val message: String? = null,



    @field:SerializedName("Notifications")
    val notifications: String? = null
): Serializable

