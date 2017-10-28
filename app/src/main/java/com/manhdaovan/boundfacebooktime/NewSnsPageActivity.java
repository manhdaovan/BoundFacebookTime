package com.manhdaovan.boundfacebooktime;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NewSnsPageActivity extends AppCompatActivity {
    private FloatingActionButton fabAddNewSnsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("vkl 123");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_new_sns_page);

        fabAddNewSnsPage = (FloatingActionButton) findViewById(R.id.fab_add_new_sns);
        fabAddNewSnsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewSnsPageActivity.this, "save", Toast.LENGTH_SHORT).show();
            }
        });
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
}
