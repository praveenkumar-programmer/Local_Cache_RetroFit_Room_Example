package com.praveen.localcacheretrofitroom.repository.retrofit;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.praveen.localcacheretrofitroom.model.PostModel;
import com.praveen.localcacheretrofitroom.repository.room.PostDao;
import com.praveen.localcacheretrofitroom.repository.room.RoomRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepository {

    private static RetrofitRepository retrofitRepository;
    private final PostApi postApi;
    private MutableLiveData<List<PostModel>> postList = new MutableLiveData<>();

    private RetrofitRepository(Application application){

        postApi = new Retrofit.Builder().baseUrl(PostApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PostApi.class);

    }

    public static synchronized RetrofitRepository getInstance(Application application){

        if(retrofitRepository == null){
            retrofitRepository = new RetrofitRepository(application);
        }
        return retrofitRepository;

    }

    public void getAllPostsFromInternet(){

        postApi.getAllPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.e("PostApi", t.getMessage());

            }
        });

    }

    public LiveData<List<PostModel>> getAllPosts(){
        return postList;
    }

}
