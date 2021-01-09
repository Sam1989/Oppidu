package com.ems.oppidu.model.response

import com.google.gson.annotations.SerializedName


data class MasterResponse<Any>(


    @field:SerializedName("isSuccess")
    var isSuccess: Boolean = false,

    @field:SerializedName("message")
    var message: String = "",

    @field:SerializedName("data")
    var data: Any? = null

)