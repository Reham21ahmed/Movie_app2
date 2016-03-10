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


public class customadapterreviwer extends BaseAdapter {
    private List<Reviewer.ResultsEntity> mreviewer;
    private Context mContext;
    private LayoutInflater inflater;


    public customadapterreviwer(Context mContext, List<Reviewer.ResultsEntity> mreviewer) {
        this.mContext = mContext;
        this.mreviewer = mreviewer;
    }


    @Override
    public int getCount() {
        return mreviewer.size();
    }

    @Override
    public Object getItem(int position) {
        return mreviewer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       Reviewer.ResultsEntity    opject = (Reviewer.ResultsEntity) getItem(position);

        convertView=inflater.inflate(R.layout.reviewer,parent,false);
        TextView url=(TextView)convertView.findViewById(R.id.reviewer);
        url.setText(opject.getUrl());
        TextView authername=(TextView)convertView.findViewById(R.id.auther);
        authername.setText(opject.getAuthor());
        TextView content=(TextView)convertView.findViewById(R.id.content);
        content.setText(opject.getContent());
        return convertView;
    }


}
