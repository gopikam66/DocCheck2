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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Docprofile extends AppCompatActivity {
EditText name,place,hospital,phone,email,experience,pswrd,conpswd,regno;
Button b1;
Spinner specialization;
    String url="";
    SharedPreferences sh;
    String ip,lid;
    JSONParser json=new JSONParser();
    ArrayList<String> did,dname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docprofile);


        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e){}
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");

        name=(EditText)findViewById(R.id.editText4);
        place=(EditText)findViewById(R.id.editText5);
        hospital=(EditText)findViewById(R.id.editText8);
        phone=(EditText)findViewById(R.id.editText12);
        email=(EditText)findViewById(R.id.editText11);
        specialization=(Spinner)findViewById(R.id.spinner);
        experience=(EditText)findViewById(R.id.editText9);
        pswrd=(EditText)findViewById(R.id.editText14);
        conpswd=(EditText)findViewById(R.id.editText15);
        regno=(EditText)findViewById(R.id.editText17);
        b1=(Button)findViewById(R.id.button);
   //     b2=(Button)findViewById(R.id.button12);

//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in=new Intent(getApplicationContext(),Docimage.class);
//                startActivity(in);
//            }
//        });
        did=new ArrayList<String>();
        dname=new ArrayList<String>();

        setSpin();




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na=name.getText().toString();
                String reg=regno.getText().toString();
                String pla=place.getText().toString();
                String hos=hospital.getText().toString();
                String pho=phone.getText().toString();
                String em=email.getText().toString();
                String ex=experience.getText().toString();
                String psd=pswrd.getText().toString();
                String conpsd=conpswd.getText().toString();
                String dept=did.get(specialization.getSelectedItemPosition());
                if(na.equalsIgnoreCase("")||reg.equalsIgnoreCase("")||pla.equalsIgnoreCase("")||pho.equalsIgnoreCase("")||ex.equalsIgnoreCase("")||hos.equalsIgnoreCase("")||psd.equalsIgnoreCase("")||em.equalsIgnoreCase("")){
                    Toast.makeText(Docprofile.this, "Fill All Fields" , Toast.LENGTH_SHORT).show();
                }
             else  if(na.equalsIgnoreCase("")||!na.matches("[a-zA-Z]+")){
                    name.setError("enter a valid name");
                    name.requestFocus();
                }
              else if(reg.equalsIgnoreCase("")||!reg.matches("^[0-9]{3,4}+$")){
                regno.setError("invalid type!!");
                    regno.requestFocus();
                }
               else  if(pla.equalsIgnoreCase("")||!pla.matches("[a-zA-Z]+")){
                    place.setError("invalid type!");
                    place.requestFocus();
                }

                    else if (hos.equalsIgnoreCase("")||!hos.matches("[a-zA-Z]+")) {
                        hospital.setError("missing!");
                        hospital.requestFocus();
                    } else if (ex.equalsIgnoreCase("")||!ex.matches("^[0-9]+$")) {
                        experience.setError("missing!");
                        experience.requestFocus();
                    }

                    else if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                        email.setError("Invalid Type!");
                        email.requestFocus();
                    }else   if (!Patterns.PHONE.matcher(pho).matches()||!pho.matches("^[0-9]{10}$")) {
                        phone.setError("INVALID TYPE!!");
                        phone.requestFocus();
                    }
               else  if (psd.isEmpty()|| psd.length()<8) {
                    pswrd.setError("Provide atleast 8 characters!!");
                    pswrd.requestFocus();
                }
                else   if (!psd.equals(conpsd)) {
                        conpswd.setError("password mismatch");
                        conpswd.requestFocus();
                    }

                    else {
                        String url = "http://" + ip + "/HOSPITAL/Android/registration.php";
                        Log.d("eeeeeeeee", url);

                        try {
                            List<NameValuePair> det = new ArrayList<NameValuePair>();
                            det.add(new BasicNameValuePair("na", na));
                            det.add(new BasicNameValuePair("reg", reg));
                            det.add(new BasicNameValuePair("pla", pla));
                            det.add(new BasicNameValuePair("hos", hos));
                            det.add(new BasicNameValuePair("pho", pho));
                            det.add(new BasicNameValuePair("em", em));
                            det.add(new BasicNameValuePair("ex", ex));
                            det.add(new BasicNameValuePair("psd", psd));
                            det.add(new BasicNameValuePair("dept", dept));

                            JSONObject job = json.makeHttpRequest(url, "GET", det);
                            String s = job.getString("res");
                           Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_LONG).show();
                            if(s.equals("2"))
                            {
                                regno.setError("REgister NUmber Allready Exist.");
                                regno.requestFocus();

                                Toast.makeText(getApplicationContext(), "REgister NUmber Allready Exist.", Toast.LENGTH_LONG).show();
                            }
                            else if (s.equals("1"))
                            {
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "DECISION PENDING...", Toast.LENGTH_LONG).show();

                            }






                        } catch (Exception e) {
                        }
                    }
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
                    did.add(jo.getString("did"));
                    dname.add(jo.getString("Name"));

                }

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,dname);
                specialization.setAdapter(ad);

            }

        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

}
