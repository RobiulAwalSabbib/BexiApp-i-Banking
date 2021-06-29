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
import android.widget.TextView;

import com.bcl.bexiapp_i_banking.Login.LoginDataM;
import com.bcl.bexiapp_i_banking.Login.LoginReqM;
import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Request_Data_Model;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LogInActivity extends AppCompatActivity {


    EditText et_user_log,et_pass_log;
    Button btn_login_log;
    TextView tv_reg_log;
    Intent intent;

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et_user_log = findViewById(R.id.et_user_log);
        et_pass_log = findViewById(R.id.et_pass_log);
        tv_reg_log = findViewById(R.id.tv_reg_log);
        btn_login_log = findViewById(R.id.btn_login_log);

        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

        tv_reg_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LogInActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btn_login_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = et_user_log.getText().toString();
                String pass = et_pass_log.getText().toString();

                if(user.isEmpty()){

                    et_user_log.setError("Please Enter User first!");
                    et_user_log.requestFocus();

                }else if(pass.isEmpty()){

                    et_pass_log.setError("Please Enter Password first!");
                    et_pass_log.requestFocus();

                }else{


                    LoginReqM reqM = new LoginReqM();
                    reqM.setUser_id(user);
                    reqM.setUser_password(pass);

                    pDialog.show();
                    doLogin(reqM);


                }


            }
        });

    }



    private void doLogin(final LoginReqM reqM) {


        disposable.add(
                apiService

                        //change 1
                        .getLogin(reqM.getUser_id(),
                                reqM.getUser_password())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<LoginDataM>() {//change 2
                            @Override
                            public void onSuccess(LoginDataM loginResult) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (loginResult.pErrorFlag.equals("N")) {
                                    //Successful
//                                    session.edit().putString("user_id", reqM.getUser_id()).commit();
//                                    session.edit().putString("session_id", "039248098").commit();
//                                    session.edit().putString("user_type", "ADMIN").commit();
//                                    session.edit().putString("user_mobile", "093284098").commit();
//                                    session.edit().putString("user_name", loginResult.user_name).commit();


                                    Intent intent = new Intent(LogInActivity.this, ApiDashboardActivity.class);
//                                    intent.putExtra("id", reqM.getUser_id());
//                                    intent.putExtra("pass", reqM.getUser_password());
                                    startActivity(intent);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(LogInActivity.this, "", loginResult.pErrorMessage);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, LogInActivity.this);
                            }
                        }));
    }




//////////////////////////// keyboard updown //////////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}