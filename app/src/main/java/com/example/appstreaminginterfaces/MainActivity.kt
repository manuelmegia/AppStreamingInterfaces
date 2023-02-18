package com.example.appstreaminginterfaces

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appstreaminginterfaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView(this)
        generarPeliAleatoria()
        binding.imageButton2.setImageResource(ultimaPeliSeleccionada.featuredImage)

        binding.materialToolbar2.title = "NETFLIX"
        binding.materialToolbar2.isTitleCentered = true
        setSupportActionBar(binding.materialToolbar2)
        binding.materialToolbar2.setOnClickListener {
        }
        binding.imageButton2.setOnClickListener {
            val intent = Intent(this, Reproductor::class.java)
            intent.putExtra("trailer", ultimaPeliSeleccionada.trailer)
            startActivity(intent)
        }
        binding.imageButton2.setOnLongClickListener {
            popupWindowShow(it, ultimaPeliSeleccionada)
            true
        }
        binding.buttonMasInformacionPeliPrincipal.setOnClickListener {
            popupWindowShow(it, ultimaPeliSeleccionada)
        }
        binding.buttonVerahoraPeliPrincipal.setOnClickListener {
            val intent = Intent(this, Reproductor::class.java)
            intent.putExtra("trailer", ultimaPeliSeleccionada.trailer)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(context: Context) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CustomAdapter(Movie.data) { movie, position, isLongClick ->
                if (isLongClick) {
                    popupWindowShow(binding.recyclerView, movie)
                } else {
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

    private fun popupWindowShow(view: View?, movie: Movie) {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.activity_popup_window_peliculas, null)

        // step 2
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val focus= true
        val popupWindow = PopupWindow(popupView, wid, high, focus)

        popupView.findViewById<ImageButton>(R.id.imageButtonPopUp).setOnClickListener {
            val intent = Intent(this, Reproductor::class.java)
            intent.putExtra("trailer", movie.trailer)
            startActivity(intent)
        }
        popupView.findViewById<ImageButton>(R.id.imageButtonPopUp).setImageResource(movie.featuredImage)
        popupView.findViewById<TextView>(R.id.textViewTituloPeliculaPopUp).text = movie.title
        popupView.findViewById<TextView>(R.id.textViewDescripcionPeliculaPopUp).text = movie.description

        // step 3
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}
