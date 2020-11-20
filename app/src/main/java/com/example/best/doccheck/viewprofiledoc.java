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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class viewprofiledoc extends AppCompatActivity {
TextView name,place,spec,hosp,exp,phone,email,regno;
Button b1;
    JSONParser json=new JSONParser();
    ImageView im;
    public static String nm,did,pl,ho,ex,sp,ph,em,reg;
    SharedPreferences sh;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofiledoc);

        im  =(ImageView)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.tname);
        place=(TextView)findViewById(R.id.tplace);
        hosp=(TextView)findViewById(R.id.thosp);
        exp=(TextView)findViewById(R.id.texper);
        spec=(TextView)findViewById(R.id.tspec);
        phone=(TextView)findViewById(R.id.tphone);
        email=(TextView)findViewById(R.id.temail);
        regno=(TextView)findViewById(R.id.treg);
        b1=(Button)findViewById(R.id.button4);


        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
        String lid=sh.getString("lid", "");
        url="http://"+ip+"/HOSPITAL/Android/doctorprof.php";
        Log.d("eeeeeeeee",lid+"--------"+url);
        try {
            List<NameValuePair> det = new ArrayList<NameValuePair>();
            det.add(new BasicNameValuePair("uid", lid));

            JSONObject job = json.makeHttpRequest(url, "GET", det);
            String s=job.getString("success");
           // Toast.makeText(getApplicationContext(), s+"----", Toast.LENGTH_LONG).show();
            String imgurl="http://"+ip+"/HOSPITAL/dimages/"+lid+".jpg";
        //    String imgurl="http://"+ip+"/HOSPITAL/dimages/"+sh.getString("drid","")+".jpg";
            did=job.getString("did");
            nm=job.getString("Name");
            pl=job.getString("place");
            ho=job.getString("hospital");
            ex=job.getString("experience");
            sp=job.getString("dep");
            ph=job.getString("phone_no");
            em=job.getString("email_id");
            reg=job.getString("reg");
            try {
                Picasso.with(viewprofiledoc.this)
                        .load(imgurl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).into(im);
            }catch (Exception e){}
            name.setText(nm);

            name.setText(nm);
            place.setText(pl);
            hosp.setText(ho);
            exp.setText(ex);
            spec.setText(sp);
            phone.setText(ph);
            email.setText(em);
            regno.setText(reg);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Editdoc.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
     super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Dochomepage.class);
        startActivity(i);
    }
}
