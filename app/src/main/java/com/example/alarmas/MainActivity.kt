package com.example.alarmas

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alarmas.R
import com.example.alarmas.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.alarmas.ui.alarm.CrearAlarmaFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar la Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar NavController
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Conectar Toolbar con la navegación
        setupActionBarWithNavController(navController)

        // Configurar BottomNavigationView con NavController
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavView.setupWithNavController(navController)

        // Configurar el FAB para abrir el fragmento CrearAlarmaFragment
        val fabAdd: FloatingActionButton = findViewById(R.id.fabCreateAlarm)
        fabAdd.setOnClickListener {
            val dialog = CrearAlarmaFragment()
            dialog.show(supportFragmentManager, "CrearAlarmaDialog")
        }

        fabAdd.post {
            Snackbar.make(fabAdd, "Presiona + para agregar una alarma", Snackbar.LENGTH_LONG).show()
        }

        val bounceAnim = ObjectAnimator.ofFloat(R.id.fabCreateAlarm, "translationY", 0f, -20f, 0f).apply {
            duration = 1000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
        }
        bounceAnim.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}

