package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.recyclerview.R;

import java.util.ArrayList;

import interfaces.OnFoodItemClickListener;
import models.Food;

/*
 * 1. 뷰 홀더 만들기
 *
 * */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    // 멤버변수 선언
    ArrayList<Food> list;
    // 부모뷰가 a인지 b인지 activity인지
    Context context;
    OnFoodItemClickListener onFoodItemClickListener;

    public FoodAdapter(ArrayList<Food> list, Context context, OnFoodItemClickListener onFoodItemClickListener) {
        this.list = list;
        this.context = context;
        this.onFoodItemClickListener = onFoodItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // new ViewHolder 생성해
        View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_food, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // BindViewHolder (화면과 연결)
        // data mapping
        Log.d("TAG", "position: " + position);
        Food food = list.get(position);
        Glide.with(context)
                .load(food.getThumbnail())
                .centerCrop()
                .transform(new CenterCrop(), new RoundedCorners(30))

                .into(holder.thumbnail);

        holder.titleTextView.setText(food.getTitle());
        holder.subTitleView.setText(food.getSubTitle());
        holder.detailTextView.setText(food.getDetail());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    // 1. 내부 클래스 만들기
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //findviewbyid재활용해서 사용하기 위해서 만든 viewHolder
        ImageView thumbnail;
        TextView titleTextView;
        TextView subTitleView;
        TextView detailTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnailImageview);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subTitleView = itemView.findViewById(R.id.subTitleTextView);
            detailTextView = itemView.findViewById(R.id.detailTextView);

            itemView.setOnClickListener(view -> {
                Toast.makeText(view.getContext(),"TEST"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                //콜백 메서드 호출
                onFoodItemClickListener.onItemClilcked(itemView, getLayoutPosition());
            });
        }
    } // end of inner-class
} // end of outer-class
