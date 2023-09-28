package com.example.bmi

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.bmi.ui.theme.BMITheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalculate = findViewById<Button>(R.id.buttonCalculateBMI)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            val weightText = findViewById<EditText>(R.id.editTextWeight).text.toString()
            val heightText = findViewById<EditText>(R.id.editTextHeight).text.toString()
            val genderId = findViewById<RadioGroup>(R.id.radioGroupGender).checkedRadioButtonId

            val gender = when (genderId) {
                R.id.radioButtonMale -> Gender.MALE
                R.id.radioButtonFemale -> Gender.FEMALE
                else -> Gender.OTHER
            }

            val weight = weightText.toDoubleOrNull()
            val height = heightText.toDoubleOrNull()

            if (weight != null && height != null) {
                val user = User(25, gender, weight, height / 100)
                val bmi = calculateBMI(user)

                displayBMIResult(bmi)
            } else {
                textViewResult.text = "Input Invalid"
            }
        }
    }

    private fun calculateBMI(user: User): Double {
        return user.weightKg / (user.heightCm * user.heightCm)

    }

    private fun displayBMIResult(bmi: Double) {
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val resultColor = when {
            bmi < 18.5 -> R.color.Underweight
            bmi < 24.9 -> R.color.Normal
            else -> R.color.Overweight

        }

        val textColor = ContextCompat.getColor(this, resultColor) // Get the color based on the resource ID
        textViewResult.setTextColor(textColor)

        val resultMessage = when {
            bmi < 18.5 -> "Under Weight"
            bmi < 24.9 -> "Normal"
            else -> "Over Weight"

        }

        textViewResult.text = "BMI: ${"%.2f".format(bmi)}\nResult: $resultMessage "
    }


}




