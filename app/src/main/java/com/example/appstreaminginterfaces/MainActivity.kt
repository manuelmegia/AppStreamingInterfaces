package com.example.appstreaminginterfaces

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appstreaminginterfaces.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        setupRecyclerView()
        generarPeliAleatoria()
        binding.imageButton2.background =
            ContextCompat.getDrawable(this, ultimaPeliSeleccionada.featuredImage!!)

        binding.materialToolbar2.title = "NETFLIX"
        binding.materialToolbar2.isTitleCentered = true
        setSupportActionBar(binding.materialToolbar2)
        binding.materialToolbar2.setOnClickListener {
            binding.scrollViewInicio.smoothScrollTo(
                0,
                0
            )
        }
        binding.imageButton2.setOnClickListener {
            savePeliculas(ultimaPeliSeleccionada, pelisUser.peliculasVistas)
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
        database = FirebaseDatabase.getInstance().getReference("Usuarios")
        database.child(auth.currentUser?.uid.toString()).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val products = it.result.getValue(EstructuraDB::class.java)
                if (products != null) {
                    pelisUser.peliculasVistas = products.peliculasVistas!!
                    internoRecyclerView(
                        binding.recyclerViewPelisVistas,
                        products.peliculasVistas ?: ArrayList<Movie>()
                    )
                    pelisUser.peliculasFavorite = products.peliculasFavorite!!
                    internoRecyclerView(
                        binding.recyclerViewPelisFavoritas,
                        products.peliculasFavorite ?: ArrayList<Movie>()
                    )
                }
                Log.d(TAG, products.toString())
            } else {
                Log.d(TAG, it.exception?.message.toString())
            }
        }
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

                    savePeliculas(movie, pelisUser.peliculasVistas)
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

        if (pelisUser.peliculasFavorite.contains(movie))
            popupView.findViewById<ToggleButton>(R.id.buttonFavorito).isChecked = true

        popupView.findViewById<TextView>(R.id.textViewDescripcionPeliculaPopUp).setOnClickListener {
            val intent = Intent(this, Reproductor::class.java)
            intent.putExtra("trailer", movie.trailer)
            startActivity(intent)
        }

        popupView.findViewById<ToggleButton>(R.id.buttonFavorito)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    savePeliculas(movie, pelisUser.peliculasFavorite)
                } else {
                    if (pelisUser.peliculasFavorite.contains(movie))
                        pelisUser.peliculasFavorite.remove(movie)
                }
            }

        var youtubePlayerView = popupView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(youtubePlayerView, youTubePlayer)
                youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                youTubePlayer.loadVideo(movie.trailer!!, 0f)
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

    fun savePeliculas(movie: Movie, lista: ArrayList<Movie>) {
        if (!lista.contains(movie))
            lista.add(movie)
        database = FirebaseDatabase.getInstance().getReference("Usuarios")
        database.child(auth.currentUser?.uid.toString()).setValue(pelisUser).addOnSuccessListener {
            Toast.makeText(this@MainActivity, "Successfully Saved", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
