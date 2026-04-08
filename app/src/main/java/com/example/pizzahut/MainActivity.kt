package com.example.pizzahut

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Tag for logging
    private val TAG = "PizzaHutOrdering"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components from activity_main.xml
        val rgSize = findViewById<RadioGroup>(R.id.rgSize)
        val cbCheese = findViewById<CheckBox>(R.id.cbCheese)
        val cbOlives = findViewById<CheckBox>(R.id.cbOlives)
        val btnCompute = findViewById<Button>(R.id.btnCompute)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        // Set Click Listener for the Compute Button
        btnCompute.setOnClickListener {
            Log.d(TAG, "Compute button clicked")
            
            var totalAmount = 0.0
            val selectedId = rgSize.checkedRadioButtonId
            
            // 1. Determine Pizza Size Price using if/else-if statements
            if (selectedId == -1) {
                // No size selected - Display error message
                val errorMsg = getString(R.string.error_select_size)
                tvTotal.text = errorMsg
                Log.d(TAG, "Error: No pizza size selected")
            } else {
                // Check which RadioButton is selected
                if (selectedId == R.id.rbSmall) {
                    totalAmount += 55.99
                    Log.d(TAG, "Size selected: Small (R55.99)")
                } else if (selectedId == R.id.rbMedium) {
                    totalAmount += 89.99
                    Log.d(TAG, "Size selected: Medium (R89.99)")
                } else if (selectedId == R.id.rbLarge) {
                    totalAmount += 119.99
                    Log.d(TAG, "Size selected: Large (R119.99)")
                }

                // 2. Add Toppings Costs using if statements
                if (cbCheese.isChecked) {
                    totalAmount += 5.50
                    Log.d(TAG, "Topping added: Extra Cheese (R5.50)")
                }
                
                if (cbOlives.isChecked) {
                    totalAmount += 3.50
                    Log.d(TAG, "Topping added: Olives (R3.50)")
                }

                // 3. Display the final total
                val formattedTotal = String.format("R%.2f", totalAmount)
                tvTotal.text = formattedTotal
                Log.d(TAG, "Calculation complete. Final Total: $formattedTotal")
            }
        }

        // Set Click Listener for the Clear Button
        btnClear.setOnClickListener {
            Log.d(TAG, "Clear button clicked")
            
            // Reset all UI selections
            rgSize.clearCheck() // Deselect all RadioButtons
            cbCheese.isChecked = false // Uncheck cheese
            cbOlives.isChecked = false // Uncheck olives
            tvTotal.text = "R0.00" // Clear total display
            
            Log.d(TAG, "All selections and total cleared")
        }
    }
}
