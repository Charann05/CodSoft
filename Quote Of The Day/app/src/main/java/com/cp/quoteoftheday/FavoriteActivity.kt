package com.cp.quoteoftheday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cp.quoteoftheday.data.QuoteRepository
import com.cp.quoteoftheday.databinding.ActivityFavoriteBinding
import com.cp.quoteoftheday.utils.PreferenceHelper

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favorites = PreferenceHelper.getFavorites(this)

        if (favorites.isEmpty()) {
            binding.tvFavorites.text = getString(R.string.no_favorites_yet)
        } else {
            binding.tvFavorites.text = favorites.joinToString("\n\n") {
                "• ${it.text}\n  — ${it.author}"
            }
        }

    }
}
