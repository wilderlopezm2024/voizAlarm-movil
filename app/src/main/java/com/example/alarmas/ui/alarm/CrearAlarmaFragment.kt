package com.example.alarmas.ui.alarm

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.alarmas.databinding.FragmentCrearAlarmaBinding
import androidx.fragment.app.DialogFragment
import com.example.alarmas.R

class CrearAlarmaFragment: DialogFragment() {

    private var _binding: FragmentCrearAlarmaBinding? = null
    private val binding get() = _binding!!

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

        // Configurar la animación frame a frame en la ImageView
        binding.animacionVozAlarma.setBackgroundResource(R.drawable.listening_animation)

        // Iniciar la animación con un pequeño retraso para asegurarse de que la vista está lista
        binding.animacionVozAlarma.post {
            val frameAnimation = binding.animacionVozAlarma.background as? AnimationDrawable
            frameAnimation?.start()
        }

        // Configurar el botón de cerrar (opcional)
        /*binding.btnCancelar.setOnClickListener {
            dismiss()
        }*/

        // Configurar el botón de confirmar (opcional)
        /*binding.btnConfirmar.setOnClickListener {
            Toast.makeText(requireContext(), "Alarma creada correctamente", Toast.LENGTH_SHORT).show()
            dismiss()
        }*/
    }


    override fun onStart() {
        super.onStart()
        // Ajustar el tamaño del diálogo para que ocupe toda la pantalla
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val frameAnimation = binding.animacionVozAlarma.background as? AnimationDrawable
        frameAnimation?.stop()
        _binding = null
    }

}
