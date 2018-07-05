package com.android.zore3x.newspaper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.zore3x.newspaper.R;

public class EverythingActivity extends AppCompatActivity {

    public static Intent newInstance(Context context) {
        return new Intent(context, EverythingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everything);
    }
}
