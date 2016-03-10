package com.example.rehamahmed.movie_app2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;


public class DetialsFragment extends Fragment {


    Button favourte;
    Button Trailer;
    Button revierer;
    Result.ResultsEntity object;
    DBconnection   mydb;





    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if(arguments==null){
            arguments=getActivity().getIntent().getExtras();
        }

        final View rootView = inflater.inflate(R.layout.fragment_detials, container, false);
        if (arguments != null){
            object=new Result.ResultsEntity();
            object.setPoster_path(arguments.getString("POSTER"));
            object.setTitle(arguments.getString("original title"));
            object.setRelease_date(arguments.getString("Release Date"));
            object.setOverview(arguments.getString("overview"));
            object.setId(arguments.getInt("id"));

            mydb=new DBconnection(getActivity());


            Picasso.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + object.getPoster_path()).into((ImageView) rootView.findViewById(R.id.image_View));

            ((TextView) rootView.findViewById(R.id.overview))
                    .setText(object.getOverview());
            ((TextView) rootView.findViewById(R.id.title))
                    .setText(object.getTitle());
            ((TextView) rootView.findViewById(R.id.relase_data))
                    .setText(object.getRelease_date());

            ((TextView) rootView.findViewById(R.id.id))
                    .setText(object.getId() + "");




            favourte = (Button) rootView.findViewById(R.id.Favorite);
            favourte.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                        favourte = (Button) rootView.findViewById(R.id.Favorite);

                            mydb.insertContact(object.getTitle(), object.getPoster_path(), object.getId(), object.getOverview(), object.getRelease_date());
                          Toast.makeText(getContext(), "SAVED AS FAVOURITE", Toast.LENGTH_SHORT).show();
                        }






            });

            Trailer=(Button)rootView.findViewById(R.id.Button1);


            Trailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), trailerfrag.class);

                    int id1;
                    intent.putExtra("id1", object.getId());


                    startActivity(intent);

                }


            });
            revierer=(Button)rootView.findViewById(R.id.Button2);
            revierer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), reviewerfrag.class);
                    int id1 ;
                    intent.putExtra("id1", object.getId());

                    startActivity(intent);

                }


            });











        }






        return rootView;

    }


}


