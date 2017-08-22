package kr.co.jayhy.weather;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import kr.co.jayhy.weather.common.CommonValues;
import kr.co.jayhy.weather.common.GpsLocationInfo;

/**
 * Created by jayhy on 2017. 8. 12..
 */

public class SplashActivity extends AppCompatActivity {

    private final static int MY_PERMISSIONS_REQUEST = 99;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();
    }

    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionLocation = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            int permissionInternet = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);

            if(permissionLocation == PackageManager.PERMISSION_DENIED || permissionInternet == PackageManager.PERMISSION_DENIED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST);

            } else {
                init();
            }
        } else {
            init();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    Toast.makeText(this, getString(R.string.message_my_permission_fail), Toast.LENGTH_LONG).show();
                }

                return;
            }
        }
    }

    private void init() {
        Intent intent;
        GpsLocationInfo locationInfo;
        try {

            locationInfo = new GpsLocationInfo(this);
            if(locationInfo.isGetLocation()) {
                CommonValues.myLatidude = locationInfo.getLatitude();
                CommonValues.myLongitude = locationInfo.getLongitude();

//                Toast.makeText(this, "location ==> " + CommonValues.myLatidude +  ", " + CommonValues.myLongitude, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, getString(R.string.message_gps_off), Toast.LENGTH_SHORT).show();
                finish();
            }

        } finally {
            finish();
        }
    }


}
