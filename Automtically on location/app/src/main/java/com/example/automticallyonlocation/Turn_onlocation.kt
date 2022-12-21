package com.example.automticallyonlocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*


class Turn_onlocation : AppCompatActivity() {
    private var textView: TextView? = null
    private var locationManager: LocationManager? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_onlocation)
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            PackageManager.PERMISSION_GRANTED
        )
        textView = findViewById(R.id.textView)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

    }

    fun buttonCheckGPS_Status(view: View?) {
        if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            textView!!.text = "GPS is ON"
        } else {
            textView!!.text = "GPS is OFF"
        }
    }

    fun buttonSwitchGPS_ON(view: View?) {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = (10000 / 2).toLong()
        val locationSettingsRequestBuilder = LocationSettingsRequest.Builder()
        locationSettingsRequestBuilder.addLocationRequest(locationRequest)
        locationSettingsRequestBuilder.setAlwaysShow(true)
        val settingsClient = LocationServices.getSettingsClient(this)
        val task = settingsClient.checkLocationSettings(locationSettingsRequestBuilder.build())
        task.addOnSuccessListener(
            this
        ) { textView!!.text = "Location settings (GPS) is ON." }
        task.addOnFailureListener(this) { e ->
            textView!!.text = "Location settings (GPS) is OFF."
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(
                        this@Turn_onlocation, Turn_onlocation.Companion.REQUEST_CHECK_SETTINGS
                    )
                } catch (sendIntentException: SendIntentException) {
                    sendIntentException.printStackTrace()
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CHECK_SETTINGS = 0x1
    }
}