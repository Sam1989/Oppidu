package com.planetsaverapp.fragment.home


import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

import com.ems.oppidu.base.AsyncViewController
import com.ems.oppidu.base.BaseViewModel
import com.ems.oppidu.model.response.homedata.HomeDataResponse
import com.ems.oppidu.model.response.loginresponse.LoginResponse
import com.ems.oppidu.webservice.ApiRegister

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(controller: AsyncViewController) : BaseViewModel(controller),
    Callback<LoginResponse> {

    val responseHomeData = MutableLiveData<HomeDataResponse>()
    val responseLogin = MutableLiveData<LoginResponse>()

    var email: ObservableField<String>?= null
    var password: ObservableField<String>?= null
    var devicetoken: ObservableField<String>
    var firebasetoken:ObservableField<String>

    init {
        email = ObservableField("")
        password = ObservableField("")
        devicetoken = ObservableField("")
        firebasetoken = ObservableField("")
    }

    fun callHomeData(token: String) {

        baseRepo.restClient.callKerchingApi(
            ApiRegister.GET_HOME_DATA, token,
            responseHomeData
        )
    }


    /*
   * Method is use for
   * call login api
   * */
    fun callLoginApi() {

    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

    }

    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        responseLogin?.value = response.body()
    }
}