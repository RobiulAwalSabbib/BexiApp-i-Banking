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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data.TransactionMediumDataModel;
import com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data.Transaction_Medium_Get_All_Data_Return_M;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Receive_Model;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Request_Model;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.TransactionTypeDataModel;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.Transaction_Type_Get_All_Data_Return_M;
import com.bcl.bexiapp_i_banking.adapter.SpinAdapter_TransactionMedium;
import com.bcl.bexiapp_i_banking.adapter.SpinAdapter_TransactionType;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TransactionPostTestActivity extends AppCompatActivity {


    EditText et_sender_acc,et_receiver_acc,et_transaction_amount,et_transaction_medium,et_transaction_type;

    Button btn_transaction_post,btn_transaction_detail_get;
////// for spinner
    Spinner sp_transction_medium,sp_transction_type;
   //String [] gender = {"-Select Gender-","Male","Female","Common"};

    String transaction_medium,transaction_type;

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    ////spinnerList
    List<TransactionTypeDataModel> transactionTypeList = new ArrayList<>();
    List<TransactionMediumDataModel> transactionMediumList = new ArrayList<>();


    // You spinner view
    //private Spinner mySpinner;
    // Custom Spinner adapter (ArrayAdapter<User>)
    // You can define as a private to use it in the all class
    // This is the object that is going to do the "magic"
    private SpinAdapter_TransactionType adapter;
    private SpinAdapter_TransactionMedium adapter2;

    SharedPreferences session;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_post_test);

        session = getSharedPreferences("app_session", MODE_PRIVATE);

        et_sender_acc = findViewById(R.id.et_sender_acc);
        et_receiver_acc = findViewById(R.id.et_receiver_acc);
        et_transaction_amount = findViewById(R.id.et_transaction_amount);
//        et_transaction_medium = findViewById(R.id.et_transaction_medium);
//        et_transaction_type = findViewById(R.id.et_transaction_type);

        btn_transaction_post = findViewById(R.id.btn_transaction_post);
        btn_transaction_detail_get = findViewById(R.id.btn_transaction_detail_get);

        sp_transction_medium = findViewById(R.id.sp_transction_medium);
        sp_transction_type = findViewById(R.id.sp_transction_type);



        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");


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


        btn_transaction_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sender_acc = et_sender_acc.getText().toString();
                String receiver_acc = et_receiver_acc.getText().toString();
                String transaction_amount = et_transaction_amount.getText().toString();
//                String transaction_medium = et_transaction_medium.getText().toString();
//                String transaction_type = et_transaction_type.getText().toString();


                sp_transction_medium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Here you get the current item (a User object) that is selected by its position
                        //TransactionMediumDataModel tran_medium = adapter2.getItem(i);
                        TransactionMediumDataModel tran_medium = (TransactionMediumDataModel) adapterView.getItemAtPosition(i);
                        // Here you can do the action you want to...
                        transaction_medium = tran_medium.getTm_desc().toString();
                        Log.e("transaction_medium",transaction_medium);


//                        Toast.makeText(Main.this, "ID: " + user.getId() + "\nName: " + user.getName(),
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                sp_transction_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Here you get the current item (a User object) that is selected by its position
                        TransactionTypeDataModel tran_type = (TransactionTypeDataModel) adapterView.getItemAtPosition(i);
                        // Here you can do the action you want to...
                        transaction_type = tran_type.getTt_desc();
                        Log.e("transaction_type",transaction_type);


