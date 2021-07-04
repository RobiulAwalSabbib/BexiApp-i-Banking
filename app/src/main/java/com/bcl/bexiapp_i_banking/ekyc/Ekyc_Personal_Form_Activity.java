package com.bcl.bexiapp_i_banking.ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.R;

public class Ekyc_Personal_Form_Activity extends AppCompatActivity {


    EditText et_ekyc_nid,et_ekyc_applicant_name,et_ekyc_applicant_dob,et_ekyc_applicant_father_name,et_ekyc_applicant_mother_name;
    EditText et_ekyc_applicant_spouse_name,et_ekyc_applicant_contact_number,et_ekyc_applicant_gender,et_ekyc_applicant_religion;
    EditText et_ekyc_applicant_monthly_income,et_ekyc_applicant_present_address,et_ekyc_applicant_permanent_address;
    EditText et_ekyc_applicant_email_address,et_ekyc_applicant_source_of_fund,et_ekyc_applicant_profession,et_ekyc_applicant_nationality;
    Button btn_ekyc_personal_info_next;
    RadioGroup rg_ekyc_applicant_busines_address,rg_ekyc_applicant_mailing_address,same_as_present_add;
    Spinner et_ekyc_applicant_gender_ss,et_ekyc_applicant_religion_ss,division_name,district_name,upazila_name,union_name;
    Spinner per_division_name,per_district_name,per_upazila_name,per_union_name;
    Spinner bus_division_name,bus_district_name,bus_upazila_name,bus_union_name;

    LinearLayout lout_ekyc_per_address,lout_ekyc_business_address;


    SharedPreferences sharedPreferences;

    String [] gender = {"Male","Female"};
    String [] religion = {"Islam","Hinduism","Buddism","Chrishtian","Other"};
    String [] divisionName = {"Dhaka","Borishal","Rajshahi","Khulna","Syllet","Chittagong"};
    String [] districtName = {"Dhaka","Borishal","Rajshahi","Khulna","Syllet","Chittagong"};
    String [] upazilaName = {"Nobabpur","Dohar","Savar","Abdullahpur","KeraniGonge"};
    String [] unionName = {"Dhamrai","Dhorshona","Kamarpara","XXXXX","YYYYY","ZZZZ"};

    String v_gender = "";
    String v_religion = "";


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

        et_ekyc_applicant_gender_ss = findViewById(R.id.et_ekyc_applicant_gender_ss);
        et_ekyc_applicant_religion_ss = findViewById(R.id.et_ekyc_applicant_religion_ss);
        division_name = findViewById(R.id.division_name);
        district_name = findViewById(R.id.district_name);
        upazila_name = findViewById(R.id.upazila_name);
        union_name = findViewById(R.id.union_name);
        per_division_name = findViewById(R.id.per_division_name);
        per_district_name = findViewById(R.id.per_district_name);
        per_upazila_name = findViewById(R.id.per_upazila_name);
        per_union_name = findViewById(R.id.per_union_name);
        bus_division_name = findViewById(R.id.bus_division_name);
        bus_district_name = findViewById(R.id.bus_district_name);
        bus_upazila_name = findViewById(R.id.bus_upazila_name);
        bus_union_name = findViewById(R.id.bus_union_name);

        lout_ekyc_per_address = findViewById(R.id.lout_ekyc_per_address);
        lout_ekyc_business_address = findViewById(R.id.lout_ekyc_business_address);

        et_ekyc_applicant_monthly_income = findViewById(R.id.et_ekyc_applicant_monthly_income);
        et_ekyc_applicant_present_address = findViewById(R.id.et_ekyc_applicant_present_address);
        et_ekyc_applicant_permanent_address = findViewById(R.id.et_ekyc_applicant_permanent_address);
        et_ekyc_applicant_email_address = findViewById(R.id.et_ekyc_applicant_email_address);
        et_ekyc_applicant_source_of_fund = findViewById(R.id.et_ekyc_applicant_source_of_fund);
        et_ekyc_applicant_profession = findViewById(R.id.et_ekyc_applicant_profession);
        et_ekyc_applicant_nationality = findViewById(R.id.et_ekyc_applicant_nationality);

