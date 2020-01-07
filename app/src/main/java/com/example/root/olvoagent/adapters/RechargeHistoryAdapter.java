package com.example.root.olvoagent.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.RechargeHistory.HistoryModel;

import java.util.List;

/**
 * Created by root on 30/9/19.
 */

public class RechargeHistoryAdapter extends RecyclerView.Adapter<RechargeHistoryAdapter.ViewHolder> {
    public Context mcontext;
    public List<HistoryModel> historyModels;



    public RechargeHistoryAdapter(Context mcontext,List<HistoryModel> historyModels){
        this.mcontext=mcontext;
        this.historyModels=historyModels;
    }

    @Override
    public RechargeHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_hist_item_list,parent,false);
        return new RechargeHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HistoryModel historyModel=historyModels.get(position);

        holder.text_shopName.setText(historyModel.getShopName());
        holder.text_currencyIso1.setText(historyModel.getTopupName());
        holder.text_transcation_no.setText(historyModel.getTranscationNumber());

        holder.text_expiryDate.setText(historyModel.getDate());
        holder.text_currencyIso2.setText(historyModel.getTopupName());
        //holder.text_creditCurrencyIso.setText(""+historyModel.getProductCurrency());


    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_shopName,text_currencyIso1,text_transcation_no,text_expiryDate,text_currencyIso2,text_creditCurrencyIso;

        public ViewHolder(View itemView) {
            super(itemView);
            text_shopName=itemView.findViewById(R.id.tv_shop_name);
            text_currencyIso1=itemView.findViewById(R.id.tv_currency_iso);
            text_transcation_no=itemView.findViewById(R.id.tv_transcation_no);
            text_expiryDate=itemView.findViewById(R.id.tv_expiry_date);
            text_currencyIso2=itemView.findViewById(R.id.tv_currency_iso_credits);
            text_creditCurrencyIso=itemView.findViewById(R.id.tv_credits_currency);

        }
    }
}
