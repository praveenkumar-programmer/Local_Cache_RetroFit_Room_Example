package com.praveen.localcacheretrofitroom.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.praveen.localcacheretrofitroom.model.PostModel;
import com.praveen.localcacheretrofitroom.repository.retrofit.RetrofitRepository;
import com.praveen.localcacheretrofitroom.repository.room.RoomRepository;



public class Repository {

    private static Repository repository;
    private final RoomRepository roomRepository;
    private final RetrofitRepository retrofitRepository;

    private Repository(Application application){

        roomRepository = RoomRepository.getInstance(application);
        retrofitRepository = RetrofitRepository.getInstance(application);

        retrofitRepository.getAllPosts().observeForever(postModels -> {
            if(postModels != null)
                roomRepository.setAllPosts(postModels);
        });

    }

    public static Repository getInstance(Application application){

        if(repository == null){
            repository = new Repository(application);
        }
        return repository;

    }

    public LiveData<PagedList<PostModel>> getAllPosts(){
        return roomRepository.getAllPosts();
    }

    public void updateLocalCache(){

        retrofitRepository.getAllPostsFromInternet();

    }

}
