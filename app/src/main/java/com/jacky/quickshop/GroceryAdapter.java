package com.jacky.quickshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ModelGrocery> mList;

    GroceryAdapter(Context context, ArrayList<ModelGrocery> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public GroceryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_grocery_item, parent, false);
        GroceryAdapter.ViewHolder viewHolder = new GroceryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryAdapter.ViewHolder holder, int position) {

        ModelGrocery groceryitem = mList.get(position);
        ImageView image1 = holder.image;
        TextView name2 = holder.groceryName;
        TextView price3 = holder.price;


        image1.setImageResource(groceryitem.getImage());
        name2.setText(groceryitem.getName());
        price3.setText(groceryitem.getPrice());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView groceryName;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image1);
            groceryName = itemView.findViewById(R.id.grocery);
            price = itemView.findViewById(R.id.price);


        }
    }
}
