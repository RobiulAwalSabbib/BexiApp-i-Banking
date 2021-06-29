package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerGetAllDataM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRVadapter;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRecM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerReqM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.GetDataModel;
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

public class Ag_Customer_Getall_Data_Activity extends AppCompatActivity {

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    List<GetDataModel> customerList = new ArrayList<>();

    RecyclerView rv_getAllCustomerData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ag__customer__getall__data);



        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");
        ///////////////////////////////////////////////////////////////////////////////////

        rv_getAllCustomerData = findViewById(R.id.rv_getAllCustomerData);

        get_AG_CUSTOMER();

    }



    private void get_AG_CUSTOMER(){
        pDialog.show();
        disposable.add(
                (Disposable) apiService

                        //change 1
                        .getAGcustomerAllData()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<AGCustomerGetAllDataM>>() {//change 2
                            @Override
                            public void onSuccess( List<AGCustomerGetAllDataM> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() <1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(Ag_Customer_Getall_Data_Activity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for(int i=0; i < recM.size(); i++ ){

                                        customerList.add(new GetDataModel(recM.get(i).getCustId()+"",
                                                                                recM.get(i).getCustName()+"",
                                                                                    recM.get(i).getCustGender()+"",
                                                                        recM.get(i).getCustDob()+""));


                                    }

                                }

                                AGCustomerRVadapter adapter = new AGCustomerRVadapter(customerList);

                                rv_getAllCustomerData.setHasFixedSize(true);
                                rv_getAllCustomerData.setLayoutManager(new LinearLayoutManager(Ag_Customer_Getall_Data_Activity.this));
                                rv_getAllCustomerData.setAdapter(adapter);


                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, Ag_Customer_Getall_Data_Activity.this);
                            }
                        }));



    }





}