package com.example.firebasecloudservice

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val mediaPlayer: MediaPlayer = MediaPlayer.create(this@MainActivity,com.example.firebasecloudservice.R.raw.music)
//        mediaPlayer.isLooping = true
//        mediaPlayer.start();

        Log.d("dinkar","Hi My name is Dinkar")

    }

}