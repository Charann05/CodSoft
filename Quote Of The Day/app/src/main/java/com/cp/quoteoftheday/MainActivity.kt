package com.cp.quoteoftheday

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cp.quoteoftheday.data.QuoteRepository
import com.cp.quoteoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRandomQuote()

        binding.btnNewQuote.setOnClickListener {
            showRandomQuote()
        }

        binding.btnShare.setOnClickListener {
            shareQuote()
        }

        binding.btnViewFavorites.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }

    private fun showRandomQuote() {
        val quote = QuoteRepository.getRandomQuote()
        binding.tvQuote.text = "\"${quote.text}\"\n- ${quote.author}"
    }

    private fun shareQuote() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, binding.tvQuote.text.toString())
        startActivity(Intent.createChooser(intent, "Share Quote"))
    }
}
