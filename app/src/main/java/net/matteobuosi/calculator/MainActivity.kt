package net.matteobuosi.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.matteobuosi.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val numberText = findViewById<TextView>(R.id.numberText)
    val zeroButton = findViewById<Button>(R.id.zeroButton)
    val oneButton = findViewById<Button>(R.id.oneButton)
    val twoButton = findViewById<Button>(R.id.twoButton)
    val threeButton = findViewById<Button>(R.id.threeButton)
    val fourButton = findViewById<Button>(R.id.fourButton)
    val fiveButton = findViewById<Button>(R.id.fiveButton)
    val sixButton = findViewById<Button>(R.id.sixButton)
    val sevenButton = findViewById<Button>(R.id.sevenButton)
    val eightButton = findViewById<Button>(R.id.eightButton)
    val nineButton = findViewById<Button>(R.id.nineButton)
    val cancelButton = findViewById<Button>(R.id.cancelButton)
    val percentageButton = findViewById<Button>(R.id.percentageButton)
    val divideButton = findViewById<Button>(R.id.divideButton)
    val multiplicationButton = findViewById<Button>(R.id.multiplicationButton)
    val minusButton = findViewById<Button>(R.id.minusButton)
    val addButton = findViewById<Button>(R.id.addButton)
    val resultButton = findViewById<Button>(R.id.resultButton)
    val pointButton = findViewById<Button>(R.id.pointButton)

    private var numberInString: String = ""
        get() = binding.numberText.text.toString()
        set(value) {
            binding.numberText.text = value
            field = value
        }

    private var currentNumber: Float = 0f

    private var oldNumber: Float = 0f

    private var operation: String = ""

    private var previousOperation: String = ""

    private var isNewOperation: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onItemClick(view: View) {
        if (isNewOperation) {
            oldNumber = currentNumber
            numberInString = ""
        }
        isNewOperation = false
        when (view.id) {
            R.id.zeroButton -> {
                setNumber(0)
            }

            R.id.oneButton -> {
                setNumber(1)
            }

            R.id.twoButton -> {
                setNumber(2)
            }

            R.id.threeButton -> {
                setNumber(3)
            }

            R.id.fourButton -> {
                setNumber(2)
            }

            R.id.fiveButton -> {
                setNumber(5)
            }

            R.id.sixButton -> {
                setNumber(6)
            }

            R.id.sevenButton -> {
                setNumber(7)
            }

            R.id.eightButton -> {
                setNumber(8)
            }

            R.id.nineButton -> {
                setNumber(9)
            }

            R.id.cancelButton -> {
                cancel()
            }

            R.id.invertButton -> {
                invert()
            }

            R.id.percentageButton -> {
                operation = "%"
                total()
                previousOperation = operation
            }

            R.id.divideButton -> {
                operation = "/"
                total()
                previousOperation = "/"
            }

            R.id.multiplicationButton -> {
                operation = "*"
                total()
                previousOperation = "*"
            }

            R.id.minusButton -> {
                operation = "-"
                total()
                previousOperation = "-"
            }

            R.id.addButton -> {
                operation = "+"
                total()
                previousOperation = "+"
            }

            R.id.resultButton -> {
                result()
            }

            R.id.pointButton -> {
                if (!numberInString.contains('.')) {
                    numberInString = "$numberInString."
                }
            }
        }
    }

    private fun cancel() {
        currentNumber = 0f
        oldNumber = 0f
        numberInString = "0"
    }

    private fun result() {
        makeOperations(operation)
        oldNumber = 0f
    }

    private fun invert() {
        var number: Float = numberInString.toFloat()
        number *= -1
        currentNumber = number
        numberInString = number.toString()
    }

    private fun setNumber(number: Int) {
        numberInString = if (numberInString == "0") {
            "$number"
        } else {
            "$numberInString$number"
        }

        currentNumber = numberInString.toFloat()
    }

    private fun total() {
        if (oldNumber != 0f && currentNumber != 0f) {
            makeOperations(previousOperation)
        }
        isNewOperation = true
    }

    private fun calculatePercentage(fullNumber: Float, percentage: Float) =
        (fullNumber * percentage) / 100

    private fun makeOperations(operation: String) {
        var finalResult = 0f
        when (operation) {
            "+" -> {
                finalResult = oldNumber + currentNumber
            }

            "-" -> {
                finalResult = oldNumber - currentNumber
            }

            "*" -> {
                finalResult = oldNumber * currentNumber
            }

            "/" -> {
                finalResult = oldNumber / currentNumber
            }

            "%" -> {
                finalResult = calculatePercentage(oldNumber, currentNumber)
            }
        }
        currentNumber = finalResult
        numberInString = if (currentNumber % 1 == 0f) {
            finalResult.toInt().toString()
        } else {
            finalResult.toString()
        }
    }
}
