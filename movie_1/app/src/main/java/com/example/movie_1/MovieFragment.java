package com.example.movie_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.movie_1.adapter.MovieAdpater;
import com.example.movie_1.databinding.FragmentMovieBinding;
import com.example.movie_1.interfaces.OnChangeToolbarType;
import com.example.movie_1.interfaces.OnMovieItemClicked;
import com.example.movie_1.models.Movie;
import com.example.movie_1.models.YTSData;
import com.example.movie_1.repository.MovieService;
import com.example.movie_1.utils.Define;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment implements OnMovieItemClicked {
    // 안드로이드에서 만들어 준 클래스
    private FragmentMovieBinding binding;
    private static final String TAG = MovieFragment.class.getName(); //불변성 유지..final
    // 여기서 통신 요청을 할 예정
    private MovieService movieService;
    private MovieAdpater movieAdapter;
    private List<Movie> list = new ArrayList<>();
    private int currentPageNumber = 1;
    // 스크롤시 중복 이벤트 발생 해결 방안
    private boolean preventDuplicateScrollEvent = true;
    private OnChangeToolbarType onChangeToolbarType;
    private static MovieFragment movieFragment;
    private boolean isFirstLoading = true;

    private MovieFragment(OnChangeToolbarType onChangeToolbarType) {
        this.onChangeToolbarType = onChangeToolbarType;
    }


    public static MovieFragment getInstance(OnChangeToolbarType onChangeToolbarType) {
        // 위는 메서드이다. static 메서드를 통해서 new MovieFragment()하고있다.

        if (movieFragment == null) {
            movieFragment = new MovieFragment(onChangeToolbarType);

        }
        return movieFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        movieService = MovieService.retrofit.create(MovieService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // findViewById대체(뷰 바인딩 활용)
        // inflater.inflate(R.layout.fragment_movie, container, false);
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        //리싸이클러 뷰 만들어 주기
        // 아직 없음 //안드로이드는 입체적으로 생각해야함.
        setupRecyclerView(list);
        if (isFirstLoading) {
            requestMoviesData(currentPageNumber);
        } else {
            setVisiblityProgressBar(View.GONE);
        }
        onChangeToolbarType.onSetupType(Define.PAGE_TITLE_MOVIE);
        // ㄴ 여기까지는 null point exception뜬다 => 주소를 연결해 줘야 한다.(누가 내 메서드를 콜백 받을 지 연결.)
        // 연결 방법은 2가지. (1. 생성자를 통해서 연결, 2. 메서드를 통해서 연결)
        return binding.getRoot();

    }

    private void requestMoviesData(int requestPage) {
        int ITEM_LIMIT = 10;
        Log.d(TAG, "통신요청");
        movieService.repoContributors("rating", ITEM_LIMIT, requestPage)
                .enqueue(new Callback<YTSData>() {
                    @Override
                    public void onResponse(Call<YTSData> call, Response<YTSData> response) {
                        if (response.isSuccessful()) {
                            // 통신을 통해서 데이터를 넘겨받았으면 adapter에 데이터를 전달해서
                            // 화면을 갱신 처리 하면 됨.
                            List<Movie> list = response.body().getData().getMovies();
                            // 어댑터의 메서드 호출
                            movieAdapter.addItem(list);
                            currentPageNumber++;
                            preventDuplicateScrollEvent = true;
                            isFirstLoading = false;
                            setVisiblityProgressBar(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<YTSData> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                        setVisiblityProgressBar(View.GONE);
                        // xml --> text_view만들어서,, 네트워크가 불안정 합니다.(show처리)
                    }
                });


    }

    // 통신해서 JSON받은 다음에 매게변수로 데이터를 넘길 예정
    private void setupRecyclerView(List<Movie> movieList) {
        //1. 어댑터
        movieAdapter = new MovieAdpater();
        movieAdapter.setOnMovieItemClicked(this);
        movieAdapter.initItemList(movieList);
        // 2. 매니저필요
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        // 3. xml파일에 선언한 recyclerview 에 세팅
        binding.movieRecyclerView.setAdapter(movieAdapter);
        binding.movieRecyclerView.setLayoutManager(manager);
        binding.movieRecyclerView.hasFixedSize();
        // 이벤트 리스너
        binding.movieRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (preventDuplicateScrollEvent) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) (binding.movieRecyclerView.getLayoutManager());
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    Log.d(TAG, "lastVisibleItemPostiion" + lastVisibleItemPosition);
                    // adapter(데이터) list.size
                    // 한번 더 통신을 했을 때 (10)
                    //9
                    //10
                    int itemTotalCount = binding.movieRecyclerView.getAdapter().getItemCount() - 1;
                    if (lastVisibleItemPosition == itemTotalCount) {
                        if (currentPageNumber != 1) {
                            Toast.makeText(getContext(), "마지막 위치입니다.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "check event !!!");
                            // 네트워크 요청!!
                            preventDuplicateScrollEvent = false;
                            requestMoviesData(currentPageNumber);
                        }
                    }

                }
            }
        });

    }

    private void setVisiblityProgressBar(int visible) {
        binding.progressIndicator.setVisibility(visible);
    }

    @Override
    public void selectedItem(Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // 직렬화란,, object -> byte단위로 변환해서 던진다.
        intent.putExtra(MovieDetailActivity.PARAM_NAME_1, movie);
        startActivity(intent);
    }
}