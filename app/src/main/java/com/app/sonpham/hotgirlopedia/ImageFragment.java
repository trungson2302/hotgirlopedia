package com.app.sonpham.hotgirlopedia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    String url;
    public ImageFragment() {
        // Required empty public constructor
    }

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_image, container, false);
        imageView=(ImageView)view.findViewById(R.id.imageView6);
        Picasso.with(view.getContext())
                .load(getArguments().getString("url"))
                .centerCrop()
                .fit()
                .error(R.drawable.photo_error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
        return view;
    }

}
