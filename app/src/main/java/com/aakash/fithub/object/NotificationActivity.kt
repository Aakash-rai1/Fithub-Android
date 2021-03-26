package com.aakash.fithub.`object`

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import adapter.NotificationChannels
import com.aakash.fithub.R

class NotificationActivity : AppCompatActivity() {
    private lateinit var btnNotification1: Button
    private lateinit var btnNotification2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnNotification1=findViewById(R.id.btnNotification1)
        btnNotification2=findViewById(R.id.btnNotification2)


        btnNotification1.setOnClickListener{
            showHighPriorityNotification()
        }

        btnNotification2.setOnClickListener{
            showLowPriorityNotification()
        }


    }

    private fun showLowPriorityNotification() {
        val notificationManager= NotificationManagerCompat.from(this)

        val notificationChannels= NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification= NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("low priority notification")
                .setContentText("this is high priority")
                .setColor(Color.YELLOW)
                .build()
        notificationManager.notify(2, notification)
    }

    private fun showHighPriorityNotification() {
        val notificationManager= NotificationManagerCompat.from(this)

        val notificationChannels= NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification= NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("high priority notification")
                .setContentText("THis is low priority notification")
                .setColor(Color.YELLOW)
                .build()
        notificationManager.notify(1, notification)

    }
}