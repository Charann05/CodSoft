package com.cp.quoteoftheday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cp.quoteoftheday.data.QuoteRepository
import com.cp.quoteoftheday.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favorites = QuoteRepository.getFavorites()

        binding.tvFavorites.text =
            if (favorites.isEmpty()) getString(R.string.no_favorites_yet)
            else favorites.joinToString("\n\n") { "\"${it.text}\" — ${it.author}" }
    }
}
