package kr.co.jayhy.weather.thread

import android.content.Context

import kr.co.jayhy.weather.R
import kr.co.jayhy.weather.common.CommonValues
import kr.co.jayhy.weather.listener.ForecastWeatherCallback
import kr.co.jayhy.weather.repo.WeatherForecastRepo
import kr.co.jayhy.weather.service.WeatherRepoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.GsonConverterFactory
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by jayhy on 2017. 8. 12..
 */

class WeatherForecastThread(context: Context, lat: Double, lon: Double, private val callback: ForecastWeatherCallback) : Thread() {

    private val context: Context = context

    private val lat = lat
    private val lon = lon

    override fun run() {
        super.run()

        val retrofit = Retrofit.Builder().baseUrl(context!!.getString(R.string.url_weather)).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherRepoService::class.java)
        val call = service.getForecastWeather(CommonValues.WEATHER_APP_KEY, lat, lon)
        call.enqueue(object : Callback<WeatherForecastRepo> {
            override fun onResponse(call: Call<WeatherForecastRepo>, response: Response<WeatherForecastRepo>) {
                if (response.isSuccessful) {
                    val repo = response.body()

                    callback.onForecastWeatherCallback(true, repo)
                    //                    caseRepo = response.body();

                } else {
                    callback.onForecastWeatherCallback(false, null!!)
                }
            }

            override fun onFailure(call: Call<WeatherForecastRepo>, t: Throwable) {
                callback.onForecastWeatherCallback(false, null!!)
            }
        })
    }

    companion object {
        private val TAG = "jayHy"
    }


}
