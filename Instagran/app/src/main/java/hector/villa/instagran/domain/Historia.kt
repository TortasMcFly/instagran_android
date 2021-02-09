package hector.villa.instagran.domain

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Historia(
        val username: String,
        val profileImage: String,
        val creationDate: Date,
        val imagenes: ArrayList<ImagenHistoria>
): Serializable