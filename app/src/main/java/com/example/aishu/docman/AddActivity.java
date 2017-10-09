package com.example.aishu.docman;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class AddActivity extends AppCompatActivity {
    private Button mUploadBtn;
    private EditText editTextName;
    private ImageView mImageView;
    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private Uri imageUri;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mAuth = FirebaseAuth.getInstance();
        mStorage= FirebaseStorage.getInstance().getReference();
        String userid=mAuth.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog1").child(userid);


        mUploadBtn=(Button) findViewById(R.id.upload);
        mImageView=(ImageView) findViewById(R.id.imageView);
        editTextName=(EditText) findViewById(R.id.editText);



        mUploadBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK)
        {
            imageUri=data.getData();
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] databaos = baos.toByteArray();
            mImageView.setImageBitmap(bitmap);
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();


            long productname;
            final String name=editTextName.getText().toString().trim();
            final StorageReference imagesRef = storageRef.child("Photos").child(name);

            //send this name to database
            //upload image
            UploadTask uploadTask = imagesRef.putBytes(databaos);
             uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddActivity.this, "Sending done", Toast.LENGTH_SHORT).show();
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    DatabaseReference newpost=mDatabase.push();
                    //String userid=mAuth.getCurrentUser().getUid();

                    newpost.child("title").setValue(downloadUrl.toString());
                    newpost.child("Name").setValue(name);



                }
            });
        }
    }

}








