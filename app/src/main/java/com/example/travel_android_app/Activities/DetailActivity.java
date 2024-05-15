package com.example.travel_android_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travel_android_app.Domain.PopularDomain;
import com.example.travel_android_app.R;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, authorTxt, bookTxt, borrowTxt, onlineTxt, descriptionTxt, scoreTxt;
    private PopularDomain item;
    private ImageView picImg,backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.d("DetailActivity", "onCreate: DetailActivity created");

        initView();
        setVariable();
    }

    private void setVariable() {
        item=(PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        scoreTxt.setText((int) item.getScore());
        authorTxt.setText(item.getAuthor());
        bookTxt.setText(item.getBooks());
        descriptionTxt.setText(item.getDescription());

        if(item.isBorrow()){
            borrowTxt.setText("Borrow");
        }else {
            borrowTxt.setText("No-Borrow");
        }
        if (item.isOnline()){
            onlineTxt.setText("Online");
        }else {
            onlineTxt.setText("No-Online");
        }
        int drawableResId=getResources().getIdentifier(item.getPic(),"drawable",getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        titleTxt=findViewById(R.id.titleTxt);
        authorTxt=findViewById(R.id.authorTxt);
        bookTxt=findViewById(R.id.bookTxt);
        borrowTxt=findViewById(R.id.borrowTxt);
        onlineTxt=findViewById(R.id.onlineTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        scoreTxt=findViewById(R.id.scoreTxt);
        picImg=findViewById(R.id.picImg);
        backBtn=findViewById(R.id.backBtn);





    }
}