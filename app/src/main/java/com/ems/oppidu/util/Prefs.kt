package com.ems.oppidu.util


import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.ems.oppidu.base.MainApplication

import com.google.gson.Gson

class Prefs {

    val PREF_LOGIN_DATA = "PREF_LOGIN_DATA"
    val PREF_MERCHENT_DATA = "PREF_MERCHENT_DATA"
    val PREF_CATEGORY_DATA = "PREF_CATEGORY_DATA"
    val PREF_EMAIL = "PREF_EMAIL"
    val PREF_TOKEN = "PREF_TOKEN"
    val PREF_MEMBER_ID = "PREF_MEMBER_ID"
    val PREF_PASSWORD = "PREF_PASSWORD"
    val PREF_FIRSTNAME = "PREF_FIRSTNAME"
    var PREF_KERCHING_TERM = "PREF_KERCHING_TERM"
    var apptoken = "apptoken"
    var first_time = "first_time"
    var IS_LOGIN = "IS_LOGIN"
    var LOGIN_FROM = "LOGIN_FROM"
    var REMEMBER_ME = "REMEMBER_ME"
    var SEARCH = ""
    var INFORMATION_VALUE = "INFORMATIONVALUE"
    var TOTAL_OFFER = "TOTAL_OFFER"
    var MERCHANT_AUTOID = "MERCHANT_AUTOID"
    var BARCODE = "BARCODE"
    var PREF_BAR_CODE = "PREF_BAR_CODE"


    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.get().getContext())

    init {
        instance = this
    }

    val gson = Gson()

    companion object {
        private var instance: Prefs? = null
        fun get(): Prefs {
            if (instance == null) {
                instance = Prefs()
            }
            return instance!!
        }
    }

    /*termPurchase*/

    var kerchingTerm: String?
        get() {
            val str = sharedPreferences.getString(PREF_KERCHING_TERM, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_KERCHING_TERM, value) }
        }


    /*Save token*/

    var token: String?
        get() {
            val str = sharedPreferences.getString(apptoken, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(apptoken, value) }
        }

    /*isFirstTime*/

    var isFirstTime: String?
        get() {
            val str = sharedPreferences.getString(first_time, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(first_time, value) }
        }

    /*Login*/
    var LoginOrNot: String?
        get() {
            val str = sharedPreferences.getString(IS_LOGIN, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(IS_LOGIN, value) }
        }

    /*loginfrom*/
    var SocialLoginFrom: String?
        get() {
            val str = sharedPreferences.getString(LOGIN_FROM, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(LOGIN_FROM, value) }
        }


    /*loginfrom*/
    var member_id: String?
        get() {
            val str = sharedPreferences.getString(PREF_MEMBER_ID, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_MEMBER_ID, value) }
        }

    var useremail: String?
        get() {
            val str = sharedPreferences.getString(PREF_EMAIL, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_EMAIL, value) }
        }

    var userpassword: String?
        get() {
            val str = sharedPreferences.getString(PREF_PASSWORD, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_PASSWORD, value) }
        }

    var username: String?
        get() {
            val str = sharedPreferences.getString(PREF_FIRSTNAME, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_FIRSTNAME, value) }
        }


    /*barcodenumber*/

    var barcode: String?
        get() {
            val str = sharedPreferences.getString(PREF_BAR_CODE, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_BAR_CODE, value) }
        }

//    var loginData: MemberDetailsResponse?
//        get() {
//            val str = sharedPreferences.getString(PREF_LOGIN_DATA, "") ?: ""
//            if (!str.isBlank()) return gson.fromJson(str, MemberDetailsResponse::class.java)
//            return null
//        }
//        set(value) {
//            sharedPreferences.edit { putString(PREF_LOGIN_DATA, gson.toJson(value)) }
//        }

    /*remember*/
    var rememberMe: String?
        get() {
            val str = sharedPreferences.getString(REMEMBER_ME, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(REMEMBER_ME, value) }
        }

    var searchitem: String?
        get() {
            val str = sharedPreferences.getString(SEARCH, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(SEARCH, value) }
        }



    var barcodeValue: String?
        get() {
            val str = sharedPreferences.getString(BARCODE, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(BARCODE, value) }
        }


    var typevalue: String?
        get() {
            val str = sharedPreferences.getString(INFORMATION_VALUE, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(INFORMATION_VALUE, value) }
        }

    var totaloffer: String?
        get() {
            val str = sharedPreferences.getString(TOTAL_OFFER, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(TOTAL_OFFER, value) }
        }

    var merchantautoid: String?
        get() {
            val str = sharedPreferences.getString(MERCHANT_AUTOID, "") ?: ""
            if (!str.isBlank()) return str
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(MERCHANT_AUTOID, value) }
        }

   /* var merchantItem: MerchantListItem?
        get() {
            val str = sharedPreferences.getString(PREF_MERCHENT_DATA, "") ?: ""
            if (!str.isBlank()) return gson.fromJson(str, MerchantListItem::class.java)
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_MERCHENT_DATA, gson.toJson(value)) }
        }

    var categoryItem: CategoryItem?
        get() {
            val str = sharedPreferences.getString(PREF_CATEGORY_DATA, "") ?: ""
            if (!str.isBlank()) return gson.fromJson(str, CategoryItem::class.java)
            return null
        }
        set(value) {
            sharedPreferences.edit { putString(PREF_CATEGORY_DATA, gson.toJson(value)) }
        }
*/
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }


}