package hector.villa.instagran.domain

import java.util.*

data class ImagenHistoria(
        val urlImage: String,
        val createDate: Date,
        var vista: Boolean
)