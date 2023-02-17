package com.example.appstreaminginterfaces

import kotlin.random.Random

lateinit var ultimaPeliSeleccionada: Movie

fun generarPeliAleatoria(): Int {
    var aux = Random.nextInt(Movie.data.size)
    return aux
}