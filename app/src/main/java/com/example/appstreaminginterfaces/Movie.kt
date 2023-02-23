package com.example.appstreaminginterfaces

data class Movie (
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var featuredImage: Int? = null,
    var featuredImageTumbada: Int? = null,
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
                    R.drawable.bbad_landscape__2_,
                    "HhesaQXLuRY"
                ),
                Movie(
                    2,
                    "Guía para escribir tu primer cuento",
                    "Incursiona en el mundo de la narración infantil",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    3,
                    "Optimizar trabajos grupales",
                    "Aplica estas estrategias para mejorar tus trabajos en grupo",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    4,
                    "Libros que nunca habías escuchado",
                    "Te presentamos la lista de los libros más raros",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    5,
                    "Cómo mejorar en la universidad",
                    "En este artículo una actitud adecuada para la U",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    6,
                    "40 buscadores de artículos científicos",
                    "Descubre los buscadores más importantes para cada área del conocimiento",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    7,
                    "Pautas para escribir un ensayo",
                    "Karla te explica un marco de trabajo para hace ensayos",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    8,
                    "Crear un ambiente de estudio para llegar a \"la zona\"",
                    "Aprende a modificar tu entorno para sacar el máximo beneficio de tu mente",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    9,
                    "Estudiar 80 horas semanales",
                    "Como Carlos logró estudiar 80 horas sin agotarse",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                ),
                Movie(
                    10,
                    "Lo que tu tutor de tesis no te dice",
                    "Consejos para terminar trabajos de grado rápido",
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    "HhesaQXLuRY"
                )
            )
    }
}