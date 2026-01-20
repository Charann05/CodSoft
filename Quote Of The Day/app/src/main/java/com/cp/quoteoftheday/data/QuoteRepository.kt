package com.cp.quoteoftheday.data

import android.content.Context
import com.cp.quoteoftheday.utils.PreferenceHelper
import java.time.LocalDate
import kotlin.random.Random

object QuoteRepository {

    private val quotes = listOf(
        Quote(1, "Believe in yourself.", "Unknown"),
        Quote(2, "Stay hungry, stay foolish.", "Steve Jobs"),
        Quote(3, "Dream big and dare to fail.", "Norman Vaughan"),
        Quote(4, "Success is not final, failure is not fatal.", "Winston Churchill")
    )

    private var favoriteQuotes: MutableList<Quote> = mutableListOf()

    fun initFavorites(context: Context) {
        favoriteQuotes = PreferenceHelper.getFavorites(context).toMutableList()
    }

    fun getRandomQuote(): Quote {
        return quotes[Random.nextInt(quotes.size)]
    }

    fun addToFavorites(context: Context, quote: Quote) {
        if (!favoriteQuotes.contains(quote)) {
            favoriteQuotes.add(quote)
            PreferenceHelper.saveFavorites(context, favoriteQuotes)
        }
    }

    fun getFavorites(): List<Quote> = favoriteQuotes

    fun getDailyQuote(context: Context): Quote {
        val today = LocalDate.now().toString()
        val savedDate = PreferenceHelper.getSavedDate(context)

        return if (today == savedDate) {
            PreferenceHelper.getDailyQuote(context)!!
        } else {
            val newQuote = getRandomQuote()
            PreferenceHelper.saveDailyQuote(context, newQuote, today)
            newQuote
        }
    }

}
