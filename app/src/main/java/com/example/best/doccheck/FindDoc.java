package com.example.best.doccheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindDoc extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    EditText ed;
    JSONParser json=new JSONParser();
    String url="";
    SharedPreferences sh;
    String ip,lid;
    ArrayList<String> logid,nam,phone,hosp,departmt,full;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc);

        ed=(EditText)findViewById(R.id.editText18);
        lv=(ListView)findViewById(R.id.lv);
        lv.setOnItemClickListener(this);

        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");


        searchDoctor("");
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                searchDoctor(ed.getText().toString());
            }
        });
    }

    public void searchDoctor(String name){

        String url = "http://" + ip + "/HOSPITAL/Android/doctor_list.php";
        try {
            List<NameValuePair> det = new ArrayList<NameValuePair>();
            det.add(new BasicNameValuePair("name", name));

            JSONObject job = json.makeHttpRequest(url, "GET", det);
            String s = job.getString("success");
            Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
            if (s.equalsIgnoreCase("1"))
            {
                logid=new ArrayList<>();
                nam=new ArrayList<>();
             //   place=new ArrayList<>();
                phone=new ArrayList<>();
                hosp=new ArrayList<>();
             //   email=new ArrayList<>();
                departmt=new ArrayList<>();
                full=new ArrayList<>();
                JSONArray ja=job.getJSONArray("res");
                for(int i=0;i<ja.length();i++)

                {
                    JSONObject j=ja.getJSONObject(i);
                    logid.add(j.getString("log_id"));
                    nam.add(j.getString("Name"));
                  //  place.add(j.getString("place"));
                    hosp.add(j.getString("hospital"));
                    phone.add(j.getString("phone_no"));
                  //  email.add(j.getString("email_id"));
                    departmt.add(j.getString("department"));
                    full.add("name:"+j.getString("Name")+"\nHospital:"+j.getString("hospital")+"\nPhone_no.:"+j.getString("phone_no")+"\nDepartment:"+j.getString("department"));



                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,full);
                lv.setAdapter(arrayAdapter);

            }

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences sb=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edd=sb.edit();
        String drid=logid.get(i);
        edd.putString("drid",drid);
        edd.commit();

        Intent in=new Intent(getApplicationContext(),Addbooking.class);
        startActivity(in);



    }
}
