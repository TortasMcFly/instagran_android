package hector.villa.instagran.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import hector.villa.instagran.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = Navigation.findNavController(this, R.id.host_fragment)
        val buttomNavigation: BottomNavigationView = findViewById<BottomNavigationView>(R.id.buttom_navigation)
        NavigationUI.setupWithNavController(buttomNavigation, navController)
    }
}