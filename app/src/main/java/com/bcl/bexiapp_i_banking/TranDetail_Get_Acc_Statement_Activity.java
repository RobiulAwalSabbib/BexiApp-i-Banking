package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementAdapter;
import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementDataModel;
import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementReceiveModel;
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

public class TranDetail_Get_Acc_Statement_Activity extends AppCompatActivity {


    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    SharedPreferences session;

    ArrayList<StatementDataModel> statmetList = new ArrayList<>();

    RecyclerView rv_statement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_detail__get__acc__statement_);



        session = getSharedPreferences("app_session", MODE_PRIVATE);

        rv_statement = findViewById(R.id.rv_statement);

        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");



        get_TranDetail(session.getString("acNo", "0"));

    }





    private void get_TranDetail( String acNo ){

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .getAllStatementData(acNo)


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<StatementReceiveModel>>() {//change 2
                            @Override
                            public void onSuccess( List<StatementReceiveModel> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if(null == recM || recM.size()<1){
                                    new CustomAlert().showErrorMessage(TranDetail_Get_Acc_Statement_Activity.this, "", "No Statement Data Found!");
                                }else {

                                    for (int i = 0; i < recM.size(); i++) {
                                        statmetList.add(new StatementDataModel(
                                                recM.get(i).getTnumber()+"",
                                                recM.get(i).getAcnumber()+"",
                                                recM.get(i).getDot()+"",
                                                recM.get(i).getMot()+"",
                                                recM.get(i).getTransactionType()+"",
                                                recM.get(i).getTransactionAmount()+"",
                                                recM.get(i).getDestinationAc()+"",
                                                recM.get(i).getOpeningBalance()+""
                                        ));
                                    }


                                }



                                StatementAdapter adapter = new StatementAdapter(statmetList);

                                rv_statement.setHasFixedSize(true);
                                rv_statement.setLayoutManager(new LinearLayoutManager(TranDetail_Get_Acc_Statement_Activity.this));
                                rv_statement.setAdapter(adapter);

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, TranDetail_Get_Acc_Statement_Activity.this);
                            }
                        }));



    }


}