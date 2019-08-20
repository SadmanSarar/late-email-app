package com.sadmansarar.app.latemailsend.data.repository.implimentation

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Sadman Sarar on 9/10/17.
 * Repository class - uses NetworkBoundResource to load data from API
 */
class PrefRepository constructor(private val mContext: Context) {

    companion object {
        const val APP_SHARED_PREF = "APP_SHARED_PREF"
        const val SP_YOUR_NAME = "SP_YOUR_NAME"
        const val SP_MANAGERIAL_MAIL = "SP_MANAGERIAL_MAIL"
        const val SP_HR_MAIL = "SP_HR_MAIL"
        const val SP_MANAGERIAL_GREETINGS = "SP_MANAGERIAL_GREETINGS"
        const val SP_MAIL_BODY = "SP_MAIL_BODY"
        const val SP_IS_SCHEDULED = "SP_IS_SCHEDULED"
    }

    private val mSharedPref: SharedPreferences

    init {
        mSharedPref = mContext.getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE)
    }


    fun saveMailBody(mail: String) {
        saveString(SP_MAIL_BODY, mail)
    }

    fun getMailBody(): String {
        return getString(SP_MAIL_BODY)
    }

    fun saveIsScheduled(mail: Boolean) {
        saveBool(SP_IS_SCHEDULED, mail)
    }

    fun getIsScheduled(): Boolean {
        return getBool(SP_IS_SCHEDULED)
    }

    fun saveManagerialGreetings(mail: String) {
        saveString(SP_MANAGERIAL_GREETINGS, mail)
    }

    fun getManagerialGreetings(): String {
        return getString(SP_MANAGERIAL_GREETINGS)
    }


    fun saveHrEmail(mail: String) {
        saveString(SP_HR_MAIL, mail)
    }

    fun getHrEmail(): String {
        return getString(SP_HR_MAIL)
    }

    fun saveManagerialEmail(mail: String) {
        saveString(SP_MANAGERIAL_MAIL, mail)
    }

    fun getManagerialEmail(): String {
        return getString(SP_MANAGERIAL_MAIL)
    }

    fun saveYourName(mail: String) {
        saveString(SP_YOUR_NAME, mail)
    }

    fun getYourName(): String {
        return getString(SP_YOUR_NAME)
    }

    private fun saveString(key: String, value: String) {
        val editor = mSharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getString(key: String): String {
        return mSharedPref.getString(key, "") ?: ""
    }

    private fun saveBool(key: String, value: Boolean) {
        val editor = mSharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getBool(key: String): Boolean {
        return mSharedPref.getBoolean(key, false)
    }

}