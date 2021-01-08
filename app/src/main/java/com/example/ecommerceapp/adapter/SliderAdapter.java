package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.TopBar;

import java.util.ArrayList;


class SliderAdapter extends LoopingPagerAdapter<TopBar> {


    private final ClickTopSlider clickTopSlider;

    SliderAdapter(Context context, ArrayList<TopBar> itemList, ClickTopSlider clickTopSlider) {
        super(context, itemList, false);
        this.clickTopSlider = clickTopSlider;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.item_slider_adapter_, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        ImageView imageView = convertView.findViewById(R.id.imageview);
        /* RequestOptions requestOption = new RequestOptions().placeholder(R.drawable.tvs_logo_s).centerInside();*/


        Glide.with(convertView.getContext()).load(itemList.get(listPosition).getImageLink())
                .into(imageView);

        imageView.setOnClickListener(v -> clickTopSlider.onClick(listPosition, itemList.get(listPosition).getSeriesID()));

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }


    public interface ClickTopSlider {
        void onClick(int position, int catID);
    }
}
