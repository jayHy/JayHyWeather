package kr.co.jayhy.weather.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import kr.co.jayhy.weather.R;
import kr.co.jayhy.weather.common.CommonValues;
import kr.co.jayhy.weather.common.GeoCodingService;
import kr.co.jayhy.weather.common.GpsLocationInfo;
import kr.co.jayhy.weather.item.WeatherItem;
import kr.co.jayhy.weather.listener.MyWeatherCallback;
import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.thread.WeatherCurrentThread;
import kr.co.jayhy.weather.thread.WeatherForecastThread;
import kr.co.jayhy.weather.util.Util;

/**
 * Created by jhkim on 2017-08-13.
 */

public class MainViewModel implements MyWeatherCallback {

    public final ObservableField<String> temp = new ObservableField<>();
    public final ObservableField<String> temp_min = new ObservableField<>();
    public final ObservableField<String> temp_max = new ObservableField<>();
    public final ObservableField<String> current_time = new ObservableField<>();
    public final ObservableField<String> weather = new ObservableField<>();

    private Context context;

    private final static String TAG = "jayHy";

    public MainViewModel(Context context) {
        this.context = context;
    }

    public void onCreate() {
        getMyWeather();
    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDestroy() {

    }

    private void getMyWeather() {
        WeatherCurrentThread weatherCurrentThread;
        WeatherForecastThread weatherForecastThread;
        try {
            if(CommonValues.myLatidude != 0.0 && CommonValues.myLongitude != 0.0) {

                Log.d(TAG, "CommonValues.myLatidude ==>" + CommonValues.myLatidude);
                Log.d(TAG, "CommonValues.myLongitude ==>" + CommonValues.myLongitude);

                weatherCurrentThread = new WeatherCurrentThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
                weatherCurrentThread.start();

//                weatherForecastThread = new WeatherForecastThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
//                weatherForecastThread.start();

            } else {
                GpsLocationInfo locationInfo = new GpsLocationInfo(context);
                if(locationInfo.isGetLocation()) {
                    CommonValues.myLatidude = locationInfo.getLatitude();
                    CommonValues.myLongitude = locationInfo.getLongitude();

                    weatherCurrentThread = new WeatherCurrentThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
                    weatherCurrentThread.start();

//                    weatherForecastThread = new WeatherForecastThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
//                    weatherForecastThread.start();

                }
            }
        } finally {

        }

    }

    @Override
    public void onMyWeatherComplate(boolean isSuccess, WeatherCurrentRepo result) {
        if(isSuccess) {
            if(Util.NVLString(result.cod)) {

                setWeatherStatus(result);

//                myLoactionAddr(Double.parseDouble(result.coord.lat), Double.parseDouble(result.coord.lon));
            }
        }
    }

//    private void myLoactionAddr(double lat, double lon) {
//        GeoCodingService geoCodingService;
//        try {
//            geoCodingService = new GeoCodingService(this);
//            String result = geoCodingService.getLocationToAddress(lat, lon);
//            if(result != null) {
//                binding.toolbar.setTitle(result);
//            }
//        } finally {
//
//        }
//    }

    private void setWeatherStatus(WeatherCurrentRepo repo) {

        try {
            WeatherItem item = new WeatherItem(context);
            item = item.getCurrentItem(repo);

            current_time.set(item.getDt());
            temp.set(item.getTemp());
            temp_min.set(item.getTemp_min());
            temp_max.set(item.getTemp_max());

//            Glide.with(this).load(item.getIcon_url())
//                    .asBitmap()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .skipMemoryCache(true)
//                    .fitCenter()
//                    .placeholder(R.color.transparent)
//                    .error(R.color.transparent)
//                    .into(binding.imgIcon);

            weather.set(item.getMain());

        } finally {

        }

    }

}