//                        Toast.makeText(Main.this, "ID: " + user.getId() + "\nName: " + user.getName(),
//                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                if(sender_acc.isEmpty()){

                    et_sender_acc.setError("Please Enter Your Account first!");
                    et_sender_acc.requestFocus();

                }else if(receiver_acc.isEmpty()){

                    et_receiver_acc.setError("Please Enter Receiver Account first!");
                    et_receiver_acc.requestFocus();

                }else if(transaction_amount.isEmpty()){

                    et_transaction_amount.setError("Please Enter Transaction Amount first!");
                    et_transaction_amount.requestFocus();

                }/*else if(transaction_medium.isEmpty()){

                    et_transaction_medium.setError("Please Enter Transaction Medium first!");
                    et_transaction_medium.requestFocus();

                }else if(transaction_type.isEmpty()){

                    et_transaction_type.setError("Please Enter Transaction Type first!");
                    et_transaction_type.requestFocus();

                }*/else{


                    Transaction_Post_Request_Model reqM = new Transaction_Post_Request_Model();
                    reqM.setSender_acc(sender_acc);
                    reqM.setReceiver_acc(receiver_acc);
                    reqM.setTransaction_amount(transaction_amount);
//                    reqM.setTransaction_medium(transaction_medium);
//                    reqM.setTransaction_type(transaction_type);
                    reqM.setTransaction_medium(transaction_medium);
                    reqM.setTransaction_type(transaction_type);

                    pDialog.show();
                    post_Transaction(reqM);


                }

            }
        });



        btn_transaction_detail_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acNo = et_sender_acc.getText().toString();

                session.edit().putString("acNo", acNo).apply();

                intent = new Intent(getApplicationContext(),TranDetail_Get_Acc_Statement_Activity.class);
                intent.putExtra("acNo", acNo);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////// call Transaction_Type spinner api ////////////////////////////
        //////////////////////////////////////// call Transaction_Type spinner api ////////////////////////////

        get_Transaction_Type();

        //////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// call Transaction_Medium spinner api ////////////////////////////

        get_Transaction_Medium();

        ////////////////////////////////////////////////////////////////////////////////////

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
//////////////////////////// Transaction Post Api Disposal Call /////////////////////////////


    private void post_Transaction( Transaction_Post_Request_Model reqM ){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .postTransaction(reqM.getSender_acc(),
                                reqM.getReceiver_acc(),
                                reqM.getTransaction_amount(),
                                reqM.getTransaction_medium(),
                                reqM.getTransaction_type())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Transaction_Post_Receive_Model>() {//change 2
                            @Override
                            public void onSuccess( Transaction_Post_Receive_Model recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (recM.getErrorFlag().equals("N")) {
                                    //Successful
//                                    session.edit().putString("user_id", reqM.getUser_id()).commit();
//                                    session.edit().putString("session_id", "039248098").commit();
//                                    session.edit().putString("user_type", "ADMIN").commit();
//                                    session.edit().putString("user_mobile", "093284098").commit();
//                                    session.edit().putString("user_name", loginResult.user_name).commit();

                                    new CustomAlert().showSuccessMessage(TransactionPostTestActivity.this, "", recM.getErrorMessage());
                                    //Intent intent = new Intent(NavigationDrawer.this, Dashboard2.class);
//                                    intent.putExtra("id", reqM.getUser_id());
//                                    intent.putExtra("pass", reqM.getUser_password());
                                    // startActivity(intent);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(TransactionPostTestActivity.this, "", recM.getErrorMessage());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TransactionPostTestActivity.this);
                            }
                        }));



    }


    ///////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////// asyntask disposal network call for Transaction Type Spinner ///////////////////////////////////////

    private void get_Transaction_Type(){

       // pDialog.show();

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .getTransactionTypeAllData()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Transaction_Type_Get_All_Data_Return_M>>() {//change 2
                            @Override
                            public void onSuccess( List<Transaction_Type_Get_All_Data_Return_M> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() <1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(TransactionPostTestActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for(int i=0; i < recM.size(); i++ ){

                                        transactionTypeList.add(new TransactionTypeDataModel(recM.get(i).getTtId()+"",
                                                recM.get(i).getTtDesc()+"",
                                                recM.get(i).getTtStatus()+""));



                                    }

                                }


                                // Initialize the adapter sending the current context
                                // Send the simple_spinner_item layout
                                // And finally send the Users array (Your data)
                                adapter = new SpinAdapter_TransactionType(TransactionPostTestActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        transactionTypeList);
                                sp_transction_type = (Spinner) findViewById(R.id.sp_transction_type);
                                sp_transction_type.setAdapter(adapter); // Set the custom adapter to the spinner
                                // You can create an anonymous listener to handle the event when is selected an spinner item



                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TransactionPostTestActivity.this);
                            }
                        }));



    }



    ///////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////// asyntask disposal network call for Transaction Medium Spinner ///////////////////////////////////////

    private void get_Transaction_Medium(){

       // pDialog.show();

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .getTransactionMediumAllData()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Transaction_Medium_Get_All_Data_Return_M>>() {//change 2
                            @Override
                            public void onSuccess( List<Transaction_Medium_Get_All_Data_Return_M> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() <1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(TransactionPostTestActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for(int i=0; i < recM.size(); i++ ){

                                        transactionMediumList.add(new TransactionMediumDataModel(recM.get(i).getTmId()+"",
                                                recM.get(i).getTmDesc()+"",
                                                recM.get(i).getTmStatus()+""));



                                    }

                                }


                                // Initialize the adapter sending the current context
                                // Send the simple_spinner_item layout
                                // And finally send the Users array (Your data)
                                adapter2 = new SpinAdapter_TransactionMedium(TransactionPostTestActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        transactionMediumList);
                                sp_transction_medium = (Spinner) findViewById(R.id.sp_transction_medium);
                                sp_transction_medium.setAdapter(adapter2); // Set the custom adapter to the spinner
                                // You can create an anonymous listener to handle the event when is selected an spinner item



                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TransactionPostTestActivity.this);
                            }
                        }));



    }





}