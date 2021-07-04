package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.bcl.bexiapp_i_banking.billpay.BillPayRecM;
import com.bcl.bexiapp_i_banking.billpay.BillPayReqM;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Acc_GetData_M;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Acc_RcvM;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Operator_GetData_M;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Operator_RecM;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Operator_Type_GetData_M;
import com.bcl.bexiapp_i_banking.billpay.BillPay_Operator_Type_RcvM;
import com.bcl.bexiapp_i_banking.billpay.Spain_BillPay_Acc;
import com.bcl.bexiapp_i_banking.billpay.Spain_BillPay_Operator;
import com.bcl.bexiapp_i_banking.billpay.Spain_BillPay_Operator_Type;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BillPayActivity extends AppCompatActivity {

    EditText et_bill_Mumb, et_bill_meter, et_bill_no,et_bill_amount,et_bill_pin;
    Button btn_bill_submit;
    ////// for spinner
    Spinner sp_bill__accno, sp_bill_option, sp_bill_oper_type;
    //String [] gender = {"-Select Gender-","Male","Female","Common"};

    String bill__accno, bill_option, bill_oper_type;

    private final String TAG = "BillPay";

    //Network call
    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    ////spinnerList
    List<BillPay_Operator_GetData_M> BillPayOperatorList = new ArrayList<>();
    List<BillPay_Operator_Type_GetData_M> BillPayOperatorTypeList = new ArrayList<>();
    List<BillPay_Acc_GetData_M> BillPayAccTypeList = new ArrayList<>();



    // You spinner view
    //private Spinner mySpinner;
    // Custom Spinner adapter (ArrayAdapter<User>)
    // You can define as a private to use it in the all class
    // This is the object that is going to do the "magic"
    private Spain_BillPay_Operator adapter;
    private Spain_BillPay_Operator_Type adapter2;
    private Spain_BillPay_Acc adapter3;

    SharedPreferences session;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bii_pay);

        session = getSharedPreferences("app_session", MODE_PRIVATE);

        et_bill_Mumb = findViewById(R.id.et_bill_Mumb);
        et_bill_meter = findViewById(R.id.et_bill_meter);
        et_bill_no = findViewById(R.id.et_bill_no);
        et_bill_amount = findViewById(R.id.et_bill_amount);
        et_bill_pin = findViewById(R.id.et_bill_pin);

        btn_bill_submit = findViewById(R.id.btn_bill_submit);

        sp_bill__accno = findViewById(R.id.sp_bill__accno);
        sp_bill_option = findViewById(R.id.sp_bill_option);
        sp_bill_oper_type = findViewById(R.id.sp_bill_oper_type);


        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

        BillPay_Operator_Api();
        BillPay_Operator_Type_Api();
        BillPay_AccApi();

        ///////////////////////////////////////////////////////////////////////////////////
        //////////////////////// For Using spinner and set data on spinner Adapter //////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // Specify the layout to use when the list of choices appears
        // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        //sp_gender.setAdapter(dataAdapter);

        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////// Transaction Post Process Api /////////////////////////

        btn_bill_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String MobileNo = et_bill_Mumb.getText().toString();
                String MiterNo = et_bill_meter.getText().toString();
                String BillNo = et_bill_no.getText().toString();
                String BillAmount = et_bill_amount.getText().toString();
                String BillPin = et_bill_pin.getText().toString();

//                String transaction_medium = et_transaction_medium.getText().toString();
//                String transaction_type = et_transaction_type.getText().toString();

                sp_bill__accno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Here you get the current item (a User object) that is selected by its position
                        BillPay_Acc_GetData_M bill_oper = (BillPay_Acc_GetData_M) adapterView.getItemAtPosition(i);
                        // Here you can do the action you want to...
                        bill__accno = bill_oper.getACNUMBER().toString();
                        Log.e("bill__accno", bill__accno);


//                        Toast.makeText(Main.this, "ID: " + user.getId() + "\nName: " + user.getName(),
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                sp_bill_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Here you get the current item (a User object) that is selected by its position
                        BillPay_Operator_GetData_M bill_oper = (BillPay_Operator_GetData_M) adapterView.getItemAtPosition(i);
                        // Here you can do the action you want to...
                        bill_option = bill_oper.getOPER_NAME();
                        Log.e("bill_option", bill_option);


//                        Toast.makeText(Main.this, "ID: " + user.getId() + "\nName: " + user.getName(),
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                sp_bill_oper_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Here you get the current item (a User object) that is selected by its position
                        BillPay_Operator_Type_GetData_M oper_type = (BillPay_Operator_Type_GetData_M) adapterView.getItemAtPosition(i);
                        // Here you can do the action you want to...
                        bill_oper_type = oper_type.getTYPE_NAME();
                        Log.e("bill_oper_type", bill_oper_type);


