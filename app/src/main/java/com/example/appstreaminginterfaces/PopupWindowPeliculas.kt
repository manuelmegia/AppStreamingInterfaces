package com.example.appstreaminginterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appstreaminginterfaces.databinding.ActivityPopupWindowPeliculasBinding

class PopupWindowPeliculas : AppCompatActivity() {
    private lateinit var binding: ActivityPopupWindowPeliculasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupWindowPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}