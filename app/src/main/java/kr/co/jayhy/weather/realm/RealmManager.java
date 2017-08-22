package kr.co.jayhy.weather.realm;

import io.realm.Realm;
import kr.co.jayhy.weather.realm.dao.WeatherDao;

/**
 * Created by jhkim on 2017-08-14.
 */

public class RealmManager {

    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static WeatherDao getInstance_WeatherDao() {
        checkForOpenRealm();
        return new WeatherDao(mRealm);
    }

    public static void clear() {
        checkForOpenRealm();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.close();
                //clear rest of your dao classes
            }
        });
    }

    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
