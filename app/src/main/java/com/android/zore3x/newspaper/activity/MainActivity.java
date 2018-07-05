package com.android.zore3x.newspaper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.zore3x.newspaper.R;

public class MainActivity extends AppCompatActivity {

    private Button mTopHeadlinesButton;

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
    }
}
