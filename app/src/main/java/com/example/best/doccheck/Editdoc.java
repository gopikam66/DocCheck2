package com.example.best.doccheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Editdoc extends AppCompatActivity {
    EditText name,place,hospital,phone,email,experience;
    Button b1,b2;
    Spinner specialization;
    String url="";
    SharedPreferences sh;
    String ip,lid;

    JSONParser json=new JSONParser();
    ArrayList<String> dpid,dpname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdoc);

        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");

        name=(EditText) findViewById(R.id.tname);

        place=(EditText)findViewById(R.id.editText5);
        hospital=(EditText)findViewById(R.id.editText8);
        phone=(EditText)findViewById(R.id.editText12);
        email=(EditText)findViewById(R.id.editText11);
        specialization=(Spinner)findViewById(R.id.spinner2);
        experience=(EditText)findViewById(R.id.editText9);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.pic);
        email.setEnabled(false);
        name.setEnabled(false);
        dpid=new ArrayList<String>();
        dpname=new ArrayList<String>();

        setSpin();

        name.setText(viewprofiledoc.nm);
        hospital.setText(viewprofiledoc.ho);
        phone.setText(viewprofiledoc.ph);
        email.setText(viewprofiledoc.em);
        experience.setText(viewprofiledoc.ex);
        place.setText(viewprofiledoc.pl);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),  "updated successfully", Toast.LENGTH_LONG).show();
                String na = name.getText().toString();
                String pla = place.getText().toString();
                String hos = hospital.getText().toString();
                String pho = phone.getText().toString();
                String em = email.getText().toString();
                int id = specialization.getSelectedItemPosition();
                String sp = dpid.get(id);
                String ex = experience.getText().toString();



                String ip = sh.getString("ip", "");
                String lid = sh.getString("lid", "");
              //  if(na.equalsIgnoreCase("")){
                //    name.setError("name");
                 //   name.requestFocus();
               // }
                if(pla.equalsIgnoreCase("")||!pla.matches("[a-zA-Z]+")){
                    place.setError("missing!");
                    place.requestFocus();
                } else if (hos.equalsIgnoreCase("")||!hos.matches("[a-zA-Z]+")) {
                    hospital.setError("missing!");
                    hospital.requestFocus();
                } else if (ex.equalsIgnoreCase("")||!ex.matches("^[0-9]+$")) {
                    experience.setError("missing!");
                    experience.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                    email.setError("Invalid Type!");
                    email.requestFocus();
                } else if (!Patterns.PHONE.matcher(pho).matches()||!pho.matches("^[0-9]{10}$")) {
                    phone.setError("INVALID TYPE!!");
                    phone.requestFocus();
                } else {
                    url = "http://" + ip + "/HOSPITAL/Android/updatedoct.php";
                    Log.d("eeeeeeeee", url);
                    try {
                        List<NameValuePair> det = new ArrayList<NameValuePair>();
                        det.add(new BasicNameValuePair("uid", lid));
                        det.add(new BasicNameValuePair("na", na));
                        det.add(new BasicNameValuePair("pla", pla));
                        det.add(new BasicNameValuePair("hos", hos));
                        det.add(new BasicNameValuePair("pho", pho));
                        det.add(new BasicNameValuePair("em", em));
                        det.add(new BasicNameValuePair("ex", ex));
                        det.add(new BasicNameValuePair("dept", sp));

                        JSONObject job = json.makeHttpRequest(url, "GET", det);
                        String s = job.getString("success");
                        //Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),  "updated successfully", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), Dochomepage.class);
                        startActivity(i);


                    } catch (Exception e) {
                    }

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Docimage.class);
                startActivity(i);

            }
        });







    }

    private void setSpin() {

       String url="http://"+ip+"/HOSPITAL/Android/departments.php";
        Log.d("eeeeeeeee",url);

        try {
            List<NameValuePair> det=new ArrayList<NameValuePair>();

            JSONObject job=json.makeHttpRequest(url, "GET", det);
            String s=job.getString("success");
            Toast.makeText(getApplicationContext(), s+"----", Toast.LENGTH_LONG).show();



            if(s.equals("0"))
            {
                Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
            }
            else if(s.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                JSONArray ja=job.getJSONArray("res");
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo=ja.getJSONObject(i);
                    dpid.add(jo.getString("did"));
                    dpname.add(jo.getString("Name"));

                }

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,dpname);
                specialization.setAdapter(ad);

            }

        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Dochomepage.class);
        startActivity(i);
    }

}
