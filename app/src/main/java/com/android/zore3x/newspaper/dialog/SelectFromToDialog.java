package com.android.zore3x.newspaper.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.activity.EverythingActivity;
import com.android.zore3x.newspaper.model.api.Endpoints.Everything;

import java.util.Calendar;
import java.util.Date;

public class SelectFromToDialog extends DialogFragment {

    private static final String DIALOG_DATE_FROM = "DialogDateFrom";
    private static final String DIALOG_DATE_TO = "DialogDateTo";

    private static final int REQUEST_DATE_FROM = 0;
    private static final int REQUEST_DATE_TO = 1;

    public static final String EXTRA_DATE_FROM = "ExtraDateFrom";
    public static final String EXTRA_DATE_TO = "ExtraDateTo";

    private Button mButtonSelectDateFrom;
    private Button mButtonSelectDateTo;


    Date mDateFrom;
    Date mDateTo;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter_from_to, null);

        mButtonSelectDateFrom = view.findViewById(R.id.date_from_button);
        mButtonSelectDateTo = view.findViewById(R.id.date_to_button);
        mButtonSelectDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment datePickerFrom = DatePickerFragment.newInstance(new Date());
                datePickerFrom.setTargetFragment(SelectFromToDialog.this, REQUEST_DATE_FROM);
                datePickerFrom.show(fm, DIALOG_DATE_FROM);
            }
        });

        mButtonSelectDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment datePickerTo = DatePickerFragment.newInstance(new Date());
                datePickerTo.setTargetFragment(SelectFromToDialog.this, REQUEST_DATE_TO);
                datePickerTo.show(fm, DIALOG_DATE_TO);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select date")
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_DATE_FROM, mDateFrom.getTime());
                        intent.putExtra(EXTRA_DATE_TO, mDateTo.getTime());

                        ((EverythingActivity)getActivity()).onActivityResult(1, Activity.RESULT_OK, intent);
                    }
                });

        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE_FROM) {
            int year = data.getIntExtra(DatePickerFragment.EXTRA_YEAR, -1);
            int month = data.getIntExtra(DatePickerFragment.EXTRA_MONTH, -1);
            int day = data.getIntExtra(DatePickerFragment.EXTRA_DAY, -1);

            Calendar calendarFrom = Calendar.getInstance();
            calendarFrom.set(Calendar.MONTH, month);
            calendarFrom.set(Calendar.YEAR, year);
            calendarFrom.set(Calendar.DAY_OF_MONTH, day);

            mDateFrom = calendarFrom.getTime();

            mButtonSelectDateFrom.setText(DateFormat.format("dd.MM.yy", mDateFrom));
        }
        if (requestCode == REQUEST_DATE_TO) {
            int year = data.getIntExtra(DatePickerFragment.EXTRA_YEAR, -1);
            int month = data.getIntExtra(DatePickerFragment.EXTRA_MONTH, -1);
            int day = data.getIntExtra(DatePickerFragment.EXTRA_DAY, -1);

            Calendar calendarTo = Calendar.getInstance();
            calendarTo.set(Calendar.MONTH, month);
            calendarTo.set(Calendar.YEAR, year);
            calendarTo.set(Calendar.DAY_OF_MONTH, day);

            mDateTo = calendarTo.getTime();

            mButtonSelectDateTo.setText(DateFormat.format("dd.MM.yy", mDateTo));
        }
    }
}
