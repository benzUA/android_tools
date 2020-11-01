package com.example.tools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastActivity = "Tools";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calculator.setOnClickListener { goTo("CALC"); }
        btn_stopwatch.setOnClickListener { goTo("STPW"); }
        btn_tool3.setOnClickListener { goTo("TIMER"); }
        btn_tool4.setOnClickListener { goTo("RAND"); }
    }

    fun goTo(str:String)
    {
        val newIntent:Intent;

        when(str){
            "CALC" -> {
                newIntent = Intent(this, CalculatorActivity::class.java);
                startActivity(newIntent);
                lastActivity = "Calculator";
            };
            "STPW" -> {
                newIntent = Intent(this, StopwatchActivity::class.java);
                startActivity(newIntent);
                lastActivity = "Stopwatch";
            };
            "TIMER" -> {
                newIntent = Intent(this, TimerActivity::class.java);
                startActivity(newIntent);
                lastActivity = "Timer";
            };
            "RAND" -> {
                newIntent = Intent(this, RandomActivity::class.java);
                startActivity(newIntent);
                lastActivity = "Randomizer";
            };
            else -> {
                val newAlert = AlertDialog()
                val manager = supportFragmentManager
                newAlert.show(manager, "");
                lastActivity = "Tools";
            }
        }

        last_activity.text = last_activity.text.substring(0, 10) + lastActivity;
    }
}