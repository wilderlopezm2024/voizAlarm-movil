package com.example.alarmas.ui.alarm

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.alarmas.R
import com.example.alarmas.databinding.FragmentCrearAlarmaBinding

class CrearAlarmaFragment : DialogFragment() {

    private var _binding: FragmentCrearAlarmaBinding? = null
    private val binding get() = _binding!!
    private var speechRecognizer: SpeechRecognizer? = null
    private var handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrearAlarmaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 Iniciar animación de la onda de voz
        binding.animacionVozAlarma.setBackgroundResource(R.drawable.listening_animation)
        binding.animacionVozAlarma.post {
            val frameAnimation = binding.animacionVozAlarma.background as? AnimationDrawable
            frameAnimation?.start()
        }

        // 🔹 Configurar botón de cierre
        binding.btnCerrar.setOnClickListener { dismiss() }

        binding.btnCancelarAlarma.setOnClickListener { dismiss() }

        binding.btnConfirmarAlarma.setOnClickListener {
            binding.recuadroConfirmacionAlarma.visibility = View.GONE
            binding.recuadroTranscripcion.visibility = View.GONE
            binding.recuadroConfirmacionVoz.visibility = View.GONE
            binding.confirmacionAlarma.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                dismiss()  // 🔹 Cierra el DialogFragment después de 3 segundos
            }, 3000)
        }

        iniciarReconocimientoDeVozConDelay()

    }

    private fun iniciarReconocimientoDeVozConDelay() {
        handler.postDelayed({
            Log.d("Voz", "Iniciando reconocimiento de voz...")
            iniciarReconocimientoDeVoz()
        }, 1000) // 🔹 Espera 1 segundo antes de empezar a escuchar
    }

    private fun iniciarReconocimientoDeVoz() {
        Log.d("Voz", "Inicializando SpeechRecognizer...")
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())

        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("Voz", "Listo para escuchar...")
            }

            override fun onBeginningOfSpeech() {
                Log.d("Voz", "Comenzando a escuchar...")
                binding.tvTranscripcionTiempoReal.text = "Escuchando..."
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    Log.d("Voz", "Reconocimiento exitoso: ${matches[0]}")
                    binding.tvTranscripcionTiempoReal.text = "Lo que has dicho:\n" + matches[0]
                    binding.recuadroMensaje.visibility = View.GONE
                    binding.frameLayoutAnimacion.visibility = View.GONE
                    binding.tvEscuchandoAlarma.visibility = View.GONE
                    binding.recuadroConfirmacionVoz.visibility = View.VISIBLE
                    binding.recuadroConfirmacionAlarma.visibility = View.VISIBLE
                }
            }

            override fun onError(error: Int) {
                val mensaje = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "Error de audio"
                    SpeechRecognizer.ERROR_CLIENT -> "Error en el cliente"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Faltan permisos"
                    SpeechRecognizer.ERROR_NETWORK -> "Error de red"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Tiempo de espera agotado"
                    SpeechRecognizer.ERROR_NO_MATCH -> {
                        reiniciarEscucha()
                        "No se detectó voz, reintentando..."
                    }
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Reconocedor ocupado"
                    SpeechRecognizer.ERROR_SERVER -> "Error del servidor"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Tiempo de espera agotado"
                    else -> "Error desconocido: $error"
                }
                Log.e("Voz", mensaje)
                Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
            }

            override fun onEndOfSpeech() {
                Log.d("Voz", "Fin del reconocimiento")
                binding.tvTranscripcionTiempoReal.text = "Fin del reconocimiento"
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }

        if (isRunningOnEmulator()) {
            Log.d("Voz", "Ejecutando en emulador: simulando respuesta.")
            binding.tvTranscripcionTiempoReal.text = "Lo que has dicho:\nSimulación: Programa una alarma a las 6 AM"
            binding.recuadroMensaje.visibility = View.GONE
            binding.frameLayoutAnimacion.visibility = View.GONE
            binding.tvEscuchandoAlarma.visibility = View.GONE
            binding.recuadroConfirmacionVoz.visibility = View.VISIBLE
            binding.recuadroConfirmacionAlarma.visibility = View.VISIBLE
        } else {
            Log.d("Voz", "Ejecutando en dispositivo físico, iniciando escucha real.")
            speechRecognizer?.startListening(intent)
        }
    }

    private fun reiniciarEscucha() {
        handler.postDelayed({
            Log.d("Voz", "Reintentando reconocimiento de voz...")
            iniciarReconocimientoDeVoz()
        }, 2000) // 🔹 Reintenta después de 2 segundos
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val frameAnimation = binding.animacionVozAlarma.background as? AnimationDrawable
        frameAnimation?.stop()
        speechRecognizer?.destroy()
        speechRecognizer = null
        _binding = null
    }

    private fun isRunningOnEmulator(): Boolean {
        return (Build.FINGERPRINT.contains("generic") ||
                Build.FINGERPRINT.contains("unknown") ||
                Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for") ||
                Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
                "google_sdk" == Build.PRODUCT ||
                Build.HARDWARE.contains("goldfish") ||  // Más detecciones
                Build.HARDWARE.contains("ranchu") ||
                Build.MANUFACTURER.contains("Genymotion") ||
                Build.HOST.startsWith("android-x86") ||
                Build.USER == "android-build")
    }
}
