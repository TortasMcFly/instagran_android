package hector.villa.instagran.ui.inicio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia
import hector.villa.instagran.domain.ImagenHistoria
import hector.villa.instagran.ui.detalle_historia.StoriesActivity
import java.util.*
import kotlin.collections.ArrayList

class InicioFragment : Fragment()
    //, HistoriaAdapter.OnStoryClickListener
{

    val historias = ArrayList<Historia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewRoot = inflater.inflate(R.layout.fragment_inicio, container, false)
        val recyclerHistorias = viewRoot.findViewById<RecyclerView>(R.id.recyclerHistorias)

        historias.add(Historia("hector88", "https://miro.medium.com/max/1082/1*E3DrG0S77WuiXaPYmXMn4Q.jpeg", Date(), obtenerImagenesEjemplo2()))
        historias.add(Historia("tony789", "https://imagenes.20minutos.es/files/og_thumbnail/uploads/imagenes/2020/05/12/elon-musk-director-de-testa-y-spacex.jpeg", Date(), obtenerImagenesEjemplo()))
        historias.add(Historia("tortasMcFly", "https://pm1.narvii.com/6959/0abc2ee7487547f50e380ab77069beb11245eb0ar1-1008-500v2_00.jpg", Date(), obtenerImagenesEjemplo()))
        historias.add(Historia("roberto_bolaños", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/spiderman-sony-spiderverso-1567749360.jpeg", Date(), obtenerImagenesEjemplo2()))

        context?.let {
            val historiaAdapter = HistoriaAdapter(historias, it)
            //historiaAdapter.onStorylickListener = this
            historiaAdapter.onStoryAction = { position -> openDetail( position ) }
            recyclerHistorias.adapter = historiaAdapter
            recyclerHistorias.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
        }

        return viewRoot
    }

    private fun obtenerImagenesEjemplo(): ArrayList<ImagenHistoria> {

        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("https://miro.medium.com/max/1082/1*E3DrG0S77WuiXaPYmXMn4Q.jpeg", Date(), true))
        imagenes.add(ImagenHistoria("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/spiderman-sony-spiderverso-1567749360.jpeg", Date(), true))
        imagenes.add(ImagenHistoria("https://pm1.narvii.com/6959/0abc2ee7487547f50e380ab77069beb11245eb0ar1-1008-500v2_00.jpg", Date(), true))

        return imagenes
    }

    private fun obtenerImagenesEjemplo2(): ArrayList<ImagenHistoria> {

        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("https://miro.medium.com/max/1082/1*E3DrG0S77WuiXaPYmXMn4Q.jpeg", Date(), true))
        imagenes.add(ImagenHistoria("https://pm1.narvii.com/6959/0abc2ee7487547f50e380ab77069beb11245eb0ar1-1008-500v2_00.jpg", Date(), false))
        imagenes.add(ImagenHistoria("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/spiderman-sony-spiderverso-1567749360.jpeg", Date(), false))

        return imagenes
    }

    private fun openDetail(position: Int) {
        context?.let {
            val storiesIntent = Intent(it, StoriesActivity::class.java)
            storiesIntent.putExtra("start_position", position)
            storiesIntent.putExtra("historias_usuarios", historias)
            it.startActivity(storiesIntent)
        }
    }

    /*override fun onStoryClickListener(position: Int) {
        context?.let {
            openDetail( position )
        }
    }*/

}