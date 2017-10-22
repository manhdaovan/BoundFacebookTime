package com.manhdaovan.boundfacebooktime;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpeningWebpage extends AppCompatActivity {

    private CustomTabsIntent customTabsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customTabsIntent = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(customTabsIntent == null){
            Intent intent = getIntent();
            String url = intent.getStringExtra("URL");

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(Color.RED);
            builder.setSecondaryToolbarColor(Color.GREEN);
            customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }else{
            finish();
        }
    }
}
