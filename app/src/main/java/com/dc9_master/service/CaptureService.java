package com.dc9_master.service;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.dc9_master.authentication.SessionManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nitinbhati on 27-02-2016.
 */

/**
 * Service is declaired to operate on background
 */
public class CaptureService extends Service {
    GPSTracker gps;
    int level;
    int SignalStrength = 0;
    //myPhoneStateListener pslistener;
    TelephonyManager TelephonManager;
    String sending_latt, sending_longg,str_timestamp,permit_id;
    double latt, longg;
    String response;

    Context context;
    // Logger Log;
    PackageInfo pInfo = null;
    Handler mHandler;
    Timer timer;
    TimerTask timerTask;
    SessionManager sessionManager;

    /**
     * Binder is involked for service binding
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    /**
     * Intializing the objects on creation of Service
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //GPSTracker object involked

        TelephonManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        sessionManager = new SessionManager(CaptureService.this);
        permit_id = sessionManager.GET_EMP_ID();

        //Broadcast receiver involked
        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        String version = pInfo.versionName;

    }

    /**
     * Broadcast receiver is initalized and capturing battery level
     */
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {

            level = intent.getIntExtra("level", 0);
//            Log.e(context, "test", String.valueOf(level) + "%");

        }
    };

    @Override
    public void onDestroy() {

       // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * Thread Initialized to sync all outputs within perticular time interval
         */

       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        gps = new GPSTracker(CaptureService.this);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                  while (true) {
                   //startJob();
                    try {
                        //Thread.sleep(3600000);
                       // Thread.sleep(1800000);
                        Thread.sleep(3600000);
                        if (gps.canGetLocation()) {
                              if (gps.canGetLocation()) {
                                new SendToServer(gps.getLongitude(), gps.getLatitude()).execute();
                             }

                            gps.getLongitude();
                            gps.getLatitude();
                            gps.getTime();
                            sending_latt = String.valueOf(gps.getLatitude());
                            sending_longg = String.valueOf(gps.getLongitude());
                            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date d = new Date(gps.getTime());
                            str_timestamp = sdf.format(d);
                            Log.e( "CaptureService ", "GPSTIME  : " + str_timestamp);

                        }

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
        return START_STICKY;
    }

    private void startJob() {
        //do job here

     }




    public class SendToServer extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        // public SendToServer() {
        public SendToServer(Double latitude, Double longitude) {
            // TODO Auto-generated constructor stub
            sending_latt = String.valueOf(latitude);
            sending_longg = String.valueOf(longitude);
//            Log.e("from", "sendtoserver");
        }

       /* @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(CaptureService.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }
*/
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {


                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_punch_sch1");
                   // HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_punch_sch1");
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    nameValuePairs.add(new BasicNameValuePair("permit_id",sessionManager.GET_EMP_ID()));
                    nameValuePairs.add(new BasicNameValuePair("project_id", sessionManager.GET_PROJECT()));
                    nameValuePairs.add(new BasicNameValuePair("mobile_date", str_timestamp));
                    nameValuePairs.add(new BasicNameValuePair("latt", sending_latt));
                    nameValuePairs.add(new BasicNameValuePair("longg", sending_longg));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    //Log.e("name value pair", "" + nameValuePairs);
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = httpclient.execute(httppost, responseHandler);

                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("response", "" + response);
                    //   Log.e("response", response + "+" + e.getMessage().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }



    }



}