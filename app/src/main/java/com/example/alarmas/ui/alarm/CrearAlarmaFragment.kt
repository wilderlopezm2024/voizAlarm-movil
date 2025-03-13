package com.example.alarmas.ui.alarm

import android.content.Context
import android.graphics.Color
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

        // Iniciar animación de la onda de voz
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink)
        binding.animacionVozAlarma.startAnimation(animation)

        // Configurar el botón de cerrar
        /*binding.btnCancelar.setOnClickListener {
            dismiss()
        }*/

        // Configurar el botón de confirmar
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
        _binding = null
    }
}
