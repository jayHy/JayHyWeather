package kr.co.jayhy.weather.realm.`object`

import io.realm.RealmObject

/**
 * Created by jhkim on 2017-08-14.
 */

class WeatherObject : RealmObject() {

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
}
