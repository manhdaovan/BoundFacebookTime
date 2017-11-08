package com.manhdaovan.boundfacebooktime;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class NewSnsPageActivity extends AppCompatActivity {
    private FloatingActionButton fabAddNewSnsPage;
    private EditText edtSnsName;
    private EditText edtSnsUrl;
    private Switch swUseAppTime;
    private TextView tvSnsUseTime;
    private SeekBar sbSnsUseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("New webpage object");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_new_sns_page);
        setUp();
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = createSaveAlertBox();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            AlertDialog alertDialog = createSaveAlertBox();
            alertDialog.show();
            return true;
        };
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog createSaveAlertBox(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Exiting confirm:");
        alertDialog.setMessage("Exit without saving?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
                finish();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        return alertDialog;
    }

    private void setUp(){
        fabAddNewSnsPage = (FloatingActionButton) findViewById(R.id.fab_add_new_sns);
        fabAddNewSnsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewSnsPageActivity.this, "save", Toast.LENGTH_SHORT).show();
            }
        });

        edtSnsName = (EditText) findViewById(R.id.edt_sns_name);
        edtSnsUrl = (EditText) findViewById(R.id.edt_sns_url);
        tvSnsUseTime = (TextView) findViewById(R.id.tv_app_use_time);
        sbSnsUseTime = (SeekBar) findViewById(R.id.sb_app_use_time);
        swUseAppTime = (Switch) findViewById(R.id.sw_use_app_time);

        swUseAppTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    sbSnsUseTime.setVisibility(View.GONE);
                    tvSnsUseTime.setVisibility(View.GONE);
                }
                else {
                    sbSnsUseTime.setVisibility(View.VISIBLE);
                    tvSnsUseTime.setVisibility(View.VISIBLE);
                }
            }
        });

        sbSnsUseTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSnsUseTime.setText(progress + " minute(s)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
