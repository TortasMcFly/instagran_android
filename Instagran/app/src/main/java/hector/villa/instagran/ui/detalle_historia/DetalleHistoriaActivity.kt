package hector.villa.instagran.ui.detalle_historia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia

class DetalleHistoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_historia)
        title = "Historia"

        val historia = intent.getSerializableExtra("historia_detalle") as Historia
    }
}