package com.dc9_master.store_audit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.dc9_master.R;
import com.dc9_master.camera.CameraLast_Activity;
import com.dc9_master.location.FusedLocationService;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Store_Audit_ReportUpload_Activity extends Activity {

    Button btn_camera,btn_finish,btn_uprep,btn_pics;
    ScrollView multiPic_Scroll;

    Location mLocation = null;
    FusedLocationService fusedLocationService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_audit_report_upload);

        btn_camera=findViewById(R.id.btn_camera);
        btn_finish=findViewById(R.id.finish_btn);


        multiPic_Scroll=(ScrollView) findViewById(R.id.multiPic);
        btn_pics=(Button) findViewById(R.id.done_pics);

        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();


       /* btn_uprep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiPic_Scroll.setVisibility(View.VISIBLE);
            }
        });
        btn_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiPic_Scroll.setVisibility(View.GONE);
            }
        });*/


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(Material_Recv_Item_Activity.this, Camera_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                if (mLocation != null) {
                    //startActivity(new Intent(SurveyActivity.this, Camera_Activity.class));
                    Intent i = new Intent(Store_Audit_ReportUpload_Activity.this, CameraLast_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {

                    ShowAlertagain();
                }
            }
        });


        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Store_Audit_ReportUpload_Activity.this, Upload.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
        });



    }


    public void ShowAlertagain() {
        if (!isLocationServiceEnabled()) {
            // Toast.makeText(getApplicationContext(), "please wait while location is fetching", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Store_Audit_ReportUpload_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* latt = 0;
                                    longg = 0;
                                    */
                                    startActivity(new Intent(Store_Audit_ReportUpload_Activity.this, CameraLast_Activity.class));
                                    // finish();
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                    dialog.cancel();
                                }
                            });
                    builder.setNegativeButton("SETTINGS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        } else {

            startActivity(new Intent(Store_Audit_ReportUpload_Activity.this, CameraLast_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);

            //   Log.e("Latitude", "" + mLocation.getLatitude());
            //   Log.e("Longitude", "" + mLocation.getLongitude());
        }
    }


    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true).trim();
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals(provider));
    }

}

