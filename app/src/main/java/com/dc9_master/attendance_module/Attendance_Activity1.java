package com.dc9_master.attendance_module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.Home_BajajActivity;
import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.HTTPURLConnection;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_SiteInspection;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.selfiecamera.MainActivity;
import com.dc9_master.service.CaptureService;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Attendance_Activity1 extends Activity {

    ImageButton btn_punchIn, btn_punchOut,btn_leave;
    Button back_btn, pending_btn,od_cancel,od_punchin;
    LinearLayout pop_od;
    FrameLayout pendingHolder_btn;
    EditText ed_remark;
    String str_remark;
    int meterInDec_in,meterInDec_out,meterInDec_od;

    ConnectionDetector connectionDetector;
    String internet_interrupt = null;

    Location mLocation = null;
    FusedLocationService fusedLocationService;
    String sending_latt, sending_longg;
    String permit_id, str_timestamp;
    double latt, longg;
    String response;
    ProgressDialog pd;

    SessionManager sessionManager;
    SQLiteAdapter1 sqLiteAdapter;
    SQLiteAdapterWorkprogress sqLiteAdapterWorkprogress;

    //String string_last_date,string_current_date;
    Date last_date,current_date;
    //int a=00000000,b=00000000,c=00000000;
    String  string_current_date;

    private ProgressDialog pDialog;
    private JSONObject json;
    private int success=0;
    private HTTPURLConnection service;
    double phone_version = 0.0;
    double server_version=0.0;
    PackageInfo pInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        sqLiteAdapter=new SQLiteAdapter1(Attendance_Activity1.this);
        sqLiteAdapterWorkprogress=new SQLiteAdapterWorkprogress(Attendance_Activity1.this);

        try {
            // cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        back_btn = (Button) findViewById(R.id.backpageId2);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent i = new Intent(Attendance_Activity.this, Home_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        });

        string_current_date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        fusedLocationService = new FusedLocationService(this);
         mLocation = fusedLocationService.getLocation();
        connectionDetector = new ConnectionDetector(Attendance_Activity1.this);
        sessionManager = new SessionManager(Attendance_Activity1.this);
        permit_id = sessionManager.GET_EMP_ID();
        service=new HTTPURLConnection();


        pendingHolder_btn = (FrameLayout) findViewById(R.id.pending_holder);
        pending_btn = (Button) findViewById(R.id.pending);
        pending_btn.setVisibility(View.GONE);
        pendingHolder_btn.setVisibility(View.GONE);
        setVisibile();
        pending_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pop_od=(LinearLayout)findViewById(R.id.pop_od);
        pop_od.setVisibility(View.GONE);

        //------------ For Punch In Button--------------------------//
        btn_punchIn = (ImageButton) findViewById(R.id.punch_in);
        btn_punchIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocation = fusedLocationService.getLocation();
                if (isMockSettingsON(getApplicationContext(),mLocation) == false)
                {
                    str_timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    if (mLocation != null) {
                        if (connectionDetector.isConnectingToInternet()) {
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            dialog.setContentView(R.layout.custom);
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText(" Are you Sure to Punch In ");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNo);
                            dialogButton.setText("Yes");
                            dialogButtonNO.setText("No");
                            dialogButtonNO.setVisibility(View.VISIBLE);

                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                   // new PostDataTOServer().execute();

                                    // Toast.makeText(getApplicationContext(), String.valueOf(meter) + String.valueOf(meterInDec), Toast.LENGTH_LONG).show();
                                    //----coordinate distance code End----------------//
                                    DataHolder_SiteInspection.getInstance().setCamera_lat(String.valueOf(mLocation.getLatitude()));
                                    DataHolder_SiteInspection.getInstance().setCamera_long(String.valueOf(mLocation.getLongitude()));
                                    DataHolder_SiteInspection.getInstance().setCamera_time(str_timestamp);
                                    DataHolder_SiteInspection.getInstance().setLoc_distance(String.valueOf(meterInDec_in));

                                    Intent i = new Intent(Attendance_Activity1.this, SignatureActivity.class);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);


                                }
                            });
                            dialogButtonNO.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();

                            //new SendToServer().execute();


                        } else {
                            // custom dialog
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER);
                            dialog.setContentView(R.layout.custom);
                            //  dialog.setTitle("Title...");

                            // set the custom dialog components - text, image and button
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText("Internet Not connecting");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            // if button is clicked, close the custom dialog
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();
                            //                        Toast.makeText(getApplicationContext(), "record saved", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        // ShowAlertagain();

                        final Dialog dialog = new Dialog(Attendance_Activity1.this);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.getWindow().setGravity(Gravity.CENTER);
                        dialog.setContentView(R.layout.custom);
                        TextView text = (TextView) dialog.findViewById(R.id.text);
                        text.setText("Do you want to on location Setting?");
                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                                dialog.cancel();

                            }
                        });

                        dialog.show();
                    }

                }else
                {
                    showAlertMock();
                }
            }

        });

        //-------For logout Button--------------------------//
        btn_punchOut = (ImageButton) findViewById(R.id.punch_out);
        btn_punchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocation = fusedLocationService.getLocation();
                if (isMockSettingsON(getApplicationContext(),mLocation) == false) {
                    str_timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    if (mLocation != null) {
                        if (connectionDetector.isConnectingToInternet()) {
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            dialog.setContentView(R.layout.custom);
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText(" Are you Sure to Punch Out ");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNo);
                            dialogButton.setText("Yes");
                            dialogButtonNO.setText("No");
                            dialogButtonNO.setVisibility(View.VISIBLE);

                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    // Toast.makeText(getApplicationContext(), String.valueOf(meter) + String.valueOf(meterInDec), Toast.LENGTH_LONG).show();
                                    //----coordinate distance code End----------------//


                                        DataHolder_SiteInspection.getInstance().setCamera_lat(String.valueOf(mLocation.getLatitude()));
                                        DataHolder_SiteInspection.getInstance().setCamera_long(String.valueOf(mLocation.getLongitude()));
                                        DataHolder_SiteInspection.getInstance().setCamera_time(str_timestamp);
                                        DataHolder_SiteInspection.getInstance().setLoc_distance(String.valueOf(meterInDec_out));

                                        Intent i = new Intent(Attendance_Activity1.this, Signature_OUTActivity.class);
                                        startActivity(i);
                                        stopService();
                                        overridePendingTransition(R.anim.right_in, R.anim.left_out);



                                }
                            });
                            dialogButtonNO.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                            dialog.show();
                            //Log.e("Latitude", "" + mLocation.getLatitude());
                            //Log.e("Longitude", "" + mLocation.getLongitude());
                            //new SendToServerPunchOut(mLocation.getLatitude(), mLocation.getLongitude()).execute();
                            //new SendToServer().execute();
                        } else {
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER);
                            dialog.setContentView(R.layout.custom);
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText("Internet Not Connecting");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            // if button is clicked, close the custom dialog
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    }
                            });
                            dialog.show();
                             }
                            } else {
                        //ShowAlertagain();
                        final Dialog dialog = new Dialog(Attendance_Activity1.this);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.getWindow().setGravity(Gravity.CENTER);
                        dialog.setContentView(R.layout.custom);
                        TextView text = (TextView) dialog.findViewById(R.id.text);
                        text.setText("Do you want to on location Setting?");
                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                                dialog.cancel();

                            }
                        });

                        dialog.show();
                    }
                }else
                {
                    showAlertMock();
                }


            }

        });


        btn_leave = (ImageButton) findViewById(R.id.btn_leave);
        btn_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocation = fusedLocationService.getLocation();

                      if (connectionDetector.isConnectingToInternet()) {
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            dialog.setContentView(R.layout.custom);
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText(" Are you Sure to apply for Leave ");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNo);
                            dialogButton.setText("Yes");
                            dialogButtonNO.setText("No");
                            dialogButtonNO.setVisibility(View.VISIBLE);

                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    //Intent i = new Intent(Attendance_Activity1.this, AttendanceLeave_Activity.class);
                                    Intent i = new Intent(Attendance_Activity1.this, LeaveUpload.class);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                 }
                            });
                            dialogButtonNO.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    }
                            });

                            dialog.show();
                            //new SendToServer().execute();

                        } else {
                            // custom dialog
                            final Dialog dialog = new Dialog(Attendance_Activity1.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER);
                            dialog.setContentView(R.layout.custom);
                            //  dialog.setTitle("Title...");

                            // set the custom dialog components - text, image and button
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText("Internet Not connecting");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            // if button is clicked, close the custom dialog
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();
                            //                        Toast.makeText(getApplicationContext(), "record saved", Toast.LENGTH_SHORT).show();
                        }

            }

        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }


    private void setVisibile() {
        sqLiteAdapterWorkprogress.openToRead();
        sqLiteAdapterWorkprogress.openToWrite();
        int recev_id = 0;
        int store_int = 0;
        int loading_int = 0;
        int sor_int = 0;
        int call_int = 0;
        int hhsc_int = 0;
        int network_int = 0;
        int jmc_int = 0;
        int issue_int = 0;
        int safety_int = 0;
        int pole_int = 0;
        int good_int = 0;

        hhsc_int = sqLiteAdapterWorkprogress.countDataHHSC();
        network_int = sqLiteAdapterWorkprogress.countDataNETWORK();

        sqLiteAdapterWorkprogress.close();


        if (hhsc_int > 0 || network_int > 0) {


            pending_btn.setVisibility(View.VISIBLE);
            pendingHolder_btn.setVisibility(View.VISIBLE);

           /* Intent intent = new Intent(Attendance_Activity1.this, BackgroundService.class);
              startService(intent);*/
        } else {
            pending_btn.setVisibility(View.INVISIBLE);
            pendingHolder_btn.setVisibility(View.GONE);
            pending_btn.setVisibility(View.GONE);
        }
    }



    //-------------------for coordinates setting  ----------------------------------------------//

    public static boolean isMockSettingsON(Context context,Location location) {
        // returns true if mock location enabled, false if not enabled.
        boolean isMock = false;
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                isMock = location.isFromMockProvider();
            }catch (Exception ex)
            {
                // Toast.makeText(context,ex.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        } else {
            isMock = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
        }

        /*if (Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ALLOW_MOCK_LOCATION).equals("0")) {
            return false;
        }
        else
            return true;*/
        return isMock;

    }



    public void ShowAlertagain() {
        if (!isLocationServiceEnabled()) {
            // Toast.makeText(getApplicationContext(), "please wait while location is fetching", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Attendance_Activity1.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    latt = 0;
                                    longg = 0;
                                    if (connectionDetector.isConnectingToInternet()) {
                                        //new SendToServer(latt, longg).execute();
                                    } else {

                                    }
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

            //   Log.e("Latitude", "" + mLocation.getLatitude());
            //   Log.e("Longitude", "" + mLocation.getLongitude());
        }
    }


    public void showAlertMock() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Attendance_Activity1.this);
        builder.setMessage("Please OFF Mock Location from setting");
        //builder.setCancelable(true);


        builder
                //  .setMessage("Click yes to exit!")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        startActivity(new Intent(Attendance_Activity1.this, Home_Activity.class));
                        Attendance_Activity1.this.finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }


    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true).trim();
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals(provider));
    }


    //--------------------- Alert for Logout---------------------//
    public void startService() {
        startService(new Intent(getBaseContext(), CaptureService.class));
    }

    // Method to stop the service
    public void stopService() {
        stopService(new Intent(getBaseContext(), CaptureService.class));
    }



    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        //String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Attendance_Activity1.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams=new HashMap<String, String>();
            postDataParams.put("permit_id", sessionManager.GET_EMP_ID());
            postDataParams.put("project_id", sessionManager.GET_PROJECT());
             //Call ServerData() method to call webservice and store result in response
            response= service.ServerData(sessionManager.GET_CURRENT_URL()+"insert_version",postDataParams);
            try {
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("success=" + json.get("response"));
                //success = json.getInt("resp");
                //JSONArray contacts = json.getJSONArray("resp");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            try {

                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("resp");

                        int fileLength = contacts.length();

                        // for (int i = 0; i < contacts.length(); i++) {

                        if (fileLength == 0) {
                            //Toast.makeText(getApplicationContext(), " device not configured ", Toast.LENGTH_LONG).show();

                        } else {
                            JSONObject c = contacts.getJSONObject(0);
                            //String total_survey = c.getString("total_survey");
                            String max_ver = c.getString("max_ver");
                            sessionManager.createVersionSession(max_ver);
                            //Toast.makeText(getApplicationContext(), ""+max_ver, Toast.LENGTH_SHORT).show();
                            // custom dialog
                            phone_version = Double.parseDouble(pInfo.versionName);
                            server_version= Double.parseDouble(sessionManager.GET_VERSION());
                            if(server_version>phone_version) {
                                // Toast.makeText(getApplicationContext(), ""+server_version, Toast.LENGTH_SHORT).show();
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.DCNINE_BAJAJ&hl=en")));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.DCNINE_BAJAJ&hl=en")));
                                }

                            }
                            else if(server_version==phone_version) {
                                DataHolder_SiteInspection.getInstance().setCamera_lat(String.valueOf(mLocation.getLatitude()));
                                DataHolder_SiteInspection.getInstance().setCamera_long(String.valueOf(mLocation.getLongitude()));
                                DataHolder_SiteInspection.getInstance().setCamera_time(str_timestamp);
                                DataHolder_SiteInspection.getInstance().setLoc_distance(String.valueOf(meterInDec_in));
                                Intent i = new Intent(Attendance_Activity1.this, SignatureActivity.class);
                                startActivity(i);
                                //startService();
                                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                }
                        }
                        // }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Toast.makeText(getApplicationContext(), " device not configured ", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    //  Log.e("ServiceHandler", "Couldn't get any data from the url");
                }

            } catch (Exception e) {

                // Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }

}

