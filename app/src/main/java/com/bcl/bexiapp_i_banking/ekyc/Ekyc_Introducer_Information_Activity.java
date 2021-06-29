package com.bcl.bexiapp_i_banking.ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bcl.bexiapp_i_banking.R;

public class Ekyc_Introducer_Information_Activity extends AppCompatActivity {


    Button btn_submit_ekyc;

    EditText et_intro_name_ekyc,et_intro_acc_ekyc, et_intro_nid_ekyc, et_intro_mbl_ekyc;

    TextView tvAcNoError_ekyc;

    RadioButton rb_ob_yes, rb_ob_no, rb_cd_yes, rb_cd_no;

    RadioGroup rg_otherbank_ekyc, rg_creditcrd_ekyc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducer_information);

        tvAcNoError_ekyc = findViewById(R.id.tvAcNoError_ekyc);
        btn_submit_ekyc = findViewById(R.id.btn_submit_ekyc);

        et_intro_name_ekyc = findViewById(R.id.et_intro_name_ekyc);
        et_intro_acc_ekyc = findViewById(R.id.et_intro_acc_ekyc);
        et_intro_nid_ekyc = findViewById(R.id.et_intro_nid_ekyc);
        et_intro_mbl_ekyc = findViewById(R.id.et_intro_mbl_ekyc);

        rg_otherbank_ekyc = findViewById(R.id.rg_otherbank_ekyc);
        rg_creditcrd_ekyc = findViewById(R.id.rg_creditcrd_ekyc);

        rb_ob_yes = findViewById(R.id.rb_ob_yes);
        rb_ob_no = findViewById(R.id.rb_ob_no);
        rb_cd_yes = findViewById(R.id.rb_cd_yes);
        rb_cd_no = findViewById(R.id.rb_cd_no);

        btn_submit_ekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ekyc_Introducer_Information_Activity.this, Ekyc_Review_Activity.class);
                startActivity(intent);
            }
        });


    }
}