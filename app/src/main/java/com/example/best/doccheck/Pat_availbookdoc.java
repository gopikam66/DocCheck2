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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pat_availbookdoc extends AppCompatActivity {

    CalendarView day;
    TextView start, end, count;
    String dt = "";
    String tm1 = "", tm2 = "";
    String url="";
    Button b1;
    SharedPreferences sh;
    String ip, did;
    JSONParser json = new JSONParser();
    String time_id="";
    public static String pc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_availbookdoc);

        day = (CalendarView) findViewById(R.id.calendarView);
        start = (TextView) findViewById(R.id.tstart);
        end = (TextView) findViewById(R.id.tend);
        count = (TextView) findViewById(R.id.tcount);
        b1=(Button)findViewById(R.id.button13);


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } catch (Exception e) {
        }

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        did = sh.getString("drid", "");

        b1.setEnabled(false);
        day.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dt = i + "/" + (i1 + 1) + "/" + i2;
//                SimpleDateFormat sm=new SimpleDateFormat("yyyy:mm:dd a");
//                String dt= sm.format(new Date(d));

//                edd.putString("date",date);
//                edd.commit();
                Toast.makeText(getApplicationContext(), dt + "", Toast.LENGTH_LONG).show();
                url="http://"+ip+"/HOSPITAL/Android/avail_book.php";
                Log.d("eeeeeeeee",did+"--------"+url);
                try {
                    List<NameValuePair> det = new ArrayList<NameValuePair>();
                    det.add(new BasicNameValuePair("did", did));
                    det.add(new BasicNameValuePair("dt", dt));

                    JSONObject job = json.makeHttpRequest(url, "GET", det);
                    String s=job.getString("success");
//                    Toast.makeText(getApplicationContext(), s+"----", Toast.LENGTH_LONG).show();

                    if(s.equalsIgnoreCase("1")) {
                        String st = job.getString("start");
                        String en = job.getString("end");
                        pc = job.getString("pcount");
                        time_id=job.getString("time_id");
                        start.setText(st);
                        end.setText(en);
                        count.setText(pc);
                        b1.setEnabled(true);
                    }
                    else{
                        Toast.makeText(Pat_availbookdoc.this, "not available..!!", Toast.LENGTH_SHORT).show();
                        b1.setEnabled(false);
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Booknow.class);
                i.putExtra("time_id",time_id);
                i.putExtra("pc",pc);
                startActivity(i);
            }
        });
    }
}