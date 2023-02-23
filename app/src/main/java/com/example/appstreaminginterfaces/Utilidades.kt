package com.example.appstreaminginterfaces

import kotlin.random.Random

lateinit var ultimaPeliSeleccionada: Movie
var pelisUser = PeliculasUser()

fun generarPeliAleatoria() {
    var aux = Random.nextInt(Movie.data.size)
    ultimaPeliSeleccionada = Movie.data[aux]
}