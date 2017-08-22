package kr.co.jayhy.weather.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jayhy on 2017. 8. 12..
 */

public class Util {

    public static double convertKelvinCelsius(double kelvin) {
        double result = 0;

        double celsius = 273.15;
        if(kelvin != 0.0) {
            result = kelvin - celsius;
        }

        result = Double.parseDouble(String.format("%.1f", result));

        return result;
    }

    public static boolean NVLString(String value) {
        boolean result = false;
        if(value != null && !value.equals("") && value.length() > 0) {
            result = true;
        }

        return result;

    }

    public static String convertUTCtoLocalCurrent(long dt) {

        String result = "";
        Date date = null;
        SimpleDateFormat sdf = null;

        try {
            date = new Date(dt * 1000L);
            sdf = new SimpleDateFormat("MM월dd일 HH시mm분 기준");
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
            result = sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(date != null) date = null;
            if(sdf != null) sdf = null;
        }

        return result;

    }

    public static String convertUTCtoLocalDate(long dt) {

        String result = "";
        Date date = null;
        SimpleDateFormat sdf = null;

        try {
            date = new Date(dt * 1000L);
            sdf = new SimpleDateFormat("MM-dd");
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
            result = sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(date != null) date = null;
            if(sdf != null) sdf = null;
        }

        return result;

    }

    public static String convertUTCtoLocalTime(long dt) {

        String result = "";
        Date date = null;
        SimpleDateFormat sdf = null;

        try {
            date = new Date(dt * 1000L);
            sdf = new SimpleDateFormat("mm:ss");
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
            result = sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(date != null) date = null;
            if(sdf != null) sdf = null;
        }

        return result;

    }
}
