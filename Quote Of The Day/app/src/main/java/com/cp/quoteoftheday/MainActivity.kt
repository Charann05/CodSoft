package com.cp.quoteoftheday

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cp.quoteoftheday.data.Quote
import com.cp.quoteoftheday.data.QuoteRepository
import com.cp.quoteoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentQuote: Quote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDailyQuote()
        QuoteRepository.initFavorites(this)
        //showRandomQuote()

        binding.btnNewQuote.setOnClickListener {
            showRandomQuote()
        }

        binding.btnShare.setOnClickListener {
            shareQuote()
        }

        binding.btnViewFavorites.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.btnAddFavorite.setOnClickListener {
            currentQuote?.let { QuoteRepository.addToFavorites(this, it) }
        }

        binding.btnViewFavorites.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

    }

    private fun showDailyQuote() {
        val quote = QuoteRepository.getDailyQuote(this)
        currentQuote = quote
        binding.tvQuote.text = "\"${quote.text}\"\n- ${quote.author}"
    }


    private fun showRandomQuote() {
        val quote = QuoteRepository.getRandomQuote()
        currentQuote = quote
        binding.tvQuote.text = "\"${quote.text}\"\n- ${quote.author}"
    }

    private fun shareQuote() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, binding.tvQuote.text.toString())
        startActivity(Intent.createChooser(intent, "Share Quote"))
    }
}
