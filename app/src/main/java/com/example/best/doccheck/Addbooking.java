package com.example.best.doccheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Addbooking extends AppCompatActivity {

    TextView name,place,dept,hosp,phone,email,regno;
    Button b1;
    JSONParser json=new JSONParser();
    ImageView im;


    SharedPreferences sh;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooking);


        im  =(ImageView)findViewById(R.id.imageView3);
        name=(TextView)findViewById(R.id.tname);
        place=(TextView)findViewById(R.id.tplace);
        hosp=(TextView)findViewById(R.id.thosp);
        dept=(TextView)findViewById(R.id.tdep);
        phone=(TextView)findViewById(R.id.tphone);
        email=(TextView)findViewById(R.id.temail);
        regno=(TextView)findViewById(R.id.treg);
        b1=(Button)findViewById(R.id.button1);

        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
        String lid=sh.getString("lid", "");
        url="http://"+ip+"/HOSPITAL/Android/booking2.php";
        try {
            List<NameValuePair> det = new ArrayList<NameValuePair>();
            det.add(new BasicNameValuePair("uid", sh.getString("drid","")));

            JSONObject job = json.makeHttpRequest(url, "GET", det);
            String s=job.getString("success");
            Toast.makeText(getApplicationContext(), s+"----", Toast.LENGTH_LONG).show();
           String nm=job.getString("Name");
           String pl=job.getString("place");
           String ho=job.getString("hospital");
          String  sp=job.getString("dep");
           String ph=job.getString("phone_no");
          String  em=job.getString("email_id");
          String  reg=job.getString("reg");
            String imgurl="http://"+ip+"/HOSPITAL/dimages/"+sh.getString("drid","")+".jpg";

            try {
                Picasso.with(Addbooking.this)
                        .load(imgurl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).into(im);
            }catch (Exception e){}
            name.setText(nm);
            place.setText("Place: "+pl);
            hosp.setText("Hospital: "+ho);
            dept.setText("Department: "+sp);
            phone.setText("Mobile: "+ph);
            email.setText("Email: "+em);
            regno.setText("Regno: "+reg);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Pat_availbookdoc.class);
                startActivity(i);
            }
        });




    }
}
