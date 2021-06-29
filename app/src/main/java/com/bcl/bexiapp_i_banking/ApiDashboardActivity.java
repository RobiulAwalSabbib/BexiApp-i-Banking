package com.bcl.bexiapp_i_banking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.widget.Spinner;

import com.bcl.bexiapp_i_banking.Menu_Get.DataModel;
import com.bcl.bexiapp_i_banking.Menu_Get.MenuAdapter;
import com.bcl.bexiapp_i_banking.Menu_Get.ReceiveModel;
import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementAdapter;
import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementDataModel;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.TransactionTypeDataModel;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.Transaction_Type_Get_All_Data_Return_M;
import com.bcl.bexiapp_i_banking.adapter.SpinAdapter_TransactionType;
import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ApiDashboardActivity extends AppCompatActivity implements MenuAdapter.OnNoteListener {


    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;

    Intent intent;

    RecyclerView rv_menu_get;
    Context context ;

    ArrayList<DataModel> dataModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_dashboard);

        rv_menu_get = findViewById(R.id.rv_menu_get);

        //////////////////////// network call initialization ////////////////////////////
        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

        /////////////////////// call menu api //////////////////////////////////////
        get_Menu();

        ///////////////////////////////////////////////////////////////////////////////

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// call menu api /////////////////////////////////////////

    private void get_Menu(){

         pDialog.show();

        disposable.add(
                (Disposable) apiService

                        //change 1
                        .getMenuAllData()


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<ReceiveModel>>() {//change 2
                            @Override
                            public void onSuccess( List<ReceiveModel> recM ) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Receive  Result
                                if (recM == null || recM.size() <1) {
                                    //UnSuccessful

                                    new CustomAlert().showErrorMessage(ApiDashboardActivity.this, "", "No Data Found.....!");

                                } else {
                                    //Successful

                                    for(int i=0; i < recM.size(); i++ ){

                                        dataModels.add(new DataModel(recM.get(i).getSlNo()+"",
                                                recM.get(i).getUnitType()+"",
                                                recM.get(i).getMenuCode()+"",
                                                recM.get(i).getParentCode()+"",
                                                recM.get(i).getDescription()+"",
                                                recM.get(i).getWebParmMobNo()+"",
                                                recM.get(i).getWebParmToAccNo()+"",
                                                recM.get(i).getWebParmTranAmt()+"",
                                                recM.get(i).getWebParmPinNo()+"",
                                                recM.get(i).getPermAdd()+"",
                                                recM.get(i).getPermModify()+"",
                                                recM.get(i).getPermDelete()+"",
                                                recM.get(i).getPermView()+"",
                                                recM.get(i).getCreatedCode()+"",
                                                recM.get(i).getCreatedDate()+""
                                                ));



                                    }

                                }


                                MenuAdapter adapter = new MenuAdapter(dataModels,context,ApiDashboardActivity.this);

                                rv_menu_get.setHasFixedSize(true);
                                rv_menu_get.setLayoutManager(new LinearLayoutManager(ApiDashboardActivity.this));
                                rv_menu_get.setAdapter(adapter);



                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                // Log.e(TAG, "onError: " + e.getMessage());
                                ErrorUtil.showError(e, ApiDashboardActivity.this);
                            }
                        }));



    }


    @Override
    public void onNoteClick(int position) {

        dataModels.get(position);
        if (dataModels.get(position).getSL_NO().equals("20")){

            intent = new Intent(ApiDashboardActivity.this,TopupRequestActivity.class);
            startActivity(intent);

        }

    }
}