//                        Toast.makeText(Main.this, "ID: " + user.getId() + "\nName: " + user.getName(),
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                if (MobileNo.isEmpty()) {

                    et_bill_Mumb.setError("Please Enter Mobile Number first!");
                    et_bill_Mumb.requestFocus();

                } else if (BillNo.isEmpty()) {

                    et_bill_no.setError("Please Enter Bill Number!");
                    et_bill_no.requestFocus();

                } else if (MiterNo.isEmpty()) {

                    et_bill_meter.setError("Please Enter Meter Number!");
                    et_bill_meter.requestFocus();

                } else if (BillAmount.isEmpty()) {

                    et_bill_amount.setError("Please Enter Your Amount!");
                    et_bill_amount.requestFocus();

                } else if (BillPin.isEmpty()) {

                    et_bill_pin.setError("Please Enter Your Pin!");
                    et_bill_pin.requestFocus();

                }/*else if(transaction_medium.isEmpty()){

                    et_transaction_medium.setError("Please Enter Transaction Medium first!");
                    et_transaction_medium.requestFocus();

                }else if(transaction_type.isEmpty()){

                    et_transaction_type.setError("Please Enter Transaction Type first!");
                    et_transaction_type.requestFocus();

                }*/ else {
                    BillPayReqM reqM = new BillPayReqM();
                    reqM.setMobile_no(MobileNo);
                    reqM.setBill_no(BillNo);
                    reqM.setMeter_no(MiterNo);
                    reqM.setAmount(BillAmount);
                    reqM.setPin(BillPin);
//                    reqM.setTransaction_medium(transaction_medium);
//                    reqM.setTransaction_type(transaction_type);
                    reqM.setBillpay__name(bill_option);
                    reqM.setBillpay_type(bill_oper_type);
                    reqM.setAcc_no(bill__accno);

                    pDialog.show();
                    BillPay_Api(reqM);

                }

            }
        });

    }


    private void BillPay_Api (BillPayReqM reqM){
        disposable.add(
                apiService

                        //change 1
                        .billpay(reqM.getAcc_no(), reqM.getMobile_no(), reqM.getBillpay__name(), reqM.getBillpay_type(),
                                reqM.getBill_no(),
                                reqM.getMeter_no(),
                                reqM.getAmount(),
                                reqM.getPin())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<BillPayRecM>() {//change 2
                            @Override
                            public void onSuccess(BillPayRecM loginResult) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (loginResult.pErrorFlag.equals("N")) {
                                    new CustomAlert().showSuccessMessage(BillPayActivity.this, "", loginResult.pErrorMessage);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(BillPayActivity.this, "", loginResult.pErrorMessage);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, BillPayActivity.this);
                            }
                        }));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    private void BillPay_AccApi() {

        // pDialog.show();

        disposable.add(
                apiService

                        //change 1
                        .billpayacc()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BillPay_Acc_RcvM>>() {//change 2
                            @Override
                            public void onSuccess(List<BillPay_Acc_RcvM> recM) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() < 1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(BillPayActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for (int i = 0; i < recM.size(); i++) {

                                        BillPayAccTypeList.add(new BillPay_Acc_GetData_M(recM.get(i).getAcnumber() + "",
                                                recM.get(i).getCustid() + "", recM.get(i).getBid() + "", recM.get(i).getOpeningBalance() + "", recM.get(i).getAod() + "",
                                                recM.get(i).getAtype() + "", recM.get(i).getAstatus() +""
                                        ));


                                    }

                                }


                                // Initialize the adapter sending the current context
                                // Send the simple_spinner_item layout
                                // And finally send the Users array (Your data)
                                adapter3 = new Spain_BillPay_Acc(BillPayActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        BillPayAccTypeList);
                                sp_bill__accno = (Spinner) findViewById(R.id.sp_bill__accno);
                                sp_bill__accno.setAdapter(adapter3); // Set the custom adapter to the spinner
                                // You can create an anonymous listener to handle the event when is selected an spinner item


                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, BillPayActivity.this);
                            }
                        }));


    }


    ////////////////////////////////////////// asyntask disposal network call for Transaction Type Spinner ///////////////////////////////////////

    private void BillPay_Operator_Api() {

        // pDialog.show();

        disposable.add(
                apiService

                        //change 1
                        .billpayoperator()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BillPay_Operator_RecM>>() {//change 2
                            @Override
                            public void onSuccess(List<BillPay_Operator_RecM> recM) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() < 1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(BillPayActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for (int i = 0; i < recM.size(); i++) {

                                        BillPayOperatorList.add(new BillPay_Operator_GetData_M(recM.get(i).getOperCode() + "",
                                                recM.get(i).getOperName() + ""
                                        ));


                                    }

                                }


                                // Initialize the adapter sending the current context
                                // Send the simple_spinner_item layout
                                // And finally send the Users array (Your data)
                                adapter = new Spain_BillPay_Operator(BillPayActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        BillPayOperatorList);
                                sp_bill_option = (Spinner) findViewById(R.id.sp_bill_option);
                                sp_bill_option.setAdapter(adapter); // Set the custom adapter to the spinner
                                // You can create an anonymous listener to handle the event when is selected an spinner item


                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, BillPayActivity.this);
                            }
                        }));


    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////// asyntask disposal network call for Transaction Medium Spinner ///////////////////////////////////////

    private void BillPay_Operator_Type_Api() {

        // pDialog.show();

        disposable.add(
                apiService
                        //change 1
                        .billpayoperatortype()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BillPay_Operator_Type_RcvM>>() {//change 2
                            @Override
                            public void onSuccess(List<BillPay_Operator_Type_RcvM> recM) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() < 1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(BillPayActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for (int i = 0; i < recM.size(); i++) {

                                        BillPayOperatorTypeList.add(new BillPay_Operator_Type_GetData_M(recM.get(i).getTypeCode() + "",
                                                recM.get(i).getTypeName() + ""
                                        ));


                                    }

                                }


                                // Initialize the adapter sending the current context
                                // Send the simple_spinner_item layout
                                // And finally send the Users array (Your data)
                                adapter2 = new Spain_BillPay_Operator_Type(BillPayActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        BillPayOperatorTypeList);
                                sp_bill_oper_type = (Spinner) findViewById(R.id.sp_bill_oper_type);
                                sp_bill_oper_type.setAdapter(adapter2); // Set the custom adapter to the spinner
                                // You can create an anonymous listener to handle the event when is selected an spinner item


                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, BillPayActivity.this);
                            }
                        }));


    }

    ////////////////////////////// keyboard up-down //////////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent (MotionEvent ev){
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);

    }
}