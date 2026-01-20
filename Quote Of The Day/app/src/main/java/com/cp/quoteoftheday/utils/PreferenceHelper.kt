package com.cp.quoteoftheday.utils

import android.content.Context
import android.content.SharedPreferences
import com.cp.quoteoftheday.data.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferenceHelper {

    private const val PREF_NAME = "quote_prefs"
    private const val KEY_FAVORITES = "favorites"
    private const val KEY_DAILY_QUOTE = "daily_quote"
    private const val KEY_DAILY_AUTHOR = "daily_author"
    private const val KEY_DAILY_DATE = "daily_date"


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

    fun saveDailyQuote(context: Context, quote: Quote, date: String) {
        getPrefs(context).edit()
            .putString(KEY_DAILY_QUOTE, quote.text)
            .putString(KEY_DAILY_AUTHOR, quote.author)
            .putString(KEY_DAILY_DATE, date)
            .apply()
    }

    fun getDailyQuote(context: Context): Quote? {
        val prefs = getPrefs(context)
        val text = prefs.getString(KEY_DAILY_QUOTE, null)
        val author = prefs.getString(KEY_DAILY_AUTHOR, null)

        return if (text != null && author != null) {
            Quote(0, text, author)
        } else null
    }

    fun getSavedDate(context: Context): String? {
        return getPrefs(context).getString(KEY_DAILY_DATE, null)
    }

}
