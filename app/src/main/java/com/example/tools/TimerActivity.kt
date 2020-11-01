package com.example.tools

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_timer.*


class TimerActivity : AppCompatActivity() {
    var secondsTotal = 0;
    var hours:Int = 0;
    var min:Int = 0;
    var sec:Int = 0;
    private val NOTIFY_ID = 99;
    var isRunning = false;

    lateinit var mHandler: Handler;
    private var mRunnable = object:Runnable{
        override fun run() {
            hours = secondsTotal / 3600;
            min = secondsTotal / 60 % 60;
            sec = secondsTotal % 60;

            time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
            secondsTotal--;
            if(secondsTotal != -1) {
                mHandler.postDelayed(this, 1000);
            }
            else {
                clearVar();
                notificationCreate();
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        mHandler = Handler();
        btn_start.setOnClickListener {
            if(!isRunning) {
                secondsTotal = hours * 3600 + min * 60 + sec;
                startTimer();
                isRunning = true;
            }
        }
        btn_pause.setOnClickListener { pauseTimer(); }
        btn_stop.setOnClickListener { stopTimer(); }

        btn_up_hours.setOnClickListener {
            if(!isRunning) {
                hours++;
                time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
            }
        }

        btn_up_mins.setOnClickListener {
            if(!isRunning) {
                min++;
                time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
            }
        }

        btn_up_sec.setOnClickListener {
            if(!isRunning) {
                sec++;
                time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
            }
        }

        btn_down_hours.setOnClickListener {
            if(hours > 0 && !isRunning)
            {
                hours--;
            }
            time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
        }

        btn_down_mins.setOnClickListener {
            if(min > 0 && !isRunning)
            {
                min--;
            }
            time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
        }

        btn_down_sec.setOnClickListener {
            if(sec > 0 && !isRunning)
            {
                sec--;
            }
            time_last.text = String.format("%d:%02d:%02d", hours, min, sec);
        }
    }

    private fun startTimer() {
        if(secondsTotal>0) {
            mHandler.post(mRunnable);
        }
    }

    private fun pauseTimer(){
        mHandler.removeCallbacks(mRunnable);
        isRunning = false;
    }

    private fun stopTimer(){
        mHandler.removeCallbacks(mRunnable);
        clearVar();
    }

    private fun clearVar()
    {
        hours = 0;
        min = 0;
        sec = 0;
        secondsTotal = 0;
        time_last.text = "0:00:00";
        val manager = NotificationManagerCompat.from(this@TimerActivity);
        manager.cancel(NOTIFY_ID);
        isRunning = false
    }

    private fun notificationCreate()
    {
        val notIntent = Intent(this, TimerActivity::class.java);
        val contentIntent = PendingIntent.getActivity(
            this@TimerActivity,
            0, notIntent,
            PendingIntent.FLAG_CANCEL_CURRENT)

        val builder =
            NotificationCompat.Builder(this@TimerActivity, NOTIFY_ID.toString())
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Timer")
                .setContentText("Time is up!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);


        val notManager = NotificationManagerCompat.from(this@TimerActivity);

        notManager.notify(NOTIFY_ID,builder.build());
    }
}