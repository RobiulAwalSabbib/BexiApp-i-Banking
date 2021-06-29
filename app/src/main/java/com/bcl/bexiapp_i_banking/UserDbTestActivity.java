package com.bcl.bexiapp_i_banking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.db.UserDB;
import com.bcl.bexiapp_i_banking.db.UserM;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class UserDbTestActivity extends AppCompatActivity {

    TextInputEditText et_userdb_name, et_userdb_mobile, et_userdb_email, et_userdb_dob, et_userdb_update_id;
    Button btn_userdb_insert, btn_userdb_view_all_row, btn_userdb_export, btn_userdb_import, btn_userdb_update, btn_userdb_delete_single_row, btn_userdb_view_single_row;
    TextView tv_userdb_showdata;
    ArrayList<UserM> users = new ArrayList<>();

    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_db_test);

        et_userdb_name = findViewById(R.id.et_userdb_name);
        et_userdb_mobile = findViewById(R.id.et_userdb_mobile);
        et_userdb_email = findViewById(R.id.et_userdb_email);
        et_userdb_dob = findViewById(R.id.et_userdb_dob);
        et_userdb_update_id = findViewById(R.id.et_userdb_update_id);

        btn_userdb_insert = findViewById(R.id.btn_userdb_insert);
        btn_userdb_view_all_row = findViewById(R.id.btn_userdb_view_all_row);
        btn_userdb_export = findViewById(R.id.btn_userdb_export);
        btn_userdb_import = findViewById(R.id.btn_userdb_import);
        btn_userdb_update = findViewById(R.id.btn_userdb_update);
        btn_userdb_delete_single_row = findViewById(R.id.btn_userdb_delete_single_row);
        btn_userdb_view_single_row = findViewById(R.id.btn_userdb_view_single_row);

        tv_userdb_showdata = findViewById(R.id.tv_userdb_showdata);


        //////////////////////// initialize database ///////////////////////////////////

        userDB = new UserDB(this);

        btn_userdb_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserM m = new UserM();
                m.setName(Objects.requireNonNull(et_userdb_name.getText()).toString());
                m.setMobile(Objects.requireNonNull(et_userdb_mobile.getText()).toString());
                m.setEmail(Objects.requireNonNull(et_userdb_email.getText()).toString());
                m.setDob(Objects.requireNonNull(et_userdb_dob.getText()).toString());

                long response = userDB.saveUser(m);
                showUsers();
                if (response > 0) {
                    new CustomAlert().showSuccessMessage(UserDbTestActivity.this, "", "Insert Successful!");
                } else {
                    new CustomAlert().showErrorMessage(UserDbTestActivity.this, "", "Insert Failed!");
                }

            }
        });


        btn_userdb_view_all_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDbTestActivity.this, UserDbViewAllDataActivity.class);
                startActivity(intent);
            }
        });



        ////////////////////////// call showUsers()/////////
        showUsers();

    }


    private void showUsers() {
        try {
            users = userDB.getUsers();
            String data = "";
            UserM model = new UserM();
            for (int i = 0; i < users.size(); i++) {
                model = users.get(i);
                data += "\n" + "ID: " + model.getId() + " Name: " + model.getName() + " Mobile: " + model.getMobile() + " DOB: " + model.getDob();
            }

            tv_userdb_showdata.setText(data);
        } catch (Exception e) {

        }
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