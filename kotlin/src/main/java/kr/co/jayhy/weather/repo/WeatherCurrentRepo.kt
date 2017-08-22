package kr.co.jayhy.weather.repo

import com.google.gson.annotations.SerializedName

/**
 * Created by jhkim on 2017-08-06.
 */

class WeatherCurrentRepo {

    @SerializedName("cod")
    var cod: String? = null

    @SerializedName("coord")
    var coord: Coord? = null

    @SerializedName("weather")
    var weathers: List<Weather>? = null

    @SerializedName("base")
    var base: String? = null

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("visibility")
    var visibility: String? = null

    @SerializedName("wind")
    var wind: Wind? = null

    @SerializedName("rain")
    var rain: Rain? = null

    @SerializedName("snow")
    var snow: Snow? = null

    @SerializedName("clouds")
    var clouds: Clouds? = null

    @SerializedName("dt")
    var dt: String? = null

    inner class Coord {

        @SerializedName("lon")
        var lon: String? = null
        @SerializedName("lat")
        var lat: String? = null

    }

    inner class Weather {

        @SerializedName("id")
        var id: String? = null

        @SerializedName("main")
        var main: String? = null

        @SerializedName("description")
        var description: String? = null

        @SerializedName("icon")
        var icon: String? = null

    }

    inner class Main {

        @SerializedName("temp")
        var temp: String? = null

        @SerializedName("pressure")
        var pressure: String? = null

        @SerializedName("humidity")
        var humidity: String? = null

        @SerializedName("temp_min")
        var temp_min: String? = null

        @SerializedName("temp_max")
        var temp_max: String? = null
    }

    inner class Wind {

        @SerializedName("speed")
        var speed: String? = null

        @SerializedName("deg")
        var deg: String? = null

    }

    inner class Rain {
        @SerializedName("3h")
        var rain: String? = null
    }

    inner class Snow {
        @SerializedName("3h")
        var snow: String? = null
    }

    inner class Clouds {
        @SerializedName("all")
        var all: String? = null
    }

    inner class Sys {
        @SerializedName("type")
        var type: String? = null

        @SerializedName("id")
        var id: String? = null

        @SerializedName("message")
        var message: String? = null

        @SerializedName("country")
        var country: String? = null

        @SerializedName("sunrise")
        var sunrise: String? = null

        @SerializedName("sunset")
        var sunset: String? = null

    }
}
