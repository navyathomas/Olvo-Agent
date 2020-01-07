package com.example.root.olvoagent.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by root on 18/11/19.
 */

public class DateFragment extends DialogFragment {


    DatePickerDialog.OnDateSetListener dateToSetListener;
    DatePickerDialog.OnDateSetListener dateFromListener;
    private int year, month, day;
    private int fromYear,fromMonth,fromDay;
    private static final  int Date_Picker_To=0;
    private static  final int Date_Picker_From=1;



    public DateFragment() {

    }


    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        dateToSetListener = ondate;

    }

    public void setCallBackFrom(DatePickerDialog.OnDateSetListener fromDate){
        dateFromListener=fromDate;
    }

    public void setArguments(Bundle arg) {

        super.setArguments(arg);
        year = arg.getInt("year");
        month = arg.getInt("month");
        day = arg.getInt("day");

    }


    public void setArgumentsFrom(Bundle args){

        super.setArguments(args);

        fromYear=args.getInt("fromYear");
        fromMonth=args.getInt("fromMonth");
        fromDay=args.getInt("fromDay");
    }



    protected Dialog onCreateDialog(int id){
        switch (id){
            case Date_Picker_To:
                return new DatePickerDialog(getActivity(), dateToSetListener, year, month, day);

            case Date_Picker_From:
                return new DatePickerDialog(getActivity(), dateFromListener, fromYear, fromMonth, fromDay);
        }
        return null;
    }


}
