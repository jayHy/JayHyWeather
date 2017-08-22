package kr.co.jayhy.weather.repo;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jayhy on 2017. 8. 12..
 */

public class WeatherForecastRepo {

    @SerializedName("cod")
    public String cod;

    @SerializedName("city")
    public City city;

    @SerializedName("cnt")
    public String cnt;

    @SerializedName("list")
    public ArrayList<List> list;

    public class City {

        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("coord")
        public Coord coord;

        @SerializedName("country")
        public String country;

        public class Coord {
            @SerializedName("lon")
            public String lon;

            @SerializedName("lat")
            public String lat;

        }
    }


    public class List {
        @SerializedName("dt")
        public String dt;

        @SerializedName("main")
        public Main main;

        @SerializedName("weather")
        public ArrayList<Weather> weathers;

        @SerializedName("clouds")
        public Clouds clouds;

        @SerializedName("wind")
        public Wind wind;

        @SerializedName("rain")
        public Rain rain;

        @SerializedName("snow")
        public Snow snow;

        @SerializedName("dt_txt")
        public String dt_txt;

        public class Main {
            @SerializedName("temp")
            public String temp;

            @SerializedName("temp_min")
            public String temp_min;

            @SerializedName("temp_max")
            public String temp_max;

            @SerializedName("pressure")
            public String pressure;

            @SerializedName("sea_level")
            public String sea_level;

            @SerializedName("grnd_level")
            public String grnd_level;

            @SerializedName("humidity")
            public String humidity;

            @SerializedName("temp_kf")
            public String temp_kf;

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

        public class Clouds {
            @SerializedName("all")
            public String all;
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

    }


}
