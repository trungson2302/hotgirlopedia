package com.app.sonpham.hotgirlopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private Bundle mBundle;
    private ArrayList<HotGirl> mData;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView)findViewById(R.id.recyclerView);

        /**
         * setup RecyclerView and show mData
         */
        mGridLayoutManager =new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mBundle =getIntent().getExtras();
        mData = mBundle.getParcelableArrayList("data");
        myAdapter=new MyAdapter(MainActivity.this, mData);
        mRecyclerView.setAdapter(myAdapter);
    }
}
