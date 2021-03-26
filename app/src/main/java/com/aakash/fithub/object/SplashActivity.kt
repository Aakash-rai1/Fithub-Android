package com.aakash.fithub.`object`

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aakash.fithub.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            startActivity(
                    Intent(
                            this@SplashActivity,
                            SliderActivity::class.java
                    )
            )
            finish()
        }
    }
}