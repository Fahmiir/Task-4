package com.example.fahmi.task4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DataBaseHelper myDB ;
    EditText Date ;
    TextView Income, Outcome, Result ;
    Button Save, Update, Delete, List ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DataBaseHelper(this);
        Date = (EditText)findViewById(R.id.editText4);
        final TextView Income = (TextView)findViewById(R.id.textView2);
        final TextView Outcome = (TextView)findViewById(R.id.textView3);
        final TextView Result = (TextView)findViewById(R.id.textView4);
        final EditText Date = (EditText)findViewById(R.id.editText4);

        Intent intent_obj = getIntent();
        Income.setText("Income = " + intent_obj.getStringExtra("income"));
        Outcome.setText("Outcome = " + intent_obj.getStringExtra("outcome"));
        Result.setText("Result = " + intent_obj.getStringExtra("hasil"));
        Save = (Button)findViewById(R.id.button2);
        Update = (Button)findViewById(R.id.button3);
        Delete = (Button)findViewById(R.id.button5);
        List = (Button)findViewById(R.id.button4);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = myDB.save_student(
                        Income.getText().toString(),
                        Outcome.getText().toString(),
                        Result.getText().toString()
                );
                if (result) {
                    Toast.makeText(MainActivity.this, "Success Add Note", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Fails Add Note", Toast.LENGTH_LONG).show();
                }
            }
        });

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor students = myDB.list_student();
                if (students.getCount()==0) {
                    alert_message("Message", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (students.moveToNext()) {
                    buffer.append("Day : " + students.getString(0) + "\n");
                    buffer.append("Income : " + students.getString(1) + "\n");
                    buffer.append("Outcome : " + students.getString(2) + "\n");
                    buffer.append("Total : " + students.getString(3) + "\n\n\n");
                }
                alert_message("List Students", buffer.toString());
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = myDB.update_student(Date.getText().toString(),
                        Income.getText().toString(),
                        Outcome.getText().toString(),
                        Result.getText().toString());
                if (result)
                    Toast.makeText(MainActivity.this, "Success update data Student", Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this, "Fails update data Student", Toast.LENGTH_LONG).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {public void onClick(View View) {
            Integer result1 = myDB.delete_student(Date.getText().toString());
            if (result1 > 0)
                Toast.makeText(MainActivity.this, "Success delete a Student", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Fails delete a Student", Toast.LENGTH_LONG).show();

        }});

        if (Income.getText().toString().equals("Income = " + null) && Outcome.getText().toString().equals("Outcome = " +  null) ){

            Result.setText("Total");
        }
        else {
            String num1 = intent_obj.getStringExtra("income");
            String num2 = intent_obj.getStringExtra("outcome");
            Integer num3 = Integer.valueOf(num1);
            Integer num4 = Integer.valueOf(num2);
            Integer num5 = num3 - num4;
            String hasil = String.valueOf(num5);
            Result.setText(hasil);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
          Intent intent_obj = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent_obj);
        } else if (id == R.id.nav_gallery) {
          Intent intent_obj = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent_obj);
        } else if (id == R.id.nav_slideshow) {
            Intent intent_obj = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent_obj);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void alert_message(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
