package com.example.tools

import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_random.*
import kotlin.random.Random

class RandomActivity : AppCompatActivity() {
    var mHandler = Handler();
    var i = 0;
    var isRandomising = false;
    var sound = SoundPool(1, AudioManager.STREAM_RING, 1);
    var soundId: Int = -1;

    private var mRunnable = object: Runnable{
        override fun run() {
            randomiser.text = Random.nextInt(0,150).toString();
            i++;
            if(i<14) {
                mHandler.postDelayed(this, 200);
            }
            else {
                result.text = String.format("Result: %d", Random.nextInt(0,150));
                showResult();
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        soundId = sound.load(this@RandomActivity, R.raw.dice, 1);

        btn_gen.setOnClickListener {
            if(!isRandomising) {
                start();
            }
        }
    }

    private fun randomize()
    {
        isRandomising = true;
        mHandler.post(mRunnable);
    }

    fun showResult()
    {
        randomiser.text = "";
        i=0;
        isRandomising = false;
        sound.stop(soundId);
    }

    private fun start()
    {
        mHandler.removeCallbacks(mRunnable);
        randomiser.text="0";
        result.text = "";
        randomize();
        playSound();
    }

    private fun playSound()
    {
        sound.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
}