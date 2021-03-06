package com.example.best.doccheck;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class viewbooking_pat extends AppCompatActivity {

    ListView lv;
    JSONParser json=new JSONParser();
    String url="";
    SharedPreferences sh;
    String ip,lid;
    ArrayList<String> name,start,end,date,full;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbooking_pat);
        lv=(ListView)findViewById(R.id.lv);
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");

        Toast.makeText(getApplicationContext(), lid + "-lid---", Toast.LENGTH_SHORT).show();

        String url = "http://" + ip + "/HOSPITAL/Android/bookingview_pat.php";
        try {
            List<NameValuePair> det = new ArrayList<NameValuePair>();
            det.add(new BasicNameValuePair("log_id", lid));

            JSONObject job = json.makeHttpRequest(url, "GET", det);
            String s = job.getString("success");
            Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_SHORT).show();
            if (s.equalsIgnoreCase("1")) {
                date = new ArrayList<>();
                full = new ArrayList<>();
                name = new ArrayList<>();
                start = new ArrayList<>();
                end = new ArrayList<>();
                JSONArray ja = job.getJSONArray("res");
                for (int i = 0; i < ja.length(); i++)

                {
                    JSONObject j = ja.getJSONObject(i);
                    date.add(j.getString("date"));
                    name.add(j.getString("name"));
                    start.add(j.getString("start"));
                    end.add(j.getString("end"));
                    full.add("Doctor: " + j.getString("name") + "\nTime from: " + j.getString("start")  + "\nTO: " + j.getString("end") + "\nOn " + j.getString("date"));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, full);
                lv.setAdapter(arrayAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "NO BOOKING YET!!!..", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


    }
}
