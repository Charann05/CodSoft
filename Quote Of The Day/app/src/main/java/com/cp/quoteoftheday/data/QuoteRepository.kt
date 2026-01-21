package com.cp.quoteoftheday.data

import android.content.Context
import com.cp.quoteoftheday.utils.PreferenceHelper
import java.time.LocalDate
import kotlin.random.Random

object QuoteRepository {

    private val quotes = listOf(
        Quote(1, "Success is not final, failure is not fatal.", "Winston Churchill"),
        Quote(2, "Believe you can and you're halfway there.", "Theodore Roosevelt"),
        Quote(3, "Do what you can, with what you have, where you are.", "Theodore Roosevelt"),
        Quote(4, "Dream big and dare to fail.", "Norman Vaughan"),
        Quote(5, "The only way to do great work is to love what you do.", "Steve Jobs"),
        Quote(6, "Don’t watch the clock; do what it does. Keep going.", "Sam Levenson"),
        Quote(7, "Everything you’ve ever wanted is on the other side of fear.", "George Addair"),
        Quote(8, "Hardships often prepare ordinary people for an extraordinary destiny.", "C.S. Lewis"),
        Quote(9, "Success usually comes to those who are too busy to be looking for it.", "Henry David Thoreau"),
        Quote(10, "Opportunities don't happen, you create them.", "Chris Grosser"),

        Quote(11, "It always seems impossible until it's done.", "Nelson Mandela"),
        Quote(12, "Start where you are. Use what you have. Do what you can.", "Arthur Ashe"),
        Quote(13, "If you want to lift yourself up, lift up someone else.", "Booker T. Washington"),
        Quote(14, "The future depends on what you do today.", "Mahatma Gandhi"),
        Quote(15, "Don’t limit your challenges. Challenge your limits.", "Jerry Dunn"),
        Quote(16, "Push yourself, because no one else is going to do it for you.", "Unknown"),
        Quote(17, "Great things never come from comfort zones.", "Unknown"),
        Quote(18, "Success doesn’t just find you. You have to go out and get it.", "Unknown"),
        Quote(19, "Dream it. Wish it. Do it.", "Unknown"),
        Quote(20, "Little things make big days.", "John Wooden"),

        Quote(21, "Don’t stop when you’re tired. Stop when you’re done.", "Unknown"),
        Quote(22, "The harder you work for something, the greater you’ll feel when you achieve it.", "Unknown"),
        Quote(23, "Wake up with determination. Go to bed with satisfaction.", "George Lorimer"),
        Quote(24, "Do something today that your future self will thank you for.", "Sean Patrick Flanery"),
        Quote(25, "Failure will never overtake me if my determination to succeed is strong enough.", "Og Mandino"),
        Quote(26, "We may encounter many defeats but we must not be defeated.", "Maya Angelou"),
        Quote(27, "Knowing is not enough; we must apply.", "Johann Wolfgang von Goethe"),
        Quote(28, "Quality is not an act, it is a habit.", "Aristotle"),
        Quote(29, "The secret of getting ahead is getting started.", "Mark Twain"),
        Quote(30, "Act as if what you do makes a difference. It does.", "William James"),

        Quote(31, "Don’t be pushed around by the fears in your mind.", "Roy T. Bennett"),
        Quote(32, "Your limitation—it’s only your imagination.", "Unknown"),
        Quote(33, "Sometimes later becomes never. Do it now.", "Unknown"),
        Quote(34, "Great minds discuss ideas; average minds discuss events.", "Eleanor Roosevelt"),
        Quote(35, "Happiness depends upon ourselves.", "Aristotle"),
        Quote(36, "Turn your wounds into wisdom.", "Oprah Winfrey"),
        Quote(37, "If opportunity doesn’t knock, build a door.", "Milton Berle"),
        Quote(38, "The best way to predict the future is to create it.", "Peter Drucker"),
        Quote(39, "Everything has beauty, but not everyone sees it.", "Confucius"),
        Quote(40, "What we think, we become.", "Buddha"),

        Quote(41, "The journey of a thousand miles begins with one step.", "Lao Tzu"),
        Quote(42, "Well done is better than well said.", "Benjamin Franklin"),
        Quote(43, "If you can dream it, you can do it.", "Walt Disney"),
        Quote(44, "Don’t wait. The time will never be just right.", "Napoleon Hill"),
        Quote(45, "Doubt kills more dreams than failure ever will.", "Suzy Kassem"),
        Quote(46, "Action is the foundational key to all success.", "Pablo Picasso"),
        Quote(47, "Work hard in silence, let success make the noise.", "Frank Ocean"),
        Quote(48, "Be yourself; everyone else is already taken.", "Oscar Wilde"),
        Quote(49, "Stay hungry, stay foolish.", "Steve Jobs"),
        Quote(50, "You miss 100% of the shots you don’t take.", "Wayne Gretzky")
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
