package com.ems.oppidu.model.response.loginresponse


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(

    @field:SerializedName("Message")
    val message: String? = null,

    @field:SerializedName("Notifications")
    val notifications: String? = null,



    @field:SerializedName("Response")
    val response: String? = null

) : Serializable