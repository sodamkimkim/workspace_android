package com.example.movie_1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.movie_1.R;
import com.example.movie_1.interfaces.OnMovieItemClicked;
import com.example.movie_1.models.Movie;

import java.util.ArrayList;
import java.util.List;

// 내부 클래스 먼저 만들기
public class MovieAdpater extends RecyclerView.Adapter<MovieAdpater.MyViewHolder> {

    private List<Movie> list = new ArrayList<>();
    OnMovieItemClicked onMovieItemClicked;

    public void setOnMovieItemClicked(OnMovieItemClicked onMovieItemClicked) {
        this.onMovieItemClicked = onMovieItemClicked;
    }


    //통신 배우기 전에는 생성자에서 데이터를 전달 받아서 화면을 구성했음
    // 지금은 네트워크 통신이기 때문에 화면을 그리는 시점보다 더 늦게 데이터가 도달할 수 있다.
    public void initItemList(List<Movie> list) {
        this.list = list;
        notifyDataSetChanged();// 화면 다시그려줌
    }

    public void addItem(List<Movie> addList) {
        // 한번도 통신 x => list.size()=0
        //10
        //20
        //,,
        this.list.addAll(list.size(), addList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_movie_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = list.get(position);
        holder.setItem(movie);
        holder.itemView.setOnClickListener(view -> {
            onMovieItemClicked.selectedItem(movie);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //inner static class
    // view binding 결합 안된다. 직접 해야한다. --> 단방향
    // (data binding 뷰결합) --> 양방향

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // itemView에 선언한 데이터 타입들 선언하고 생성 시점에 R.id.xxx연결해주면 된다.
        private View itemView;
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView ratingTextView;
        private RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
//        fidViewbyId가 성능에 치명적이기 때문에 뷰홀더 이용해 주는거 뿐임

        public void setItem(Movie movie) {
            titleTextView.setText(movie.getTitle());
            ratingTextView.setText(String.valueOf(movie.getRating()));
            //9.312() --> Math.floor()해서 처리해줘도됨.
            ratingBar.setRating((float) movie.getRating());
            Glide.with(posterImageView.getContext())
                    .load(movie.getMediumCoverImage())
                    .placeholder(R.drawable.round_image)
                    .transform(new FitCenter(), new RoundedCorners(20))
                    .into(posterImageView);
            itemView.setOnClickListener(view -> {
                Log.d("TAG", "" + view.getContext());
            });
        }
    } // end of inner-class
} // end of outer-class
