package com.app.sonpham.hotgirlopedia;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity2 extends FragmentActivity implements View.OnClickListener{
    TabLayout tabLayout;
    Bundle bundle;
    ArrayList<String> listImage;
    ViewPager viewPager;
    Button buttonFB,buttonINSTA;
    TextView textView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_2);

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        buttonFB=(Button)findViewById(R.id.button4);
        buttonINSTA=(Button)findViewById(R.id.button5);
        textView=(TextView)findViewById(R.id.textView4);

        bundle=getIntent().getExtras();
        listImage=new ArrayList<String>();
        listImage.add(bundle.getString("photo1"));
        listImage.add(bundle.getString("photo2"));
        listImage.add(bundle.getString("photo3"));
        listImage.add(bundle.getString("photo4"));
        listImage.add(bundle.getString("photo5"));


        MyFragmentPagerAdapter adapter= new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true,new ZoomOutPageTransFormer());

        tabLayout.setupWithViewPager(viewPager);

        textView.setText(bundle.getString("name"));
        buttonFB.setOnClickListener(this);
        buttonINSTA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent=new Intent(Main3Activity2.this,Main4Activity.class);
        if(v.getId()==R.id.button4){
            intent.putExtra("url",bundle.getString("fb"));
            startActivity(intent);
        }
        else {
            intent.putExtra("url",bundle.getString("fb"));
            startActivity(intent);
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return doSomeThing(listImage.get(position));
        }

        @Override
        public int getCount() {
            return listImage.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "A";
        }

        public Fragment doSomeThing(String s){
            Bundle b=new Bundle();
            b.putString("url",s);
            ImageFragment fragment=new ImageFragment();
            fragment.setArguments(b);
            return fragment;
        }
    }
}
