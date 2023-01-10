package com.example.notification

import android.Manifest
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
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "noti"
    private val description = "notification"


    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPermission()

        val btn = findViewById<Button>(R.id.btn)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;

        btn.setOnClickListener {


            val intent = Intent(this@MainActivity, afterNotification::class.java)

            val pendingIntent = PendingIntent.getActivity(
                this@MainActivity, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val contentView = RemoteViews(packageName, R.layout.activity_after_notification)

            // checking if android version is greater than oreo(API 26) or not
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
                    .setContentTitle("Title")
                    .setContentText("Desc")
                    .setSound(notificationSoundUri)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234, builder.build())
        }
    }


        private fun getPermission() {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
                Dexter.withContext(this)
                    .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,

                    ).withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                            report.let {

                                if (report.areAllPermissionsGranted()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Permissions Granted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Please Grant Permissions to use the app",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                                        )
                                    )
                                }

                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                            p1: PermissionToken?
                        ) {
                            p1?.continuePermissionRequest()
                        }
                    }).withErrorListener {
                        Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
                    }.check()
            }
            else {
                Dexter.withContext(this)
                    .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.POST_NOTIFICATIONS
                    ).withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                            report.let {

                                if (report.areAllPermissionsGranted()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Permissions Granted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Please Grant Permissions to use the app",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                                        )
                                    )
                                }

                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                            p1: PermissionToken?
                        ) {
                            p1?.continuePermissionRequest()
                        }
                    }).withErrorListener {
                        Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
                    }.check()
            }

        }

    }
