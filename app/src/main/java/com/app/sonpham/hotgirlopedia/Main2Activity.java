package com.app.sonpham.hotgirlopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    StaggeredGridLayoutManager gridLayoutManager;
    Bundle bundle;
    ArrayList<HotGirl> data;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        gridLayoutManager=new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        bundle=getIntent().getExtras();
        data=bundle.getParcelableArrayList("data");
        myAdapter=new MyAdapter(Main2Activity.this,data);
        recyclerView.setAdapter(myAdapter);

    }
}
