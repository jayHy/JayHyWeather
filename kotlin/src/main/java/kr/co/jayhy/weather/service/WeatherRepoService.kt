package kr.co.jayhy.weather.service

import kr.co.jayhy.weather.repo.WeatherCurrentRepo
import kr.co.jayhy.weather.repo.WeatherForecastRepo
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by jhkim on 2017-08-06.
 */

interface WeatherRepoService {

    @Headers("Accept: application/json")
    @POST("data/2.5/weather")
    fun getCurrentWeather(@Query("APPID") appId: String,
                          @Query("lat") lat: Double,
                          @Query("lon") lon: Double): Call<WeatherCurrentRepo>

    @Headers("Accept: application/json")
    @POST("data/2.5/forecast")
    fun getForecastWeather(@Query("APPID") appId: String,
                           @Query("lat") lat: Double,
                           @Query("lon") lon: Double): Call<WeatherForecastRepo>


}
