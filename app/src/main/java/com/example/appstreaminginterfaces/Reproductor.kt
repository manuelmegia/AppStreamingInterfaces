package com.example.appstreaminginterfaces

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.appstreaminginterfaces.databinding.ActivityMainBinding
import com.example.appstreaminginterfaces.databinding.ActivityReproductorBinding

class Reproductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityReproductorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filePlace = "android.resource://" + packageName + "/raw/" + intent.getIntExtra("trailer", 0)
        binding.videoView.setVideoURI(Uri.parse(filePlace))
        binding.videoView.setMediaController(MediaController(this))
        binding.videoView.start()
        binding.buttonVolverReproductor.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}