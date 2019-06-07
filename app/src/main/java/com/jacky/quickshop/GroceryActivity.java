package com.jacky.quickshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroceryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView;
    ArrayList<ModelGrocery> groceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        ButterKnife.bind(this);

        groceryList = new ArrayList<>();
        groceryList.add(new ModelGrocery(R.drawable.veg1, "Brocolli", "250"));
        groceryList.add(new ModelGrocery(R.drawable.veg2, "Pepper", "100"));
        groceryList.add(new ModelGrocery(R.drawable.veg3, "Red Cabbage", "230"));
        groceryList.add(new ModelGrocery(R.drawable.veg4, "Tomatoes", "250"));
        groceryList.add(new ModelGrocery(R.drawable.veg5, "Carrots", "150"));
        groceryList.add(new ModelGrocery(R.drawable.veg6, "Potatoes", "290"));



        GridLayoutManager layoutManager  = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager recyclerLayoutManager = layoutManager;

        recyclerView.setLayoutManager(recyclerLayoutManager);
        GroceryAdapter adapter  = new GroceryAdapter(this,groceryList);
        recyclerView.setAdapter(adapter);



    }
}
