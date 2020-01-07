package com.example.root.olvoagent.ui.invoice;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.ui.fragment.DateFragmentContainer;
import com.example.root.olvoagent.ui.fragment.ShopFilterFragment;

/**
 * Created by root on 18/11/19.
 */

public class InvoiceFilterFragment extends AppCompatActivity {
    private ImageView imageBack;
    private static FragmentManager fragmentManager;
    private RelativeLayout relativeShopFragment, relativeDateFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_filter_fragment);


        imageBack = findViewById(R.id.img_qick_rechrge_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageFilterPageBack = new Intent(getApplicationContext(), Invoice.class);
                startActivity(imageFilterPageBack);
                finish();
            }
        });

        fragmentManager = getFragmentManager();

        if (findViewById(R.id.frame_container_invoice) != null) {

            if (savedInstanceState != null) {
                return;
            }

            relativeShopFragment = findViewById(R.id.rel_shop_fragment);
            relativeDateFragment = findViewById(R.id.rel_date_fragment);

            relativeShopFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ShopFilterFragment shopFilterFragment = new ShopFilterFragment();
                    fragmentTransaction.replace(R.id.frame_container_invoice, shopFilterFragment, null).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            relativeDateFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    DateFragmentContainer dateFragmentContainer = new DateFragmentContainer();
                    fragmentTransaction.replace(R.id.frame_container_invoice, dateFragmentContainer, null).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });


            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ShopFilterFragment shopFilterFragment = new ShopFilterFragment();
            fragmentTransaction.replace(R.id.frame_container_invoice, shopFilterFragment, null).addToBackStack(null);
            fragmentTransaction.commit();


        }
    }


//    public void onResume(){
//        super.onResume();
//        Intent backToMenu=new Intent(getApplicationContext(), Invoice.class);
//        startActivity(backToMenu);
//        finish();
//    }
}
