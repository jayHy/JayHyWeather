package kr.co.jayhy.weather.thread;

import android.content.Context;
import android.util.Log;

import kr.co.jayhy.weather.R;
import kr.co.jayhy.weather.common.CommonValues;
import kr.co.jayhy.weather.listener.MyWeatherCallback;
import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.service.WeatherRepoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jhkim on 2017-08-06.
 */

public class WeatherCurrentThread extends Thread {

    private Context context = null;

    private double lat = 0;
    private double lon = 0;

    private MyWeatherCallback callback;

    private final static String TAG = "jayHy";

    public WeatherCurrentThread(Context context, double lat, double lon, MyWeatherCallback callback) {
        this.context = context;
        this.lat = lat;
        this.lon = lon;
        this.callback = callback;
    }

    @Override
    public void run() {
        super.run();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(context.getString(R.string.url_weather)).addConverterFactory(GsonConverterFactory.create()).build();
        WeatherRepoService service = retrofit.create(WeatherRepoService.class);
        Call<WeatherCurrentRepo> call = service.getCurrentWeather(CommonValues.WEATHER_APP_KEY, lat, lon);
        call.enqueue(new Callback<WeatherCurrentRepo>() {
            @Override
            public void onResponse(Call<WeatherCurrentRepo> call, Response<WeatherCurrentRepo> response) {
                if(response.isSuccessful()) {
                    WeatherCurrentRepo repo = response.body();
                    callback.onMyWeatherComplate(true, repo);
                } else {
                    callback.onMyWeatherComplate(false, null);
                }
            }

            @Override
            public void onFailure(Call<WeatherCurrentRepo> call, Throwable t) {
                callback.onMyWeatherComplate(false, null);
            }
        });
    }
}
