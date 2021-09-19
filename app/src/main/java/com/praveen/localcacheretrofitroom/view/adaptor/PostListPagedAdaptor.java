package com.praveen.localcacheretrofitroom.view.adaptor;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.praveen.localcacheretrofitroom.R;
import com.praveen.localcacheretrofitroom.model.PostModel;

public class PostListPagedAdaptor extends PagedListAdapter<PostModel, PostListPagedAdaptor.ViewHolder> {

    public PostListPagedAdaptor(){
        super(
                new DiffUtil.ItemCallback<PostModel>() {
            @Override
            public boolean areItemsTheSame(@NonNull PostModel oldItem, @NonNull PostModel newItem) {
                return (oldItem.postId == newItem.postId);
            }

            @Override
            public boolean areContentsTheSame(@NonNull PostModel oldItem, @NonNull PostModel newItem) {
                return (oldItem.postId == newItem.postId);
            }});
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if(getItem(position) != null){

            holder.postId.setText(String.valueOf(getItem(position).postId));
            holder.userId.setText("USER ID : " + String.valueOf(getItem(position).userID));
            holder.title.setText(getItem(position).title);
            holder.body.setText(getItem(position).body);
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView postId;
        MaterialTextView userId;
        MaterialTextView title;
        MaterialTextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postId = itemView.findViewById(R.id.postId);
            userId = itemView.findViewById(R.id.userId);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

        }

    }


}
