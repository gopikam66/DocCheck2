package com.example.best.doccheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Patienthome extends AppCompatActivity {
    Button  finddoc,notify,booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthome);
        finddoc=(Button)findViewById(R.id.button6);
        notify=(Button)findViewById(R.id.button7);
       booking=(Button)findViewById(R.id.button8);


        finddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),FindDoc.class);
                startActivity(i);
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PatientNot.class);
                startActivity(i);
            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),viewbooking_pat.class);
                startActivity(i);
            }
        });
    }
}
