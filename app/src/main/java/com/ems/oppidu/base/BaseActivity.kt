package com.ems.oppidu.base

import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.oppidu.oppidu.R
import com.oppidu.oppidu.databinding.ActivityAccountHandlerBinding
import com.oppidu.oppidu.databinding.AppCustomDialogBinding
import com.oppidu.oppidu.databinding.LayoutToolbaarBinding
import com.oppidu.oppidu.databinding.LayoutToolbarBinding
import com.ems.oppidu.model.response.MasterResponse
import com.ems.oppidu.util.MyProgress
import com.ems.oppidu.util.Prefs
import com.ems.oppidu.utilities.AlertDialogUtils
import com.ems.oppidu.utilities.GeneralCallback


abstract class BaseActivity : AppCompatActivity(), CommonCallbacks, GeneralCallback,
    DialogInterface.OnClickListener {

    private var aD: AlertDialog? = null
    private val mBaseViewModel by viewModels<BaseActivityViewModel> { MyViewModelProvider(this as AsyncViewController) }

    lateinit var activityAccountHandlerBinding: ActivityAccountHandlerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
        setupBasics()
    }

    private fun setupBasics() {
        setObservers()
    }

    private fun showProgress() {
        MyProgress.show(this)
    }

    private fun hideProgress() {
        MyProgress.hide(this)
    }


    override fun showProgressDialog() {
        if (mBaseViewModel.progressDialogStatus.value == null || !mBaseViewModel.progressDialogStatus.value.equals(
                "_show"
            )
        ) {
            runOnUiThread(Runnable {
                mBaseViewModel.progressDialogStatus.value = "_show"
            })
        }
    }

    override fun hideProgressDialog() {
        if (mBaseViewModel.progressDialogStatus.value == null || !mBaseViewModel.progressDialogStatus.value.equals(
                "_hide"
            )
        ) {
            runOnUiThread(Runnable {
                mBaseViewModel.progressDialogStatus.value = "_hide"
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val bf = getCurrentFragment(BaseFragment::class.java)
        if (bf?.onFragBack() == false) {

            showAppCloseDialog()
        } else {
            super.onBackPressed()
        }
    }

    fun closeActivity() {
        finish()
    }

    private fun setObservers() {

        mBaseViewModel.keyboardController.observe(this, Observer {
            if (it) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            } else {
                val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
            }
        })

        mBaseViewModel.progressDialogStatus.observe(this, Observer {
            it?.let {
                if (it == "_show") {
                    showProgress()
                } else if (it == "_hide") {
                    hideProgress()

                }
            }
        })

        mBaseViewModel.alertDialogController.observe(this, Observer {
            it?.let {
                if (aD?.isShowing == true) aD?.dismiss()

                val builder = AlertDialog.Builder(this).setCancelable(false)

                val dialogBinding =
                    AppCustomDialogBinding.inflate(layoutInflater, null,
                        false).apply {
                        mBaseViewModel.alertDialogSpecs.message = it
                        specs = mBaseViewModel.alertDialogSpecs
                    }
                builder.setView(dialogBinding.root)
                aD = builder.create()
                aD!!.window?.setBackgroundDrawableResource(R.color.transparent)

                dialogBinding.btnYes.setOnClickListener {
                    mBaseViewModel.alertDialogSpecs.alertDialogBtnListener?.onClick(
                        aD,
                        DialogInterface.BUTTON_POSITIVE
                    )
                    aD?.dismiss()
                }
                dialogBinding.btnNo.setOnClickListener {
                    mBaseViewModel.alertDialogSpecs.alertDialogBtnListener?.onClick(
                        aD,
                        DialogInterface.BUTTON_NEGATIVE
                    )
                    aD?.dismiss()
                }

                aD!!.show()
                mBaseViewModel.alertDialogController.value = null
            }
        })

        mBaseViewModel.responseLogOut.observe(this, Observer {
//            if (it.isSuccess == true) {
//                onLogOutSuccess()
//            }
        })
    }


/*
    override fun setupToolBar(
        toolbarBinding: LayoutToolbarBinding,
        showBack: Boolean,
        title: String?
    ) {
        setSupportActionBar(toolbarBinding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (title?.isNotEmpty() == true) {
            toolbarBinding.layoutToolvarTitle.text = title
        } else {
            toolbarBinding.layoutToolvarTitle.text = ""
        }

        if (showBack) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbarBinding.toolbar.setNavigationIcon(R.drawable.ic_back)

        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }
*/


/*
    override fun setToolBar(
        toolbarBinding: LayoutToolbaarBinding,
        show: Boolean,
        transparent: Boolean,
        noback: Boolean,
        title: String?,
        subTitle: String
        , image: Boolean
        , back_icon: Boolean
    ) {
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (!show) {
            toolbarBinding.toolbar.visibility = View.GONE
        } else {
            toolbarBinding.toolbar.visibility = View.VISIBLE
        }

        if (transparent) {
            toolbarBinding.toolbar.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.transparent
                )
            )
            if (back_icon) {
                toolbarBinding.toolbarBack.setImageResource(R.drawable.ic_left_white_24dp)
                toolbarBinding.toolbarBack.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
            } else {
                toolbarBinding.toolbarBack.setImageResource(R.drawable.ic_close_24dp)
                toolbarBinding.toolbarBack.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimaryDark
                    )
                )
            }
        } else {
            toolbarBinding.toolbar.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimaryDark
                )
            )
            if (back_icon) {
                toolbarBinding.toolbarBack.setImageResource(R.drawable.ic_left_white_24dp)
                toolbarBinding.toolbarBack.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
            } else {
                toolbarBinding.toolbarBack.setImageResource(R.drawable.ic_close_24dp)
                toolbarBinding.toolbarBack.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
            }
        }

        if (back_icon) {

        }

        if (noback) {
            toolbarBinding.toolbar.setNavigationIcon(null)
            toolbarBinding.toolbarBack.visibility = View.GONE
        } else {
            toolbarBinding.toolbarBack.visibility = View.VISIBLE
        }

        if (image) {
            toolbarBinding.mainToolbarLogo.visibility = View.VISIBLE
        } else {
            toolbarBinding.mainToolbarLogo.visibility = View.GONE
        }

        if (title?.isNotEmpty() == true) {
            toolbarBinding.toolBarTitleTv.text = title
            toolbarBinding.toolBarsubtitle.text = subTitle
        } else {
            toolbarBinding.toolBarTitleTv.text = ""
        }
    }
*/

    override fun getSharedModel(): BaseActivityViewModel {
        return mBaseViewModel
    }

    override fun showAlertDialog(msg: String, btnListener: DialogInterface.OnClickListener?) {
        runOnUiThread {
            mBaseViewModel.alertDialogSpecs = AlertDialogSpecs()
            mBaseViewModel.alertDialogSpecs.alertDialogBtnListener = btnListener
            mBaseViewModel.alertDialogController.value = msg
        }
    }

    override fun showAlertDialog(
        title: String,
        msg: String,
        btnPosTxt: String,
        btnNegTxt: String,
        btnListener: DialogInterface.OnClickListener?
    ) {
        runOnUiThread {
            mBaseViewModel.alertDialogSpecs = AlertDialogSpecs()
            mBaseViewModel.alertDialogSpecs.title = title
            mBaseViewModel.alertDialogSpecs.btnPos = btnPosTxt
            mBaseViewModel.alertDialogSpecs.btnNeg = btnNegTxt
            mBaseViewModel.alertDialogSpecs.alertDialogBtnListener = btnListener
            mBaseViewModel.alertDialogController.value = msg
        }
    }

    override fun hideAlertDialog() {
        runOnUiThread {
            mBaseViewModel.alertDialogController.value = "null"
        }
    }

    override fun hideKeyboard() {
        runOnUiThread {
            mBaseViewModel.keyboardController.value = false
        }
    }

    override fun hideKeyboard(v: View) {
        runOnUiThread {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun showKeyboard() {
        runOnUiThread {
            mBaseViewModel.keyboardController.value = true
        }
    }

    override fun onNoInternet() {
        runOnUiThread {
            showAlertDialog(getString(R.string.no_network_connected), null)
        }
    }

    override fun onApiCallSuccess(apiUrl: String, body: MasterResponse<*>): Boolean {
        return false
    }

    override fun onApiCallFailed(
        apiUrl: String,
        isSuccess: Boolean,
        errorMessage: String
    ): Boolean {

        if (errorMessage != "Record not found.")
            showSingleAlertDialog(this, errorMessage)

        return true
    }


    override fun setupActionBarWithNavController(toolbar: Toolbar) {}

    override fun requestLogout() {
        AlertDialogUtils.showDialog(
            this, getString(R.string.logout), getString(R.string.sure_to_logout),
            getString(R.string.yes), getString(R.string.no), this
        )
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

                mBaseViewModel.logOut()

            }
            DialogInterface.BUTTON_NEGATIVE -> {

            }
        }
        dialog!!.dismiss()
    }


    fun onLogOutSuccess() {  //private
        Prefs.get().clear()
        /* startActivity(
             Intent(this, AccountHandlerActivity::class.java)
                 .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
         )
         finish()*/
    }

    override fun forceBack() {
        super.onBackPressed()
    }

    private fun showAppCloseDialog() {

        finish()

    }

    fun showSingleAlertDialog(context: Context, msg: String) {
        AlertDialogUtils.showDialog(context,
            context.getString(R.string.app_name),
            msg,
            context.getString(R.string.ok),
            DialogInterface.OnClickListener { _, _ -> })
    }

    override fun hasPermission(s: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        return (ContextCompat.checkSelfPermission(
            applicationContext,
            s
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission(perms: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this@BaseActivity, perms, requestCode)
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == Constant.REQC_PICK_IMAGE) {
//            if (Util.areAllPermissionsAccepted(grantResults)) {
//                fileFetcher.startAction(this) { onFilePicked(it) }
//            }
//        } else super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

    override fun isConnectedToNetwork(): Boolean {
        val cm =
            MainApplication.get().getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null
    }
}