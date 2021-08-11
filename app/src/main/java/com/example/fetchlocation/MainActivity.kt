package com.example.fetchlocation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var lm:LocationManager
    lateinit var loc:Location
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("reverseGeocode", "hello:0 ")
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 111
            )


            lm=getSystemService(LOCATION_SERVICE) as LocationManager
            loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) as Location

        val ll=object :LocationListener{
            override fun onLocationChanged(location: Location) {
                Log.d("reverseGeocode", "hello1: ")

                var gc=Geocoder(applicationContext, Locale.getDefault())
                var addresses=gc.getFromLocation(loc!!.latitude, loc.longitude, 2)
                var addredd=addresses.get(0)

                val lan=addredd.getAddressLine(0)
                Log.d("akshay: ", lan)
                Log.d("akshay: ", addredd.locality)
                reverseGeocode(location)
            }
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.2f, ll)

    }

    private fun reverseGeocode(location: Location?) {
        Log.d("reverseGeocode", "hello:2 ")
//            var gc=Geocoder(this, Locale.getDefault())
//            var addresses=gc.getFromLocation(loc!!.latitude, loc.longitude, 2)
//             var addredd=addresses.get(0)
//
//val lan=addredd.getAddressLine(0)
//        Log.d("akshay: ", lan)
//        Log.d("akshay: ", addredd.locality)
    }
}