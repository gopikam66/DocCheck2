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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Docnotification extends AppCompatActivity {

    EditText txt;
    Button b2;
    String url="";
    SharedPreferences sh;
    String ip,lid;
    JSONParser json=new JSONParser();
    CalendarView day;
    String dt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docnotification);

        day=(CalendarView)findViewById(R.id.calendarView);

        txt=(EditText)findViewById(R.id.editText19);

        b2=(Button)findViewById(R.id.button5);
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= txt.getText().toString();
                String url = "http://" + ip + "/HOSPITAL/Android/notifydoc.php";
                Log.d("eeeeeeeee", url);
                if(msg.equalsIgnoreCase("")||dt.equalsIgnoreCase("")){
                    Toast.makeText(Docnotification.this, "Fill All Fields" , Toast.LENGTH_SHORT).show();
                }
                else {

                    try {
                    List<NameValuePair> det = new ArrayList<NameValuePair>();
                    det.add(new BasicNameValuePair("did", lid));
                    det.add(new BasicNameValuePair("mesg", msg));
                    det.add(new BasicNameValuePair("dt", dt));

                    JSONObject job = json.makeHttpRequest(url, "GET", det);
                    String s = job.getString("res");
                    //Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Dochomepage.class);
                    startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }}
            }
        });


        day.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dt=i+"-"+(i1+1)+"-"+i2;

                Toast.makeText(getApplicationContext(),dt+"",Toast.LENGTH_LONG).show();
            }});



    }
}
