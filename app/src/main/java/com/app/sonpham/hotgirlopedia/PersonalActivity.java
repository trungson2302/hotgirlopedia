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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonalActivity extends FragmentActivity implements View.OnClickListener{
    private TabLayout mTabLayout;
    private Bundle mBundle;
    private ArrayList<String> mImageList;
    private ViewPager mViewPager;
    private Button mButtonFb, mButtonInsta;
    private TextView mTextView;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mViewPager =(ViewPager)findViewById(R.id.viewPager);
        mTabLayout =(TabLayout)findViewById(R.id.tabLayout);
        mButtonFb =(Button)findViewById(R.id.button4);
        mButtonInsta =(Button)findViewById(R.id.button5);
        mTextView =(TextView)findViewById(R.id.textView4);

        mBundle =getIntent().getExtras();
        mImageList =new ArrayList<String>();
        mImageList.add(mBundle.getString("photo1"));
        mImageList.add(mBundle.getString("photo2"));
        mImageList.add(mBundle.getString("photo3"));
        mImageList.add(mBundle.getString("photo4"));
        mImageList.add(mBundle.getString("photo5"));


        MyFragmentPagerAdapter adapter= new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransFormer());

        mTabLayout.setupWithViewPager(mViewPager);

        mTextView.setText(mBundle.getString("name"));
        mButtonFb.setOnClickListener(this);
        mButtonInsta.setOnClickListener(this);
    }

    /**
     * move to WebViewActivity loaded with url
     * @param v
     */
    @Override
    public void onClick(View v) {
        mIntent =new Intent(PersonalActivity.this,WebViewActivity.class);
        if(v.getId()==R.id.button4){
            mIntent.putExtra("url", mBundle.getString("fb"));
            startActivity(mIntent);
        }
        else {
            mIntent.putExtra("url", mBundle.getString("fb"));
            startActivity(mIntent);
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return doSomeThing(mImageList.get(position));
        }

        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "A";
        }

        /**
         *
         * @param s
         * @return
         */
        public Fragment doSomeThing(String s){
            Bundle b=new Bundle();
            b.putString("url",s);
            ImageFragment fragment=new ImageFragment();
            fragment.setArguments(b);
            return fragment;
        }
    }
}
