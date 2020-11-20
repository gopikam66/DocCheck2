package com.example.best.doccheck;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    EditText ed_uname,ed_password;
    Button button;
    JSONParser json=new JSONParser();
    TextView doc,pat;

    SharedPreferences sh;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_uname=(EditText)findViewById(R.id.editText);
        ed_password=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button);
        doc=(TextView)findViewById(R.id.textView22);
        pat=(TextView)findViewById(R.id.textView23);


        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
        url="http://"+ip+"/HOSPITAL/Android/login.php";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un=ed_uname.getText().toString();
                String pw=ed_password.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(un).matches()){
                    ed_uname.setError("INVALID TYPE!!");
                    ed_uname.requestFocus();
                }
                else if(pw.equalsIgnoreCase("")){
                    ed_password.setError("missing!!");
                    ed_password.requestFocus();
                }
                try {
                    List<NameValuePair> det=new ArrayList<NameValuePair>();
                    det.add(new BasicNameValuePair("un", un));
                    det.add(new BasicNameValuePair("pn", pw));

                    JSONObject job=json.makeHttpRequest(url, "GET", det);
                    String s=job.getString("lid");
                    String tp=job.getString("tp");

                 //   Toast.makeText(getApplicationContext(), s+"----"+tp, Toast.LENGTH_LONG).show();

                    if(s.equals("0"))
                    {
                        Toast.makeText(getApplicationContext(), "invalid user name or password", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                      //  Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor e=sh.edit();
                        e.putString("lid", s);
                        e.commit();
                        if(tp.equalsIgnoreCase("doctor")) {
                            Intent i = new Intent(getApplicationContext(), Dochomepage.class);
                            startActivity(i);
                        }
                        else if(tp.equalsIgnoreCase("user")) {
                            Intent i2 = new Intent(getApplicationContext(), notiservise.class);
                            startService(i2);

                            Intent i = new Intent(getApplicationContext(), Patienthome.class);
                            startActivity(i);
                        }
                        else if(tp.equalsIgnoreCase("doctor_pending")){
                            notis();
                        }

                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });


        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Docprofile.class);
                startActivity(i);
            }
        });

        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Pat_register.class);
                startActivity(i);
            }
        });



    }
    public void notis() {
        // TODO Auto-generated method stub
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("your request pocessing!!!")
                .setContentText("click here...!!")
                .setAutoCancel(true);
//Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(getApplicationContext(), Login.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Login.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
//mBuilder.setVibrate();
        Notification note=mBuilder.build();
        note.defaults |= Notification.DEFAULT_VIBRATE;
        note.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(100,note);// mBuilder.build());


    }
}
