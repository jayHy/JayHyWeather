package kr.co.jayhy.weather.realm.dao

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import kr.co.jayhy.weather.realm.`object`.WeatherObject

/**
 * Created by jhkim on 2017-08-14.
 */

class WeatherDao(private val mRealm: Realm) {

    fun current_save(item: WeatherObject) {
        mRealm.executeTransaction { realm ->
            val `object` = realm.createObject(WeatherObject::class.java)
            `object`.main = item.main
            `object`.icon_url = item.icon_url
            `object`.temp = item.temp
            `object`.temp_min = item.temp_min
            `object`.temp_max = item.temp_max
            `object`.dt = item.dt
        }
    }

    fun current_loadAll(): RealmResults<WeatherObject> {
        return mRealm.where(WeatherObject::class.java).findAll()
    }

    fun current_loadAllAsync(): RealmResults<WeatherObject> {
        return mRealm.where(WeatherObject::class.java).findAll()
    }

    fun current_remove(`object`: RealmObject) {
        val results = mRealm.where(WeatherObject::class.java).findAll()
        mRealm.executeTransaction { results.deleteAllFromRealm() }
    }

    fun removeAll() {
        mRealm.executeTransaction { }
    }


    fun current_count(): Long {
        return mRealm.where(WeatherObject::class.java).count()
    }
}
