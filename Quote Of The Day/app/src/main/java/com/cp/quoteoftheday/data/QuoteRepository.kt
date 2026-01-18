package com.cp.quoteoftheday.data

object QuoteRepository {

    private val quotes = listOf(
        Quote("Believe in yourself.", "Unknown"),
        Quote("Dream big and dare to fail.", "Norman Vaughan"),
        Quote("Success is not final, failure is not fatal.", "Winston Churchill"),
        Quote("Stay hungry, stay foolish.", "Steve Jobs"),
        Quote("Hard work beats talent when talent doesn’t work hard.", "Tim Notke")
    )

    fun getRandomQuote(): Quote {
        return quotes.random()
    }
}
