package kr.co.jayhy.weather.listener;

import kr.co.jayhy.weather.repo.WeatherCurrentRepo;

/**
 * Created by jayhy on 2017. 8. 12..
 */

public interface MyWeatherCallback {

    void onMyWeatherComplate(boolean isSuccess, WeatherCurrentRepo result);
}
