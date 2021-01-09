package com.ems.oppidu.util


import android.widget.Toast
import com.ems.oppidu.base.MainApplication


class Util {
    companion object {
        fun toast(s: String) {
            Toast.makeText(MainApplication.get().getContext(), s, Toast.LENGTH_SHORT).show()
        }
    }
}