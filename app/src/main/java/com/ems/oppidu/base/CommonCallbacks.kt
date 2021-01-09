package com.ems.oppidu.base

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.oppidu.oppidu.databinding.LayoutToolbaarBinding
import com.oppidu.oppidu.databinding.LayoutToolbarBinding

interface CommonCallbacks : AsyncViewController {

  //  fun setupToolBar(toolbarBinding: LayoutToolbarBinding, showBack: Boolean, title: String?)

    fun setupActionBarWithNavController(toolbar: Toolbar)

    fun hideKeyboard(v: View)

    fun forceBack()

    fun isConnectedToNetwork(): Boolean

    fun requestLogout()

    fun getSharedModel(): BaseActivityViewModel

//    fun setToolBar(
//        toolbarBinding: LayoutToolbaarBinding,
//        show: Boolean,
//        transparent: Boolean,
//        noback: Boolean,
//        title: String?,
//        subTitle: String,
//        image: Boolean,
//        back_icon:Boolean
//    )
}