package com.example.appstreaminginterfaces

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appstreaminginterfaces.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //this.window.setFlags(WindowInsets.Type.statusBars())
        setContentView(binding.root)
        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)
        setupRecyclerView(this)
        binding.materialToolbar2.title = "NETFLIX"
        binding.materialToolbar2.isTitleCentered = true
        setSupportActionBar(binding.materialToolbar2)
        binding.materialToolbar2.setOnClickListener {
        }
        binding.imageButton2.setImageResource(Movie.data[generarPeliAleatoria()].featuredImage)
    }

    private fun setupRecyclerView(context: Context) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CustomAdapter(Movie.data) { movie, position ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked on movie: ${movie.title}",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(context, Reproductor::class.java)
                intent.putExtra("trailer", movie.trailer)
                startActivity(intent)
            }
        }
    }
}
