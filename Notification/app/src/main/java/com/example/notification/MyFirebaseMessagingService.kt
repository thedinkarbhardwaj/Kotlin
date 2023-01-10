package com.example.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseMessagingService: FirebaseMessagingService() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "noti"
    private val description = "noti"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.let { message ->
            Log.d("TAG", "PO title" + message.notification?.title.toString())

            notificationfunction(message.notification?.title.toString())
        }
    }

    @SuppressLint("RemoteViewLayout")
    fun notificationfunction(title:String){

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;

        val intent = Intent(this, afterNotification::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val contentView = RemoteViews(packageName, R.layout.activity_after_notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            val notificationSoundUri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.rim)
            // val notificationSoundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.rim);

            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationChannel.setSound(notificationSoundUri, audioAttributes)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentTitle("Title")
                .setSound(notificationSoundUri)
                .setContentText("Desc")
                .setContentIntent(pendingIntent)

        } else {

            val notificationSoundUri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.rim)
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentTitle("Title" + title)
                .setContentText("Desc")
                .setSound(notificationSoundUri)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())

    }

}