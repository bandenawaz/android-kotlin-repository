package com.walmart.firstapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationChannelCompat


class WalmartActivity : AppCompatActivity() {

    /* TODO: Declare all the buttons */
    private lateinit var  btnDial: Button
    private lateinit var  btnAlert: Button
    private lateinit var  btnBack: Button
    private lateinit var btnNotification: Button

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId:String = "walmart.apps.notification"
    private val description: String = "Test notification from Walmart"

    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walmart)


        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //TODO: Initialise all the button
        btnDial = findViewById(R.id.buttonDial)
        btnAlert = findViewById(R.id.buttonAlert)
        btnBack = findViewById(R.id.buttonBack)
        btnNotification = findViewById(R.id.buttonShowNotification)


        //TODO: Handle individual clicks of buttons
        btnDial.setOnClickListener{

            startActivity(Intent(Intent.ACTION_DIAL))
        }

        btnAlert.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit App")
            builder.setMessage("Do you want to exit?")
            builder.setCancelable(false)
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener{
                dialog, id -> finish()
            })

            builder.setNegativeButton("No", DialogInterface.OnClickListener{
                dialog, id -> dialog.dismiss()
            })

            val alert = builder.create()
            alert.show()

        }

        btnBack.setOnClickListener{

            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

        btnNotification.setOnClickListener{

            val intent = Intent(applicationContext,AfterNotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(packageName, R.layout.activity_after_notification)

            //Checking if android version is greater than API26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.CYAN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.walmart_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pi)
            }else {
                builder = Notification.Builder(this)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.walmart_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pi)
            }
            notificationManager.notify(1234,builder.build())


        }

    }


}