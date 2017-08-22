package kr.co.jayhy.weather.recycler

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import java.util.ArrayList

import kr.co.jayhy.weather.R
import kr.co.jayhy.weather.databinding.ItemForecastRecyclerBinding
import kr.co.jayhy.weather.item.WeatherItem

/**
 * Created by jhkim on 2017-08-06.
 */

class MainRecyclerAdapter(private val context: Context, private val items: ArrayList<WeatherItem>) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    private var binding: ItemForecastRecyclerBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_recycler, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding!!.temp.text = items[position].temp

        Glide.with(context).load(items[position].icon_url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .fitCenter()
                .placeholder(R.color.transparent)
                .error(R.color.transparent)
                .into(binding!!.icon)

        binding!!.iconName.text = items[position].main
        binding!!.date.text = items[position].dt_date
        binding!!.time.text = items[position].dt_time

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(list: ArrayList<WeatherItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            binding = DataBindingUtil.bind<ItemForecastRecyclerBinding>(itemView)
        }
    }

    companion object {

        private val TAG = "jayHy"
    }
}
