package com.example.rehamahmed.movie_app2;

import android.content.ClipData;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class customadapter extends BaseAdapter {
    private List<Result.ResultsEntity>mMovieitem;
    private Context mContext;
    private LayoutInflater inflater;

    public customadapter(Context mContext, List<Result.ResultsEntity> mMovieitem) {
        this.mContext = mContext;
        this.mMovieitem=mMovieitem;
    }



    public int getCount() {
        return mMovieitem.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
   LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View Viewroot=inflater.inflate(R.layout.grid_item_forcast, parent, false);

        Result .ResultsEntity opject = (Result.ResultsEntity) getItem(position);
        ImageView imageView =(ImageView) Viewroot.findViewById(R.id.imageView);

        String imageurl=opject.getPoster_path();
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + imageurl).into(imageView);
        return Viewroot;
    }

}
