package com.aakash.fithub.ui.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aakash.fithub.R
import com.google.android.youtube.player.YouTubePlayer

class ChestActivity : AppCompatActivity() {

    private lateinit var youTubePlayer: YouTubePlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)


        initializePlayer()
    }

    private fun initializePlayer() {

    }
}