package com.example.firebasecloudservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseToken"
    private lateinit var notificationManager: NotificationManager
    private val ADMIN_CHANNEL_ID = "User"
    var intent: Intent? = null
    var notificationId:Int?=null

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        p0.let { message ->
            Log.d(TAG, "PO title" +message.data.get("title").toString())
            Log.d(TAG, "Msg title" + message.data.get("message").toString())
//            Log.d(TAG, "P0" + p0.data.get("title").toString())
//            Log.d(TAG, "msg"+message.getData().toString())
//            Log.e(TAG,"status:"+ message.getData().get("requestStatus").toString())
//            var page1 = message.getData().get("page_open").toString()
//            Log.e(TAG,"page open :"+ page1)

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //Setting up Notification channels for android O and above

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                setupNotificationChannels()
            }

//
//            if(page1.equals("0"))
//            {
//                intent = Intent(this@MyFirebaseMessagingService, Promotion::class.java)
//            }
//            else if(page1.equals("1"))
//            {
//                intent = Intent(this@MyFirebaseMessagingService, Notifications::class.java)
//            }
            else
            {
                intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java)
            }

            val notificationId = Random().nextInt(60000)

            intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            var pendingIntent = PendingIntent.getActivity(
                this@MyFirebaseMessagingService, 0, intent,
//                  PendingIntent.FLAG_ONE_SHOT,
//                 PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT } else { PendingIntent.FLAG_UPDATE_CURRENT }
            )
            val largeIcon =
                BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)


            val notificationSoundUri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.rim)
             var notificationBuilder = NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)  //a resource for your custom small icon
                .setLargeIcon(largeIcon)
                .setContentTitle(p0.notification?.title) //the "title" value you sent in your notification
                .setContentText(message.data["body"]) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setSound(notificationSoundUri)
                .setDefaults(Notification.DEFAULT_LIGHTS )
                .setContentIntent(pendingIntent)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(
                notificationId /* ID of notification */,
                notificationBuilder.build()
            )
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupNotificationChannels() {

        Log.d("setupnotificationcheck","This function call");
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
       // val notificationSoundUri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.rim)
        val notificationSoundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.rim);

        val adminChannelName = "Xeats"
        val adminChannelDescription = "Xeats"
        val adminChannel: NotificationChannel
        adminChannel = NotificationChannel(
            ADMIN_CHANNEL_ID,
            adminChannelName,
            NotificationManager.IMPORTANCE_LOW
        )
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.RED
        adminChannel.enableVibration(true)
        adminChannel.vibrationPattern = LongArray(1000)
        adminChannel.setSound(notificationSoundUri, audioAttributes)
        notificationManager.createNotificationChannel(adminChannel)

    }

}