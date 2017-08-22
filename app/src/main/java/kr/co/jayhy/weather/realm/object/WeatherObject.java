package kr.co.jayhy.weather.realm.object;

import io.realm.RealmObject;

/**
 * Created by jhkim on 2017-08-14.
 */

public class WeatherObject extends RealmObject {

    private String dt;
    private String dt_date;
    private String dt_time;
    private String temp;
    private String temp_min;
    private String temp_max;
    private String main;
    private String icon_url;

    private String lat;
    private String lon;

    private String wind_spped;
    private String wind_drag;

    private String cloud;

    private String rain;
    private String snow;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getDt_date() {
        return dt_date;
    }

    public void setDt_date(String dt_date) {
        this.dt_date = dt_date;
    }

    public String getDt_time() {
        return dt_time;
    }

    public void setDt_time(String dt_time) {
        this.dt_time = dt_time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getWind_spped() {
        return wind_spped;
    }

    public void setWind_spped(String wind_spped) {
        this.wind_spped = wind_spped;
    }

    public String getWind_drag() {
        return wind_drag;
    }

    public void setWind_drag(String wind_drag) {
        this.wind_drag = wind_drag;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }
}
