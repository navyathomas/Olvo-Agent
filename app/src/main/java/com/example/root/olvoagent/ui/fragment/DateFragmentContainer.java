package com.example.root.olvoagent.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.root.olvoagent.R;

import java.util.Calendar;

/**
 * Created by root on 4/10/19.
 */

public class DateFragmentContainer extends Fragment {

    private TextView dateFrom, dateTo;
    private int mYear, mMonth, mDay;

    public DateFragmentContainer() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_fragment_container_list, container, false);

        dateTo = view.findViewById(R.id.txt_date_to);
        dateFrom = view.findViewById(R.id.txt_date_from);
        //((RechargeHistory)getActivity()).callRechargeApi();    //calling a method in RechargeHistory into this fragment..//
        return view;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();

            }
        });


        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog();
            }
        });

    }

    private void DatePicker() {

        DateFragment dateFragment = new DateFragment();

        Calendar calendar = Calendar.getInstance();
        Bundle arg = new Bundle();

        arg.putInt("year", calendar.get(Calendar.YEAR));
        arg.putInt("month", calendar.get(Calendar.MONTH));
        arg.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));

        dateFragment.setArguments(arg);


        dateFragment.setCallBack(onDateSetListener);
        dateFragment.show(getFragmentManager(), null);
    }



    private void datePickerDialog(){


        DateFragment dateFragmentFrom=new DateFragment();

        Calendar calendar2=Calendar.getInstance();
        Bundle args=new Bundle();

        args.putInt("fromYear",calendar2.get(Calendar.YEAR));
        args.putInt("fromMonth",calendar2.get(Calendar.MONTH));
        args.putInt("fromDay",calendar2.get(Calendar.DAY_OF_MONTH));

        dateFragmentFrom.setArgumentsFrom(args);

        dateFragmentFrom.setCallBackFrom(onDateFromListener);
        dateFragmentFrom.show(getFragmentManager(),null);
    }





    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker,final int year, final int month,final int day) {


                    dateTo.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));

           // dateFrom.setText(String.valueOf(day)+"/"+String.valueOf(month+1)+"/" +String.valueOf(year));
        }
    };

    DatePickerDialog.OnDateSetListener onDateFromListener=new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker dateFromPicker,int fromYear,int fromMonth,int fromDay){

            dateFrom.setText(String.valueOf(fromDay)+ "/"+ String.valueOf(fromMonth+1) + "/" + String.valueOf(fromYear));
        }

    };


//    public void datePicker(){
//        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHttpClient=new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request=chain.request();
//                Request.Builder builder=request.newBuilder();
//
//                Request request1=builder.build();
//                return chain.proceed(request1);
//            }
//        }).addInterceptor(httpLoggingInterceptor).build();
//
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(ApiService.BaseUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//        ApiService apiService=retrofit.create(ApiService.class);
//        Call<> call=apiService.getProfile();
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onResponse(Call call, retrofit2.Response response) {
//
//
//                if (response.isSuccessful()){
//
//
//
//
//                }
//
//                else {
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }

}


