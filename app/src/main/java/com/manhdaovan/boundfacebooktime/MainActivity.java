package com.manhdaovan.boundfacebooktime;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.manhdaovan.boundfacebooktime.utils.Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CountDownTimer countDownTimer;
    private long remainTime;
    private Intent intent;
    private int backBtnPressCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        remainTime = 30 * 1000;
        intent = new Intent(this, OpeningWebPageActivity.class);
        backBtnPressCounter = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetBackBtnCounter();
//        settingCountDownTimer();
    }

    @Override
    public void onBackPressed() {
        backBtnPressCounter += 1;

        if (backBtnPressCounter == 1){
            Toast.makeText(this, "Press back button "+ (Constants.MAX_BACK_BTN_PRESS_TO_EXIT - 1) +" times again to exit app", Toast.LENGTH_SHORT).show();
        }

        if (backBtnPressCounter == Constants.MAX_BACK_BTN_PRESS_TO_EXIT){
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
//        else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, AppSettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String url = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            url = "https://google.com";
        } else if (id == R.id.nav_gallery) {
            url = "https://facebook.com";
        } else if (id == R.id.nav_slideshow) {
            url = "https://twitter.com";
        } else if (id == R.id.nav_manage) {
            url = "https://www.instagram.com";
        } else if (id == R.id.nav_share) {
            url = "https://www.reddit.com/";
        } else if (id == R.id.nav_send) {
            url = "https://news.ycombinator.com/";
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if(url != null){
            intent.putExtra(Constants.INTENT_URL, url);
            startActivity(intent);
        }

        return true;
    }

    private void settingCountDownTimer(){
        Log.e("MMM", "IM IN settingCountDownTimer: " + (countDownTimer == null) + "|" + remainTime);

        if (countDownTimer != null){
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(remainTime, 1000) {
            @Override
            public void onTick(long l) {
                Log.e("KKK", "At: " + l);
                remainTime -= 1000;
            }

            @Override
            public void onFinish() {
                finish();
            }
        };

        countDownTimer.start();
    }

    private void resetBackBtnCounter(){
        backBtnPressCounter = 0;
    }
}
