package com.manhdaovan.boundfacebooktime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.manhdaovan.boundfacebooktime.utils.Constants;
import com.manhdaovan.boundfacebooktime.utils.CustomTabsHelper;

import java.util.HashMap;

public class OpeningWebPageActivity extends AppCompatActivity {
    private String webPageUrl;

    private CustomTabsIntent customTabsIntent;
    private Bitmap backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customTabsIntent = null;
        backButton  = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(customTabsIntent == null){
            String packageName = CustomTabsHelper.getPackageNameToUse(this);
            Log.e("VKL", "dmm + :" + packageName);

            Intent intent = getIntent();
            final Intent intent2 = new Intent(this, MainActivity.class);
            webPageUrl = intent.getStringExtra(Constants.INTENT_URL);

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(Color.RED);
            builder.setSecondaryToolbarColor(Color.GREEN);
            builder.setCloseButtonIcon(backButton);

            customTabsIntent = builder.build();
            Log.e("DMMM", "launchUrl");
            customTabsIntent.launchUrl(this, Uri.parse(webPageUrl));
            Log.e("DMMM", "out");
            Toast.makeText(this, "Out of 30", Toast.LENGTH_LONG).show();
            Log.e("DMMM", "finish");
            new CountDownTimer(10000, 1000){
                @Override
                public void onFinish(){
                    startActivity(intent2);
                }
                public void onTick(long t){

                }
            }.start();
        }else{
            finish();
        }
    }
}
