package com.example.rehamahmed.movie_app2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class MainActivityFragment extends Fragment {

    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    GridView gridView;
    customadapter adapter;
    asynctask task = new asynctask();



    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    public void formatasync(String url) {
        task = new asynctask();
        String Str2 = null;
        try {
            Str2 = task.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Result gsonobject = new Result();
        Gson parser = new Gson();
        gsonobject = parser.fromJson(Str2, Result.class);


        adapter = new customadapter(getActivity(), gsonobject.getResults());
        gridView.setAdapter(adapter);
        if(mPosition != ListView.INVALID_POSITION){ //llchange oriantaion
            gridView.smoothScrollToPosition(mPosition);
        }


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//llsorting elli f el menu

        int id = item.getItemId();
        String po = null;
        String hi = null;


        if (id == R.id.Popular) {

            po = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=e45b8128053d3195e54299ea58a9f909";
            formatasync(po);
            return true;


        } else if (id == R.id.highest_rated) {


            hi = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=e45b8128053d3195e54299ea58a9f909";
            formatasync(hi);

            return true;
        }

        if (id == R.id.Favorite) {
            List<Result.ResultsEntity> favourite = new ArrayList<>();


            DBconnection mydb = new DBconnection(getContext());
            Cursor res = mydb.getallrecord();

            while (res.isAfterLast() == false) {
                Result.ResultsEntity movie = new  Result.ResultsEntity();

                movie.setTitle(res.getString(res.getColumnIndex(mydb.CONTACTS_COLUMN_NAME)));
                movie.setId(res.getInt(res.getColumnIndex(mydb.CONTACTS_COLUMN_ID)));
                movie.setPoster_path(res.getString(res.getColumnIndex(mydb.CONTACTS_COLUMN_poster)));
                movie.setOverview(res.getString(res.getColumnIndex(mydb.CONTACTS_COLUMN_overview)));
                movie.setRelease_date(res.getString(res.getColumnIndex(mydb.CONTACTS_COLUMN_releasedata)));
                favourite.add(movie);
                res.moveToNext();

            }
            adapter = new customadapter(getActivity(), favourite);
            gridView.setAdapter(adapter);
            if(mPosition != ListView.INVALID_POSITION){
                gridView.smoothScrollToPosition(mPosition);
            }
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView_forcast);
        String Str2 = null;


            Str2 = "http://api.themoviedb.org/3/discover/movie?.desc&api_key=e45b8128053d3195e54299ea58a9f909";
            formatasync(Str2);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Result.ResultsEntity forecast = (Result.ResultsEntity) adapter.getItem(position);
                    if (forecast != null) {
                        ((Callback) getActivity())
                                .onItemSelected(forecast);
                    }




                }
            });





        return rootView;

    }

    public interface Callback {
        public void onItemSelected(Result.ResultsEntity movie);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }


}








