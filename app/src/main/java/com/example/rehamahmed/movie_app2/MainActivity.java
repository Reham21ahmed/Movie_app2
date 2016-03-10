package com.example.rehamahmed.movie_app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback {

    private boolean mTwoPane;

    private final String DETAILSActivityFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.container2) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new DetialsFragment(), DETAILSActivityFRAGMENT_TAG).commit();
            }
        } else {
            mTwoPane = false;
        }
    }


    @Override
    public void onItemSelected(Result.ResultsEntity movie) {
        if (mTwoPane) {
            Bundle args = new Bundle();

            args.putString("POSTER", movie.getPoster_path());
            args.putString("original title", movie.getTitle());
            args.putString("Release Date", movie.getRelease_date());
            args.putString("overview", movie.getOverview());
            args.putInt("id", movie.getId());


            DetialsFragment fragment = new DetialsFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container2, fragment, DETAILSActivityFRAGMENT_TAG)
                    .commit();
        } else {

  Intent intent = new Intent(this, Detials.class);

            intent.putExtra("POSTER", movie.getPoster_path());
            intent.putExtra("original title", movie.getTitle());
            intent.putExtra("Release Date", movie.getRelease_date());
            intent.putExtra("overview", movie.getOverview());
            intent.putExtra("id", movie.getId());
            startActivity(intent);

        }

    }
}
