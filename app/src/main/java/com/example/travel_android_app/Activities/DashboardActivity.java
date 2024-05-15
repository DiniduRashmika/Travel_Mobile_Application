package com.example.travel_android_app.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_android_app.Adapters.CategoryAdapter;
import com.example.travel_android_app.Adapters.PopularAdapter;
import com.example.travel_android_app.Domain.CategoryDomain;
import com.example.travel_android_app.Domain.PopularDomain;
import com.example.travel_android_app.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular,adapterCat;
    private  RecyclerView recyclerViewPopular,recyclerViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain>items=new ArrayList<>();
        items.add(new PopularDomain("Hotel Ananthaya","Anuradhapura","Anuradhapura, an ancient city in Sri Lanka, is renowned for its well-preserved ruins of an early Sinhala civilization. It features majestic stupas, monasteries, and historic palaces, showcasing rich cultural heritage.",2,true,4.8,"pict1",true,2000));
        items.add(new PopularDomain("Hotel kola-Net","Galle","Galle, a historic city in Sri Lanka, is famous for its fortified old town, colonial architecture, and vibrant coastal scenery. The Galle Fort, a UNESCO World Heritage site, highlights its rich heritage.",3,true,4.3,"pict2",false,1500));
        items.add(new PopularDomain("Hotel Rizza","Jaffna","Jaffna, a city in northern Sri Lanka, is known for its vibrant Tamil culture, historic temples, and colonial-era buildings. It offers unique cuisine, rich traditions, and beautiful coastal landscapes.",3,true,4.9,"pict3",false,3000));

        recyclerViewPopular=findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterPopular=new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);


        ArrayList<CategoryDomain> catsList=new ArrayList<>();
        catsList.add(new CategoryDomain("Beaches","cat1"));
        catsList.add(new CategoryDomain("Camps","cat2"));
        catsList.add(new CategoryDomain("Forest","cat3"));
        catsList.add(new CategoryDomain("Plains","cat4"));
        catsList.add(new CategoryDomain("Mountains","cat5"));



        recyclerViewCategory=findViewById((R.id.view_cat));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterCat= new CategoryAdapter(catsList);
        recyclerViewCategory.setAdapter(adapterCat);

    }
}