package kr.co.jayhy.weather.listener

import kr.co.jayhy.weather.repo.WeatherForecastRepo

/**
 * Created by jayhy on 2017. 8. 12..
 */

interface ForecastWeatherCallback {

    fun onForecastWeatherCallback(isSuccess: Boolean, result: WeatherForecastRepo)
}
