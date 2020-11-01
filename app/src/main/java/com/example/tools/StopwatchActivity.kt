package com.example.tools

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stopwatch.*

class StopwatchActivity : AppCompatActivity() {

    var seconds = 0;
    var isRunning = false;

    lateinit var mHandler: Handler;
    private var mRunnable = object:Runnable{
        override fun run() {
            var hours:Int = seconds / 3600;
            var min:Int = seconds / 60 % 60;
            var sec:Int = seconds % 60;

            time_show.text = String.format("%d:%02d:%02d", hours, min, sec);
            seconds++;
            mHandler.postDelayed(this, 1000);

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        mHandler = Handler();
        btn_start.setOnClickListener { if(!isRunning){
            startTimer()
            isRunning = true;
        }; }
        btn_pause.setOnClickListener { pauseTimer(); }
        btn_stop.setOnClickListener { stopTimer(); }
    }

    private fun startTimer() {
        mHandler.post(mRunnable);
    }

    private fun pauseTimer(){
        mHandler.removeCallbacks(mRunnable);
        isRunning = false;
    }

    private fun stopTimer(){
        mHandler.removeCallbacks(mRunnable);
        time_show.text = "0:00:00";
        seconds = 0;
        isRunning = false;
    }
}