package com.bcl.bexiapp_i_banking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Receive_Data_Model;
import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Request_Data_Model;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.customer_registration_post.Customer_Registration_Receive_Model;
import com.bcl.bexiapp_i_banking.customer_registration_post.Customer_Registration_Request_Model;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.bcl.bexiapp_i_banking.App.CHANNEL_1_ID;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_goto_loginActivity;
    RadioGroup rg_registration_acNo;

    EditText et_name_reg,et_mobile_reg,et_email_reg,et_dob_reg,et_password_reg,et_confirmPassword_reg,et_ac_no_reg,et_password_reg_1;
    Button btn_reg_reg,btn_reg_reg_1;

    // notification manager
    private NotificationManagerCompat notificationManager;

    CardView cv_regi_formate_1,cv_regi_formate_2;

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        tv_goto_loginActivity = findViewById(R.id.tv_goto_loginActivity);
        rg_registration_acNo = findViewById(R.id.rg_registration_acNo);
        cv_regi_formate_1 = findViewById(R.id.cv_regi_formate_1);
        cv_regi_formate_2 = findViewById(R.id.cv_regi_formate_2);

        et_name_reg = findViewById(R.id.et_name_reg);
        et_mobile_reg = findViewById(R.id.et_mobile_reg);
        et_email_reg = findViewById(R.id.et_email_reg);
        et_dob_reg = findViewById(R.id.et_dob_reg);
        et_password_reg = findViewById(R.id.et_password_reg);
        et_confirmPassword_reg = findViewById(R.id.et_confirmPassword_reg);
        et_ac_no_reg = findViewById(R.id.et_ac_no_reg);
        et_password_reg_1 = findViewById(R.id.et_password_reg_1);

        btn_reg_reg = findViewById(R.id.btn_reg_reg);
        btn_reg_reg_1 = findViewById(R.id.btn_reg_reg_1);

        // // notification manager
        notificationManager = NotificationManagerCompat.from(this);


        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");


////////////////////////////////////////// Radio Group Call onClick ///////////////////////
        rg_registration_acNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_registration_no){

                    cv_regi_formate_2.setVisibility(View.GONE);
                    cv_regi_formate_1.setVisibility(View.VISIBLE);

                }else if(checkedId == R.id.rb_registration_yes){


                    cv_regi_formate_1.setVisibility(View.GONE);
                    cv_regi_formate_2.setVisibility(View.VISIBLE);

                }
            }
        });



       //  home action bar configuration
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registration Activity");
        actionBar.setSubtitle("BexiApp I Banking");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);


///////////////////////////// Goto Lonin Activity ////////////////////////////////////////////

        tv_goto_loginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });


////////////////////////////////// Post New Registration ////////////////////////////////////////
        btn_reg_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_name_reg.getText().toString();
                String mobile = et_mobile_reg.getText().toString();
                String email = et_email_reg.getText().toString();
                String dob = et_dob_reg.getText().toString();
                String password = et_password_reg.getText().toString();
                String conPassword = et_confirmPassword_reg.getText().toString();

                if(name.isEmpty()){
                    et_name_reg.setError("Please Enter Your Name first!");
                    et_name_reg.requestFocus();
                }else if(mobile.isEmpty()){

                    et_mobile_reg.setError("Please Enter Your Mobile first!");
                    et_mobile_reg.requestFocus();


                }else if(email.isEmpty()){

                    et_email_reg.setError("Please Enter Your Email first!");
                    et_email_reg.requestFocus();


                }else if(dob.isEmpty()){

                    et_dob_reg.setError("Please Enter Your DOB first!");
                    et_dob_reg.requestFocus();


                }
                else if(password.isEmpty()){

                    et_password_reg.setError("Please Enter Your Password first!");
                    et_password_reg.requestFocus();


                } else if(conPassword.isEmpty()){

                    et_confirmPassword_reg.setError("Please Confirm Password first!");
                    et_confirmPassword_reg.requestFocus();


                }else{

                    Customer_Registration_Request_Model reqM = new Customer_Registration_Request_Model();
                    reqM.setApplicant_name(name);
                    reqM.setMobile(mobile);
                    reqM.setEmail(email);
                    reqM.setDob(dob);

                    reqM.setPasswords(password);
                    reqM.setConfirm_passwords(conPassword);

                    pDialog.show();
                    post_Customer_Registration(reqM);


                }

            }
        });

    }



    ////////////////////////////////////// back arrow toolbar button alert use /////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            leaveAlert();
        }
        return true;
    }



    /////////////////////////////////////////// keypad up-down////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    ////////////////////////////////////// back arrow toolbar button alert //////////////////////////////
    private void leaveAlert(){

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrationActivity.this);
        alertBuilder.setTitle("Confirmation!");
        alertBuilder.setMessage("Do you Want to leave the page?");
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                dialog.dismiss();

                //finish();
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //showToast("No");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }


    ////////////////////////////////// Customer Registration Post Api call ////////////////////////////////////////

    private void post_Customer_Registration( Customer_Registration_Request_Model reqM ){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .postCustomerRegistration(reqM.getApplicant_name(),
                                reqM.getMobile(),
                                reqM.getEmail(),
                                reqM.getDob(),
                                reqM.getPasswords(),
                                reqM.getConfirm_passwords())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Customer_Registration_Receive_Model>() {//change 2
                            @Override
                            public void onSuccess( Customer_Registration_Receive_Model recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive   Result
                                if (recM.getpErrorFlag().equals("N")) {
                                    //Successful

                                    Notification notification = new NotificationCompat.Builder(RegistrationActivity.this, CHANNEL_1_ID)
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

                                    new CustomAlert().showSuccessMessage(RegistrationActivity.this, "", recM.getpErrorMessage());
                                    //Intent intent = new Intent(NavigationDrawer.this, Dashboard2.class);
//                                    intent.putExtra("id", reqM.getUser_id());
//                                    intent.putExtra("pass", reqM.getUser_password());
                                    // startActivity(intent);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(RegistrationActivity.this, "", recM.getpErrorMessage());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, RegistrationActivity.this);
                            }
                        }));



    }


}