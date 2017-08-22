package kr.co.jayhy.weather;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import kr.co.jayhy.weather.realm.module.WeatherModule;

/**
 * Created by jhkim on 2017-08-14.
 */

public class JayHyApplication extends Application {

    public static JayHyApplication myApp;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("realm-jayhy.db")
                .modules(new WeatherModule())
                .build();

        Realm.setDefaultConfiguration(realmConfig);
    }

    public static JayHyApplication getInstance() {
        if(myApp == null)
            myApp = new JayHyApplication();
        return myApp;
    }
}
