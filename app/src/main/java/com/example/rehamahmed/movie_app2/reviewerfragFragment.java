package com.example.rehamahmed.movie_app2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.immutables.gson.Gson;

import java.util.concurrent.ExecutionException;


public class reviewerfragFragment extends Fragment {

    asynctask task2;

    customadapterreviwer d1;

    public reviewerfragFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View rootview=inflater.inflate(R.layout.fragment_reviewerfrag, container, false);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        ListView lv2=(ListView) rootview.findViewById(R.id.listreviewer);
        int id = extras.getInt("id1");

        task2 = new asynctask();
        String Str2 = null;
        try {
            Str2 = task2.execute("http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=e45b8128053d3195e54299ea58a9f909").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Reviewer gsonobject = new Reviewer();
        com.google.gson.Gson parser = new com.google.gson.Gson();
        gsonobject = parser.fromJson(Str2, Reviewer.class);
        d1 = new customadapterreviwer(getContext(),gsonobject.getResults());
       lv2 .setAdapter(d1);

        return rootview;
    }
}
