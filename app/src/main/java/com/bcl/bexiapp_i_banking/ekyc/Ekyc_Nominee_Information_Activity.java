package com.bcl.bexiapp_i_banking.ekyc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bcl.bexiapp_i_banking.R;

import java.util.ArrayList;

public class Ekyc_Nominee_Information_Activity extends AppCompatActivity {

    RadioButton rbYes, rbNo;

    AppCompatAutoCompleteTextView edtName;
    AppCompatAutoCompleteTextView edtMobile;
    AppCompatAutoCompleteTextView edtEmail;
    AppCompatAutoCompleteTextView edtDob;
    TextView rb_value;
    String rbValue = "N";
    Spinner sp_gender, sp_religon;

    Button tbn_ekyc_nominee_info;

    String [] gender = {"-Select Gender-","Male","Female","Common"};
   // ArrayList<GenderM> genderList = new ArrayList<>();

    String [] religon = {"-Select Religon", "Islam","Hindu",};
    //ArrayList<ReligonM> religonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekyc__nominee__information);



        rbYes = findViewById(R.id.rbYes);
        rbNo = findViewById(R.id.rbNo);

        sp_gender = findViewById(R.id.sp_gender);
        sp_religon = findViewById(R.id.sp_religon);

        tbn_ekyc_nominee_info = findViewById(R.id.tbn_ekyc_nominee_info);

        tbn_ekyc_nominee_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ekyc_Introducer_Information_Activity.class);
                startActivity(intent);
            }
        });

//        genderList.add(new GenderM("-Select Gender-",""));
//        genderList.add(new GenderM("Male","M"));
//        genderList.add(new GenderM("Female","F"));
//        genderList.add(new GenderM("Other","O"));
//
//        religonList.add(new ReligonM("-Select Religone-",""));
//        religonList.add(new ReligonM("Islam","I"));
//        religonList.add(new ReligonM("Hindu","H"));


        // Create an ArrayAdapter using the string array and a default spinner layout
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
//        ArrayAdapter<GenderM> dataAdapter = new ArrayAdapter<GenderM>(this, android.R.layout.simple_spinner_item, genderList);
//
//        // Specify the layout to use when the list of choices appears
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        ArrayAdapter<ReligonM> Adapter = new ArrayAdapter<ReligonM>(this, android.R.layout.simple_spinner_item, religonList);
//
//        // Specify the layout to use when the list of choices appears
//        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//
//        sp_gender.setAdapter(dataAdapter);
//
//        sp_religon.setAdapter(Adapter);
//
//
//
//        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if(position==1){
//                    rbYes.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });





        rbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbNo.setChecked(false);
                rbValue = "Y";
            }
        });

        rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbValue = "N";
                rbYes.setChecked(false);
            }
        });


    }
}