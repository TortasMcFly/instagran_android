package hector.villa.instagran.ui.inicio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R

class HistoriaAdapter(val historias: ArrayList<String>, val context: Context) : RecyclerView.Adapter<HistoriaAdapter.HistoriaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaViewHolder {
        return HistoriaViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_historia, parent)
        )
    }

    override fun onBindViewHolder(holder: HistoriaViewHolder, position: Int) {
        holder.tvUsername.text = historias[position]
    }

    override fun getItemCount(): Int {
        return historias.size
    }

    class HistoriaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
    }
}