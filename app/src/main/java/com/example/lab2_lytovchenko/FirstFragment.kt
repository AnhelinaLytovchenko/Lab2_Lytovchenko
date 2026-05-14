package com.example.lab2_lytovchenko

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class FirstFragment : Fragment(R.layout.fragment_first) {
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        val num1 = view.findViewById<EditText>(R.id.editNum1)
        val num2 = view.findViewById<EditText>(R.id.editNum2)
        val group = view.findViewById<RadioGroup>(R.id.radioGroupOps)

        viewModel.shouldClear.observe(viewLifecycleOwner) { clear ->
            if (clear == true) {
                num1.text.clear()
                num2.text.clear()
                group.clearCheck()
                viewModel.shouldClear.value = false
            }
        }

        btnOk.setOnClickListener {
            val s1 = num1.text.toString()
            val s2 = num2.text.toString()
            val checked = group.checkedRadioButtonId

            if (s1.isEmpty() || s2.isEmpty() || checked == -1) {
                Toast.makeText(requireContext(), "Заповніть всі дані!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val val1 = s1.toDouble()
            val val2 = s2.toDouble()

            if (checked == R.id.radioDiv && val2 == 0.0) {
                Toast.makeText(requireContext(), "Ділення на нуль неможливе!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (checked) {
                R.id.radioPlus  -> val1 + val2
                R.id.radioMinus -> val1 - val2
                R.id.radioMult  -> val1 * val2
                R.id.radioDiv   -> val1 / val2
                else -> 0.0
            }

            val operationSign = when (checked) {
                R.id.radioPlus  -> "+"
                R.id.radioMinus -> "-"
                R.id.radioMult  -> "×"
                R.id.radioDiv   -> "÷"
                else -> "?"
            }

            viewModel.resultText.value = "$val1 $operationSign $val2 = $result"

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SecondFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
