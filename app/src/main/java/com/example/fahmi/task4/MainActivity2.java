package com.example.fahmi.task4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.attr.itemBackground;

/**
 * Created by Fahmi on 1/26/2017.
 */

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText income, outcome, result ;
    Button hitung ;
    Double hasilakhir ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        hitung = (Button)findViewById(R.id.button);
        income = (EditText) findViewById(R.id.editText5);
        outcome = (EditText) findViewById(R.id.editText2);
        result = (EditText) findViewById(R.id.editText3);

     hitung.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         double Cek1 = Double.parseDouble(income.getText().toString());
         double Cek2 = Double.parseDouble(outcome.getText().toString());
         hasilakhir = Cek1 - Cek2;
         String hasilString = String.valueOf(hasilakhir);
         result.setText(hasilString);
             Intent intent_obj = new Intent(MainActivity2.this, MainActivity.class);

             intent_obj.putExtra("income", income.getText().toString());

             intent_obj.putExtra("outcome", outcome.getText().toString());
             intent_obj.putExtra("hasil", result.getText().toString());
             startActivity(intent_obj);
         }
     });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent_obj = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent_obj);

        } else if (id == R.id.nav_gallery) {
            Intent intent_obj = new Intent(MainActivity2.this, MainActivity2.class);
            startActivity(intent_obj);

        } else if (id == R.id.nav_slideshow) {
            Intent intent_obj = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent_obj);
        }

        DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
