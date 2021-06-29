package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRecM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerReqM;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    Intent intent;


    EditText et_AG_CUSTOMER_CUST_ID,et_AG_CUSTOMER_CUST_NAME,et_AG_CUSTOMER_CUST_GENDER,et_AG_CUSTOMER_CUST_DOB,et_AG_ADDRESS_CUST_ID,et_AG_ADDRESS_PRESENT_ADDRESS,et_AG_ADDRESS_PERMANENT_ADDRESS,et_AG_DOCUMENT_CUST_ID,et_AG_DOCUMENT_DOCUMENT_ID,et_AG_DOCUMENT_DOCUMENT_TYPE,et_AG_DOCUMENT_DOCUMENT_STATUS,et_AG_DOCUMENT_DOCUMENT_CONTENT;
    Button btn_AG_CUSTOMER_post,btn_AG_CUSTOMER_get,btn_AG_ADDRESS_post,btn_AG_ADDRESS_get,btn_AG_DOCUMENT_post,btn_AG_DOCUMENT_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        et_AG_CUSTOMER_CUST_ID = findViewById(R.id.et_AG_CUSTOMER_CUST_ID);
        et_AG_CUSTOMER_CUST_NAME = findViewById(R.id.et_AG_CUSTOMER_CUST_NAME);
        et_AG_CUSTOMER_CUST_GENDER = findViewById(R.id.et_AG_CUSTOMER_CUST_GENDER);
        et_AG_CUSTOMER_CUST_DOB = findViewById(R.id.et_AG_CUSTOMER_CUST_DOB);
        et_AG_ADDRESS_CUST_ID = findViewById(R.id.et_AG_ADDRESS_CUST_ID);
        et_AG_ADDRESS_PRESENT_ADDRESS = findViewById(R.id.et_AG_ADDRESS_PRESENT_ADDRESS);
        et_AG_ADDRESS_PERMANENT_ADDRESS = findViewById(R.id.et_AG_ADDRESS_PERMANENT_ADDRESS);
        et_AG_DOCUMENT_CUST_ID = findViewById(R.id.et_AG_DOCUMENT_CUST_ID);
        et_AG_DOCUMENT_DOCUMENT_ID = findViewById(R.id.et_AG_DOCUMENT_DOCUMENT_ID);
        et_AG_DOCUMENT_DOCUMENT_TYPE = findViewById(R.id.et_AG_DOCUMENT_DOCUMENT_TYPE);
        et_AG_DOCUMENT_DOCUMENT_STATUS = findViewById(R.id.et_AG_DOCUMENT_DOCUMENT_STATUS);
        et_AG_DOCUMENT_DOCUMENT_CONTENT = findViewById(R.id.et_AG_DOCUMENT_DOCUMENT_CONTENT);

        btn_AG_CUSTOMER_post = findViewById(R.id.btn_AG_CUSTOMER_post);
        btn_AG_CUSTOMER_get = findViewById(R.id.btn_AG_CUSTOMER_get);
        btn_AG_ADDRESS_post = findViewById(R.id.btn_AG_ADDRESS_post);
        btn_AG_ADDRESS_get = findViewById(R.id.btn_AG_ADDRESS_get);
        btn_AG_DOCUMENT_post = findViewById(R.id.btn_AG_DOCUMENT_post);
        btn_AG_DOCUMENT_get = findViewById(R.id.btn_AG_DOCUMENT_get);




        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");
        ///////////////////////////////////////////////////////////////////////////////////



        btn_AG_CUSTOMER_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cust_id = et_AG_CUSTOMER_CUST_ID.getText().toString();
                String cust_name = et_AG_CUSTOMER_CUST_NAME.getText().toString();
                String cust_gender = et_AG_CUSTOMER_CUST_GENDER.getText().toString();
                String cust_dob = et_AG_CUSTOMER_CUST_DOB.getText().toString();

                if(cust_id.isEmpty()){
                    et_AG_CUSTOMER_CUST_ID.setError("Please Enter Customer ID first!");
                    et_AG_CUSTOMER_CUST_ID.requestFocus();
                }
                else if(cust_name.isEmpty()){
                    et_AG_CUSTOMER_CUST_NAME.setError("Please Enter Customer Name first!");
                    et_AG_CUSTOMER_CUST_NAME.requestFocus();
                }
                else if(cust_gender.isEmpty()){
                    et_AG_CUSTOMER_CUST_GENDER.setError("Please Enter Customer Gender first!");
                    et_AG_CUSTOMER_CUST_GENDER.requestFocus();
                }

                else if(cust_dob.isEmpty()){
                    et_AG_CUSTOMER_CUST_DOB.setError("Please Enter Customer Date of Birth first!");
                    et_AG_CUSTOMER_CUST_DOB.requestFocus();
                }

                else{

                    AGCustomerReqM reqM = new AGCustomerReqM();
                    reqM.setCust_id(cust_id);
                    reqM.setCust_name(cust_name);
                    reqM.setCust_gender(cust_gender);
                    reqM.setCust_dob(cust_dob);

                    pDialog.show();
                    post_AG_CUSTOMER(reqM);

                }

            }
        });

        btn_AG_CUSTOMER_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Ag_Customer_Getall_Data_Activity.class);
                startActivity(intent);
            }
        });

    }


////////////////////////////// keyboard up-down //////////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////// asyntask disposal network call ///////////////////////////////////////

    private void post_AG_CUSTOMER(final AGCustomerReqM reqM ){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .postAGcustomer(reqM.getCust_id(),
                                reqM.getCust_name(),
                                reqM.getCust_gender(),
                                reqM.getCust_dob())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<AGCustomerRecM>() {//change 2
                            @Override
                            public void onSuccess( AGCustomerRecM recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (recM.pErrorFlag.equals("N")) {
                                    //Successful
//                                    session.edit().putString("user_id", reqM.getUser_id()).commit();
//                                    session.edit().putString("session_id", "039248098").commit();
//                                    session.edit().putString("user_type", "ADMIN").commit();
//                                    session.edit().putString("user_mobile", "093284098").commit();
//                                    session.edit().putString("user_name", loginResult.user_name).commit();

                                    new CustomAlert().showSuccessMessage(TestActivity.this, "", recM.pErrorMessage);
                                    //Intent intent = new Intent(NavigationDrawer.this, Dashboard2.class);
//                                    intent.putExtra("id", reqM.getUser_id());
//                                    intent.putExtra("pass", reqM.getUser_password());
                                    // startActivity(intent);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(TestActivity.this, "", recM.pErrorMessage);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TestActivity.this);
                            }
                        }));



    }




}