package com.dc9_master.camera;

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

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.location.FusedLocationService;

/**
 * Created by nitinb on 09-02-2016.
 */
public class CameraMultiPicAll_Activity extends Activity {

    Button camera_btn1,camera_btn2,camera_btn3,camera_btn4,camera_btn5,camera_btn6,camera_btn7,camera_btn8,btn_finish;
    SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_multipic_all);

        sessionManager = new SessionManager(CameraMultiPicAll_Activity.this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        camera_btn1=findViewById(R.id.camera_challan);
        camera_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera1_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    } else {
                    ShowAlertagain1();
                }

            }
        });

        camera_btn2=findViewById(R.id.camera_lr);
        camera_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera2_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain2();
                }

            }
        });



        camera_btn3=findViewById(R.id.camera_invoice);
        camera_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera3_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain3();
                }

            }
        });

        camera_btn4=findViewById(R.id.camera_packing);
        camera_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera4_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain4();
                }

            }
        });

        camera_btn5=findViewById(R.id.camera_waybill);
        camera_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera5_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain5();
                }

            }
        });


     /*   btn_finish=findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               onBackPressed();

            }
        });
*/

        camera_btn6=findViewById(R.id.camera_damaged);
        camera_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera6_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain6();
                }

            }
        });


        btn_finish=findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


    }

    @Override
    public void onBackPressed() {
        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true).trim();
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals(provider));
    }


    public void ShowAlertagain1() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera1_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera1_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
    }

    public void ShowAlertagain2() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera2_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera2_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
    public void ShowAlertagain3() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera3_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera3_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    public void ShowAlertagain4() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera4_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera4_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    public void ShowAlertagain5() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera5_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera5_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }


    public void ShowAlertagain6() {
        if (!isLocationServiceEnabled()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CameraMultiPicAll_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera6_Activity.class));
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

            startActivity(new Intent(CameraMultiPicAll_Activity.this, Camera6_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }



}
