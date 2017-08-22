package kr.co.jayhy.weather.service;

import java.util.HashMap;

import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.repo.WeatherForecastRepo;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by jhkim on 2017-08-06.
 */

public interface WeatherRepoService {

    @Headers({"Accept: application/json"})
    @POST("data/2.5/weather")
    Call<WeatherCurrentRepo> getCurrentWeather(@Query("APPID") String appId,
                                               @Query("lat") double lat,
                                               @Query("lon") double lon);

    @Headers({"Accept: application/json"})
    @POST("data/2.5/forecast")
    Call<WeatherForecastRepo> getForecastWeather(@Query("APPID") String appId,
                                                 @Query("lat") double lat,
                                                 @Query("lon") double lon);


}
