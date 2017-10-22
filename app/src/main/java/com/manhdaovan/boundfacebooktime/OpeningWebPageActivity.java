package com.manhdaovan.boundfacebooktime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
            webPageUrl = intent.getStringExtra(Constants.INTENT_URL);

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(Color.RED);
            builder.setSecondaryToolbarColor(Color.GREEN);
            builder.setCloseButtonIcon(backButton);

            customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(webPageUrl));
        }else{
            finish();
        }
    }
}
