package com.example.aishu.docman;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MydocsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;



    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydocs);
        mAuth=FirebaseAuth.getInstance();
        String userid=mAuth.getCurrentUser().getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog1").child(userid);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }
    public void onStart()
    {
        super.onStart();

        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.layout_images,
                BlogViewHolder.class,
                mDatabase





        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setTitle(getApplicationContext(),model.getTitle());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);





    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;

        }
        public void setName(String Name)
        {
            TextView textViewName= mView.findViewById(R.id.textViewName);
            textViewName.setText(Name);

        }
        public void setTitle(Context ctx,String title){
            ImageView imageView= mView.findViewById(R.id.imageView);
            Picasso.with(ctx).load(title).into(imageView);


        }




    }

}
