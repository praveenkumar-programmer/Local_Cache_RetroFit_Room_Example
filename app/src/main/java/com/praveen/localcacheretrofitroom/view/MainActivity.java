package com.praveen.localcacheretrofitroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.praveen.localcacheretrofitroom.R;
import com.praveen.localcacheretrofitroom.view.adaptor.PostListPagedAdaptor;
import com.praveen.localcacheretrofitroom.viewModel.PostViewModel;

public class MainActivity extends AppCompatActivity {

    private PostViewModel viewModel;
    private RecyclerView recyclerView;
    private PostListPagedAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this,  new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(PostViewModel.class);

        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.updateLocalCache();
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adaptor = new PostListPagedAdaptor();
        viewModel.getAllPosts().observe(this, posts -> {
            adaptor.submitList(posts);
            recyclerView.setAdapter(adaptor);
            refreshLayout.setRefreshing(false);
        });

    }
}