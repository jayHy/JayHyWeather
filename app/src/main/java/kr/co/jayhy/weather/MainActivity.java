package kr.co.jayhy.weather;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import io.realm.RealmResults;
import kr.co.jayhy.weather.common.CommonValues;
import kr.co.jayhy.weather.common.GeoCodingService;
import kr.co.jayhy.weather.common.GpsLocationInfo;
import kr.co.jayhy.weather.contract.MainContract;
import kr.co.jayhy.weather.databinding.ActivityMainBinding;
import kr.co.jayhy.weather.item.WeatherItem;
import kr.co.jayhy.weather.listener.ForecastWeatherCallback;
import kr.co.jayhy.weather.listener.MyWeatherCallback;
import kr.co.jayhy.weather.presenter.MainPresenter;
import kr.co.jayhy.weather.realm.RealmManager;
import kr.co.jayhy.weather.realm.object.WeatherObject;
import kr.co.jayhy.weather.recycler.CurrentRecyclerAdapter;
import kr.co.jayhy.weather.repo.WeatherCurrentRepo;
import kr.co.jayhy.weather.repo.WeatherForecastRepo;
import kr.co.jayhy.weather.thread.WeatherCurrentThread;
import kr.co.jayhy.weather.thread.WeatherForecastThread;
import kr.co.jayhy.weather.util.Util;
import kr.co.jayhy.weather.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainContract.View, MyWeatherCallback, ForecastWeatherCallback {

    private Context context = null;

    private MainViewModel model = new MainViewModel(this);

    private ActivityMainBinding binding;

    private CurrentRecyclerAdapter recyclerAdapter = null;
    private ArrayList<WeatherItem> weatherItems;

    private MainContract.Presenter presenter;

    private final static String TAG = "jayHy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(model);

        setSupportActionBar(binding.toolbar);

        context = this;

        RealmManager.open();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.foreRecycler.setLayoutManager(layoutManager);

        weatherItems = new ArrayList<>();
        recyclerAdapter = new CurrentRecyclerAdapter(this, weatherItems);
        binding.foreRecycler.setAdapter(recyclerAdapter);

        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getMyWeather();

                binding.swipeLayout.setRefreshing(false);
            }
        });

        checkCurrnetData();

        getMyWeather();

        presenter = new MainPresenter();
        presenter.setView(this);

//        presenter.loadItem();

    }

    private void checkCurrnetData() {
        if(RealmManager.getInstance_WeatherDao().current_count() > 0) {
            final RealmResults<WeatherObject> dataList = RealmManager.getInstance_WeatherDao().current_loadAll();
            setWeatherStatus(dataList.get(0));
        }
    }

    private void getMyWeather() {
        WeatherCurrentThread weatherCurrentThread;
        WeatherForecastThread weatherForecastThread;
        try {
            if(CommonValues.myLatidude != 0.0 && CommonValues.myLongitude != 0.0) {

                weatherCurrentThread = new WeatherCurrentThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
                weatherCurrentThread.start();

                weatherForecastThread = new WeatherForecastThread(this, CommonValues.myLatidude, CommonValues.myLongitude, this);
                weatherForecastThread.start();

            } else {
                GpsLocationInfo locationInfo = new GpsLocationInfo(this);
                if(locationInfo.isGetLocation()) {
                    CommonValues.myLatidude = locationInfo.getLatitude();
                    CommonValues.myLongitude = locationInfo.getLongitude();

                    weatherCurrentThread = new WeatherCurrentThread(context, CommonValues.myLatidude, CommonValues.myLongitude, this);
                    weatherCurrentThread.start();

                    weatherForecastThread = new WeatherForecastThread(this, CommonValues.myLatidude, CommonValues.myLongitude, this);
                    weatherForecastThread.start();

                }
            }
        } finally {

        }

    }

    @Override
    public void onMyWeatherComplate(boolean isSuccess, WeatherCurrentRepo result) {
        if(isSuccess) {
            if(Util.NVLString(result.cod)) {

                setWeatherStatus(result);
//
                myLoactionAddr(Double.parseDouble(result.coord.lat), Double.parseDouble(result.coord.lon));

            }
        }
    }

    private void myLoactionAddr(double lat, double lon) {
        GeoCodingService geoCodingService;
        try {
            geoCodingService = new GeoCodingService(this);
            String result = geoCodingService.getLocationToAddress(lat, lon);
            if(result != null) {
                binding.toolbar.setTitle(result);
            }
        } finally {

        }
    }

    private void setWeatherStatus(WeatherCurrentRepo repo) {

        try {
            WeatherItem item = new WeatherItem(context);
            item = item.getCurrentItem(repo);

            Log.d(TAG, "setWeatherStatus item ==> " + item);

            WeatherObject object = new WeatherObject();
            object.setDt(item.getDt());
            object.setTemp(item.getTemp());
            object.setTemp_min(item.getTemp_min());
            object.setTemp_max(item.getTemp_max());
            object.setIcon_url(item.getIcon_url());
            object.setMain(item.getMain());
            object.setDt(item.getDt());
            object.setRain(item.getRain());

            binding.currentTime.setText(item.getDt());
            binding.temp.setText(item.getTemp());
            binding.tempMin.setText(item.getTemp_min());
            binding.tempMax.setText(item.getTemp_max());

            Glide.with(this).load(item.getIcon_url())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .placeholder(R.color.transparent)
                    .error(R.color.transparent)
                    .into(binding.imgIcon);

            binding.weather.setText(item.getMain());
            binding.rain.setText(item.getRain() + " " + "mm");

            if(RealmManager.getInstance_WeatherDao().current_count() > 0) {
                RealmManager.getInstance_WeatherDao().current_remove(new WeatherObject());
            }

            RealmManager.getInstance_WeatherDao().current_save(object);

        } finally {

        }

    }

    private void setWeatherStatus(WeatherObject object) {
        Log.d(TAG, "setWeatherStatus ==> " + object);

        try {
            binding.currentTime.setText(object.getDt());
            binding.temp.setText(object.getTemp());
            binding.tempMin.setText(object.getTemp_min());
            binding.tempMax.setText(object.getTemp_max());

            Glide.with(this).load(object.getIcon_url())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .placeholder(R.color.transparent)
                    .error(R.color.transparent)
                    .into(binding.imgIcon);

            binding.weather.setText(object.getMain());

        } finally {

        }

    }

    @Override
    public void onForecastWeatherCallback(boolean isSuccess, WeatherForecastRepo result) {

        if(isSuccess) {
            if(Util.NVLString(result.cod)) {
                setForecastWeather(result);
            }
        }
    }

    private void setForecastWeather(WeatherForecastRepo repo) {

        try {
            WeatherItem item = new WeatherItem(context);
            ArrayList<WeatherItem> items = item.getForecasttem(repo);
            recyclerAdapter.addAll(items);
        } finally {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
