package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
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

import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Receive_Data_Model;
import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Request_Data_Model;
import com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data.TransactionMediumDataModel;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Receive_Model;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Request_Model;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.TransactionTypeDataModel;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.bcl.bexiapp_i_banking.App.CHANNEL_1_ID;

public class TopupRequestActivity extends AppCompatActivity {

    EditText et_MOBILE,et_OPERATORS_CODE,et_SIM_TYPE_NAME,et_AMOUNT,et_RECHARGE_CODE;
    Button btn_topup_request_post;
    Spinner sp_OPERATORS_CODE,sp_SIM_TYPE_NAME;

    String [] opretor = {"Robi","Bangla-Link","Airtel","GramminPhone","TeleTalk"};
    String [] simType = {"PostPaid","PrePaid"};

    String v_operator = "";
    String v_simType = "";



    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;
    // notification manager
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_request);

        // // notification manager
        notificationManager = NotificationManagerCompat.from(this);

        et_MOBILE = findViewById(R.id.et_MOBILE);
//        et_OPERATORS_CODE = findViewById(R.id.et_OPERATORS_CODE);
//        et_SIM_TYPE_NAME = findViewById(R.id.et_SIM_TYPE_NAME);
        et_AMOUNT = findViewById(R.id.et_AMOUNT);
        et_RECHARGE_CODE = findViewById(R.id.et_RECHARGE_CODE);

        sp_OPERATORS_CODE = findViewById(R.id.sp_OPERATORS_CODE);
        sp_SIM_TYPE_NAME = findViewById(R.id.sp_SIM_TYPE_NAME);


        btn_topup_request_post = findViewById(R.id.btn_topup_request_post);

        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");


        ////////////////////////////////// set operator spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> operatorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opretor);

        // Specify the layout to use when the list of choices appears
        operatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sp_OPERATORS_CODE.setAdapter(operatorAdapter);




        sp_OPERATORS_CODE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    showToast("Robi Selected");
                    v_operator = "Robi";
                }else if(position == 1){
                    showToast("BangLalink Selected");
                    v_operator = "BanglaLink";
                }else if(position == 2) {
                    showToast("Airtel Selected");
                    v_operator = "Airtel";
                }else if(position == 3) {
                    showToast("GraminPhone Selected");
                    v_operator = "GraminPhone";
                }else if(position == 4) {
                    showToast("TeleTalk Selected");
                    v_operator = "TeleTalk";
                }else{
                    showToast("Nothign selected");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////////////////////////// set Sim Type spinner /////////////////////////////////

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> simAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, simType);

        // Specify the layout to use when the list of choices appears
        simAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sp_SIM_TYPE_NAME.setAdapter(simAdapter);




        sp_SIM_TYPE_NAME.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    showToast("PostPaid Selected");
                    v_simType = "PostPaid";
                }else if(position == 1){
                    showToast("BangLalink Selected");
                    v_simType = "PrePaid";
                   }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////

        btn_topup_request_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String MOBILE = et_MOBILE.getText().toString();
                String OPERATORS_CODE = v_operator;
                String SIM_TYPE_NAME = v_simType;
                String AMOUNT = et_AMOUNT.getText().toString();
                String RECHARGE_CODE = et_RECHARGE_CODE.getText().toString();




                if(MOBILE.isEmpty()){

                    et_MOBILE.setError("Please Enter Your Cell No first!");
                    et_MOBILE.requestFocus();

                }else if(OPERATORS_CODE.isEmpty()){

                    et_OPERATORS_CODE.setError("Please Enter OPERATORS CODE first!");
                    et_OPERATORS_CODE.requestFocus();

                }else if(SIM_TYPE_NAME.isEmpty()){

                    et_SIM_TYPE_NAME.setError("Please Enter SIM_TYPE_NAME first!");
                    et_SIM_TYPE_NAME.requestFocus();

                }else if(AMOUNT.isEmpty()){

                    et_AMOUNT.setError("Please Enter Amount first!");
                    et_AMOUNT.requestFocus();

                }else if(RECHARGE_CODE.isEmpty()){

                    et_RECHARGE_CODE.setError("Please Enter RECHARGE_CODE first!");
                    et_RECHARGE_CODE.requestFocus();

                }else{


                    Recharge_Request_Post_Request_Data_Model reqM = new Recharge_Request_Post_Request_Data_Model();
                    reqM.setMOBILE(MOBILE);
                    reqM.setOPERATORS_CODE(OPERATORS_CODE);
                    reqM.setSIM_TYPE_NAME(SIM_TYPE_NAME);
                    reqM.setAMOUNT(AMOUNT);
                    reqM.setRECHARGE_CODE(RECHARGE_CODE);

                    pDialog.show();
                    post_TopUpRequest(reqM);


                }

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



    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// Post TopUp Request /////////////////////////////////////////


    private void post_TopUpRequest( Recharge_Request_Post_Request_Data_Model reqM ){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .postRechargeRequest(reqM.getMOBILE(),
                                reqM.getOPERATORS_CODE(),
                                reqM.getSIM_TYPE_NAME(),
                                reqM.getAMOUNT(),
                                reqM.getRECHARGE_CODE())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Recharge_Request_Post_Receive_Data_Model>() {//change 2
                            @Override
                            public void onSuccess( Recharge_Request_Post_Receive_Data_Model recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive Topup Result  Result
                                if (recM.getpErrorFlag().equals("N")) {
                                    //Successful

                                    Notification notification = new NotificationCompat.Builder(TopupRequestActivity.this, CHANNEL_1_ID)
                                            .setSmallIcon(R.drawable.alert_icon)
                                            .setContentTitle(recM.getpErrorFlag())
                                            .setContentText(recM.getpErrorMessage())
                                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                            .build();
                                    notificationManager.notify(1, notification);


//                                    session.edit().putString("user_id", reqM.getUser_id()).commit();
//                                    session.edit().putString("session_id", "039248098").commit();
//                                    session.edit().putString("user_type", "ADMIN").commit();
//                                    session.edit().putString("user_mobile", "093284098").commit();
//                                    session.edit().putString("user_name", loginResult.user_name).commit();

                                    new CustomAlert().showSuccessMessage(TopupRequestActivity.this, "", recM.getpErrorMessage());
                                    //Intent intent = new Intent(NavigationDrawer.this, Dashboard2.class);
//                                    intent.putExtra("id", reqM.getUser_id());
//                                    intent.putExtra("pass", reqM.getUser_password());
                                    // startActivity(intent);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(TopupRequestActivity.this, "", recM.getpErrorMessage());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TopupRequestActivity.this);
                            }
                        }));



    }


    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}