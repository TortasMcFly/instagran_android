package hector.villa.instagran.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R

class InicioFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewRoot = inflater.inflate(R.layout.fragment_inicio, container, false)
        val recyclerHistorias = viewRoot.findViewById<RecyclerView>(R.id.recyclerHistorias)
        val historias = ArrayList<String>()
        historias.add("hecth88")
        historias.add("tortasMcFly")
        historias.add("chrisevans99")
        historias.add("ironman12")
        historias.add("hectorluevano1239")

        context?.let {
            val historiaAdapter = HistoriaAdapter(historias, it)
            recyclerHistorias.adapter = historiaAdapter
            recyclerHistorias.layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
        }

        return viewRoot
    }

}