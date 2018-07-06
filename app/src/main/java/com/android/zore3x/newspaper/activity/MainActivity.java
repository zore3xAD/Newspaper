package com.android.zore3x.newspaper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.zore3x.newspaper.R;

public class MainActivity extends AppCompatActivity {

    private Button mTopHeadlinesButton;
    private Button mEverythingButton;
    private Button mSelectSourceButton;
    private Button mFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopHeadlinesButton = findViewById(R.id.top_headlines_button);
        mTopHeadlinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(TopHeadlinesActivity.newInstance(view.getContext()));
            }
        });
        mEverythingButton = findViewById(R.id.everything_button);
        mEverythingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EverythingActivity.newInstance(view.getContext()));
            }
        });
        mSelectSourceButton = findViewById(R.id.select_source_button);
        mSelectSourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mFavoriteButton = findViewById(R.id.favrite_button);
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FavoriteActivity.newInstance(view.getContext()));
            }
        });
    }
}
