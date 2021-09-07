package com.jawaid.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import net.objecthunter.exp4j.ExpressionBuilder
import soup.neumorphism.NeumorphCardView
import soup.neumorphism.NeumorphTextView

class CalculatorActivity : AppCompatActivity() {

    lateinit var expression: NeumorphTextView
    lateinit var result: NeumorphTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val one=findViewById<NeumorphCardView>(R.id.one)
        val two=findViewById<NeumorphCardView>(R.id.two)
        val three=findViewById<NeumorphCardView>(R.id.three)
        val four=findViewById<NeumorphCardView>(R.id.four)
        val five=findViewById<NeumorphCardView>(R.id.five)
        val six=findViewById<NeumorphCardView>(R.id.six)
        val seven=findViewById<NeumorphCardView>(R.id.seven)
        val eight=findViewById<NeumorphCardView>(R.id.eight)
        val nine=findViewById<NeumorphCardView>(R.id.nine)
        val zero=findViewById<NeumorphCardView>(R.id.zero)
        val dot=findViewById<NeumorphCardView>(R.id.dot)



        //operators
        val plus=findViewById<NeumorphCardView>(R.id.plus)
        val minus=findViewById<NeumorphCardView>(R.id.minus)
        val multiply=findViewById<NeumorphCardView>(R.id.multiply)
        val divide=findViewById<NeumorphCardView>(R.id.divide)
        val openBracket=findViewById<NeumorphCardView>(R.id.open_bracket)
        val closeBracket=findViewById<NeumorphCardView>(R.id.close_Bracket)
        val clearButton=findViewById<NeumorphCardView>(R.id.clear_text)
        val backButton=findViewById<NeumorphCardView>(R.id.back_btn)
        val equals=findViewById<NeumorphCardView>(R.id.equals)

        expression=findViewById<NeumorphTextView>(R.id.expression)
        result=findViewById<NeumorphTextView>(R.id.result)


        //Numbers
        one.setOnClickListener { appendOnExpresstion("1", true) }
        two.setOnClickListener { appendOnExpresstion("2", true) }
        three.setOnClickListener { appendOnExpresstion("3", true) }
        four.setOnClickListener { appendOnExpresstion("4", true) }
        five.setOnClickListener { appendOnExpresstion("5", true) }
        six.setOnClickListener { appendOnExpresstion("6", true) }
        seven.setOnClickListener { appendOnExpresstion("7", true) }
        eight.setOnClickListener { appendOnExpresstion("8", true) }
        nine.setOnClickListener { appendOnExpresstion("9", true) }
        zero.setOnClickListener { appendOnExpresstion("0", true) }
        dot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        plus.setOnClickListener { appendOnExpresstion("+", false) }
        minus.setOnClickListener { appendOnExpresstion("-", false) }
        multiply.setOnClickListener { appendOnExpresstion("*", false) }
        divide.setOnClickListener { appendOnExpresstion("/", false) }
        openBracket.setOnClickListener { appendOnExpresstion("(", false) }
        closeBracket.setOnClickListener { appendOnExpresstion(")", false) }


        clearButton.setOnClickListener {
            expression.text = ""
            result.text = ""
        }

        backButton.setOnClickListener {
            val string = expression.text.toString()
            if(string.isNotEmpty()){
                expression.text = string.substring(0,string.length-1)
            }
            result.text = ""
        }

        equals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(expression.text.toString()).build()
                val resultdouble = expression.evaluate()
                val longResult = resultdouble.toLong()
                if(resultdouble == longResult.toDouble())
                    result.text = longResult.toString()
                else
                    result.text = resultdouble.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }


    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if(result.text.isNotEmpty()){
            expression.text = ""
        }

        if (canClear) {
            result.text = ""
            expression.append(string)
        } else {
            expression.append(result.text)
            expression.append(string)
            result.text = ""
        }
    }
}