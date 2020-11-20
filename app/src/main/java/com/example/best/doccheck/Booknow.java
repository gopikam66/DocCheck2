package com.example.best.doccheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Booknow extends AppCompatActivity {
    TextView count;

    String url="";
    Button b1;
    String dt = "";
    SharedPreferences sh;
    String ip, did,pid;
    JSONParser json = new JSONParser();
    String time_id="";
    String counttt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booknow);

        count = (TextView) findViewById(R.id.tcount);
        b1=(Button)findViewById(R.id.button14);

        time_id=getIntent().getStringExtra("time_id");


        Toast.makeText(this, time_id, Toast.LENGTH_SHORT).show();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } catch (Exception e) {
        }

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        did = sh.getString("drid", "");
        pid = sh.getString("lid", "");
        String pc=getIntent().getStringExtra("pc");

        url="http://"+ip+"/HOSPITAL/Android/booknow.php";
        Log.d("eeeeeeeee",did+"--------"+url);
        try {
            List<NameValuePair> det = new ArrayList<NameValuePair>();
            det.add(new BasicNameValuePair("tid", time_id));
            JSONObject job = json.makeHttpRequest(url, "GET", det);
            Toast.makeText(this, job.toString(), Toast.LENGTH_SHORT).show();
                    String s=job.getString("success");
                    if(s.equalsIgnoreCase("1")){
                        count.setText(job.getString("count"));
                        counttt=job.getString("count");

                        try {

                            if(Integer.parseInt(pc)<=Integer.parseInt(counttt)){
                                b1.setEnabled(false);
                            }
                            else{
                                b1.setEnabled(true);
                            }
                        }catch (Exception e){}
                    }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url="http://"+ip+"/HOSPITAL/Android/bookedpop.php";
               // int dbcount=Integer.parseInt(counttt);
              //  int pcount=Integer.parseInt(Pat_availbookdoc.pc);
              //  if(dbcount>pcount)
                {

                    try {
                        List<NameValuePair> det = new ArrayList<NameValuePair>();
                        det.add(new BasicNameValuePair("pid", pid));
                        det.add(new BasicNameValuePair("tid", time_id));
                        JSONObject job = json.makeHttpRequest(url, "GET", det);
                        String s = job.getString("res");
                      Toast.makeText(getApplicationContext(),  "BOOKED SUCCESSFULLY!!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), FindDoc.class);
                        startActivity(i);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                //else{
                  //  Toast.makeText(Booknow.this, "Limit Achieved", Toast.LENGTH_SHORT).show();
               // }


                }
        });


    }
}
