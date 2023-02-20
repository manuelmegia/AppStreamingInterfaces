package com.example.appstreaminginterfaces

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appstreaminginterfaces.databinding.ActivityReproductorBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController


class Reproductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityReproductorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(binding.youtubePlayerView)
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(binding.youtubePlayerView, youTubePlayer)
                binding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                intent.getStringExtra("trailer")?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        }
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        binding.youtubePlayerView.initialize(listener, options)

        binding.buttonVolverReproductor.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}