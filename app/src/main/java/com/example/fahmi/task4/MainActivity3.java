package com.example.fahmi.task4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Fahmi on 1/30/2017.
 */

public class MainActivity3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView name_list;
    private String[] users = {

            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",
            "income","outcome","total","income","outcome","total",



    };

    ProgressBar progress_bar;

    AddArrayToListView arr_to_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent_obj = getIntent();

        progress_bar = (ProgressBar)findViewById(R.id.progressBar);
        progress_bar.setVisibility(View.VISIBLE);
        //setup adapter for populate data to listview
        name_list = (ListView)findViewById(R.id.listView);
        name_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //process adapter with asynctask
        arr_to_list_view = new AddArrayToListView();
        arr_to_list_view.execute();
    }

    class AddArrayToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> adapter;
        private int counter = 0;
        //handle loading progress with dialog
        ProgressDialog progress_dialog = new ProgressDialog(MainActivity3.this);

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) name_list.getAdapter();
            //this for init progress dialog
            progress_dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress_dialog.setTitle("On Progress ....");
            progress_dialog.setCancelable(false);
            progress_dialog.setProgress(0);
            //this will handle cacle asynctack when click cancle button
            progress_dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {
                    arr_to_list_view.cancel(true);
                    progress_bar.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                }
            });
            progress_dialog.show();
        }

        @Override

        protected Void doInBackground(Void... params) {
            for(String item: users) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isCancelled()) {
                    arr_to_list_view.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            counter ++;
            Integer current_status = (int) ((counter / (float) users.length) * 100);
            progress_bar.setProgress(current_status);
            //set progress only working for horizontal loading
            progress_dialog.setProgress(current_status);
            //setmessage will not working when using horizontal loading
            progress_dialog.setMessage(String.valueOf(current_status)+ "%");
        }

        @Override
        protected void onPostExecute(Void result) {
            //hide top progress bar
            progress_bar.setVisibility(View.INVISIBLE);
            //remove progress dialog
            progress_dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent_obj = new Intent(MainActivity3.this, MainActivity.class);

            startActivity(intent_obj);

        } else if (id == R.id.nav_gallery) {

            Intent intent_obj = new Intent(MainActivity3.this, MainActivity2.class);

            startActivity(intent_obj);

        } else if (id == R.id.nav_slideshow) {

            Intent intent_obj = new Intent(MainActivity3.this, MainActivity3.class);

            startActivity(intent_obj);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
