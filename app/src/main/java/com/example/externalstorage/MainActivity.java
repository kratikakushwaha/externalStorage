package com.example.externalstorage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button internalcache,externalcache,externalprivate,externalpublic;
    EditText editdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
         {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1211);
        }

        internalcache=findViewById(R.id.InternalCachebtn);
        externalcache=findViewById(R.id.ExternalCachebtn);
        externalprivate=findViewById(R.id.ExternalPrivatebtn);
        externalpublic=findViewById(R.id.ExternalPublicbtn);
        editdata=findViewById(R.id.editText);

        internalcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=getCacheDir();
                File file1=new File(file,"kratuInternal.txt");
                setdata(file1);

            }
        });
        externalcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=getExternalCacheDir();
                File file1=new File(file,"kratuExternal.txt");
                setdata(file1);

            }
        });

        externalprivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=getExternalFilesDir("External");
                File file1=new File(file,"kratuExternalprivate.txt");
                setdata(file1);
            }
        });
        externalpublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file1 = new File(file, "kratuexternalpublic.txt");
                setdata(file1);
            }
        });
    }

    private void setdata(File file){
        File file1=file;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(editdata.getText().toString().getBytes());
            Toast.makeText(this, file1.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
