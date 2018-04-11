package com.aanealoneal.viewpager.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.aanealoneal.viewpager.R;
import com.aanealoneal.viewpager.fragments.EdittextFragment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String ARG_INDIVIDUAL_QUESTION = "individual_question";

    private static JsonArray question;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSampleJson();

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * read json string from raw file and convert to JsonArray
     */
    private void getSampleJson() {
        InputStream inputStream = getResources().openRawResource(R.raw.json);
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            question = (JsonArray) new JsonParser().parse(new String(b));
        } catch (IOException e) {
            Log.i(TAG,"I/0 Exception: "+e.getLocalizedMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            JsonObject objQuestion = (JsonObject) question.get(position);
            switch (objQuestion.get("type").getAsString()){
                case "textfield":{
                    EdittextFragment edittextFragment = new EdittextFragment();
                    Bundle args = new Bundle();
                    args.putString(ARG_INDIVIDUAL_QUESTION,objQuestion.toString());
                    edittextFragment.setArguments(args);
                    return edittextFragment;
                }
            }

            return new PlaceholderFragment();
        }

        @Override
        public int getCount() {
            return question.size();
        }
    }
}
