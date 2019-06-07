package com.jacky.quickshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    ArrayList<ModelCategory> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        categoryList = new ArrayList<>();
        categoryList.add(new ModelCategory(R.drawable.pic2, "Groceries"));
        categoryList.add(new ModelCategory(R.drawable.pic1, "Food & Beverages"));
        categoryList.add(new ModelCategory(R.drawable.pic6, "Butchery"));
        categoryList.add(new ModelCategory(R.drawable.pic4, "Appliances"));


        LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
        RecyclerView.LayoutManager recyclerLayoutManager = layoutManager;

        recyclerView.setLayoutManager(recyclerLayoutManager);
        CategoryAdapter adapter  = new CategoryAdapter(this,categoryList);
        recyclerView.setAdapter(adapter);



    }
}
