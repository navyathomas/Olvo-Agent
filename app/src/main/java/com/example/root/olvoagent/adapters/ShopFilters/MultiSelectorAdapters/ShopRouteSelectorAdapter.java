package com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;

import java.util.List;

/**
 * Created by root on 12/11/19.
 */

public class ShopRouteSelectorAdapter extends RecyclerView.Adapter<ShopRouteSelectorAdapter.SelectorViewHolder> {
    private LayoutInflater layoutInflater;
    private List<DataArrayModel> routeSelectorData;
    private Context context;
    private int selectedPos = 0;
    private final OnItemClickListener onItemClickListener;


 public interface OnItemClickListener{
     void onItemClick(DataArrayModel dataArrayModel);
 }


    public ShopRouteSelectorAdapter(Context mcontext, List<DataArrayModel> routeSelectorData,OnItemClickListener onItemClickListener) {
        this.context = mcontext;
        this.routeSelectorData = routeSelectorData;
        this.onItemClickListener=onItemClickListener;
    }


    @Override
    public ShopRouteSelectorAdapter.SelectorViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View routeView = layoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.selector_route_list, viewGroup, false);

        return new ShopRouteSelectorAdapter.SelectorViewHolder(routeView) ;

    }

    @Override
    public void onBindViewHolder(ShopRouteSelectorAdapter.SelectorViewHolder viewHolder, final int i) {
         DataArrayModel routeSelectorDataModel = routeSelectorData.get(i);

        viewHolder.textSelectorRoute.setText(routeSelectorDataModel.getRouteName());

        viewHolder.onBind(routeSelectorData.get(i),onItemClickListener);


//        viewHolder.textSelectorRoute.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"you clicked" +i,Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return routeSelectorData.size();
    }


    public class SelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textSelectorRoute;

        public SelectorViewHolder(View itemView) {
            super(itemView);
            textSelectorRoute = itemView.findViewById(R.id.tv_route_selector_name);
        }


        public void onBind(final DataArrayModel dataArrayModel ,final OnItemClickListener onItemClickListener1){
            textSelectorRoute.setText(dataArrayModel.getRouteName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener1.onItemClick(dataArrayModel);
                }
            });
        }
        @Override
        public void onClick(View view) {
            notifyDataSetChanged();
           // int selectedPosition=getAdapterPosition();
        }
    }
}
