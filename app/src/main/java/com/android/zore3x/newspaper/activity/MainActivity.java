package com.android.zore3x.newspaper.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.zore3x.newspaper.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
//                startActivity(EverythingFragment.newInstance(view.getContext()));
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

    private void printKeyHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.android.zore3x.newspaper", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
