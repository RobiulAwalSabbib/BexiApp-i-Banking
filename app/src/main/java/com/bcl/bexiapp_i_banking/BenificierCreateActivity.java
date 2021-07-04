package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.Benificier_Info_Post.RequestModel;
import com.bcl.bexiapp_i_banking.Benificier_Info_Post.ReturnModel;
import com.bcl.bexiapp_i_banking.billpay.BillPayRecM;
import com.bcl.bexiapp_i_banking.billpay.BillPayReqM;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BenificierCreateActivity extends AppCompatActivity {


    EditText et_benificier_info_custID,tv_benificier_info_accno;
    //TextView tv_benificier_info_accno;
    Spinner sp_benificier_info_accType,sp_benificier_info_accTitle;
    Button btn_benificier_info_post;


    String [] accountType = {"C","S"};
    String [] accountTitle = {"Current Account","Savings Account"};

    String v_accountType = "";
    String v_accountTitle = "";

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    SharedPreferences session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benificier_create);

        session = getSharedPreferences("app_session", MODE_PRIVATE);
        String acno = session.getString("acNo", "0");

        et_benificier_info_custID = findViewById(R.id.et_benificier_info_custID);

        tv_benificier_info_accno = findViewById(R.id.tv_benificier_info_accno);


        sp_benificier_info_accType = findViewById(R.id.sp_benificier_info_accType);
        sp_benificier_info_accTitle = findViewById(R.id.sp_benificier_info_accTitle);


        btn_benificier_info_post = findViewById(R.id.btn_benificier_info_post);


        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

///////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////// set account type spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountType);

        // Specify the layout to use when the list of choices appears
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sp_benificier_info_accType.setAdapter(accountTypeAdapter);





        ////////////////////////////////// set account title spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> accountTitleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountTitle);

        // Specify the layout to use when the list of choices appears
        accountTitleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sp_benificier_info_accTitle.setAdapter(accountTitleAdapter);





////////////////////////////////////////////////////////////////////////////////////////////////

        btn_benificier_info_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String custid = et_benificier_info_custID.getText().toString();
                String accNo = tv_benificier_info_accno.getText().toString();

                sp_benificier_info_accType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){
                            showToast("C Selected");
                            v_accountType = "C";
                        }else if(position == 1){
                            showToast("S Selected");
                            v_accountType = "S";
                        }else{
                            showToast("Nothign selected");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                ///////////////////////////////////////////////////////////////////////
                sp_benificier_info_accTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){

                            showToast("Current Account Selected");
                            v_accountTitle = "Current Account";
                        }else if(position == 1){
                            showToast("Savings Account Selected");
                            v_accountTitle = "Savings Account";
                        }else{
                            showToast("Nothign selected");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                if (custid.isEmpty()) {

                    et_benificier_info_custID.setError("Please Enter Client CustID first!");
                    et_benificier_info_custID.requestFocus();

                } else if (accNo.isEmpty()) {

                    tv_benificier_info_accno.setError("Please Enter Your Account Number!");
                    tv_benificier_info_accno.requestFocus();

                }else{

                    RequestModel reqM = new RequestModel();
                    reqM.setCust_id(custid);
                    reqM.setAcc_no(accNo);
                    reqM.setAcc_type(v_accountType);
                    reqM.setAcc_title(v_accountTitle);

                    pDialog.show();
                    postBenificiary(reqM);

                }

            }
        });

        ////////////////////////////////////////////////////////////////////////



    }


    private void postBenificiary (RequestModel reqM){
        disposable.add(
                apiService

                        //change 1
                        .post_Benificiary_Info(reqM.getCust_id(),
                                reqM.getAcc_no(),
                                reqM.getAcc_type(),
                                reqM.getAcc_title())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ReturnModel>() {//change 2
                            @Override
                            public void onSuccess(ReturnModel recM) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (recM.pErrorFlag.equals("N")) {
                                    new CustomAlert().showSuccessMessage(BenificierCreateActivity.this, "", recM.pErrorMessage);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(BenificierCreateActivity.this, "", recM.pErrorMessage);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                Log.e( "onError: " , e.getMessage());
                                ErrorUtil.showError(e, BenificierCreateActivity.this);
                            }
                        }));
    }


    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}