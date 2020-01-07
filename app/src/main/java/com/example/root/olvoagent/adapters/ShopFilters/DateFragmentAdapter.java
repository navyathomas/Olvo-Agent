package com.example.root.olvoagent.adapters.ShopFilters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.olvoagent.R;

/**
 * Created by root on 18/11/19.
 */

public class DateFragmentAdapter extends RecyclerView.Adapter<DateFragmentAdapter.DateViewHolder> {

    private LayoutInflater dateLayoutInflater;



    @Override
    public DateFragmentAdapter.DateViewHolder onCreateViewHolder(ViewGroup viewGroup,int position){
       View view=dateLayoutInflater.inflate(R.layout.date_fragment_container_list,viewGroup,false);
        return new DateFragmentAdapter.DateViewHolder(view);

    }

    @Override
    public void onBindViewHolder(DateFragmentAdapter.DateViewHolder viewHolder,int position){

    }

    @Override
    public int getItemCount(){
        return 0;
    }




    public class DateViewHolder extends RecyclerView.ViewHolder{
        public DateViewHolder(View view){
            super(view);
        }
    }

}
