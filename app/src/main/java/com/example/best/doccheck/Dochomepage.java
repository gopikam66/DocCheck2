package com.example.best.doccheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dochomepage extends AppCompatActivity {
Button profile,leave,hour,booking,notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dochomepage);
        profile=(Button)findViewById(R.id.button6);
        leave=(Button)findViewById(R.id.button7);
        hour=(Button)findViewById(R.id.button8);
        booking=(Button)findViewById(R.id.button9);
        notify=(Button)findViewById(R.id.button10);



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewprofiledoc.class);
                        startActivity(i);
            }
        });
                leave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(getApplicationContext(),Addleave.class);
                        startActivity(i);
                    }
                });
                        hour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(getApplicationContext(),Workinghour.class);
                                startActivity(i);
                            }
                        });
                                booking.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent i=new Intent(getApplicationContext(),Viewbooking.class);
                                        startActivity(i);
                                    }
                                });
                                        notify.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent i=new Intent(getApplicationContext(),Docnotification.class);
                                                startActivity(i);
                                            }
                                        });

    }
    @Override
    public void onBackPressed() {
       super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
