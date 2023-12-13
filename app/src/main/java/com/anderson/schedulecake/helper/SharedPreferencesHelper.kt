package com.anderson.schedulecake.helper

import android.content.Context

object SharedPreferencesHelper {
    private const val PREF_NAME = "token_login"
    private const val KEY_AUTH_TOKEN = "token"

    fun saveAuthToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_AUTH_TOKEN, token)
        editor.apply()
    }

    fun getAuthToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearAuthToken(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(KEY_AUTH_TOKEN)
        editor.apply()
    }
}