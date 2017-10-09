package com.example.aishu.docman;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationActivity extends AppCompatActivity {
    EditText dname, dloc;
    Button save;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        dname = (EditText) findViewById(R.id.dname);
        dloc = (EditText) findViewById(R.id.dloc);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    String userid=mAuth.getCurrentUser().getUid();
                    String docname = dname.getText().toString().trim();
                    String docloc = dloc.getText().toString().trim();

                    // create user object and set all the properties
                    User user = new User();
                    user.setDocname(docname);
                    user.setDocLoc(docloc);

                    mDatabase.child("Location").child(userid).push().setValue(user);

                    Toast.makeText(LocationActivity.this, "Data is saved successfully",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }


            // to check if user filled all the required fieds
            public boolean validateForm() {
                boolean alldone = true;
                String docname = dname.getText().toString().trim();
                String docloc = dloc.getText().toString().trim();

                if (TextUtils.isEmpty(docname)) {
                    dname.setError("Enter Document name");
                    return false;
                } else {
                    alldone = true;
                    dname.setError(null);
                }
                if (TextUtils.isEmpty(docloc)) {
                    dloc.setError("Enter Document Location");
                    return false;
                } else {
                    alldone = true;
                    dloc.setError(null);
                }

                return alldone;
            }
        });
    }
}

