package kr.co.jayhy.weather.thread;

import android.content.Context;
import android.util.Log;

import kr.co.jayhy.weather.R;
import kr.co.jayhy.weather.common.CommonValues;
import kr.co.jayhy.weather.listener.ForecastWeatherCallback;
import kr.co.jayhy.weather.listener.MyWeatherCallback;
import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.repo.WeatherForecastRepo;
import kr.co.jayhy.weather.service.WeatherRepoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jayhy on 2017. 8. 12..
 */

public class WeatherForecastThread extends Thread {

    private Context context = null;

    private double lat = 0;
    private double lon = 0;

    private ForecastWeatherCallback callback;

    private final static String TAG = "jayHy";

    public WeatherForecastThread(Context context, double lat, double lon, ForecastWeatherCallback callback) {
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
        Call<WeatherForecastRepo> call = service.getForecastWeather(CommonValues.WEATHER_APP_KEY, lat, lon);
        call.enqueue(new Callback<WeatherForecastRepo>() {
            @Override
            public void onResponse(Call<WeatherForecastRepo> call, Response<WeatherForecastRepo> response) {
                if(response.isSuccessful()) {
                    WeatherForecastRepo repo = response.body();

                    callback.onForecastWeatherCallback(true, repo);
//                    caseRepo = response.body();

                } else {
                    callback.onForecastWeatherCallback(false, null);
                }
            }

            @Override
            public void onFailure(Call<WeatherForecastRepo> call, Throwable t) {
                callback.onForecastWeatherCallback(false, null);
            }
        });
    }


}
