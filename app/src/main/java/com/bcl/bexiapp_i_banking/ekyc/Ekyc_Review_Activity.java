package com.bcl.bexiapp_i_banking.ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.Duration;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class Ekyc_Review_Activity extends AppCompatActivity {

    Button btn_finish_ekyc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekyc__review);

        btn_finish_ekyc= findViewById(R.id.btn_finish_ekyc);

        btn_finish_ekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"Work Done ! Waite for Approval",Toast.LENGTH_LONG).show();
            }
        });

    }
}