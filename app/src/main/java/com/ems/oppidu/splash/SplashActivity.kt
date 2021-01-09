package com.ems.oppidu.splash


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

import com.oppidu.oppidu.R
import com.ems.oppidu.activity.AccountHandlerActivity
import com.ems.oppidu.base.BaseActivity


class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )

        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            navigateTo(AccountHandlerActivity::class.java)

        }, 3000)

        //--------------------

//        Handler().postDelayed({
//
//            navigateTo(AccountHandlerActivity::class.java)
//
//            /*if (Prefs.get().isFirstTime.equals("1")) {
//                navigateTo(MainActivity::class.java)
//            } else {
//                navigateTo(AccountHandlerActivity::class.java)
//            }*/
//
//        }, 2000)


        //-------------------

//        mViewModel.proceedAhead.observe(this, Observer {
//            if (it == true) {
//                Handler().postDelayed({
//
//                    navigateTo(AccountHandlerActivity::class.java)
//
//                    /*if (Prefs.get().isFirstTime.equals("1")) {
//                        navigateTo(MainActivity::class.java)
//                    } else {
//                        navigateTo(AccountHandlerActivity::class.java)
//                    }*/
//
//                }, 2000)
//            }
//        })


    }

    fun <T> navigateTo(destination: Class<T>) {
        var intent = Intent(this, destination)
        startActivity(intent)
        super.finish()
    }

}

