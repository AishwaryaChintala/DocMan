package com.example.aishu.docman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvEmail= (TextView) findViewById(R.id.txtEmail);
        tvEmail.setText(getIntent().getExtras().getString("Email"));

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId())
               {
                   case R.id.action_mydocs:
                       Toast.makeText(DashboardActivity.this, "action mydocs", Toast.LENGTH_SHORT).show();
                       Intent i1=new Intent(DashboardActivity.this,MydocsActivity.class);
                       startActivity(i1);


                       break;

                   case R.id.action_add1:
                       Toast.makeText(DashboardActivity.this, "action add", Toast.LENGTH_SHORT).show();
                       Intent i2=new Intent(DashboardActivity.this,AddActivity.class);
                       startActivity(i2);
                       break;

                   

                   case R.id.action_fav:
                       Toast.makeText(DashboardActivity.this, "action Location", Toast.LENGTH_SHORT).show();
                       Intent i4=new Intent(DashboardActivity.this,FavourtiesActivity.class);
                       startActivity(i4);
                       break;
                   case R.id.action_logout:
                       Toast.makeText(DashboardActivity.this, "action Log out", Toast.LENGTH_SHORT).show();
                       Intent i5=new Intent(DashboardActivity.this,LogoutActivity.class);
                       startActivity(i5);
                       break;

               }
               return true;

            }
        });



    }
}
