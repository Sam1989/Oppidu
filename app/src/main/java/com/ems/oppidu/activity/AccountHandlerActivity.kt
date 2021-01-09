package com.ems.oppidu.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.blankj.utilcode.util.ToastUtils


import com.oppidu.oppidu.R
import com.ems.oppidu.base.BaseActivity
import com.ems.oppidu.fragment.login.LoginFragment


/**
 * Account Handler Activity class load screen and manage navigation view
 * */

class AccountHandlerActivity : BaseActivity() {

    lateinit var navController: NavController
    var doubleBackToExitPressedOnce = false
    var login = false
    var signup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAccountHandlerBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_account_handler)

        navController = findNavController(R.id.main_nav_fragment)

        login = intent.getBooleanExtra("login", false)
        signup = intent.getBooleanExtra("signup", false)

        if (login) {
            var bundle = Bundle()
            bundle.putBoolean("login", login)
            navController.navigate(R.id.LoginFragment, bundle)

        }


        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->

            when (destination.id) {

                R.id.LoginFragment -> {
                    setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark), true)
                }

            }
        })
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (getCurrentFragment() is LoginFragment) {
            getCurrentFragment().onActivityResult(requestCode, resultCode, data)
        }
    }

    //**** method use for get current fragment *****//
    private fun getCurrentFragment(): Fragment {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_fragment)
        return navHostFragment?.childFragmentManager!!.fragments[0]
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val currentfraagment = findNavController(R.id.main_nav_fragment).currentDestination!!.id
        if (currentfraagment != R.id.LoginFragment) {
            navController.navigate(R.id.LoginFragment)
        } else if (currentfraagment == R.id.LoginFragment) {
            if (!doubleBackToExitPressedOnce) {
                doubleBackToExitPressedOnce = true
                ToastUtils.showLong(getString(R.string.sure_to_exit))

            } else {
                finish()
            }
        }
    }
}