        rg_ekyc_applicant_busines_address = findViewById(R.id.rg_ekyc_applicant_busines_address);
        rg_ekyc_applicant_mailing_address = findViewById(R.id.rg_ekyc_applicant_mailing_address);
        same_as_present_add = findViewById(R.id.same_as_present_add);

        btn_ekyc_personal_info_next = findViewById(R.id.btn_ekyc_personal_info_next);


        sharedPreferences = getSharedPreferences("ekyc_app_session", MODE_PRIVATE);




        ////////////////////////////////// set gender spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        et_ekyc_applicant_gender_ss.setAdapter(genderAdapter);




        et_ekyc_applicant_gender_ss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    showToast("Male Selected");
                    v_gender = "Male";
                }else if(position == 1){
                    showToast("Female Selected");
                    v_gender = "Female";
                }else{
                    showToast("Nothign selected");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////////////////////////// set Religion spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> religiondapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, religion);

        // Specify the layout to use when the list of choices appears
        religiondapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        et_ekyc_applicant_religion_ss.setAdapter(religiondapter);




        et_ekyc_applicant_religion_ss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    showToast("Islam Selected");
                    v_religion = "Islam";
                }else if(position == 1){
                    showToast("Hinduism Selected");
                    v_religion = "Hinduism";
                }else if(position == 2){
                    showToast("Buddism Selected");
                    v_religion = "Buddism";
                }else if(position == 3){
                    showToast("Chrishtian Selected");
                    v_religion = "Chrishtian";
                }else {
                    showToast("Other Selected");
                    v_religion = "Other";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set division spinner /////////////////////////////


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisionName);

        // Specify the layout to use when the list of choices appears
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        division_name.setAdapter(divisionAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set district spinner /////////////////////////////
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtName);

        // Specify the layout to use when the list of choices appears
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        district_name.setAdapter(districtAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set upojila spinner /////////////////////////////
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> upazilaNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, upazilaName);

        // Specify the layout to use when the list of choices appears
        upazilaNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        upazila_name.setAdapter(upazilaNameAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set union spinner /////////////////////////////
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> unionNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unionName);

        // Specify the layout to use when the list of choices appears
        unionNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        union_name.setAdapter(unionNameAdapter);

        //////////////////////////////////////////// Radio Group for permanent address //////////////////////////

        same_as_present_add.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_ekyc_pinfo_present_address_yes){
                    lout_ekyc_per_address.setVisibility(View.GONE);
                }else if(checkedId==R.id.rb_ekyc_pinfo_present_address_no){
                    lout_ekyc_per_address.setVisibility(View.VISIBLE);
                }
            }
        });
        //////////////////////////////////////////// Radio Group for business address //////////////////////////
        rg_ekyc_applicant_busines_address.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_ekyc_applicant_busines_address_yes){
                    lout_ekyc_business_address.setVisibility(View.VISIBLE);
                }else if(checkedId == R.id.rb_ekyc_applicant_busines_address_no){
                    lout_ekyc_business_address.setVisibility(View.GONE);
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set premanent division spinner /////////////////////////////

        // Apply the adapter to the spinner
        per_division_name.setAdapter(divisionAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set premanent district spinner /////////////////////////////

        // Apply the adapter to the spinner
        per_district_name.setAdapter(districtAdapter);
////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set premanent upojila spinner /////////////////////////////

        // Apply the adapter to the spinner
        per_upazila_name.setAdapter(upazilaNameAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set premanent union spinner /////////////////////////////

        // Apply the adapter to the spinner
        per_union_name.setAdapter(unionNameAdapter);



/////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set business premanent division spinner /////////////////////////////

        // Apply the adapter to the spinner
        bus_division_name.setAdapter(divisionAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set business district spinner /////////////////////////////

        // Apply the adapter to the spinner
        bus_district_name.setAdapter(districtAdapter);
////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set business upojila spinner /////////////////////////////

        // Apply the adapter to the spinner
        bus_upazila_name.setAdapter(upazilaNameAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// set business union spinner /////////////////////////////

        // Apply the adapter to the spinner
        bus_union_name.setAdapter(unionNameAdapter);



/////////////////////////////////////////////////////////////////////////////////////////////////
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



    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}