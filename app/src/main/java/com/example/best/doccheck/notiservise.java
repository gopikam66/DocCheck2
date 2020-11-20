package com.example.best.doccheck;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class notiservise extends Service {
Handler hd;
String[] tit,noti,date;
String namespace="http://connection/";//viewNot($type)
String method="ViewEmergency";
String soapaction="http://connection/ViewEmergency";
String url="";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
try {
			
			if(Build.VERSION.SDK_INT>9) {
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		hd=new Handler();
		hd.post(r);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		hd.removeCallbacks(r);
	}
	public Runnable r=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			SimpleDateFormat df=new SimpleDateFormat("yyy-MM-dd");
			String curdate=df.format(new java.util.Date());

			SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			String ip=sh.getString("ip", "");
			String lid=sh.getString("lid","");
			String lastid=sh.getString("lastid","0");
			url="http://"+ip+"/HOSPITAL/Android/pat_not_bydate.php";
			try {

				List<NameValuePair> det = new ArrayList<NameValuePair>();
				det.add(new BasicNameValuePair("lastid",lastid));
				det.add(new BasicNameValuePair("log_id", lid));
				JSONParser json = new JSONParser();
				JSONObject job = json.makeHttpRequest(url, "GET", det);
				String s = job.getString("success");
				//Toast.makeText(getApplicationContext(), s + "----", Toast.LENGTH_SHORT).show();
				if (!s.equalsIgnoreCase("0")) {

					Editor edt=sh.edit();
					edt.putString("lastid",s);
					edt.commit();
					notis();
				}
			}
				catch(Exception e)
				{
					
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
		
			
			hd.postDelayed(r, 5000);
			
		}
	};
	
	public void notis() {
		// TODO Auto-generated method stub
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("you have new  Notifications")
        .setContentText("click here...!!")
        .setAutoCancel(true);
//Creates an explicit intent for an Activity in your app
Intent resultIntent = new Intent(getApplicationContext(), PatientNot.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// Adds the back stack for the Intent (but not the Intent itself)
stackBuilder.addParentStack(PatientNot.class);
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
