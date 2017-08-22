package kr.co.jayhy.weather.common

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log

/**
 * Created by jhkim on 2017-08-06.
 */

class GeoCodingService(context: Context) {

    private val context: Context? = context

//    fun getAddressToLocation(address: String) {
//
//        try {
//            val geocoder = Geocoder(context)
//            val list = geocoder.getFromLocationName(address, 10)
//
//            if (list != null) {
//                if (list.size > 0) {
//                    val addr = list[0]
////                    val lat = addr.latitude
////                    val lon = addr.longitude
//                } else {
//
//                }
//            } else {
//
//            }
//        } catch (e: Exception) {
//
//        }
//
//    }

    fun getLocationToAddress(lat: Double, lon: Double): String {
        var result = ""
        try {
            val geocoder = Geocoder(context)
            val list = geocoder.getFromLocation(lat, lon, 10)

            if (list != null) {
                if (list.size > 0) {
                    val addr = list[0]

                    result = addr.thoroughfare
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "getAddressToLocation Exception :" + e.toString())
        }

        return result
    }

    companion object {
        private val TAG = "jayHy"
    }
}
