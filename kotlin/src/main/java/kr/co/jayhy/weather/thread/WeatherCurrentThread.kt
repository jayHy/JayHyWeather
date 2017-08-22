package kr.co.jayhy.weather.thread

import android.content.Context

import kr.co.jayhy.weather.R
import kr.co.jayhy.weather.common.CommonValues
import kr.co.jayhy.weather.listener.MyWeatherCallback
import kr.co.jayhy.weather.repo.WeatherCurrentRepo
import kr.co.jayhy.weather.service.WeatherRepoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.GsonConverterFactory
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by jhkim on 2017-08-06.
 */

class WeatherCurrentThread(context: Context, lat: Double, lon: Double, private val callback: MyWeatherCallback) : Thread() {

    private val context: Context? = context

    private val lat = lat
    private val lon = lon

    override fun run() {
        super.run()

        val retrofit = Retrofit.Builder().baseUrl(context!!.getString(R.string.url_weather)).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherRepoService::class.java)
        val call = service.getCurrentWeather(CommonValues.WEATHER_APP_KEY, lat, lon)
        call.enqueue(object : Callback<WeatherCurrentRepo> {
            override fun onResponse(call: Call<WeatherCurrentRepo>, response: Response<WeatherCurrentRepo>) {
                if (response.isSuccessful) {
                    val repo = response.body()
                    callback.onMyWeatherComplate(true, repo)
                } else {
                    callback.onMyWeatherComplate(false, null!!)
                }
            }

            override fun onFailure(call: Call<WeatherCurrentRepo>, t: Throwable) {
                callback.onMyWeatherComplate(false, null!!)
            }
        })
    }

    companion object {
        private val TAG = "jayHy"
    }
}
