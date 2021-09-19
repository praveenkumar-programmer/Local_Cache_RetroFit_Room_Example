package com.praveen.localcacheretrofitroom.repository.room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.praveen.localcacheretrofitroom.model.PostModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class RoomRepository {

    private static RoomRepository roomRepository;
    private final PostDao dao;

    private final Executor executor = Executors.newSingleThreadExecutor();


    private RoomRepository(Application application){

        dao = PostDatabase.getInstance(application).postDao();


    }

    public static RoomRepository getInstance(Application application){

        if(roomRepository == null){
            roomRepository = new RoomRepository(application);
        }
        return roomRepository;

    }


    public LiveData<PagedList<PostModel>> getAllPosts(){
        DataSource.Factory<Integer,PostModel>factory = dao.getAllPosts();
        return new LivePagedListBuilder<>(factory,
                new PagedList.Config.Builder()
                        .setPageSize(3)
                        .setInitialLoadSizeHint(5)
                        .setPrefetchDistance(1)
                        .setEnablePlaceholders(false)
                        .build())
                .build();
    }

    public void setAllPosts(List<PostModel> allPosts ){
        executor.execute( ()-> dao.insertAllPosts(allPosts));
    }

}
