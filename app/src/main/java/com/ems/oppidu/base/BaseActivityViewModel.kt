package com.ems.oppidu.base

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.ems.oppidu.model.response.MasterResponse


class BaseActivityViewModel(viewController: AsyncViewController) : BaseViewModel(viewController) {

    val progressDialogStatus: MutableLiveData<String> = MutableLiveData()
    val alertDialogController: MutableLiveData<String> = MutableLiveData()
    val keyboardController: MutableLiveData<Boolean> = MutableLiveData()
    var alertDialogSpecs: AlertDialogSpecs = AlertDialogSpecs()
    val responseLogOut: MutableLiveData<MasterResponse<String>> = MutableLiveData()
    var parcels: HashMap<Int, Bundle> = HashMap()

    fun logOut() {
//        baseRepo.restClient.callApi(ApiRegister.LOGOUT, null, responseLogOut)
    }
}