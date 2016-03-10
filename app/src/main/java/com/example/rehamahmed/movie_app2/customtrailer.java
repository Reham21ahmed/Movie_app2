package com.example.rehamahmed.movie_app2;
import android.content.ClipData;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class customtrailer extends BaseAdapter {
    private List<Trailer.ResultsEntity> mtrailer;
    private Context mContext;
    private LayoutInflater inflater;


    public customtrailer(Context mContext, List<Trailer.ResultsEntity> mtrailer) {
        this.mContext = mContext;
        this.mtrailer = mtrailer;
    }


    @Override
    public int getCount() {
        return mtrailer.size();
    }

    @Override
    public Object getItem(int position) {
        return mtrailer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Trailer.ResultsEntity    opject = (Trailer.ResultsEntity) getItem(position);

        convertView = inflater.inflate(R.layout.trailer, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.trailer);
        name.setText(opject.getName());
        return convertView;
    }


}