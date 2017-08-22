package kr.co.jayhy.weather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import kr.co.jayhy.weather.common.CommonValues
import kr.co.jayhy.weather.common.GpsLocationInfo

/**
 * Created by jayhy on 2017. 8. 12..
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()
    }


    private fun checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionLocation = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
            val permissionInternet = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.INTERNET)

            if (permissionLocation == PackageManager.PERMISSION_DENIED || permissionInternet == PackageManager.PERMISSION_DENIED) {

                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST)

            } else {
                init()
            }
        } else {
            init()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    init()
                } else {
                    Toast.makeText(this, getString(R.string.message_my_permission_fail), Toast.LENGTH_LONG).show()
                }

                return
            }
        }
    }

    private fun init() {
        try {
            val locationInfo = GpsLocationInfo(this)
            if (locationInfo.isGetLocation) {
                CommonValues.myLatidude = locationInfo.latitude
                CommonValues.myLongitude = locationInfo.longitude

                Toast.makeText(this, "location ==> " + CommonValues.myLatidude + ", " + CommonValues.myLongitude, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()

            } else {
                Toast.makeText(this, getString(R.string.message_gps_off), Toast.LENGTH_SHORT).show()
                finish()
            }

        } catch (e: Exception) {

        }

    }

    companion object {
        private val MY_PERMISSIONS_REQUEST = 99
    }


}
