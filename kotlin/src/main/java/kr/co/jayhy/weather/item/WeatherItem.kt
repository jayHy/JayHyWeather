package kr.co.jayhy.weather.item

import android.content.Context
import android.util.Log

import java.util.ArrayList

import kr.co.jayhy.weather.R
import kr.co.jayhy.weather.repo.WeatherCurrentRepo
import kr.co.jayhy.weather.repo.WeatherForecastRepo
import kr.co.jayhy.weather.util.Util

/**
 * Created by jhkim on 2017-08-13.
 */

class WeatherItem {

    var mContext: Context? = null

    var dt: String? = null
    var dt_date: String? = null
    var dt_time: String? = null
    var temp: String? = null
    var temp_min: String? = null
    var temp_max: String? = null
    var main: String? = null
    var icon_url: String? = null

    var lat: String? = null
    var lon: String? = null

    var wind_spped: String? = null
    var wind_drag: String? = null

    var cloud: String? = null

    var rain: String? = null
    var snow: String? = null

    constructor() {

    }

    constructor(context: Context) {
       this.mContext = context
    }

    fun getCurrentItem(repo: WeatherCurrentRepo): WeatherItem {
        val item = WeatherItem()

        try {

            item.dt = Util.convertUTCtoLocalCurrent(java.lang.Long.parseLong(repo.dt))

            val temp = Util.convertKelvinCelsius(java.lang.Double.parseDouble(repo.main!!.temp))
            val min = Util.convertKelvinCelsius(java.lang.Double.parseDouble(repo.main!!.temp_min))
            val max = Util.convertKelvinCelsius(java.lang.Double.parseDouble(repo.main!!.temp_max))

            item.temp = temp.toString()
            item.temp_min = "↓ " + min.toString()
            item.temp_max = "↑ " + max.toString()

            val strIcon: String? = repo.weathers!![0].icon
            val url = mContext!!.getString(R.string.url_weather_icon) + strIcon + ".png"
            item.icon_url = url

            item.main = getIconMain(strIcon)

            item.lat = repo.coord!!.lat;
            item.lon = repo.coord!!.lon;

        } catch (e: Exception) {
            Log.d(TAG, "getCurrentItem Exception : " + e.toString())
        } finally {

        }

        return item
    }

    fun getForecasttem(repo: WeatherForecastRepo): ArrayList<WeatherItem> {
        val items = ArrayList<WeatherItem>()

        var prevDate = ""
        for (list in repo.list!!) {
            val item = WeatherItem()

            val temp = Util.convertKelvinCelsius(java.lang.Double.parseDouble(list.main!!.temp))
            val min = Util.convertKelvinCelsius(java.lang.Double.parseDouble(list.main!!.temp_min))
            val max = Util.convertKelvinCelsius(java.lang.Double.parseDouble(list.main!!.temp_max))

            val dtDate = Util.convertUTCtoLocalDate(java.lang.Long.parseLong(list.dt))

            if (prevDate == "") {
                item.dt_date = dtDate
                prevDate = dtDate
            } else {
                if (dtDate == prevDate) {
                    item.dt_date = "-"
                } else {
                    item.dt_date = dtDate
                    prevDate = dtDate
                }
            }

            item.dt_time = Util.convertUTCtoLocalTime(java.lang.Long.parseLong(list.dt))

            item.temp = temp.toString()
            item.temp_min = min.toString()
            item.temp_max = max.toString()

            for (weather in list.weathers!!) {
                val strIcon = weather.icon
                val url = mContext!!.getString(R.string.url_weather_icon) + weather.icon + ".png"
                item.icon_url = url
                item.main = getIconMain(strIcon)
            }

            items.add(item)
        }

        return items
    }

    private fun getIconMain(value: String?): String {
        var result = ""

        when (value) {
            "01d", "01n" -> result = "맑음"
            "02d", "02n" -> result = "구름조금"
            "03d", "03n" -> result = "구름"
            "04d", "04n" -> result = "구름많음"
            "09d", "09n" -> result = "눈.비"
            "10d", "10n" -> result = "비"
            "11d", "11n" -> result = "천둥번개"
            "13d", "13n" -> result = "눈"
            "50d", "50n" -> result = "안개"
        }

        return result
    }

    companion object {

        private val TAG = "jayHy"
    }
}
