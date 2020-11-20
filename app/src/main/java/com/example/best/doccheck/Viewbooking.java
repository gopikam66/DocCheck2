package com.example.best.doccheck;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Viewbooking extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lv;
    JSONParser json=new JSONParser();
    SharedPreferences sh;
    String url="";
    String dt="";
    CalendarView day;
    ArrayList<String> did,name,place,phone,tkn;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbookings);
        b1=(Button)findViewById(R.id.button3);

        lv=(ListView) findViewById(R.id.lv);
        day = (CalendarView) findViewById(R.id.calendarView);
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}


       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
               String ip = sh.getString("ip", "");
               String lid = sh.getString("lid", "");
               day.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                   @Override
                   public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                       dt = i + "-" + (i1 + 1) + "-" + i2;
                       Toast.makeText(getApplicationContext(), dt + "", Toast.LENGTH_LONG).show();
                   }
               });
               url = "http://" + ip + "/HOSPITAL/Android/booking.php";
               Log.d("eeeeeeeee", url);
               try {

                   did = new ArrayList<>();
                   name = new ArrayList<>();
                   place = new ArrayList<>();
                   phone = new ArrayList<>();
                   tkn = new ArrayList<>();

                   List<NameValuePair> det = new ArrayList<NameValuePair>();
                   det.add(new BasicNameValuePair("did", lid));
                   det.add(new BasicNameValuePair("dt", dt));
                   JSONObject job = json.makeHttpRequest(url, "GET", det);

                   String s = job.getString("success");
                  Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
                   JSONArray ja = job.getJSONArray("res");
                   for (int i = 0; i < ja.length(); i++) {
                       JSONObject js = ja.getJSONObject(i);
                       did.add(js.getString("did"));
                       name.add(js.getString("Name"));
                       place.add(js.getString("place"));
                       phone.add(js.getString("phone"));
                       tkn.add(js.getString("token"));


                   }

                   if (name.size() > 0) {
                       //ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                       lv.setAdapter(new Custom(getApplicationContext(), name, place, phone, tkn));
                   }
               } catch (Exception e) {
                   Toast.makeText(Viewbooking.this, "NO BOOKING .." , Toast.LENGTH_SHORT).show();
                   //Toast.makeText(getApplicationContext(), e, Toast.LENGTH_LONG).show();
               }
           }
       });


        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

}
