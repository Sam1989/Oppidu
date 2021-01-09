package com.ds.kerching.fragment.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.Utils

import com.ems.oppidu.base.AsyncViewController
import com.ems.oppidu.base.BaseViewModel
import com.ems.oppidu.model.response.loginresponse.LoginResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
* This class is use
* as LogingViewModel
* for api calling
* and other task perform and
* send result of that task to
* view class
* */
class LoginViewModel(controller: AsyncViewController) :

    BaseViewModel(controller),Callback<LoginResponse> {



    var email:ObservableField<String>?= null
    var firstname:ObservableField<String>?= null
    var lasttname:ObservableField<String>?= null
    var password:ObservableField<String>?= null
    var devicetoken:ObservableField<String>
    var firebasetoken:ObservableField<String>
    var source_type:ObservableField<String>?= null
    var postcode:ObservableField<String>
    var mobile:ObservableField<String>?= null
    var facebook_tag:ObservableField<String>
    var referralcode:ObservableField<String>
    var text:MutableLiveData<String>? = null


    init {
        firstname = ObservableField("")
        lasttname = ObservableField("")
        email = ObservableField("")
        password = ObservableField("")
        postcode = ObservableField("")
        mobile = ObservableField("")
        facebook_tag = ObservableField("")
        devicetoken = ObservableField("")
        source_type = ObservableField("")
        firebasetoken = ObservableField("")
        referralcode = ObservableField("")
        text = MutableLiveData<String>()


    }


    fun isLoginDataValid(): Boolean {

        return false
    }

    fun callLoginApi() {

    }


    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

    }

    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        TODO("Not yet implemented")
    }




}


