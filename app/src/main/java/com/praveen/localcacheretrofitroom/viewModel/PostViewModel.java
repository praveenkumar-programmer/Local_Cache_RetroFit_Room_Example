package com.praveen.localcacheretrofitroom.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;

import com.praveen.localcacheretrofitroom.model.PostModel;
import com.praveen.localcacheretrofitroom.repository.Repository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kotlinx.coroutines.flow.Flow;

public class PostViewModel extends AndroidViewModel {

    private final Repository repository;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<PagedList<PostModel>> getAllPosts(){
        return repository.getAllPosts();
    }

    public void updateLocalCache(){
        repository.updateLocalCache();

    }


}
