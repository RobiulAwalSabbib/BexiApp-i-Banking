package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerGetAllDataM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRVadapter;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.GetDataModel;
import com.bcl.bexiapp_i_banking.Transaction_History_Get.Single_Account_DateRange_RV;
import com.bcl.bexiapp_i_banking.Transaction_History_Get.Single_Account_DateRange_Record_Data_Model;
import com.bcl.bexiapp_i_banking.Transaction_History_Get.Single_Account_DateRange_Record_Receive_Model;
import com.bcl.bexiapp_i_banking.Transaction_History_Get.Single_Account_DateRange_Record_Request_Model;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Request_Model;
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

public class TransactionHistoryTestActivity extends AppCompatActivity {

    EditText et_transaction_history_accno,et_transaction_history_fromdate,et_transaction_history_todate;
    Button btn_transaction_history_get_singleAcc;

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    RecyclerView recyclerView;

    ArrayList<Single_Account_DateRange_Record_Data_Model>  dataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history_test);


        et_transaction_history_accno = findViewById(R.id.et_transaction_history_accno);
        et_transaction_history_fromdate = findViewById(R.id.et_transaction_history_fromdate);
        et_transaction_history_todate = findViewById(R.id.et_transaction_history_todate);

        btn_transaction_history_get_singleAcc = findViewById(R.id.btn_transaction_history_get_singleAcc);

        recyclerView = findViewById(R.id.rv_transaction_history);


        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");


        ///////////////////////////////////////////////////////////////////////////////////
        //////////////////////// set Button Onclick ////////////////////////////////////

        btn_transaction_history_get_singleAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accno = et_transaction_history_accno.getText().toString();
                String fdate = et_transaction_history_fromdate.getText().toString();
                String tdate = et_transaction_history_todate.getText().toString();

                if(accno.isEmpty()){

                    et_transaction_history_accno.setError("Please Enter Your Account first!");
                    et_transaction_history_accno.requestFocus();

                }else if(fdate.isEmpty()){

                    et_transaction_history_fromdate.setError("Please Enter From Date first!");
                    et_transaction_history_fromdate.requestFocus();

                }else if(tdate.isEmpty()){

                    et_transaction_history_todate.setError("Please Enter To Date  first!");
                    et_transaction_history_todate.requestFocus();

                }else{


                    Single_Account_DateRange_Record_Request_Model reqM = new Single_Account_DateRange_Record_Request_Model();
                    reqM.setAcc_no(accno);
                    reqM.setFrom_date(fdate);
                    reqM.setTo_date(tdate);
//

                    pDialog.show();
                    get_Transaction_History_Single_Acc(reqM);


                }



            }
        });


    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// disposal method for call transaction History Get Data Single Acc/////////////

    private void get_Transaction_History_Single_Acc(Single_Account_DateRange_Record_Request_Model reqM){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .get_Tran_Hist_Single_Acc_Record(reqM.getAcc_no(),
                                                                    reqM.getFrom_date(),
                                                                    reqM.getTo_date())


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Single_Account_DateRange_Record_Receive_Model>>() {//change 2
                            @Override
                            public void onSuccess( List<Single_Account_DateRange_Record_Receive_Model> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() <1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(TransactionHistoryTestActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for(int i=0; i < recM.size(); i++ ){

                                        dataModels.add(new Single_Account_DateRange_Record_Data_Model(recM.get(i).getSlNo()+"",
                                                recM.get(i).getTranNo()+"",
                                                recM.get(i).getDat()+"",
                                                recM.get(i).getFromAcc()+"",
                                                recM.get(i).getTranMedium()+"",
                                                recM.get(i).getTranType()+"",
                                                recM.get(i).getTranAmount()+"",
                                                recM.get(i).getToAcc()+"",
                                                recM.get(i).getOldBalance()+"",
                                                recM.get(i).getNewBalance()+""
                                                ));


                                    }

                                }

                                Single_Account_DateRange_RV adapter = new Single_Account_DateRange_RV(dataModels);

                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(TransactionHistoryTestActivity.this));
                                recyclerView.setAdapter(adapter);


                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TransactionHistoryTestActivity.this);
                            }
                        }));



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





}