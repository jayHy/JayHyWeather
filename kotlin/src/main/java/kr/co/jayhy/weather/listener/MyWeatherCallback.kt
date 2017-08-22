package kr.co.jayhy.weather.listener

import kr.co.jayhy.weather.repo.WeatherCurrentRepo

/**
 * Created by jayhy on 2017. 8. 12..
 */

interface MyWeatherCallback {

    fun onMyWeatherComplate(isSuccess: Boolean, result: WeatherCurrentRepo)
}
