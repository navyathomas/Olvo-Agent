package com.example.root.olvoagent.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ShopApiModel.ShopListModel;
import com.example.root.olvoagent.ui.shop_summary.RechargeHistory;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by root on 18/9/19.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {


    public Context mcontext;
    public List<ShopListModel> shopListModell;


    public ShopListAdapter(Context mcontext, List<ShopListModel> shopListModell) {
        this.mcontext = mcontext;
        this.shopListModell = shopListModell;
    }


    @Override
    public ShopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new ShopListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopListAdapter.ViewHolder holder, int position) {

        ShopListModel shopListModel = shopListModell.get(position);


        /**Currency_Iso is getting from Profile Api**/
        SharedPreferences preferences1 = mcontext.getSharedPreferences("MyPreff", MODE_PRIVATE);
        final String currencyIso = preferences1.getString("currency_iso", "");


        holder.textViewShop.setText(shopListModel.getShopName());
        holder.textMobile.setText(shopListModel.getMobile_number());
        holder.textShopStatus.setText("" + shopListModel.getShop_status());

        String shop = "" + shopListModel.getShop_status();
//        /**ShopStatus is integer.Convert into string("" +)*/

        /** (Ternary Operator) To show shopStatus State..(If shopStatus=1 then show active) otherwise pending..**/
        String name = shop.contains("1") ? "active" : "pending";

        holder.textShopStatus.setText(name);

        /**Decimal wrapping..**/
        holder.textCreditBalance.setText(String.format("%.2f", shopListModel.getShopCredit()));
//        /**Taking only two places after decimal point..**/

        holder.textInr1.setText(currencyIso);
        holder.textWalletBalance.setText(String.format("%.2f", shopListModel.getWalletBalance()));
        holder.textInr2.setText(currencyIso);


        /**show hide content functionality**/

        holder.reltShopHiderPicker.setVisibility(View.GONE);

        holder.shopArrowHideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.reltShopHiderPicker.getVisibility() == View.GONE) {
                    holder.shopArrowHideImage.setRotation(270);
                    holder.reltShopHiderPicker.setVisibility(View.VISIBLE);
                } else {
                    holder.shopArrowHideImage.setRotation(360);
                    holder.reltShopHiderPicker.setVisibility(View.GONE);
                }
            }
        });


        /** ..Show hide content functionality..**/


        holder.shopListHiderEmail.setText(shopListModel.getShopEmail());
        holder.shopListHiderCountry.setText(shopListModel.getCountryName());
        holder.shopListRegion.setText(shopListModel.getRegion_name());
        holder.shopListHiderRoute.setText(shopListModel.getRoute_name());

        holder.shopListHiderRechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRechargeActivity = new Intent(mcontext, RechargeHistory.class);
                toRechargeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(toRechargeActivity);
            }
        });

//        holder.googleMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return shopListModell.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewShop, textMobile, textShopStatus, textCreditBalance, textInr1, textWalletBalance, textInr2;

        RelativeLayout reltShopHiderPicker;
        TextView shopListHiderEmail, shopListHiderCountry, shopListRegion, shopListHiderRoute;
        ImageView shopArrowHideImage;
        Button shopListHiderRechargeButton, googleMap;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewShop = itemView.findViewById(R.id.name);
            textMobile = itemView.findViewById(R.id.mobile);
            textShopStatus = itemView.findViewById(R.id.shop_status);
            textCreditBalance = itemView.findViewById(R.id.credit_balance);
            textInr1 = itemView.findViewById(R.id.Inr1);
            textWalletBalance = itemView.findViewById(R.id.wallet_balance);
            textInr2 = itemView.findViewById(R.id.Inr2);
            shopArrowHideImage = itemView.findViewById(R.id.shop_arrow_hide);

            reltShopHiderPicker = itemView.findViewById(R.id.relt_shopData_hide);

            shopListHiderEmail = itemView.findViewById(R.id.tv_selct_route_email_set);
            shopListHiderCountry = itemView.findViewById(R.id.tv_selct_route_country_set);
            shopListRegion = itemView.findViewById(R.id.tv_selct_route_region_set);
            shopListHiderRoute = itemView.findViewById(R.id.tv_selct_route_route_set);
            shopListHiderRechargeButton = itemView.findViewById(R.id.btn_selct_recharge);
            googleMap = itemView.findViewById(R.id.btn_shopList_map);

        }
    }
}