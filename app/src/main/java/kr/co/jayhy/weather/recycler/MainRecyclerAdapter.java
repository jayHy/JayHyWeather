package kr.co.jayhy.weather.recycler;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import kr.co.jayhy.weather.R;
import kr.co.jayhy.weather.databinding.ItemForecastRecyclerBinding;
import kr.co.jayhy.weather.item.WeatherItem;

/**
 * Created by jhkim on 2017-08-06.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private Context context;
    private ItemForecastRecyclerBinding binding;
    private ArrayList<WeatherItem> items;

    private final static String TAG = "jayHy";

    public MainRecyclerAdapter(Context context, ArrayList<WeatherItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        binding.temp.setText(items.get(position).getTemp());

        Glide.with(context).load(items.get(position).getIcon_url())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .fitCenter()
                .placeholder(R.color.transparent)
                .error(R.color.transparent)
                .into(binding.icon);

        Log.d(TAG, "dt ==> " + items.get(position).getDt_date());
        binding.iconName.setText(items.get(position).getMain());
        binding.date.setText(items.get(position).getDt_date());
        binding.time.setText(items.get(position).getDt_time());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(ArrayList<WeatherItem> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            binding =  DataBindingUtil.bind(itemView);

        }
    }
}
