package com.manhdaovan.boundfacebooktime;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.manhdaovan.boundfacebooktime.models.SnsPage;
import com.manhdaovan.boundfacebooktime.utils.Constants;
import com.manhdaovan.boundfacebooktime.utils.Utils;

public class NewSnsPageActivity extends AppCompatActivity {
    private TextView tvSnsPageName;
    private TextView tvSnsPageUrl;
    private TextView tvSnsUseTime;

    private EditText edtSnsName;
    private EditText edtSnsUrl;

    private Switch swUseAppTime;
    private SeekBar sbSnsUseTime;

    private FloatingActionButton fabAddNewSnsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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
        if (item.getItemId() == android.R.id.home) {
            AlertDialog alertDialog = createSaveAlertBox();
            alertDialog.show();
            return true;
        }
        ;
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog createSaveAlertBox() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Exiting confirm:");
        alertDialog.setMessage("Exit without saving?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return alertDialog;
    }

    private void setUp() {
        findUIs();
        setupListener();
    }

    private void findUIs() {
        tvSnsPageName = (TextView) findViewById(R.id.tv_sns_page_name);
        tvSnsPageUrl = (TextView) findViewById(R.id.tv_sns_page_url);
        tvSnsUseTime = (TextView) findViewById(R.id.tv_app_use_time);
        edtSnsName = (EditText) findViewById(R.id.edt_sns_name);
        edtSnsUrl = (EditText) findViewById(R.id.edt_sns_url);
        sbSnsUseTime = (SeekBar) findViewById(R.id.sb_app_use_time);
        swUseAppTime = (Switch) findViewById(R.id.sw_use_app_time);
        fabAddNewSnsPage = (FloatingActionButton) findViewById(R.id.fab_add_new_sns);
    }

    private void setupListener() {
        swUseAppTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    sbSnsUseTime.setVisibility(View.GONE);
                    tvSnsUseTime.setVisibility(View.GONE);
                } else {
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
                if (seekBar.getProgress() == 0) Utils.setTextViewError(tvSnsUseTime);
                else Utils.setTextViewNormal(tvSnsUseTime);
            }
        });

        fabAddNewSnsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearErrorsUI();
                String errorMsg = validateAllInfos();
                if (errorMsg == null || errorMsg.equals("")) {
                    if (saveNewSnsPage()) {
                        Utils.alertShortTime(getApplicationContext(), "Saved new sns page");
                        backToSnsPagesListScreen();
                    } else
                        Utils.alertShortTime(getApplicationContext(), "Cannot save new sns page");

                } else Utils.alertLongTime(getApplicationContext(), errorMsg);
            }
        });
    }

    private void clearErrorsUI() {
        Utils.setTextViewNormal(tvSnsPageName);
        Utils.setTextViewNormal(tvSnsPageUrl);
    }

    private String validateAllInfos() {
        String errorMsg = "";
        boolean needBreakLine = false;

        if (edtSnsUrl.getText().toString().trim().equals("")) {
            Utils.setTextViewError(tvSnsPageUrl);
            errorMsg += "SNS page URL can not be null";
            needBreakLine = true;
        }

        if (!edtSnsUrl.getText().toString().trim().equals("") && !URLUtil.isValidUrl(edtSnsUrl.getText().toString().trim())) {
            Utils.setTextViewError(tvSnsPageUrl);
            if (needBreakLine) errorMsg += "\n";
            errorMsg += "\nInvalid SNS page URL";
        }

        return errorMsg;
    }

    private boolean saveNewSnsPage() {
        boolean useAppTime = swUseAppTime.isChecked();
        SharedPreferences preferences = getSharedPreferences(Constants.SNS_PAGE_PREFERENCES, MODE_PRIVATE);
        int boundTime = useAppTime ? preferences.getInt(Constants.APP_CONFIG_PREFERENCE, Constants.DEFAULT_APP_USE_TIME) : sbSnsUseTime.getProgress();

        SnsPage snsPage = new SnsPage(edtSnsName.getText().toString(), edtSnsUrl.getText().toString(), boundTime);

        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(snsPage.getUrl(), snsPage.toJson());

        return editor.commit();
    }

    private void backToSnsPagesListScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
