package com.example.googlemap

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.googlemap.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var imggps:ImageView

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // This will store current location info
    private var currentLocation: Location? = null

    var currentmarkr:Marker ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imggps = findViewById(R.id.imggps) as ImageView

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MapsActivity)
        fetchLocation()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    private fun fetchLocation() {

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),1000)
            return
        }


        val task = fusedLocationProviderClient?.lastLocation

        task?.addOnSuccessListener{location->

            if (location!=null){
                this.currentLocation = location
                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this@MapsActivity)
                Toast.makeText(this@MapsActivity,"work",Toast.LENGTH_LONG).show()
            }
            else{
                val location2 = Location(LocationManager.GPS_PROVIDER)
                val delhi = LatLng(28.62617177737133, 77.20553937752881)
                location2.latitude = delhi.latitude
                location2.longitude = delhi.longitude
                this.currentLocation = location2
                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this@MapsActivity)
                Toast.makeText(this@MapsActivity,"not work",Toast.LENGTH_LONG).show()
            }
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            1000 -> if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                fetchLocation()
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnCameraIdleListener(object:GoogleMap.OnCameraIdleListener {

            override fun onCameraIdle() {

                val latlong2 = mMap.cameraPosition.target

               // imggps.visibility = View.VISIBLE

                if (currentmarkr!=null){

                    currentmarkr?.remove()

                    drawmarker(latlong2)
                   // Toast.makeText(this@MapsActivity,"check",Toast.LENGTH_SHORT).show()
                }

            }

        })


        // Add a marker in Sydney and move the camera
//        val delhi = LatLng(28.62617177737133, 77.20553937752881)
//        mMap.addMarker(MarkerOptions().position(delhi).title("Marker in delhi"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(delhi,16f))

        val latlong = LatLng(currentLocation?.latitude!!, currentLocation?.longitude!!)
       drawmarker(latlong)

        mMap.setOnMarkerDragListener(object : OnMarkerDragListener{
            override fun onMarkerDrag(p0: Marker) {

            }

            override fun onMarkerDragEnd(p0: Marker) {
                if (currentmarkr!=null){
                    currentmarkr?.remove()

                    var newlat_long = LatLng(p0.position.latitude,p0.position.longitude)
                    drawmarker(newlat_long)
                }
            }

            override fun onMarkerDragStart(p0: Marker) {

            }

        })


        // when we click on map this function return latitude and lngitude
//        mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
//            override fun onMapClick(p0: LatLng) {
//                val  position=p0
//                Toast.makeText(this@MapsActivity,
//                    "Lat " + position.latitude + " "
//                            + "Long " + position.longitude,
//                    Toast.LENGTH_LONG).show();
//
//                Log.d("onmapclick","Lat " + position.latitude + " "
//                        + "Long " + position.longitude)
//            }
//
//        });



    }

    private fun drawmarker(latlong: LatLng) {
       val markeroption =  MarkerOptions().position(latlong).title("i am here")
            .snippet(getAddress(latlong.longitude,latlong.latitude))
           .draggable(true)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlong,18f))


        currentmarkr =  mMap.addMarker(markeroption)
        currentmarkr?.remove()

       // imggps.visibility = View.INVISIBLE
        currentmarkr?.showInfoWindow()

        Log.d("longitude-latitude",latlong.toString())

//        mMap.setOnMarkerClickListener { marker ->
//            val position = marker.position
//            Toast.makeText(
//                this@MapsActivity,
//                "Lat " + position.latitude + " "
//                        + "Long " + position.longitude,
//                Toast.LENGTH_LONG
//            ).show()
//            true
//        }

    }

    private fun getAddress(longitude: Double, latitude: Double): String? {
        val geocoder = Geocoder(this,Locale.getDefault())
        var address = geocoder.getFromLocation(latitude,longitude,1)

        var addresstt = address[0].getAddressLine(0).toString()
        Log.d("address",addresstt)
        return addresstt
    }

//    private fun getLocation() {
//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
//    }
//    override fun onLocationChanged(location: Location) {
//        Log.d("latitude","Latitude: " + location.latitude + " , Longitude: " + location.longitude)
//
//    }
}