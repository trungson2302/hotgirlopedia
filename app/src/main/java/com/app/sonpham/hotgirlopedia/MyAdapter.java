package com.app.sonpham.hotgirlopedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Son Pham on 7/24/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<HotGirl> mData;
    private HotGirl mHotGirl;
    public MyAdapter(Context context,ArrayList<HotGirl> data) {
        mContext=context;
        mData = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotgirl, parent, false);
        return new ViewHolder(inflatedView,mData);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mData.get(position).getmPhoto1())
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .error(R.drawable.photo_error)
                .into(holder.mImage);
        holder.mName.setText(mData.get(position).getmName());
        mHotGirl =mData.get(position);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;
        public ViewHolder(View itemView, final ArrayList<HotGirl> mData) {
            super(itemView);
            mName =(TextView)itemView.findViewById(R.id.textView);
            mImage =(ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Animation animation1 =
                            AnimationUtils.loadAnimation(v.getContext(),
                                    R.anim.item_anim);
                    v.startAnimation(animation1);
                    HotGirl data=mData.get(getAdapterPosition());
                    Intent intent=new Intent(v.getContext(),PersonalActivity.class);
                    intent.putExtra("name",data.getmName());
                    intent.putExtra("fb",data.getmFB());
                    intent.putExtra("insta",data.getmInsta());
                    intent.putExtra("photo1",data.getmPhoto1());
                    intent.putExtra("photo2",data.getmPhoto2());
                    intent.putExtra("photo3",data.getmPhoto3());
                    intent.putExtra("photo4",data.getmPhoto4());
                    intent.putExtra("photo5",data.getmPhoto5());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
