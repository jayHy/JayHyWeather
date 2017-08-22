package kr.co.jayhy.weather.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

/**
 * Created by jayhy on 2017. 8. 12..
 */

object Util {

    fun convertKelvinCelsius(kelvin: Double): Double {
        var result = 0.0

        val celsius = 273.15
        if (kelvin != 0.0) {
            result = kelvin - celsius
        }

        result = java.lang.Double.parseDouble(java.lang.String.format("%.1f", result))

        return result
    }

    fun NVLString(value: String?): Boolean {
        var result = false
        if (value != null && value != "" && value.length > 0) {
            result = true
        }

        return result

    }

    fun convertUTCtoLocalCurrent(dt: Long): String {

        var result = ""
        var date: Date? = null
        var sdf: SimpleDateFormat? = null

        try {
            date = Date(dt * 1000L)
            sdf = SimpleDateFormat("MM월dd일 HH시mm분 기준")
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.timeZone = TimeZone.getTimeZone("GMT+9")
            result = sdf.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (date != null) date = null
            if (sdf != null) sdf = null
        }

        return result

    }

    fun convertUTCtoLocalDate(dt: Long): String {

        var result = ""
        var date: Date? = null
        var sdf: SimpleDateFormat? = null

        try {
            date = Date(dt * 1000L)
            sdf = SimpleDateFormat("MM-dd")
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.timeZone = TimeZone.getTimeZone("GMT+9")
            result = sdf.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (date != null) date = null
            if (sdf != null) sdf = null
        }

        return result

    }

    fun convertUTCtoLocalTime(dt: Long): String {

        var result = ""
        var date: Date? = null
        var sdf: SimpleDateFormat? = null

        try {
            date = Date(dt * 1000L)
            sdf = SimpleDateFormat("mm:ss")
            // GMT(그리니치 표준시 +9 시가 한국의 표준시
            sdf.timeZone = TimeZone.getTimeZone("GMT+9")
            result = sdf.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (date != null) date = null
            if (sdf != null) sdf = null
        }

        return result

    }
}
