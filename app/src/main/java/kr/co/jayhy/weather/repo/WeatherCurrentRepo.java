package kr.co.jayhy.weather.repo;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jhkim on 2017-08-06.
 */

public class WeatherCurrentRepo {

    @SerializedName("cod")
    public String cod;

    @SerializedName("coord")
    public Coord coord;

    @SerializedName("weather")
    public List<Weather> weathers;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public Main main;

    @SerializedName("visibility")
    public String visibility;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("rain")
    public Rain rain;

    @SerializedName("snow")
    public Snow snow;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("dt")
    public String dt;

    public class Coord {

        @SerializedName("lon")
        public String lon;
        @SerializedName("lat")
        public String lat;

    }

    public class Weather {

        @SerializedName("id")
        public String id;

        @SerializedName("main")
        public String main;

        @SerializedName("description")
        public String description;

        @SerializedName("icon")
        public String icon;

    }

    public class Main {

        @SerializedName("temp")
        public String temp;

        @SerializedName("pressure")
        public String pressure;

        @SerializedName("humidity")
        public String humidity;

        @SerializedName("temp_min")
        public String temp_min;

        @SerializedName("temp_max")
        public String temp_max;
    }

    public class Wind {

        @SerializedName("speed")
        public String speed;

        @SerializedName("deg")
        public String deg;

    }

    public class Rain {
        @Nullable
        @SerializedName("3h")
        public String rain;
    }

    public class Snow {
        @Nullable
        @SerializedName("3h")
        public String snow;
    }

    public class Clouds {
        @SerializedName("all")
        public String all;
    }

    public class Sys {
        @SerializedName("type")
        public String type;

        @SerializedName("id")
        public String id;

        @SerializedName("message")
        public String message;

        @SerializedName("country")
        public String country;

        @SerializedName("sunrise")
        public String sunrise;

        @SerializedName("sunset")
        public String sunset;

    }
}
