package com.ems.oppidu.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.blankj.utilcode.util.ToastUtils

import com.oppidu.oppidu.R
import com.ems.oppidu.base.BaseActivity
import com.oppidu.oppidu.databinding.ActivityMainBinding

/**
 * MainActivity class is loading home screen
 * **/

class MainActivity : BaseActivity() {

    lateinit var navController: NavController
    lateinit var mBinding: ActivityMainBinding

    private var back_pressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.main_dash_fragment)

    }

    /**
     * Handle back press
     * */

    override fun onBackPressed() {

        val currentfraagment = findNavController(R.id.main_dash_fragment).currentDestination!!.id

        if (currentfraagment == R.id.HomeFragment) {
            if (back_pressed + 5000 > System.currentTimeMillis()) {

                finish()
            } else
                ToastUtils.showLong(getString(R.string.sure_to_exit))

            back_pressed = System.currentTimeMillis()
        } else {
            getSupportFragmentManager().popBackStack()
        }
    }


    fun setStatusBarColor(color: Int, pIsDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            this.window.statusBarColor = color
            val lFlags: Int = this.getWindow().getDecorView().getSystemUiVisibility()
            // Update the SystemUiVisibility dependening on whether we want a Light or Dark theme.
            // Update the SystemUiVisibility dependening on whether we want a Light or Dark theme.
            this.getWindow().getDecorView()
                .setSystemUiVisibility(if (pIsDark) lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
    }
}