package com.bcl.bexiapp_i_banking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_goto_loginActivity;
    Toolbar toolbar_registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        tv_goto_loginActivity = findViewById(R.id.tv_goto_loginActivity);



       //  home action bar configuration
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registration Activity");
        actionBar.setSubtitle("BexiApp I Banking");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);




        tv_goto_loginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });




    }



    ////////////////////////////////////// back arrow toolbar button alert use /////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            leaveAlert();
        }
        return true;
    }



    /////////////////////////////////////////// keypad up-down////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    ////////////////////////////////////// back arrow toolbar button alert //////////////////////////////
    private void leaveAlert(){

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrationActivity.this);
        alertBuilder.setTitle("Confirmation!");
        alertBuilder.setMessage("Do you Want to leave the page?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                dialog.dismiss();

                //finish();
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //showToast("No");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }


}