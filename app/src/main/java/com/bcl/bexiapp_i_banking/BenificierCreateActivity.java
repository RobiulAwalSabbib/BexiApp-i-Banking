package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;

import io.reactivex.disposables.CompositeDisposable;

public class BenificierCreateActivity extends AppCompatActivity {


    EditText et_benificier_info_custID,et_benificier_info_accno,et_benificier_info_accType,et_benificier_info_accTitle;
    Button btn_benificier_info_post;

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benificier_create);

        et_benificier_info_custID = findViewById(R.id.et_benificier_info_custID);
        et_benificier_info_accno = findViewById(R.id.et_benificier_info_accno);
        et_benificier_info_accType = findViewById(R.id.et_benificier_info_accType);
        et_benificier_info_accTitle = findViewById(R.id.et_benificier_info_accTitle);

        btn_benificier_info_post = findViewById(R.id.btn_benificier_info_post);


        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

///////////////////////////////////////////////////////////////////////////////////////////



    }
}