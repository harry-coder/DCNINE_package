package com.dc9_master.service;

/**
 * Created by nitinb on 21-08-2016.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;

import com.dc9_master.sqlite_adapter.SQLiteAdapter1;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Maurya Talisetti
 */
public class BackgroundService extends IntentService {

    public Runnable mRunnable = null;
    private NotificationManager nm;
    ConnectionDetector connectionDetector;
    private final Calendar time = Calendar.getInstance();
    SQLiteAdapter1 sqLiteAdapter;
    SQLiteAdapterWorkprogress sqLiteAdapter_m;
    SessionManager sessionManager;
    //SQLiteAdapterFactory sqLite_f;
    Context context;

    private String REsponse;
    int id;
    public int globalcount = 20;
    Cursor cursor_recv,cursor_store,cursor_loading,cursor_sor,cursor_all_call,cursor_hhsc,cursor_network,
            cursor_jmc,cursor_all_issue,cursor_safety,cursor_pole,cursor_factory,cursor_dpr;


    public BackgroundService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
       /*final Handler mHandler = new Handler();
       mRunnable = new Runnable() {
           @Override
           public void run() {

               System.out.println("HYYHHYYH");
               Toast.makeText(getApplicationContext(), " GOT IT ", Toast.LENGTH_SHORT).show();

               senddata();
               mHandler.postDelayed(mRunnable, 50 * 50 * 1000);
           }
       };

       mHandler.postDelayed(mRunnable, 50 * 50 * 1000);*/

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                while (true) {
                    //startJob();
                    try {
                        //Thread.sleep(0);
                        Thread.sleep(1 * 10 * 1000);
                        if (connectionDetector.isConnectingToInternet()) {
                            try {

                                sqLiteAdapter_m.openToWrite();
                                sqLiteAdapter_m.openToRead();
                                cursor_sor = sqLiteAdapter_m.queueAll();
                                cursorsor(cursor_sor);

                                cursor_recv = sqLiteAdapter_m.queueAllR();
                                cursorRecv(cursor_recv);

                                cursor_store = sqLiteAdapter_m.queueAllSTORE();
                                cursorStore(cursor_store);

                                cursor_loading = sqLiteAdapter_m.queueAllLOADING();
                                cursorLoading(cursor_loading);

                                cursor_all_call = sqLiteAdapter_m.queueAllCALL();
                                cursorCall(cursor_all_call);

                                cursor_hhsc = sqLiteAdapter_m.queueAllHHSC();
                                cursorHhsc(cursor_hhsc);

                                cursor_network = sqLiteAdapter_m.queueAllNETWORK();
                                cursorNetwork(cursor_network);


                                cursor_jmc = sqLiteAdapter_m.queueAllJMC();
                                cursorJMC(cursor_jmc);

                                cursor_all_issue = sqLiteAdapter_m.queueAllISSUE();
                                cursorIssue(cursor_all_issue);

                                cursor_safety = sqLiteAdapter_m.queueAllSA();
                                cursorSafety(cursor_safety);

                                cursor_pole = sqLiteAdapter_m.queueAllPOLE();
                                cursorPOLE(cursor_pole);

                                cursor_dpr = sqLiteAdapter_m.queueAllWI();
                                cursorDpr(cursor_dpr);

                                cursor_factory = sqLiteAdapter_m.queueAllFACT();
                                cursorFactory(cursor_factory);



                            } catch (Exception e) {
                                // TODO: handle exception
                            }


                        }

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
        //return START_STICKY;

        //senddata();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        connectionDetector = new ConnectionDetector(BackgroundService.this);
        sqLiteAdapter = new SQLiteAdapter1(BackgroundService.this);
        sqLiteAdapter_m = new SQLiteAdapterWorkprogress(BackgroundService.this);
        sessionManager = new SessionManager(BackgroundService.this);
        /*sqLite_f= new SQLiteAdapterFactory(BackgroundService.this);*/
       /* Toast.makeText(this, "Service created at " + time.getTime(),
                Toast.LENGTH_LONG).show();*/
        // showNotification();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Cancel the persistent notification.
        // nm.cancel(R.string.service_started);
      /*  Toast.makeText(this,
                REsponse + " Service destroyed at " + time.getTime() + ";",
                Toast.LENGTH_LONG).show();*/
       /* sqLiteAdapter.openToWrite();
        sqLiteAdapter.openToRead();
        sqLiteAdapter.delete_value_All();*/
    }

    /*public void senddata() {
        // TODO Auto-generated method stub
        if (connectionDetector.isConnectingToInternet()) {
            try {
                sqLiteAdapter.openToWrite();
                sqLiteAdapter.openToRead();
                cursor_all_site = sqLiteAdapter.queueAll();
                cursorS(cursor_all_site);

              *//*  sqLiteAdapter_m.openToWrite();
                sqLiteAdapter_m.openToRead();
                cursor_all_Mile = sqLiteAdapter_m.queueAll();
                cursorM(cursor_all_Mile);*//*

            } catch (Exception e) {
                // TODO: handle exception
            }


        } else {

        }
    }*/

    public void cursorHhsc(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("permit_id", cursorSend.getString(1)));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic", cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("meter_no", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("checklist_yes", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("checklist_cancel", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("inspected_qty", cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("passed_qty", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("pass_rework_status", cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("camera_time", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(18)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(19)));

                Log.e("nameValuePairs hhscqc:", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_pmc_data");
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_hhsc_upload");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter.openToWrite();
                    sqLiteAdapter.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDHHSC(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }



    public void cursorNetwork(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("permit_id", cursorSend.getString(1)));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic", cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("inspection_id", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("item_id", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("checklist_yes", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("checklist_cancel", cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("inspected_qty", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("passed_qty", cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("pass_rework_status", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat", cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("camera_time", cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(18)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(19)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(20)));

                Log.e("nameValuePairs network:", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_pmc_data");
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_network_upload");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter.openToWrite();
                    sqLiteAdapter.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDNETWORK(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }


    //----------for Site observation reports -----------------------//
    public void cursorsor(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("item_id",cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("checklist_yes",cursorSend.getString(8) ));
                nameValuePairs.add(new BasicNameValuePair("checklist_cancel",cursorSend.getString(9) ));
                nameValuePairs.add(new BasicNameValuePair("remark",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(11) ));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(13) ));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("imgname2", cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("img2", cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(18) ));

                Log.e("nameValuePairs sor", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_site_inspection");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byID(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }



    //----------for issue type-----------------------//

    public void cursorIssue(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree",cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("issueType", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("otherIssue",cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat", cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("camera_long",cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("imgname1",cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("img1",cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(15)));

                Log.e("nameValuePairs issue", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_issue");
                   // HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_daily_monitoring_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDISSUE(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }


    //----------for ready JMC-----------------//

    public void cursorRecv(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("district_id",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("store_id", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("diNo",cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("vendorName", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("storeKeeperName", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("item_id",cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("diQty", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("RecvQty",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("item_yes", cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("item_cancel", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("camera_long",cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("camera_time", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("imgname1",cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("img1",cursorSend.getString(18)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(19)));

                Log.e("nameValuePairs recv", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_material_receiving");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_ready_for_jmc");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDR(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }



    //----------for Safety reports -----------------------//
    public void cursorSafety(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("item_id",cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("checklist_yes",cursorSend.getString(8) ));
                nameValuePairs.add(new BasicNameValuePair("checklist_cancel",cursorSend.getString(9) ));
                nameValuePairs.add(new BasicNameValuePair("remark",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(11) ));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(13) ));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("imgname2", cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("img2", cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(18) ));

                Log.e("nameValuePairs safety", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_safety_inspection");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byID(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }


    //----------for JMC Completion -----------------//

    public void cursorCompletion(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("team",cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("hdd",cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("jcb",cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("excavator",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("soil",cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(12)));

                Log.e("nameValuePairs td", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_td");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_jmc_completion");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                    String id=cursorSend.getString(0);
                   /* sqLiteAdapter_m.delete_value_byIDJCI(Integer.parseInt(id));
                    sqLiteAdapter_m.close();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }

    public void cursorDpr(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("permit_id", cursorSend.getString(1)));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic", cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("item_qty", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("camera_time", cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(14)));

                Log.e("nameValuePairs dpr:", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_workprogress");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_pmc_rework_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter.openToWrite();
                    sqLiteAdapter.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDWI(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }



    public void cursorStore(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("permit_id", cursorSend.getString(1)));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("audit_date", cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("store_id", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("pma_name", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("storeKeeperName", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("book_qty", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("physical_qty", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(10)));

                Log.e("nameValuePairs store:", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_store_audit");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_pmc_rework_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter.openToWrite();
                    sqLiteAdapter.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDSTORE(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }



    public void cursorCall(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("permit_id", cursorSend.getString(1)));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic", cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("nature_call", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("call_details", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("auto_number", cursorSend.getString(9)));

                Log.e("nameValuePairs call:", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_inspection_call_mgmt");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_pmc_rework_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter.openToWrite();
                    sqLiteAdapter.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDCALL(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }





    //-----for Splicing location--------------------//
    public void cursorJMC(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("contractorName",cursorSend.getString(7) ));
                nameValuePairs.add(new BasicNameValuePair("item_qty",cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("remark", cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(10) ));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(12) ));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(13)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(15) ));

                Log.e("nameValuePairs jmc", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_jmc");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDJMC(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }


    //-----for Factory Inspection--------------------//
    public void cursorFactory(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("insp_date",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("di_no", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("bel_rsp", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("factory_rsp", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("pma_rsp", cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("item_qty", cursorSend.getString(8)));
                nameValuePairs.add(new BasicNameValuePair("item_qty_bachno",cursorSend.getString(9)));
                nameValuePairs.add(new BasicNameValuePair("remark",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(11) ));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(13) ));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(15)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(16) ));

                Log.e("nameValuePairs fact", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_factory");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDFACT(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }




//-------------------for define ring------------//
    public void cursorLoading(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("hhsc_manpower", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("network_manpower", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(6)));

                Log.e("nameValue loading", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_manpower_loading");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                    sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDLOADING(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }


    //----------for POLe Receiving -----------------------//
    public void cursorPOLE(Cursor cursorSend) {
        if (cursorSend != null && cursorSend.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("permit_id",cursorSend.getString(1) ));
                nameValuePairs.add(new BasicNameValuePair("project", cursorSend.getString(2)));
                nameValuePairs.add(new BasicNameValuePair("geographic",cursorSend.getString(3)));
                nameValuePairs.add(new BasicNameValuePair("subareaone", cursorSend.getString(4)));
                nameValuePairs.add(new BasicNameValuePair("subareatwo", cursorSend.getString(5)));
                nameValuePairs.add(new BasicNameValuePair("subareathree", cursorSend.getString(6)));
                nameValuePairs.add(new BasicNameValuePair("vendorName",cursorSend.getString(7)));
                nameValuePairs.add(new BasicNameValuePair("invoiceNo",cursorSend.getString(8) ));
                nameValuePairs.add(new BasicNameValuePair("receivedQty",cursorSend.getString(9) ));
                nameValuePairs.add(new BasicNameValuePair("damagedQty",cursorSend.getString(10)));
                nameValuePairs.add(new BasicNameValuePair("acceptedQty",cursorSend.getString(11)));
                nameValuePairs.add(new BasicNameValuePair("remark",cursorSend.getString(12)));
                nameValuePairs.add(new BasicNameValuePair("camera_lat",cursorSend.getString(13) ));
                nameValuePairs.add(new BasicNameValuePair("camera_long", cursorSend.getString(14)));
                nameValuePairs.add(new BasicNameValuePair("camera_time",cursorSend.getString(15) ));
                nameValuePairs.add(new BasicNameValuePair("imgname1", cursorSend.getString(16)));
                nameValuePairs.add(new BasicNameValuePair("imgname2", cursorSend.getString(17)));
                nameValuePairs.add(new BasicNameValuePair("imgname3", cursorSend.getString(18)));
                nameValuePairs.add(new BasicNameValuePair("imgname4", cursorSend.getString(19)));
                nameValuePairs.add(new BasicNameValuePair("imgname5", cursorSend.getString(20)));
                nameValuePairs.add(new BasicNameValuePair("img1", cursorSend.getString(21)));
                nameValuePairs.add(new BasicNameValuePair("img2", cursorSend.getString(22)));
                nameValuePairs.add(new BasicNameValuePair("img3", cursorSend.getString(23)));
                nameValuePairs.add(new BasicNameValuePair("img4", cursorSend.getString(24)));
                nameValuePairs.add(new BasicNameValuePair("img5", cursorSend.getString(25)));
                nameValuePairs.add(new BasicNameValuePair("auto_number",cursorSend.getString(26) ));

                Log.e("nameValuePairs pole", "" + nameValuePairs);
                try {
                    //            new UploadToServer().execute();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_pole_receving");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    REsponse = httpclient.execute(httppost, responseHandler);
                   /* sqLiteAdapter_m.openToWrite();
                      sqLiteAdapter_m.openToRead();*/
                    String id=cursorSend.getString(0);
                    sqLiteAdapter_m.delete_value_byIDPOLE(Integer.parseInt(id));
                    sqLiteAdapter_m.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (cursorSend.moveToNext());

        } else {
        }
    }




}
