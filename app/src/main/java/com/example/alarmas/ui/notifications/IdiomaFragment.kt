package com.example.alarmas.ui.notifications

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.alarmas.databinding.FragmentIdiomaBinding
import com.example.alarmas.ui.home.EncendidoFragment

class IdiomaFragment: DialogFragment() {
    private var _binding: FragmentIdiomaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdiomaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val opciones = listOf("Español", "Inglés", "Chino", "Portugués")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, opciones)

        val autoCompleteTextView = binding.autoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        // Manejar selección
        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val opcionSeleccionada = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Seleccionaste: $opcionSeleccionada", Toast.LENGTH_SHORT).show()
        }

        binding.btnGuardar.setOnClickListener {
            Toast.makeText(requireContext(), "Idioma actualizado", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.btnCancelar.setOnClickListener {
            dismiss()
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