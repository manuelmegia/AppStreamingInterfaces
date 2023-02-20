package com.example.appstreaminginterfaces

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appstreaminginterfaces.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        generarPeliAleatoria()
        binding.imageButton2.setImageResource(ultimaPeliSeleccionada.featuredImageTumbada)

        binding.materialToolbar2.title = "NETFLIX"
        binding.materialToolbar2.isTitleCentered = true
        setSupportActionBar(binding.materialToolbar2)
        binding.materialToolbar2.setOnClickListener {
            binding.scrollViewInicio.smoothScrollTo(0, 0)
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

    private fun setupRecyclerView() {
        internoRecyclerView(binding.recyclerView, Movie.data)
        internoRecyclerView(binding.recyclerViewSeries, seriesAccion)
    }

    private fun internoRecyclerView(refRecView: RecyclerView, data: ArrayList<Movie>) {
        refRecView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CustomAdapter(data) { movie, position, isLongClick ->
                if (isLongClick) {
                    popupWindowShow(refRecView, movie)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on movie: ${movie.title}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@MainActivity, Reproductor::class.java)
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
        val focus = true
        val popupWindow = PopupWindow(popupView, wid, high, focus)

        popupView.findViewById<TextView>(R.id.textViewDescripcionPeliculaPopUp).setOnClickListener {
            val intent = Intent(this, Reproductor::class.java)
            intent.putExtra("trailer", movie.trailer)
            startActivity(intent)
        }

        var youtubePlayerView = popupView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(youtubePlayerView, youTubePlayer)
                youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                youTubePlayer.loadVideo(movie.trailer, 0f)
            }
        }
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        youtubePlayerView.initialize(listener, options)
        popupView.findViewById<TextView>(R.id.textViewTituloPeliculaPopUp).text = movie.title
        popupView.findViewById<TextView>(R.id.textViewDescripcionPeliculaPopUp).text =
            movie.description

        // step 3
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}
