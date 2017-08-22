package kr.co.jayhy.weather

import android.app.Application

import io.realm.Realm
import io.realm.RealmConfiguration
import kr.co.jayhy.weather.realm.module.WeatherModule

/**
 * Created by jhkim on 2017-08-14.
 */

class JayHyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        Realm.init(this)
//
//        val realmConfig = RealmConfiguration.Builder()
//                .name("realm-jayhy.db")
//                .modules(WeatherModule())
//                .build()
//
//        Realm.setDefaultConfiguration(realmConfig)
    }
}
