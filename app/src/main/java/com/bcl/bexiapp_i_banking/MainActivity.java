package com.bcl.bexiapp_i_banking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.adapter.SlideShowAdapter;
import com.bcl.bexiapp_i_banking.ekyc.Ekyc_Personal_Form_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    Button btn_app_login_activity,btn_app_registration_activity;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Intent intent;
    ViewPager viewPager;
    SlideShowAdapter slideShowAdapter;
    CircleIndicator circleIndicator;
    Handler handler;
    Runnable runnable;
    Timer timer;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////////////////////////////////////////Bottom Navigation View Setup//////////////////////
        bottomNavigationView = findViewById(R.id.bottomNavigation_ID);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);



        ////////////////////////////////////////// Toolbar Button SetUp//////////////////////////

        btn_app_login_activity = findViewById(R.id.btn_app_login_activity);
        btn_app_registration_activity = findViewById(R.id.btn_app_registration_activity);

        btn_app_login_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });

        btn_app_registration_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

//////////////////////////////////// Navigation Drawer and Setup //////////////////////////////////////
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerlayout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.menu_ekyc:
                        Toast.makeText(getApplicationContext(),"EKYC Activity",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(), Ekyc_Personal_Form_Activity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_contact:
                        Toast.makeText(getApplicationContext(),"contact is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_setting:
                        Toast.makeText(getApplicationContext(),"setting is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_image_upload:
                        Toast.makeText(getApplicationContext(),"login/signup is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(), ProfileUploadActivity.class);
                        startActivity(intent);
                        break;




                    case R.id.menu_test:
                        Toast.makeText(getApplicationContext(),"Test Activity is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(),TestActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_transaction_post:
                        Toast.makeText(getApplicationContext(),"Test Activity is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(),TransactionPostTestActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_transaction_history_get:
                        Toast.makeText(getApplicationContext(),"Test Activity is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(),TransactionHistoryTestActivity.class);
                        startActivity(intent);
                        break;




                    case R.id.menu_Topup_Request_Post:
                        Toast.makeText(getApplicationContext(),"TopUp is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(),TopupRequestActivity.class);
                        startActivity(intent);
                        break;



                    case R.id.menu_UserDbTest:
                        Toast.makeText(getApplicationContext(),"User DB is here",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(getApplicationContext(),UserDbTestActivity.class);
                        startActivity(intent);
                        break;



                }


                return true;
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////// ViewPager and SlideShowAdapter and Circle Indicator //////////////////////

        viewPager =(ViewPager)findViewById(R.id.viewpager_id);
        circleIndicator = (CircleIndicator)findViewById(R.id.circleIndicator_id);
        slideShowAdapter = new SlideShowAdapter(this);
        viewPager.setAdapter(slideShowAdapter);
        circleIndicator.setViewPager(viewPager);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////// handler , Runnable and timer ///////////////////////////


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                int i = viewPager.getCurrentItem();

                if(i==slideShowAdapter.images.length - 1){

                    i = 0;
                    viewPager.setCurrentItem(i,true);
                }else {

                    i++;
                    viewPager.setCurrentItem(i,true);

                }

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        },3000,3000);


        /////////////////////////////////////////////////////////////////////////////////////////////////////////



    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// ToolBar Menu inflate //////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.logout_db){
            Toast.makeText(getApplicationContext(),"logout selected",Toast.LENGTH_LONG).show();
        }else if(id == R.id.profile_db){
            Toast.makeText(getApplicationContext(),"profile selected",Toast.LENGTH_LONG).show();
        }else if(id == R.id.changeLanguage){

            Toast.makeText(getApplicationContext(),"language change",Toast.LENGTH_LONG).show();
        }

        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.bottom_navigation_branch_list:
                Toast.makeText(MainActivity.this, "Branch List", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.bottom_navigation_branch_map:
                Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.bottom_navigation_notification:
                Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }





}
