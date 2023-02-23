package com.example.appstreaminginterfaces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appstreaminginterfaces.databinding.MovieFillerBinding


class CustomAdapter(
    private val movieList: ArrayList<Movie>,
    private val listener: (Movie, Int, Boolean) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = MovieFillerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(movieList[position])
        holder.itemView.setOnClickListener { listener(movieList[position], position, false) }
        holder.itemView.setOnLongClickListener {
            listener(movieList[position], position, true)
            true
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(var movieBinding: MovieFillerBinding) :
        RecyclerView.ViewHolder(movieBinding.root) {
        fun bindMovie(movie: Movie) {
            movieBinding.featuredImage.setImageResource(movie.featuredImage!!)
            movieBinding.movieTitle.text = movie.title
        }
    }
}
