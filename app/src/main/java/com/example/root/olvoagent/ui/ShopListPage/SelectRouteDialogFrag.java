package com.example.root.olvoagent.ui.ShopListPage;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.root.olvoagent.R;

/**
 * Created by root on 6/11/19.
 */

public class SelectRouteDialogFrag extends DialogFragment {
    private RecyclerView selectRoute;


    public SelectRouteDialogFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.activity_select_route, container, false);


        SelectShopDialog.getInstance().callshopId();

//        SelectShopDialog selectRouteDialogFrag = (SelectShopDialog) getTargetFragment();
//        if (selectRouteDialogFrag != null) {
//            selectRouteDialogFrag.callshopId();
//        }

//        ((SelectShopDialog)getParentFragment()).callshopId();


        return view;
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(450, 400);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}
