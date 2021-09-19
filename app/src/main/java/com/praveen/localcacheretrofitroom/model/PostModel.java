package com.praveen.localcacheretrofitroom.model;

//"userId": 1,
//        "id": 1,
//        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//        "body"


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "postTable")
public class PostModel {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    public int postId;

    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    public int userID;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    public String title;

    @ColumnInfo(name = "body")
    @SerializedName("body")
    public String body;

}
