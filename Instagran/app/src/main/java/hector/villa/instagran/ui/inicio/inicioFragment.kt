package hector.villa.instagran.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R
import hector.villa.instagran.ui.domain.Historia
import hector.villa.instagran.ui.domain.ImagenHistoria
import java.util.*
import kotlin.collections.ArrayList

class InicioFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewRoot = inflater.inflate(R.layout.fragment_inicio, container, false)
        val recyclerHistorias = viewRoot.findViewById<RecyclerView>(R.id.recyclerHistorias)
        val historias = ArrayList<Historia>()
        historias.add(Historia("artemiop", "https://assets.tonica.la/__export/1565364026155/sites/debate/img/2019/08/09/mr_robot_1.jpg_463833556.jpg", Date(), obtenerImagenesEjemplo2()))

        historias.add(Historia("artemiop2", "https://assets.tonica.la/__export/1565364026155/sites/debate/img/2019/08/09/mr_robot_1.jpg_463833556.jpg", Date(), obtenerImagenesEjemplo()))

        historias.add(Historia("artemiop3", "https://assets.tonica.la/__export/1565364026155/sites/debate/img/2019/08/09/mr_robot_1.jpg_463833556.jpg", Date(), obtenerImagenesEjemplo()))

        historias.add(Historia("artemiop4", "https://assets.tonica.la/__export/1565364026155/sites/debate/img/2019/08/09/mr_robot_1.jpg_463833556.jpg", Date(), obtenerImagenesEjemplo2()))

        context?.let {
            val historiaAdapter = HistoriaAdapter(historias, it)
            recyclerHistorias.adapter = historiaAdapter
            recyclerHistorias.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
        }
        return viewRoot
    }

    private fun obtenerImagenesEjemplo(): ArrayList<ImagenHistoria> {
        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("icon.png", Date(), true))
        imagenes.add(ImagenHistoria("icon.png", Date(), true))
        imagenes.add(ImagenHistoria("icon.png", Date(), true))

        return imagenes
    }

    private fun obtenerImagenesEjemplo2(): ArrayList<ImagenHistoria> {
        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("icon.png", Date(), true))
        imagenes.add(ImagenHistoria("icon.png", Date(), false))
        imagenes.add(ImagenHistoria("icon.png", Date(), false))

        return imagenes
    }

}