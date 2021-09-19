package com.praveen.localcacheretrofitroom.repository.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.praveen.localcacheretrofitroom.model.PostModel;

@Database(entities = {PostModel.class}, version = 1, exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase db;

    public abstract PostDao postDao();

    public static synchronized PostDatabase getInstance(Context context){

        if(db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                    PostDatabase.class, "count_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}
