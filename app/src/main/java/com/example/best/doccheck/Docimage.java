package com.example.best.doccheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Docimage extends AppCompatActivity implements View.OnClickListener {

    ImageView img;
    Button b;
    int capture=110;
    String photoName="";
    byte[] photoBytes;
    String url="",lid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docimage);

        img=(ImageView)findViewById(R.id.img);
        b=(Button)findViewById(R.id.button11);

        img.setOnClickListener(this);
        b.setOnClickListener(this);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       String  ip=sh.getString("ip", "");
        lid=sh.getString("lid", "");
        url = "http://" + ip + "/HOSPITAL/Android/doc_img.php";
    }

    @Override
    public void onClick(View view) {

        if(img==view){
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, capture);

        }
        if(b==view){

            new UploadService().execute();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == capture && resultCode == RESULT_OK && null != data) {

            Uri mImageCaptureUri = data.getData();
            Uri uri = data.getData();
            try
            {
                String photo = FileUtils.getPath(this, uri);
                File fl=new File(photo);
                int ln=(int) fl.length();
                photoName=fl.getName();
                Bitmap bm= BitmapFactory.decodeFile(photo);
                img.setImageBitmap(bm);
                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead =0;
                while ((bytesRead = inputStream.read(b)) != -1)
                {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                photoBytes = bos.toByteArray();
            }
            catch(Exception e){

            }
        }
    }


    private class UploadService extends AsyncTask<Void,Void,String> {

        ProgressDialog pd;
        JSONObject js;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(Docimage.this);
            pd.setMessage("Uploading...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            try {
                JSONObject js=new JSONObject(s);
                String img=js.getString("status");
                Toast.makeText(Docimage.this, "image"+img, Toast.LENGTH_SHORT).show();

                if(js.getString("status").equalsIgnoreCase("ok")){
                    Toast.makeText(Docimage.this, "image added...!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Docimage.this, "Error...", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(Docimage.this, "Error..."+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            pd.dismiss();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String data="";
            try {


                FileUpload client = new FileUpload(url);
                client.connectForMultipart();

                client.addFormPart("lid", lid);
                client.addFilePart("photo", photoName, photoBytes);
                client.finishMultipart();
                data = client.getResponse();
            }catch (Exception e){
            }
            return data;
        }
    }

}
