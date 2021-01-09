package  com.ems.oppidu.webservice

import com.ems.oppidu.model.response.MasterResponse


interface ApiResponseListener {

    fun onApiCallSuccess(apiUrl: String, body: MasterResponse<*>): Boolean

    fun onApiCallFailed(apiUrl: String, isSuccess: Boolean, errorMessage: String): Boolean
}