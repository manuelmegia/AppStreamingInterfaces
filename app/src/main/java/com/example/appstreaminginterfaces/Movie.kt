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
                    "BREAKING BAD",
                    "Walter White, profesor de química en un instituto, descubre que tiene cáncer de pulmón y decide trabajar junto con un ex-alumno elaborando metanfetamina de alta calidad para poder ganar dinero para que su familia se mantenga.",
                    R.drawable.breaking_bad,
                    "HhesaQXLuRY"
                ), Movie(
                    2,
                    "EL PADRINO DE HARLEM",
                    "Inspirada en la increíble vida de Bumpy Johnson, uno de los mafiosos más relevantes de los Estados Unidos, Godfather of Harlem combina eventos históricos y políticos que, retratan por un lado el movimiento liderado por Martin Luther King en la década de 1960 en Estados Unidos por la lucha de los derechos civiles y, por el otro, la criminalidad de la época, efecto del negocio de las drogas y el tráfico de armas en manos de diversas y peligrosas mafias enfrentadas. En la nueva temporada, Bumpy continuará su lucha contra las familias del crimen de Nueva York, por el control de las calles de Harlem y del lucrativo oleoducto de droga que va desde Marsella hasta el puerto de Nueva York.",
                    R.drawable.el_padrino_de_harlem,
                    "mNjtn4DieXI"
                ), Movie(
                    3,
                    "GAMBITO DE DAMA",
                    "Gambito de Dama gira en torno a Beth Harmon, una tímida huérfana de 8 años de edad muy callada y completamente normal. Un día, comienza a jugar al ajedrez y la cambia la vida, ampliando sus sentidos, su pensamiento y dándola la sensación de tener control sobre su vida. Con la práctica, 8 años después, termina participando en un campeonato en Estados Unidos. Las apuestas crecen y su temor al aislamiento también mientras afina sus destrezas de jugadora profesional y, cada vez, la idea de escaparse le parece más atractiva.",
                    R.drawable.gambito_de_dama,
                    "-BBgzgNgzeQ"
                ), Movie(
                    4,
                    "HALO",
                    "9 episodios. Serie basada en la famosa saga de videojuegos. En el siglo 26, la humanidad tiene que lidiar con una amenaza alienígena conocida como the Covenant (El Pacto). Halo teje historias personales entrelazadas de acción, aventura dentro de una enriquecedora visión del futuro. La serie es una historia independiente inspirada en la franquicia del videojuego, en lugar de una continuación, adaptación o precuela.",
                    R.drawable.halo,
                    "5mcO5WiBmxM"
                ), Movie(
                    5,
                    "LA CASA DEL DRAGON",
                    "Historia ambientada 172 años \"antes de Daenerys Targaryen\", y en el noveno año del reinado de Viserys Targaryen (Paddy Considine), un rey cuya línea de sucesión está en peligro. Su esposa Aemma (Sian Brooke) está embarazada, aunque no hay garantía de que dé a luz a un heredero varón. Si no lo hace, entonces el Trono de Hierro recaerá, bien sobre el hermano de Viserys, Daemon, un gobernante impulsivo y potencialmente tiránico (Matt Smith); o bien, rompiendo con la tradición de preferencia del varón, en la hija adolescente de Viserys, Rhaenyra (Milly Alcock), cuyo reclamo del trono está destinado a tener una fuerte oposición.",
                    R.drawable.la_casa_del_dragon,
                    "oBFtJUWuGFI"
                ), Movie(
                    6,
                    "PICARD",
                    "Ambientada 18 años después de la última aparición de Jean-Luc Picard en \"Star Trek: Nemesis\", y encuentra al personaje profundamente afectado por la destrucción de Romulus como se muestra en la película \"Star Trek\" de 2009.",
                    R.drawable.picard,
                    "Nv6bEL1CYLQ"
                ), Movie(
                    7,
                    "CUNK ON EARTH",
                    "En este mockumentary, Philomena Cunk recorre toda la historia de la Civilización Humana desde la Prehistoria hasta la actualidad, \"para que nunca tengas que ver ningún otro documental\". Por el camino, Philomena pregunta a expertos preguntas importantes sobre el progreso del Hombre, visitando (o acercándose) a impresionantes ruinas y museos, antes de cortar el plano a metraje de archivo de sitios que ya no existen o en los que era demasiado caro rodar.",
                    R.drawable.cunk_on_earth,
                    "Hm6AOHq9OL4"
                )
            )
    }
}