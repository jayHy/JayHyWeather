package kr.co.jayhy.weather.realm.module;

import io.realm.annotations.RealmModule;
import kr.co.jayhy.weather.realm.object.WeatherObject;

/**
 * Created by jhkim on 2017-08-14.
 */

@RealmModule(classes = {WeatherObject.class} )
public class WeatherModule {}
