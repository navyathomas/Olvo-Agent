package com.example.root.olvoagent.adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.HomeAdModel.AdsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17/9/19.
 */

public class ViewFlipperPager extends PagerAdapter {
    Context mcontext;
    private List<AdsModel> adsModels = new ArrayList<>();

    public ViewFlipperPager(Context mcontext, List<AdsModel> adsModels) {
        this.mcontext = mcontext;
        this.adsModels = adsModels;
        // this.adsModels=adsModels1;
    }


    @Override
    public int getCount() {
        //return adsModels.size();
        return adsModels.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.ad_list_home_activity, null);

        ((ViewPager) container).addView(view);

        final ImageView imageView = (ImageView) view.findViewById(R.id.img_ad);


        AdsModel homeMainModel = adsModels.get(position);
        Picasso.with(mcontext)
                .load("" + ApiService.BaseUrl + "" + homeMainModel.getImage())
                .into(imageView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
