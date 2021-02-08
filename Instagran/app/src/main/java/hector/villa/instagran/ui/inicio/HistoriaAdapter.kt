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
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia

class HistoriaAdapter(val historias: ArrayList<Historia>, val context: Context) : RecyclerView.Adapter<HistoriaAdapter.HistoriaViewHolder>() {

    interface OnHistoryClickListener {
        fun onHistoryClickListener(position: Int)
    }

    var onHistorylickListener: OnHistoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaViewHolder {
        return HistoriaViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_historia, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoriaViewHolder, position: Int) {
        holder.btnAgregarHistoria.visibility = if(position == 0) View.VISIBLE else View.GONE
        holder.tvUsername.text = if(position == 0) "Tu historia" else historias[position].username

        Glide.with(context)
                .load( historias[position].profileImage)
                .into(holder.imageViewHistoria)

        holder.imageViewHistoria.borderColor =
                if(historiasYaVistas(position))
                {
                    if(position == 0) holder.btnAgregarHistoria.visibility = View.VISIBLE
                    context.resources.getColor(R.color.historia_vista_borde, null)
                }
                else
                {
                    if(position == 0) holder.btnAgregarHistoria.visibility = View.GONE
                    context.resources.getColor(R.color.historia_nueva, null)
                }

        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
        holder.relativeHistoria.tag = position
        holder.relativeHistoria.setOnClickListener {
            
            holder.relativeHistoria.startAnimation(animation)
            val pos = it.tag.toString().toInt()

            onHistorylickListener?.onHistoryClickListener(pos)

            if(pos == 0) Toast.makeText(context, "Agregar historia", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Ver historia de ${ historias[pos].username }", Toast.LENGTH_SHORT).show()
        }
    }

    private fun historiasYaVistas(position: Int): Boolean {
        var vistas = true
        historias[position].imagenes.forEach {
            if(!it.vista) {
                vistas = false
                return@forEach
            }
        }
        return vistas
    }

    override fun getItemCount(): Int {
        return historias.size
    }

    class HistoriaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val btnAgregarHistoria: ImageView = view.findViewById(R.id.btnAgregarHistoria)
        val relativeHistoria: RelativeLayout = view.findViewById(R.id.relativeHistoria)
        val imageViewHistoria: CircleImageView = view.findViewById(R.id.imageViewHistoria)
    }
}