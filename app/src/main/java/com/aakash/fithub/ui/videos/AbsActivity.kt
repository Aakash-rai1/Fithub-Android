package com.aakash.fithub.ui.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aakash.fithub.R

class AbsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs)
        initializePlayer()
    }

    private fun initializePlayer() {

    }
}