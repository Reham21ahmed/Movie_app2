package com.example.rehamahmed.movie_app2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class trailerfragFragment extends Fragment {

    asynctask task2;
    customtrailer adaptertrailer;



    public trailerfragFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_trailerfrag, container, false);
        ListView lv1=(ListView) rootview.findViewById(R.id.listtrailer);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        int id = extras.getInt("id1");
        task2 = new asynctask();
        String Str2 = null;
        try {
            Str2 = task2.execute("http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=e45b8128053d3195e54299ea58a9f909").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Trailer gsonobject = new Trailer();
        Gson parser = new Gson();
        gsonobject = parser.fromJson(Str2, Trailer.class);
        adaptertrailer = new customtrailer(getActivity(), gsonobject.getResults());
       lv1 .setAdapter(adaptertrailer);

       lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //your code goes here .
                if (adapterView.getItemAtPosition(i) instanceof Trailer.ResultsEntity) {
                    Trailer.ResultsEntity video = (Trailer.ResultsEntity) adapterView.getItemAtPosition(i);
                    Uri uri = Uri.parse("http://www." + video.getSite() + ".com/watch?v=" + video.getKey());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        return rootview;
    }



}
