package hector.villa.instagran.ui.detalle_historia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hector.villa.instagran.R

class DetalleHistoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_historia)
        title = "Historia"
    }
}