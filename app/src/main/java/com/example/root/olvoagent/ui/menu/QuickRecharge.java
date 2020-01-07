package com.example.root.olvoagent.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.root.olvoagent.R;

/**
 * Created by root on 18/9/19.
 */

public class QuickRecharge extends AppCompatActivity {

    ImageView imgQickRechrgeBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        imgQickRechrgeBack = findViewById(R.id.img_qick_rechrge_back);
        imgQickRechrgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quickRecharge = new Intent(QuickRecharge.this, MenuPage.class);
                quickRecharge.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                quickRecharge.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(quickRecharge);
                finish();
            }
        });

    }
}
