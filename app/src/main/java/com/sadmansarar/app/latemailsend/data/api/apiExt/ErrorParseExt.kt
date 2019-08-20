package com.singularitybd.app.something.data.api.apiExt

import retrofit2.Response

fun <T> parseErrorBody(response: Response<T>? = null): String {
    var userMsg = "Something went wrong"
    try {
//        val error = Gson().fromJson(response?.errorBody()?.string(), ::class.java)
//        userMsg = error.user_message
//        if (error.code == 422) {
//            userMsg = error.user_message
//        }

    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return userMsg
}

fun parseErrorBody(): String {
    return "Something went wrong"
}