package hector.villa.instagran.ui.inicio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hector.villa.instagran.R

class HistoriaAdapter(val historias: ArrayList<String>, val context: Context) : RecyclerView.Adapter<HistoriaAdapter.HistoriaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaViewHolder {
        return HistoriaViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_historia, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoriaViewHolder, position: Int) {
        holder.btnAgregarHistoria.visibility = if (position == 0) View.VISIBLE else View.GONE
        holder.tvUsername.text = historias[position]

        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)

        holder.relativeHistoria.tag = position
        holder.relativeHistoria.setOnClickListener {
            holder.relativeHistoria.startAnimation(animation)

            val pos = it.tag.toString().toInt()
            if (pos == 0) Toast.makeText(context, "Agregar historia", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Ver historia ${ historias[pos] }", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return historias.size
    }

    class HistoriaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val btnAgregarHistoria: ImageView = view.findViewById(R.id.btnAgregarHistoria)
        val relativeHistoria: RelativeLayout = view.findViewById(R.id.relativeHistoria)
    }
}