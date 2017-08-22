package kr.co.jayhy.weather.common;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.List;

/**
 * Created by jhkim on 2017-08-06.
 */

public class GeoCodingService {

    private Context context = null;

    private final static String TAG = "jayHy";

    public GeoCodingService(Context context) {
        this.context = context;
    }

    public void getAddressToLocation(String address) {
        Geocoder geocoder = null;
        List<Address> list = null;
        try {
            geocoder = new Geocoder(context);
            list = geocoder.getFromLocationName(address, 10);
        } catch(Exception e) {
            Log.d(TAG, "getAddressToLocation Exception :" + e.toString());
        } finally {
            if(geocoder != null) geocoder = null;
        }

        if(list != null) {
            if(list.size() > 0) {
                Address addr = list.get(0);
                double lat = addr.getLatitude();
                double lon = addr.getLongitude();
                Log.d(TAG, "lat ==> " + lat);
                Log.d(TAG, "lon ==> " + lon);
            } else {

            }
        } else {

        }
    }

    public String getLocationToAddress(double lat, double lon) {
        String result = "";
        Geocoder geocoder = null;
        List<Address> list = null;
        try {
            geocoder = new Geocoder(context);
            list = geocoder.getFromLocation(lat, lon, 10);
        } catch(Exception e) {
            Log.d(TAG, "getAddressToLocation Exception :" + e.toString());
        } finally {
            if(geocoder != null) geocoder = null;
        }

        if(list != null) {
            if(list.size() > 0) {
                Address addr = list.get(0);
//                Log.d(TAG, "addr ==>" + addr);

//                result = addr.getAdminArea() + " " + addr.getLocality() + " " + addr.getThoroughfare();
                result = addr.getThoroughfare();


            } else {

            }
        } else {

        }

        return result;
    }
}
