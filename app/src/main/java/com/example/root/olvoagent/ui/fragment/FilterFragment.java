package com.example.root.olvoagent.ui.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.ui.shop_summary.RechargeHistory;

/**
 * Created by root on 4/10/19.
 */

public class FilterFragment extends AppCompatActivity {

    public static android.app.FragmentManager fragmentManager;
    //private RelativeLayout shopFragment, dateFragment;
    private ImageView imageQuickRechrgeBack;
    private TextView textShopTitle;
    private RelativeLayout relativeButtonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_history_filter);


        relativeButtonOk=findViewById(R.id.rel_button_ok);
        relativeButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirectToRechargeHistory=new Intent(getApplicationContext(),RechargeHistory.class);
                startActivity(redirectToRechargeHistory);
            }
        });



        imageQuickRechrgeBack = findViewById(R.id.img_qick_rechrge_back);
        imageQuickRechrgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageFilterPageBack = new Intent(FilterFragment.this, RechargeHistory.class);
                startActivity(imageFilterPageBack);
            }
        });


        fragmentManager = getFragmentManager();

        if (findViewById(R.id.frame_container) != null) {

            if (savedInstanceState != null) {
                return;
            }


            /**when Shop Filter is clicked..**/
//            shopFragment = findViewById(R.id.rel_shop_fragment);
//            dateFragment = findViewById(R.id.rel_date_fragment);

//            shopFragment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    shopFragment.setBackgroundColor(Color.parseColor("#FD5373"));
////                    textShopTitle.setTextColor(Color.parseColor("#FFFFFF"));
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    ShopFilterFragment shopFilterFragment = new ShopFilterFragment();
//                    fragmentTransaction.replace(R.id.frame_container, shopFilterFragment, null);
//                    fragmentTransaction.commit();
//
//                }
//            });
//
//
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DateFragmentContainer dateFragmentContainer = new DateFragmentContainer();
            fragmentTransaction.replace(R.id.frame_container, dateFragmentContainer, null);
            fragmentTransaction.commit();



            /**when Date Filter is clicked..**/
//            dateFragment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    DateFragmentContainer dateFragmentContainer = new DateFragmentContainer();
//                    fragmentTransaction.replace(R.id.frame_container, dateFragmentContainer, null);
//                    fragmentTransaction.commit();
//                }
//            });
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toMenuActivity = new Intent(this, RechargeHistory.class);
        startActivity(toMenuActivity);
        finish();
    }
}
