package com.example.lab2_lytovchenko

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class SecondFragment : Fragment(R.layout.fragment_second) {
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txt = view.findViewById<TextView>(R.id.txtResult)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        viewModel.resultText.observe(viewLifecycleOwner) { result ->
            txt.text = result
        }

        btnCancel.setOnClickListener {
            viewModel.resultText.value = ""
            viewModel.shouldClear.value = true
            parentFragmentManager.popBackStack()
        }
    }
}
