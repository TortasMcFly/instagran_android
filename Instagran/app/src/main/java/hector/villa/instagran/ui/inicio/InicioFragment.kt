package hector.villa.instagran.ui.inicio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia
import hector.villa.instagran.domain.ImagenHistoria
import hector.villa.instagran.ui.detalle_historia.DetalleHistoriaActivity
import java.util.*
import kotlin.collections.ArrayList

class InicioFragment : Fragment()
    //, HistoriaAdapter.OnHistoryClickListener
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
            //historiaAdapter.onHistorylickListener = this
            historiaAdapter.onHistoryAction = { position -> openDetail( position ) }
            recyclerHistorias.adapter = historiaAdapter
            recyclerHistorias.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
        }

        return viewRoot
    }

    private fun obtenerImagenesEjemplo(): ArrayList<ImagenHistoria> {

        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("https://www.infobae.com/new-resizer/XgIWdTkfiBsP4e1xvV1QVYEIY-c=/1200x900/filters:format(jpg):quality(85)/cloudfront-us-east-1.images.arcpublishing.com/infobae/SPXYC3CGMBD6VM3BOGWUZDISUE.jpg", Date(), true))
        imagenes.add(ImagenHistoria("https://www.prensalibre.com/wp-content/uploads/2019/06/Guitarra-1.jpg?quality=82&w=760&h=430&crop=1", Date(), true))
        imagenes.add(ImagenHistoria("ajshdka.png", Date(), true))

        return imagenes
    }

    private fun obtenerImagenesEjemplo2(): ArrayList<ImagenHistoria> {

        val imagenes = ArrayList<ImagenHistoria>()
        imagenes.add(ImagenHistoria("https://www.infobae.com/new-resizer/XgIWdTkfiBsP4e1xvV1QVYEIY-c=/1200x900/filters:format(jpg):quality(85)/cloudfront-us-east-1.images.arcpublishing.com/infobae/SPXYC3CGMBD6VM3BOGWUZDISUE.jpg", Date(), true))
        imagenes.add(ImagenHistoria("https://pm1.narvii.com/6959/0abc2ee7487547f50e380ab77069beb11245eb0ar1-1008-500v2_00.jpg", Date(), false))
        imagenes.add(ImagenHistoria("https://www.prensalibre.com/wp-content/uploads/2019/06/Guitarra-1.jpg?quality=82&w=760&h=430&crop=1", Date(), false))

        return imagenes
    }

    private fun openDetail(position: Int) {
        context?.let {
            if(position == 0) Toast.makeText(it, "Agregar historia", Toast.LENGTH_SHORT).show()
            else Toast.makeText(it, "Ver historia de ${ historias[position].username }", Toast.LENGTH_SHORT).show()

            val detalleHistoriaIntent = Intent(it, DetalleHistoriaActivity::class.java)
            detalleHistoriaIntent.putExtra("historia_detalle", historias[position])
            it.startActivity(detalleHistoriaIntent)
        }
    }

    /*override fun onHistoryClickListener(position: Int) {
        context?.let {
            openDetail( position )
        }
    }*/

}