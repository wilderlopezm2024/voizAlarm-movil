package com.example.alarmas

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import android.widget.Toast
import android.Manifest

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    private val REQUEST_RECORD_AUDIO_PERMISSION = 100

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

        verificarPermisos()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun verificarPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {

            // Mostrar alerta antes de solicitar permiso
            AlertDialog.Builder(this)
                .setTitle("Permiso necesario")
                .setMessage("Se necesita acceso al micrófono para reconocimiento de voz.")
                .setPositiveButton("Aceptar") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this, arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION
                    )
                }
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado, ve a Configuración para activarlo", Toast.LENGTH_LONG).show()
            }
        }
    }

}

