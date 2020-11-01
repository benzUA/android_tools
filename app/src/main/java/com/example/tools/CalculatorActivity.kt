package com.example.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_calculator.*
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder;

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        btn_0.setOnClickListener{ setInFieldText("0") }
        btn_1.setOnClickListener{ setInFieldText("1") }
        btn_2.setOnClickListener{ setInFieldText("2") }
        btn_3.setOnClickListener{ setInFieldText("3") }
        btn_4.setOnClickListener{ setInFieldText("4") }
        btn_5.setOnClickListener{ setInFieldText("5") }
        btn_6.setOnClickListener{ setInFieldText("6") }
        btn_7.setOnClickListener{ setInFieldText("7") }
        btn_8.setOnClickListener{ setInFieldText("8") }
        btn_9.setOnClickListener{ setInFieldText("9") }
        btn_plus.setOnClickListener{ setInFieldText("+") }
        btn_minus.setOnClickListener{ setInFieldText("-") }
        btn_mult.setOnClickListener{ setInFieldText("*") }
        btn_div.setOnClickListener{ setInFieldText("/") }
        btn_left.setOnClickListener{ setInFieldText("(") }
        btn_right.setOnClickListener{ setInFieldText(")") }
        btn_dot.setOnClickListener{ setInFieldText(".") }
        btn_clr.setOnClickListener{ clearAllFields() }
        btn_back.setOnClickListener {
            val str = math_in.text.toString();
            if(str.isNotEmpty())
                math_in.text = str.substring(0, str.length - 1)
            clearAllFields();
        }
        btn_equal.setOnClickListener {
            try{
                val ex = ExpressionBuilder(math_in.text.toString()).build();
                val res = ex.evaluate();

                val longRes = res.toLong();
                if(res == longRes.toDouble())
                    math_out.text = longRes.toString();
                else math_out.text = res.toString();
            } catch(e:Exception) {
                Log.d("Error: ", "${e.message}");
            }
        }
    }

    private fun setInFieldText(str: String)
    {
        if(math_out.text != "")
            math_in.text = math_out.text;
        math_in.append(str);
        math_out.text = "";
    }

    private fun clearAllFields()
    {
        math_in.text = "";
        math_out.text = "";
    }
}