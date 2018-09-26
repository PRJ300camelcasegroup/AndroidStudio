package com.example.user.shanesandapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.shanesandapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private double v;
    ActivityMainBinding bind;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private double totalcounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bind.btn1c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one_cent(bind);
            }
        });
        bind.btn2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two_cent(bind);
            }
        });
        bind.btn5c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five_cent(bind);
            }
        });
        bind.btn10c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten_cent(bind);
            }
        });
        bind.btn20c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twenty_cent(bind);
            }
        });
        bind.btn50c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifty_cent(bind);
            }
        });
        bind.btn1E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one_euro(bind);
            }
        });
        bind.btn2E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two_euro(bind);
            }
        });
     //   EditText firstnum1 = (EditText) findViewById(R.id.);
    //    EditText secondnum2 = (EditText) findViewById(R.id.secondnum);
        // Button addbtn = (Button) findViewById(R.id.addbtn);



    }
    private void one_cent(ActivityMainBinding n){
        v=0;

        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("HelloWorld");
        if (n.tex1c.getText().toString().equals("") == false)
        {
            v = Double.parseDouble(n.tex1c.getText().toString()) * 0.01;
            n.output.setText(String.format("Number of 1c : €%.2f", v));
            totalcounter += v;
            bind.total.setText(String.format("Total : €%.2f", totalcounter));
        }

    }
    private void two_cent(ActivityMainBinding n){
        v=0;
        DatabaseReference second = database.getReference("DriverName");
        second.setValue("Shane");
        if (n.tex2c.getText().toString().equals("") == false)
        {
            v=Double.parseDouble(n.tex2c.getText().toString()) * 0.02;
            n.output.setText(String.format("Number of 2c : €%.2f", v));
            totalcounter += v;
            bind.total.setText(String.format("Total : €%.2f", totalcounter));
        }
    }
    private void five_cent(ActivityMainBinding n){
            v=0;

            if(n.tex5c.getText().toString().equals("") == false)
            {
                v=Double.parseDouble(n.tex5c.getText().toString()) * 0.05;
                n.output.setText(String.format("Number of 5c : €%.2f", v));
                totalcounter += v;
                bind.total.setText(String.format("Total : €%.2f", totalcounter));
            }
        }
    private void ten_cent(ActivityMainBinding n){
        v=0;
       if (n.tex10c.getText().toString().equals("") == false)
       {
           v=Double.parseDouble(n.tex10c.getText().toString()) * 0.10;
           n.output.setText(String.format("Number of 10c : €%.2f", v));
           totalcounter += v;
           bind.total.setText(String.format("Total : €%.2f", totalcounter));
       }
    }
    private void twenty_cent(ActivityMainBinding n){
        v=0;
       if (n.tex20c.getText().toString().equals("") == false)
       {
           v=Double.parseDouble(n.tex20c.getText().toString()) * 0.20;
           n.output.setText(String.format("Number of 20c : €%.2f", v));
           totalcounter += v;
           bind.total.setText(String.format("Total : €%.2f", totalcounter));
       }
    }
    private void fifty_cent(ActivityMainBinding n){
        v=0;
        if (n.tex50c.getText().toString().equals("") == false)
        {
            v=Double.parseDouble(n.tex50c.getText().toString()) * 0.50;
            n.output.setText(String.format("Number of 50c : €%.2f", v));
            totalcounter += v;
            bind.total.setText(String.format("Total : €%.2f", totalcounter));
        }
    }
    private void one_euro(ActivityMainBinding n){
        v=0;
        if (n.tex1euro.getText().toString().equals("") == false)
        {
            v=Double.parseDouble(n.tex1euro.getText().toString()) * 1.00;
            n.output.setText(String.format("Number of €1 : €%.2f", v));
            totalcounter += v;
            bind.total.setText(String.format("Total : €%.2f", totalcounter));
        }
    }
    private void two_euro(ActivityMainBinding n){
        v=0;
       if (n.tex2euro.getText().toString().equals("") == false)
       {
           v=Double.parseDouble(n.tex2euro.getText().toString()) * 2.00;
           n.output.setText(String.format("Number of €2 : €%.2f", v));
           totalcounter += v;
           bind.total.setText(String.format("Total : €%.2f", totalcounter));
       }
    }
}


