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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelCategory> mList;
    CategoryAdapter(Context context, ArrayList<ModelCategory> list){
        mContext = context;
        mList = list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_category_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelCategory categoryitem =mList.get(position);
        ImageView image1 = holder.image;
        TextView name2 = holder.categoryName;

        image1.setImageResource(categoryitem.getImage());
        name2.setText(categoryitem.getName());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView categoryName;

        public ViewHolder(View itemView){
            super(itemView);

            image = itemView.findViewById(R.id.image);
            categoryName = itemView.findViewById(R.id.category);


        }
    }
}
