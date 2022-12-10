package com.neijr.calculatefuelconsumption

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.neijr.calculatefuelconsumption.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private fun View.hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(applicationWindowToken, 0)
    }


    private fun isNumber(s: String?): Boolean {
        return try {
            s?.toFloat()
            true
        } catch (ex: NumberFormatException) {
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Fuel Calculator"
        }

        fun onBlurInputs() {
            binding.editDistance.clearFocus()
            binding.editPrice.clearFocus()
            binding.editAutonomy.clearFocus()
        }

        fun validateInputValue(textInputEditText: TextInputEditText): Boolean {
            val value = textInputEditText.text.toString()
            if (value.isEmpty()) {
                textInputEditText.error = "Please Enter"
                return false
            }
            return if (isNumber(value)) {
                if (value.toFloat() <= 0) {
                    textInputEditText.error = "This Value cannot be zero."
                    return false
                } else {
                    true
                }
            } else {
                textInputEditText.error = "Invalid Value"
                false
            }
        }

        @SuppressLint("SetTextI18n")
        fun calcConsumption() {
            val isValidDistance = validateInputValue(binding.editDistance)
            val isValidPrice = validateInputValue(binding.editPrice)
            val isValidAutonomy = validateInputValue(binding.editAutonomy)

            if (isValidDistance && isValidPrice && isValidAutonomy) {
                val distance = binding.editDistance.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()
                val result = (distance * price) / autonomy

                val dec = DecimalFormat("###.00")

                ("AED " + dec.format(result)
                    .replace(".", ".")).also {
                    binding.textResult.text = it
                }
            }

        }

        binding.container.setOnClickListener {
            it.hideKeyboard()
            onBlurInputs()
        }

        binding.btnCalcConsumption.setOnClickListener {
            calcConsumption()
        }
    }
}