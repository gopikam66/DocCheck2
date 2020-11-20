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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pat_register extends AppCompatActivity {
    EditText name,place,phone,age,pswrd,conpswd,email;
    Button b1;
    String url="";
    SharedPreferences sh;
    String ip,lid;
    JSONParser json=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_register);



        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");

        name=(EditText)findViewById(R.id.ename);
        place=(EditText)findViewById(R.id.eplace);
        age=(EditText)findViewById(R.id.eage);
        phone=(EditText)findViewById(R.id.ephone);
        pswrd=(EditText)findViewById(R.id.editText14);
        conpswd=(EditText)findViewById(R.id.editText15);
        email=(EditText)findViewById(R.id.euser);
        b1=(Button)findViewById(R.id.button15);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na=name.getText().toString();
                String pla=place.getText().toString();
                String ag=age.getText().toString();
                String pho=phone.getText().toString();
                String em=email.getText().toString();
                String psd=pswrd.getText().toString();
                String conpsd=conpswd.getText().toString();
                if(na.equalsIgnoreCase("")||psd.equalsIgnoreCase("")||pla.equalsIgnoreCase("")||pho.equalsIgnoreCase("")||ag.equalsIgnoreCase("")||em.equalsIgnoreCase("")){
                    Toast.makeText(Pat_register.this, "Fill All Fields" , Toast.LENGTH_SHORT).show();
                }
               else if(na.equalsIgnoreCase("")||!na.matches("[a-zA-Z]+")) {
                    name.setError("name");
                    name.requestFocus();
                }

                else if(!ag.matches("^[0-9]+$")){
                    age.setError("missing!!");
                    age.requestFocus();

                }
                else if(pla.equalsIgnoreCase("")||!pla.matches("[a-zA-Z]+")){
                    place.setError("invalid type!!!");
                    place.requestFocus();
                }
                else if(!Patterns.PHONE.matcher(pho).matches()||!pho.matches("^[0-9]{10}$")){
                    phone.setError("INVALID TYPE!!");
                    phone.requestFocus();
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                    email.setError("Invalid Type!");
                    email.requestFocus();
                }
                else  if (psd.isEmpty()|| psd.length()<8) {
                    pswrd.setError("Provide atleast 8 characters!!");
                    pswrd.requestFocus();
                }

                else if(!psd.equals(conpsd)){
                    conpswd.setError("password mismatch");
                    conpswd.requestFocus();
                }
                else {
                    String url = "http://" + ip + "/HOSPITAL/Android/pat_register.php";
                    Log.d("eeeeeeeee", url);

                    try {
                        List<NameValuePair> det = new ArrayList<NameValuePair>();
                        det.add(new BasicNameValuePair("na", na));

                        det.add(new BasicNameValuePair("pla", pla));
                        det.add(new BasicNameValuePair("ag", ag));
                        det.add(new BasicNameValuePair("pho", pho));
                        det.add(new BasicNameValuePair("em", em));
                        det.add(new BasicNameValuePair("psd", psd));

                        JSONObject job = json.makeHttpRequest(url, "GET", det);
                        String s = job.getString("res");
                        Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();


                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);

                        Toast.makeText(getApplicationContext(), "LOGIN USING EMAIL AS USERNAME!!!...", Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }


            }
        });



    }
}
