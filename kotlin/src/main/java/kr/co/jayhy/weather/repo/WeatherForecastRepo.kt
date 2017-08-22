package kr.co.jayhy.weather.repo

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by jayhy on 2017. 8. 12..
 */

class WeatherForecastRepo {

    @SerializedName("cod")
    var cod: String? = null

    @SerializedName("city")
    var city: City? = null

    @SerializedName("cnt")
    var cnt: String? = null

    @SerializedName("list")
    var list: ArrayList<List>? = null

    inner class City {

        @SerializedName("id")
        var id: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("coord")
        var coord: Coord? = null

        @SerializedName("country")
        var country: String? = null

        inner class Coord {
            @SerializedName("lon")
            var lon: String? = null

            @SerializedName("lat")
            var lat: String? = null

        }
    }


    inner class List {
        @SerializedName("dt")
        var dt: String? = null

        @SerializedName("main")
        var main: Main? = null

        @SerializedName("weather")
        var weathers: ArrayList<Weather>? = null

        @SerializedName("clouds")
        var clouds: Clouds? = null

        @SerializedName("wind")
        var wind: Wind? = null

        @SerializedName("rain")
        var rain: Rain? = null

        @SerializedName("snow")
        var snow: Snow? = null

        @SerializedName("dt_txt")
        var dt_txt: String? = null

        inner class Main {
            @SerializedName("temp")
            var temp: String? = null

            @SerializedName("temp_min")
            var temp_min: String? = null

            @SerializedName("temp_max")
            var temp_max: String? = null

            @SerializedName("pressure")
            var pressure: String? = null

            @SerializedName("sea_level")
            var sea_level: String? = null

            @SerializedName("grnd_level")
            var grnd_level: String? = null

            @SerializedName("humidity")
            var humidity: String? = null

            @SerializedName("temp_kf")
            var temp_kf: String? = null

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

        inner class Clouds {
            @SerializedName("all")
            var all: String? = null
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

    }


}
