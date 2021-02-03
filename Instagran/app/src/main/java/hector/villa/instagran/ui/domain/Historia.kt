package hector.villa.instagran.ui.domain

import java.util.*

data class Historia (
    val username: String,
    val profileImage: String,
    val creationDate: Date,
    val imagenes: ArrayList <ImagenHistoria>
    )