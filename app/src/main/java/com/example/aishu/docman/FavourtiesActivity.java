package com.example.aishu.docman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FavourtiesActivity extends AppCompatActivity {

    Button b;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourties);
        b=(Button)findViewById(R.id.buttonLoc);
        b1=(Button)findViewById(R.id.buttonShow);



    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(FavourtiesActivity.this, LocationActivity.class);
            startActivity(i);
        }
    });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FavourtiesActivity.this, LocationShowActivity.class);
                startActivity(i);
            }
        });



    }
}

