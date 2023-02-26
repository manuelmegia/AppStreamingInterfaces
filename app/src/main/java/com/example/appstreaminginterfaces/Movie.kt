package com.example.appstreaminginterfaces

data class Movie(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var featuredImage: Int? = null,
    var trailer: String? = null
) {
    companion object {
        val data
            get() = arrayListOf(
                Movie(
                    1,
                    "Breaking Bad",
                    "Walter White, profesor de química en un instituto, descubre que tiene cáncer de pulmón y decide trabajar junto con un ex-alumno elaborando metanfetamina de alta calidad para poder ganar dinero para que su familia se mantenga.",
                    R.drawable.bbad_vertical__1_,
                    "HhesaQXLuRY"
                ), Movie(
                    2,
                    "Guía para escribir tu primer cuento",
                    "Incursiona en el mundo de la narración infantil",
                    R.drawable.bbad_vertical__1_,
                    "HhesaQXLuRY"
                ), Movie(
                    3,
                    "Optimizar trabajos grupales",
                    "Aplica estas estrategias para mejorar tus trabajos en grupo",
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ), Movie(
                    4,
                    "Libros que nunca habías escuchado",
                    "Te presentamos la lista de los libros más raros",
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ), Movie(
                    5,
                    "Cómo mejorar en la universidad",
                    "En este artículo una actitud adecuada para la U",
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ), Movie(
                    6,
                    "40 buscadores de artículos científicos",
                    "Descubre los buscadores más importantes para cada área del conocimiento",
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ), Movie(
                    7,
                    "Pautas para escribir un ensayo",
                    "Karla te explica un marco de trabajo para hace ensayos",
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                )
            )
    }
}