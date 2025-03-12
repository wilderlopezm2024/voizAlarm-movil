package com.example.alarmas.ui.home

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
import com.example.alarmas.databinding.FragmentEncendidoBinding
import androidx.fragment.app.DialogFragment
import com.example.alarmas.R

class EncendidoFragment: DialogFragment() {

    private var _binding: FragmentEncendidoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEncendidoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicar animación al círculo
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink)
        binding.circleView.startAnimation(animation)

        // Botón para cerrar el diálogo
        binding.btnVolver.setOnClickListener {
            dismiss()
        }

        // Botón para cambiar a la vista de posponer
        binding.btnPosponer.setOnClickListener {
            binding.viewFlipper.showNext()

            // Enfocar el input y abrir el teclado automáticamente
            binding.edtMinutos.requestFocus()
            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.edtMinutos, InputMethodManager.SHOW_IMPLICIT)
        }

        // Botón para aceptar los minutos y cerrar el diálogo
        binding.btnAceptar.setOnClickListener {
            val minutosIngresados = binding.edtMinutos.text.toString()
            if (minutosIngresados.isNotEmpty()) {
                Toast.makeText(requireContext(), "Alarma postergada", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Ingrese los minutos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}