package com.example.alarmas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar animación de entrada
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        // Crear un WebView para mostrar la animación
        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true // Habilitar JavaScript
        webView.webViewClient = WebViewClient() // Evita abrir navegador externo
        webView.loadUrl("file:///android_asset/wave-animation.html") // Cargar animación

        setContentView(webView) // Mostrar WebView

        // Esperar 4 segundos y pasar a MainActivity con animación
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Aplicar animación de salida al cambiar de actividad
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            finish()
        }, 4000)
    }
}
