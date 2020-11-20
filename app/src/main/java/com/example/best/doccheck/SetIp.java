package com.example.best.doccheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class SetIp extends AppCompatActivity {
EditText edip;
Button b1;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);
        edip=(EditText) findViewById(R.id.edip);
        b1=(Button)findViewById(R.id.button);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SetIp.this);
        String ipad=sharedPreferences.getString("ip","");
        edip.setText(ipad);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=edip.getText().toString();
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("ip",ip);
                editor.commit();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }

}
