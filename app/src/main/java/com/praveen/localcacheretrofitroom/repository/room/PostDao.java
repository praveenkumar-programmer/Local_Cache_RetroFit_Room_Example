package com.praveen.localcacheretrofitroom.repository.room;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.praveen.localcacheretrofitroom.model.PostModel;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM postTable")
    DataSource.Factory<Integer, PostModel> getAllPosts();

    @Insert(onConflict = REPLACE)
    void insertAllPosts(List<PostModel> allPosts);

}
