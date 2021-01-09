package com.ems.oppidu.base

import android.content.DialogInterface
import com.ems.oppidu.model.response.MasterResponse


interface AsyncViewController {

    fun showProgressDialog()

    fun hideProgressDialog()

    fun showAlertDialog(msg: String, btnListener: DialogInterface.OnClickListener?)

    fun showAlertDialog(
        title: String,
        msg: String,
        btnPosTxt: String,
        btnNegTxt: String,
        btnListener: DialogInterface.OnClickListener?
    )

    fun hideAlertDialog()

    fun hideKeyboard()

    fun showKeyboard()

    fun onNoInternet()

    fun onApiCallFailed(apiUrl: String, isSuccess: Boolean, errorMessage: String): Boolean

    fun onApiCallSuccess(apiUrl: String, body: MasterResponse<*>): Boolean

    fun hasPermission(s: String): Boolean
}