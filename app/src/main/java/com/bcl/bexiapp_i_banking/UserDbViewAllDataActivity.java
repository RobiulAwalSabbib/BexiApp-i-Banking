package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bcl.bexiapp_i_banking.db.UserDB;
import com.bcl.bexiapp_i_banking.db.UserM;
import com.bcl.bexiapp_i_banking.db.UserViewAllRV;

import java.util.ArrayList;

public class UserDbViewAllDataActivity extends AppCompatActivity {

    RecyclerView rv;

    ArrayList<UserM> datamodel = new ArrayList<>();
    ArrayList<UserM> users = new ArrayList<>();

    UserDB userDB;
    UserM model;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_db_view_all_data);

        userDB = new UserDB(this);

        model = new UserM();

        rv = findViewById(R.id.rv_userdb_viewalldata);
//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        UserViewAllRV adapter = new UserViewAllRV(datamodel,this);
//        rv.setAdapter(adapter);
        showUsers();



    }

    private void showUsers() {
            try {
                users = userDB.getUsers();

                for (int i = 0; i < users.size(); i++) {
                    model = users.get(i);
                    datamodel.add(model);
                }
                 rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(this));
                UserViewAllRV adapter = new UserViewAllRV(datamodel,this);
                rv.setAdapter(adapter);
            } catch (Exception e) {

            }
    }





}