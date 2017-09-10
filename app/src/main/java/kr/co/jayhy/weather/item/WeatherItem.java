package kr.co.jayhy.weather.item;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.realm.RealmObject;
import kr.co.jayhy.weather.R;
import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.repo.WeatherForecastRepo;
import kr.co.jayhy.weather.util.Util;

/**
 * Created by jhkim on 2017-08-13.
 */

public class WeatherItem {

    private Context context;

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

    private final static String TAG = "jayHy";

    public WeatherItem() {

    }

    public WeatherItem(Context context) {
        this.context = context;
    }

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

    public WeatherItem getCurrentItem(WeatherCurrentRepo repo) {
        WeatherItem item = new WeatherItem();

        try {

            item.setDt(Util.convertUTCtoLocalCurrent(Long.parseLong(repo.dt)));

            double temp = Util.convertKelvinCelsius(Double.parseDouble(repo.main.temp));
            double min = Util.convertKelvinCelsius(Double.parseDouble(repo.main.temp_min));
            double max = Util.convertKelvinCelsius(Double.parseDouble(repo.main.temp_max));

            item.setTemp(String.valueOf(temp));
            item.setTemp_min("↓ " + String.valueOf(min));
            item.setTemp_max("↑ " + String.valueOf(max));

            String strIcon = repo.weathers.get(0).icon;
            String url = context.getString(R.string.url_weather_icon) + strIcon + ".png";
            item.setIcon_url(url);

            item.setMain(getIconMain(strIcon));

            if(repo.rain != null) {
                if(repo.rain.rain == null) {
                    item.setRain("0.0");
                } else {
                    if(repo.rain.rain.length() > 0) {
                        item.setRain(repo.rain.rain);
                    } else {
                        item.setRain("0.0");
                    }
                }
            } else {
                item.setRain("0.0");
            }


//            if(repo.rain.rain == null || repo.rain.rain.equals("") ||
//                    repo.rain.rain.equals(null) || repo.rain.rain.length() == 0) {
//                item.setRain("0,0");
//            } else {
//                item.setRain(repo.rain.rain);
//            }

        } catch (Exception e) {
            Log.d(TAG, "getCurrentItem Exception : " + e.toString());
        } finally {

        }

        return item;
    }

    public ArrayList<WeatherItem> getForecasttem(WeatherForecastRepo repo) {
        ArrayList<WeatherItem> items = new ArrayList<>();

        String prevDate = "";
        for(WeatherForecastRepo.List list : repo.list) {
            WeatherItem item = new WeatherItem();

            double temp = Util.convertKelvinCelsius(Double.parseDouble(list.main.temp));
            double min = Util.convertKelvinCelsius(Double.parseDouble(list.main.temp_min));
            double max = Util.convertKelvinCelsius(Double.parseDouble(list.main.temp_max));

            String dtDate = Util.convertUTCtoLocalDate(Long.parseLong(list.dt));

            /**
             * 현재 시간이면 유지
             * 다른 시간 이면 한번 표시
             */
            if(prevDate.equals("")) {
                item.setDt_date(dtDate);
                prevDate = dtDate;
            } else {
                if(dtDate.equals(prevDate)) {
                    item.setDt_date("-");
                } else {
                    item.setDt_date(dtDate);
                    prevDate = dtDate;
                }
            }

            item.setDt_time(Util.convertUTCtoLocalTime(Long.parseLong(list.dt)));

            item.setTemp(String.valueOf(temp));
            item.setTemp_min(String.valueOf(min));
            item.setTemp_max(String.valueOf(max));

            for(WeatherForecastRepo.List.Weather weather : list.weathers) {
                String strIcon = weather.icon;
                String url = context.getString(R.string.url_weather_icon) + weather.icon + ".png";
                item.setIcon_url(url);
                item.setMain(getIconMain(strIcon));
            }

            items.add(item);
        }

        return items;
    }

    private String getIconMain(String value) {
        String result = "";

        switch (value) {
            case "01d": case "01n":
                result = "맑음";
                break;
            case "02d": case "02n":
                result = "구름조금";
                break;
            case "03d": case "03n":
                result = "구름";
                break;
            case "04d": case "04n":
                result = "구름많음";
                break;
            case "09d": case "09n":
                result = "눈.비";
                break;
            case "10d": case "10n":
                result = "비";
                break;
            case "11d": case "11n":
                result = "천둥번개";
                break;
            case "13d": case "13n":
                result = "눈";
                break;
            case "50d": case "50n":
                result = "안개";
                break;
        }

        return result;
    }
}
