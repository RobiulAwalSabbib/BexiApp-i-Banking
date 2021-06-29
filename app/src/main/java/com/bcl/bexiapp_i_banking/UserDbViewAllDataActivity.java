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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_db_view_all_data);

        userDB = new UserDB(this);
        //datamodel = userDB.getUsers();

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
                 rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(this));
                UserViewAllRV adapter = new UserViewAllRV(users,this);
                rv.setAdapter(adapter);
            } catch (Exception e) {

            }
    }





}