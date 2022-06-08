package com.example.todayslunch.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todayslunch.R;


public class CategoryAdapter extends RecyclerView.Adapter<FoodAda{


    // 내부클래스
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //findviewbyid재활용해서 사용하기 위해서 만든 viewHolder
        private ImageView categoryTumbnail;
        private TextView categoryTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTumbnail = itemView.findViewById(R.id.categoryImageView);
            categoryTitle = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(view->{
                Toast.makeText(view.getContext(),"TEST"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                onFoodItemClickListener.onItemClicked(itemView, getLayoutPosition());
            });
        }
    }
}
