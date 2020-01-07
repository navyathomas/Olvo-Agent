package com.example.root.olvoagent.ui.shop_summary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.root.olvoagent.R;

/**
 * Created by root on 3/10/19.
 */

public class RechargeFilter extends AppCompatActivity {

    private LinearLayout filterBack;
    private RelativeLayout relativeShopFragment;
    private RelativeLayout addFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_history_filter);

        filterBack = findViewById(R.id.linr_back_arrow);
        //relativeShopFragment = findViewById(R.id.rel_shop_fragment);

//        relativeShopFragment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent fragment = new Intent(RechargeFilter.this, FilterFragment.class);
//                startActivity(fragment);
//            }
//        });


        addFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Filter", MODE_PRIVATE);
                String shopIdFilter = sharedPreferences.getString("shop_id", "");
                Log.e("", shopIdFilter);

            }
        });


        filterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent filterBack = new Intent(RechargeFilter.this, RechargeHistory.class);
                startActivity(filterBack);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toRechargeActivity = new Intent(this, RechargeHistory.class);
        toRechargeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        toRechargeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        toRechargeActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toRechargeActivity);
        finish();
    }



}
