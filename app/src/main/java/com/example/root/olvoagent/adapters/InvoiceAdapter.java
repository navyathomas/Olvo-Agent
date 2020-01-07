package com.example.root.olvoagent.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.InvoiceHistory.PaymentList;

import java.util.List;

/**
 * Created by root on 18/11/19.
 */

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InnvoiceViewHolder> {

    private LayoutInflater invoiceInflater;
    private Context mcontext;
    private List<PaymentList> paymentLists;


    public InvoiceAdapter(Context context,List<PaymentList> paymentList){
        this.mcontext=context;
        this.paymentLists=paymentList;
    }



    @Override
     public InvoiceAdapter.InnvoiceViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View view=invoiceInflater.inflate(R.layout.invoice_item_list,viewGroup,false);
        return new InvoiceAdapter.InnvoiceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(InvoiceAdapter.InnvoiceViewHolder innvoiceViewHolder,int position){
        PaymentList paymentList=paymentLists.get(position);


    }

    @Override
    public int getItemCount(){
        return paymentLists.size();
    }




    public class InnvoiceViewHolder extends RecyclerView.ViewHolder{


        public InnvoiceViewHolder(View view){
            super(view);
        }
    }
}
