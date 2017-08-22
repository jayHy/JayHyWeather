package kr.co.jayhy.weather

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import java.util.ArrayList

import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.jayhy.weather.common.CommonValues
import kr.co.jayhy.weather.common.GeoCodingService
import kr.co.jayhy.weather.common.GpsLocationInfo
import kr.co.jayhy.weather.databinding.ActivityMainBinding
import kr.co.jayhy.weather.item.WeatherItem
import kr.co.jayhy.weather.listener.ForecastWeatherCallback
import kr.co.jayhy.weather.listener.MyWeatherCallback
import kr.co.jayhy.weather.realm.RealmManager
import kr.co.jayhy.weather.realm.`object`.WeatherObject
import kr.co.jayhy.weather.recycler.MainRecyclerAdapter
import kr.co.jayhy.weather.repo.WeatherCurrentRepo
import kr.co.jayhy.weather.repo.WeatherForecastRepo
import kr.co.jayhy.weather.thread.WeatherCurrentThread
import kr.co.jayhy.weather.thread.WeatherForecastThread
import kr.co.jayhy.weather.util.Util
import kr.co.jayhy.weather.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), MyWeatherCallback, ForecastWeatherCallback {

    private var context: Context? = null

//    private val model = MainViewModel(this)

    private var binding: ActivityMainBinding? = null

    private var recyclerAdapter: MainRecyclerAdapter? = null
    private var weatherItems: ArrayList<WeatherItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        binding!!.setModel(model)

        setSupportActionBar(binding!!.toolbar)

        context = this

//        RealmManager.open()

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding!!.foreRecycler.layoutManager = layoutManager

        weatherItems = ArrayList<WeatherItem>()
        recyclerAdapter = MainRecyclerAdapter(this, weatherItems!!)
        binding!!.foreRecycler.adapter = recyclerAdapter

//        checkCurrnetData()

        getMyWeather()

    }

    private fun checkCurrnetData() {
        if (RealmManager.instance_WeatherDao.current_count() > 0) {
            val dataList = RealmManager.instance_WeatherDao.current_loadAll()
            setWeatherStatus(dataList[0])
        }
    }

    private fun getMyWeather() {
        val weatherCurrentThread: WeatherCurrentThread
        val weatherForecastThread: WeatherForecastThread
        try {
            if (CommonValues.myLatidude != 0.0 && CommonValues.myLongitude != 0.0) {

                weatherCurrentThread = WeatherCurrentThread(context!!, CommonValues.myLatidude, CommonValues.myLongitude, this)
                weatherCurrentThread.start()

                weatherForecastThread = WeatherForecastThread(this, CommonValues.myLatidude, CommonValues.myLongitude, this)
                weatherForecastThread.start()

            } else {
                val locationInfo = GpsLocationInfo(this)
                if (locationInfo.isGetLocation) {
                    CommonValues.myLatidude = locationInfo.latitude
                    CommonValues.myLongitude = locationInfo.longitude

                    weatherCurrentThread = WeatherCurrentThread(context!!, CommonValues.myLatidude, CommonValues.myLongitude, this)
                    weatherCurrentThread.start()

                    weatherForecastThread = WeatherForecastThread(this, CommonValues.myLatidude, CommonValues.myLongitude, this)
                    weatherForecastThread.start()

                }
            }
        } finally {

        }

    }

    override fun onMyWeatherComplate(isSuccess: Boolean, result: WeatherCurrentRepo) {
        if (isSuccess) {
            if (Util.NVLString(result.cod)) {

                setWeatherStatus(result)
                //
                myLoactionAddr(java.lang.Double.parseDouble(result.coord!!.lat), java.lang.Double.parseDouble(result.coord!!.lon))

            }
        }
    }

    private fun myLoactionAddr(lat: Double, lon: Double) {
        val geoCodingService: GeoCodingService
        try {
            geoCodingService = GeoCodingService(this)
            val result = geoCodingService.getLocationToAddress(lat, lon)
            if (result != null) {
                binding!!.toolbar.title = result
            }
        } finally {

        }
    }

    private fun setWeatherStatus(repo: WeatherCurrentRepo) {

        try {
            var item = WeatherItem(context!!)
            item = item.getCurrentItem(repo)

            val `object` = WeatherObject()
            `object`.dt = item.dt
            `object`.temp = item.temp
            `object`.temp_min = item.temp_min
            `object`.temp_max = item.temp_max
            `object`.icon_url = item.icon_url
            `object`.main = item.main
            `object`.dt = item.dt

           current_time.text = item.dt
            binding!!.temp.text = item.temp
            binding!!.tempMin.text = item.temp_min
            binding!!.tempMax.text = item.temp_max

            Glide.with(this).load(item.icon_url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .placeholder(R.color.transparent)
                    .error(R.color.transparent)
                    .into(binding!!.imgIcon)

            binding!!.weather.text = item.main

//            if (RealmManager.instance_WeatherDao.current_count() > 0) {
//                RealmManager.instance_WeatherDao.current_remove(WeatherObject())
//            }

//            RealmManager.instance_WeatherDao.current_save(`object`)

        } finally {

        }

    }

    private fun setWeatherStatus(`object`: WeatherObject) {
        Log.d(TAG, "setWeatherStatus ==> " + `object`)

        try {
            binding!!.currentTime.text = `object`.dt
            binding!!.temp.text = `object`.temp
            binding!!.tempMin.text = `object`.temp_min
            binding!!.tempMax.text = `object`.temp_max

            Glide.with(this).load(`object`.icon_url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .placeholder(R.color.transparent)
                    .error(R.color.transparent)
                    .into(binding!!.imgIcon)

            binding!!.weather.text = `object`.main

        } finally {

        }

    }

    override fun onForecastWeatherCallback(isSuccess: Boolean, result: WeatherForecastRepo) {

        if (isSuccess) {
            if (Util.NVLString(result.cod)) {
                setForecastWeather(result)
            }
        }
    }

    private fun setForecastWeather(repo: WeatherForecastRepo) {

        try {
            val item = WeatherItem(context!!)
            val items = item.getForecasttem(repo)
            recyclerAdapter!!.addAll(items)
        } finally {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val TAG = "jayHy"
    }

}
