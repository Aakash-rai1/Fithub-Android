package com.aakash.fithub.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.aakash.fithub.response.IntroSlide
import com.aakash.fithub.R
import com.aakash.fithub.adapter.IntroSliderAdapter
import com.google.android.material.button.MaterialButton

class SliderActivity : AppCompatActivity() {
    private lateinit var introSliderViewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var buttonNext: MaterialButton
    private lateinit var textSkipIntro: TextView




    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide("Workout from home",
            "Workout. Anytime. Anywhere. with Fithub",
            R.drawable.fitness),

            IntroSlide("Video Streaming",
            "Watch tutorials for performing your workouts",
            R.drawable.stream)
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)




        if(!isFirstTimeAppStart()){
            setAppStartStatus(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        //calling the id from splash activity
        introSliderViewPager = findViewById(R.id.introSliderViewPager)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        buttonNext = findViewById(R.id.buttonNext)
        textSkipIntro = findViewById(R.id.textSkipIntro)

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem+1< introSliderAdapter.itemCount){
                introSliderViewPager.currentItem+=1
            }
            else{
                setAppStartStatus(true)
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)

                }
                finish()
            }
        }

        textSkipIntro.setOnClickListener {
            setAppStartStatus(true)
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }





    private fun isFirstTimeAppStart(): Boolean {
        val pref= applicationContext.getSharedPreferences("SLIDER_APP", Context.MODE_PRIVATE)
        return pref.getBoolean("APP_START", true)

    }

    private fun setAppStartStatus(status: Boolean) {
        val pref= applicationContext.getSharedPreferences("SLIDER_APP", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor= pref.edit()
        editor.putBoolean("APP_START", status)
        editor.apply()
    }

    private fun setupIndicators(){
        val indicators= arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams= LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i]= ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive
                )
                )
                this?.layoutParams=layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }

    //for buttons
    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorContainer.childCount
        for(i in 0 until childCount){
            val imageView= indicatorContainer[i] as ImageView
            if(i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_active
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive
                    )
                )

            }
        }
    }


}