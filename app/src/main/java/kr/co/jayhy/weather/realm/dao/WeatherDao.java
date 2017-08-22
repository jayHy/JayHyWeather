package kr.co.jayhy.weather.realm.dao;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import kr.co.jayhy.weather.item.WeatherItem;
import kr.co.jayhy.weather.realm.object.WeatherObject;

/**
 * Created by jhkim on 2017-08-14.
 */

public class WeatherDao {

    private Realm mRealm;

    public WeatherDao(Realm realm) {
        this.mRealm = realm;
    }

    public void current_save(final WeatherObject item) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WeatherObject object = realm.createObject(WeatherObject.class);
                object.setMain(item.getMain());
                object.setIcon_url(item.getIcon_url());
                object.setTemp(item.getTemp());
                object.setTemp_min(item.getTemp_min());
                object.setTemp_max(item.getTemp_max());
                object.setDt(item.getDt());
            }
        });
    }

    public RealmResults<WeatherObject> current_loadAll() {
        return mRealm.where(WeatherObject.class).findAll();
    }

    public RealmResults<WeatherObject> current_loadAllAsync() {
        return mRealm.where(WeatherObject.class).findAll();
    }

    public void current_remove(@NonNull final RealmObject object) {
        final RealmResults<WeatherObject> results = mRealm.where(WeatherObject.class).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    public void removeAll() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
            }
        });
    }


    public long current_count() {
        return mRealm.where(WeatherObject.class).count();
    }
}
