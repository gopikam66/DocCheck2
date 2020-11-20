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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workinghour extends AppCompatActivity {
EditText pcount;
CalendarView day;
TimePicker start,end;
String d,m,y;
    String dt="";
    String tm1="",tm2="";

Button b1;
    SharedPreferences sh;
    String ip,lid;
    JSONParser json=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workinghour);

        day = (CalendarView) findViewById(R.id.calendarView);
        pcount = (EditText) findViewById(R.id.editText16);
        start = (TimePicker) findViewById(R.id.timePicker);
        end = (TimePicker) findViewById(R.id.timePicker2);
        b1 = (Button) findViewById(R.id.button3);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } catch (Exception e) {
        }

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        lid = sh.getString("lid", "");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long d = day.getMaxDate();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String dt = sdf.format(d);
                Toast.makeText(Workinghour.this, "" + dt, Toast.LENGTH_SHORT).show();
                //String sta=start.getHour()+":"+start.getMinute()+"00";
                //String en= end.toString();
                String pno = pcount.getText().toString();
                if(tm1.equalsIgnoreCase("")||tm2.equalsIgnoreCase("")||dt.equalsIgnoreCase("")||pno.equalsIgnoreCase("")){
                    Toast.makeText(Workinghour.this, "Fill All Fields" , Toast.LENGTH_SHORT).show();
                }
                else {


                    String url = "http://" + ip + "/HOSPITAL/Android/addworkinghour.php";
                    Log.d("eeeeeeeee", url);
                    try {
                        List<NameValuePair> det = new ArrayList<NameValuePair>();
                        det.add(new BasicNameValuePair("did", lid));
                        det.add(new BasicNameValuePair("date", dt));
                        det.add(new BasicNameValuePair("start", tm1));
                        det.add(new BasicNameValuePair("end", tm2));

                        det.add(new BasicNameValuePair("count", pno));

                        JSONObject job = json.makeHttpRequest(url, "GET", det);
                        String s = job.getString("res");
                        Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Dochomepage.class);
                        startActivity(i);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        day.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                 dt=i+"-"+(i1+1)+"-"+i2;
//                SimpleDateFormat sm=new SimpleDateFormat("yyyy:mm:dd a");
//                String dt= sm.format(new Date(d));

//                edd.putString("date",date);
//                edd.commit();
                Toast.makeText(getApplicationContext(),dt+"",Toast.LENGTH_LONG).show();
            }});

        start.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                tm1=i+":"+i1+":00";
//                SimpleDateFormat sm=new SimpleDateFormat("hh:mm:ss a");
//                Date.parse()
//                String tm1= sm.format(new Date(t));
                Toast.makeText(getApplicationContext(),tm1+"",Toast.LENGTH_LONG).show();
            }
        });
        end.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                tm2=i+":"+i1+":00";
                Toast.makeText(getApplicationContext(),tm2,Toast.LENGTH_LONG).show();
            }
        });
    }
}