package com.cp.quoteoftheday.utils

import android.content.Context
import android.content.SharedPreferences
import com.cp.quoteoftheday.data.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferenceHelper {

    private const val PREF_NAME = "quote_prefs"
    private const val KEY_FAVORITES = "favorites"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveFavorites(context: Context, favorites: List<Quote>) {
        val json = Gson().toJson(favorites)
        getPrefs(context).edit().putString(KEY_FAVORITES, json).apply()
    }

    fun getFavorites(context: Context): List<Quote> {
        val json = getPrefs(context).getString(KEY_FAVORITES, null)
        return if (json.isNullOrEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<Quote>>() {}.type
            Gson().fromJson(json, type)
        }
    }
}
