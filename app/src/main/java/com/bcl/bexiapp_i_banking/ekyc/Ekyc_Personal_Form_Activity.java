package com.bcl.bexiapp_i_banking.ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.bcl.bexiapp_i_banking.R;

public class Ekyc_Personal_Form_Activity extends AppCompatActivity {


    EditText et_ekyc_nid,et_ekyc_applicant_name,et_ekyc_applicant_dob,et_ekyc_applicant_father_name,et_ekyc_applicant_mother_name;
    EditText et_ekyc_applicant_spouse_name,et_ekyc_applicant_contact_number,et_ekyc_applicant_gender,et_ekyc_applicant_religion;
    EditText et_ekyc_applicant_monthly_income,et_ekyc_applicant_present_address,et_ekyc_applicant_permanent_address;
    EditText et_ekyc_applicant_email_address,et_ekyc_applicant_source_of_fund,et_ekyc_applicant_profession,et_ekyc_applicant_nationality;
    Button btn_ekyc_personal_info_next;
    RadioGroup rg_ekyc_applicant_busines_address,rg_ekyc_applicant_mailing_address;


    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekyc__personal__form);


        et_ekyc_nid = findViewById(R.id.et_ekyc_nid);
        et_ekyc_applicant_name = findViewById(R.id.et_ekyc_applicant_name);
        et_ekyc_applicant_dob = findViewById(R.id.et_ekyc_applicant_dob);
        et_ekyc_applicant_father_name = findViewById(R.id.et_ekyc_applicant_father_name);
        et_ekyc_applicant_mother_name = findViewById(R.id.et_ekyc_applicant_mother_name);
        et_ekyc_applicant_spouse_name = findViewById(R.id.et_ekyc_applicant_spouse_name);
        et_ekyc_applicant_contact_number = findViewById(R.id.et_ekyc_applicant_contact_number);
        et_ekyc_applicant_gender = findViewById(R.id.et_ekyc_applicant_gender);
        et_ekyc_applicant_religion = findViewById(R.id.et_ekyc_applicant_religion);
        et_ekyc_applicant_monthly_income = findViewById(R.id.et_ekyc_applicant_monthly_income);
        et_ekyc_applicant_present_address = findViewById(R.id.et_ekyc_applicant_present_address);
        et_ekyc_applicant_permanent_address = findViewById(R.id.et_ekyc_applicant_permanent_address);
        et_ekyc_applicant_email_address = findViewById(R.id.et_ekyc_applicant_email_address);
        et_ekyc_applicant_source_of_fund = findViewById(R.id.et_ekyc_applicant_source_of_fund);
        et_ekyc_applicant_profession = findViewById(R.id.et_ekyc_applicant_profession);
        et_ekyc_applicant_nationality = findViewById(R.id.et_ekyc_applicant_nationality);

        rg_ekyc_applicant_busines_address = findViewById(R.id.rg_ekyc_applicant_busines_address);
        rg_ekyc_applicant_mailing_address = findViewById(R.id.rg_ekyc_applicant_mailing_address);

        btn_ekyc_personal_info_next = findViewById(R.id.btn_ekyc_personal_info_next);


        sharedPreferences = getSharedPreferences("ekyc_app_session", MODE_PRIVATE);



        btn_ekyc_personal_info_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nid = et_ekyc_nid.getText().toString();
                String name = et_ekyc_applicant_name.getText().toString();
                String dob = et_ekyc_applicant_dob.getText().toString();

                if(nid.isEmpty()){

                    et_ekyc_nid.setError("Please Enter Your NID first!");
                    et_ekyc_nid.requestFocus();

                }else if(name.isEmpty()){

                    et_ekyc_applicant_name.setError("Please Enter Your Name first!");
                    et_ekyc_applicant_name.requestFocus();

                }else if(dob.isEmpty()){

                    et_ekyc_applicant_dob.setError("Please Enter Your Name first!");
                    et_ekyc_applicant_dob.requestFocus();

                }else{

                    sharedPreferences.edit().putString("ekyc_nid", nid).apply();
                    sharedPreferences.edit().putString("ekyc_name", name).apply();
                    sharedPreferences.edit().putString("ekyc_dob", dob).apply();

                    Intent intent = new Intent(Ekyc_Personal_Form_Activity.this,Ekyc_Nid_Picture_Upload_Activity.class);
                    intent.putExtra("ekyc_nid", nid);
                    intent.putExtra("ekyc_name", name);
                    intent.putExtra("ekyc_dob", dob);
                    startActivity(intent);

                }
            }
        });




    }
}