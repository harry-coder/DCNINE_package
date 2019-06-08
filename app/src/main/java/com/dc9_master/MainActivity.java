package com.dc9_master;

//import android.graphics.Color;
//import android.graphics.PorterDuff;
//import android.graphics.drawable.Drawable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView tv1;
    private Context activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tv1=(TextView)findViewById(R.id.sanfrancet);
//        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/Capsuula.ttf");
//        tv1.setTypeface(face);

    }

    @Override
    protected void onStart() {
        super.onStart();
       // runTimePermission();
    }

    //here we are specifying the permissions we have to ask to the user..
    public boolean runTimePermission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
            ) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 100);

            return true;
        }
        return false;

    }

    //here we are checking if the permission is taken or not...
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED && grantResults[4] == PackageManager.PERMISSION_GRANTED&& grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                runTimePermission();
            }

        }

    }
    //    Button button;
//
//    public void onClick(View v) {
//
//
//
//        switch (v.getId()) {
////            case R.id.lotReject:
////
////                if (button == null) {
////                    button = (Button) findViewById(v.getId());
////                } else {
////                    button.setBackgroundColor(Color.argb(25,0,0,1));
////                    button = (Button) findViewById(v.getId());
////                }
////                button.setBackgroundColor(Color.argb(25,0,0,1));
////
////                break;
////
////            case R.id.lotAccepted:
////                if (button == null) {
////                    button = (Button) findViewById(v.getId());
////                } else {
////                    button.setBackgroundColor(Color.argb(25,0,0,1));
////                    button = (Button) findViewById(v.getId());
////                }
////                button.setBackgroundColor(Color.argb(25,0,0,1));
////
////                break;
////
////            default:
////                break;
//        }
//    }


}



