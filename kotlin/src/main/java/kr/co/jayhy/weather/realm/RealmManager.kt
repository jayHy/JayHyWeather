package kr.co.jayhy.weather.realm

import io.realm.Realm
import kr.co.jayhy.weather.realm.dao.WeatherDao

/**
 * Created by jhkim on 2017-08-14.
 */

object RealmManager {

    var mRealm: Realm? = null

    fun open(): Realm {
        mRealm = Realm.getDefaultInstance()
        return mRealm!!
    }

    fun close() {
        if (mRealm != null) {
            mRealm!!.close()
        }
    }

    val instance_WeatherDao: WeatherDao
        get() {
            checkForOpenRealm()
            return WeatherDao(mRealm!!)
        }

    fun clear() {
        checkForOpenRealm()
        mRealm!!.executeTransaction { realm ->
            realm.close()
            //clear rest of your dao classes
        }
    }

    private fun checkForOpenRealm() {
        if (mRealm == null || mRealm!!.isClosed) {
            throw IllegalStateException("RealmManager: Realm is closed, call open() method first")
        }
    }
}
