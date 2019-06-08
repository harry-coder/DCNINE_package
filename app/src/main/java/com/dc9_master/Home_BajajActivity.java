package com.dc9_master;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dc9_master.attendance_module.Attendance_Activity1;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.authentication.TelephonyInfo;
import com.dc9_master.call_mgmt.Call_Mgmt_Activity;
import com.dc9_master.issues.Issues_Activity;
import com.dc9_master.manpower.Manpower_Activity;
import com.dc9_master.pole_recv.Pole_Recv_Activity;
import com.dc9_master.safety_check.Safety_Activity;
import com.dc9_master.service.BackgroundService;
import com.dc9_master.sor.Site_Inspection_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

/**
 * Created by nitinb on 28-01-2016.
 */
public class Home_BajajActivity extends Activity {
    //ImageButton attendance_btn,sor_btn,habqc_btn,issues_btn,store_btn,manpower_btn,jmc_btn,mat_recv_btn,habqc_network_btn,safety_btn,pole_btn,call_btn;
    ImageButton attendance_btn,sor_btn,issues_btn,manpower_btn,safety_btn,pole_btn,call_btn;

    Button back_btn;
    TextView tvView;
    String permit_id;
    String imeiSIM1,imeiSIM2;
    String version;
    PackageInfo pInfo;
    SessionManager sessionManager;
    String district_id;
    ConnectionDetector detector;
    String project_id;
    SQLiteAdapterWorkprogress sqLiteAdapterWorkprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //  setContentView(R.layout.activity_home);
       // Mint.initAndStartSession(Home_Activity.this, "db388112");
        setContentView(R.layout.activity_home_bajaj);
        sessionManager = new SessionManager(Home_BajajActivity.this);
        this.district_id = sessionManager.GET_CURRENT_URL();
        sqLiteAdapterWorkprogress=new SQLiteAdapterWorkprogress(Home_BajajActivity.this);
        tvView = (TextView) findViewById(R.id.tvVer);

        isDualSimOrNot();
        try {
            //	cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        }   catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        version=pInfo.versionName;

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        tvView.setText("Ver: " + version);


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

        recev_id = sqLiteAdapterWorkprogress.countDataR();
        store_int = sqLiteAdapterWorkprogress.countDataSTORE();
        loading_int = sqLiteAdapterWorkprogress.countDataLOADING();
        sor_int = sqLiteAdapterWorkprogress.countData();
        call_int = sqLiteAdapterWorkprogress.countDataCALL();
        hhsc_int = sqLiteAdapterWorkprogress.countDataHHSC();
        network_int = sqLiteAdapterWorkprogress.countDataNETWORK();
        jmc_int = sqLiteAdapterWorkprogress.countDataJMC();
        issue_int = sqLiteAdapterWorkprogress.countDataISSUE();
        safety_int = sqLiteAdapterWorkprogress.countDataSA();
        pole_int = sqLiteAdapterWorkprogress.countDataPOLE();

        sqLiteAdapterWorkprogress.close();


        if (recev_id > 0 || store_int > 0 || loading_int > 0 || sor_int > 0 ||
                call_int > 0 || hhsc_int > 0 || network_int > 0 || jmc_int > 0 || issue_int > 0 ||
                safety_int > 0  || pole_int > 0) {

            //pending_btn.setVisibility(View.VISIBLE);

            Intent intent = new Intent(Home_BajajActivity.this, BackgroundService.class);
            startService(intent);

        } else {

           // Toast.makeText(getApplicationContext(), "No Pending Record", Toast.LENGTH_SHORT).show();

            /*pending_btn.setVisibility(View.INVISIBLE);
            pending_btn.setVisibility(View.GONE);*/
        }

        attendance_btn=(ImageButton) findViewById(R.id.attendanceid1);
        sor_btn=(ImageButton) findViewById(R.id.sorId);
       /* habqc_btn=(ImageButton) findViewById(R.id.habqc_id);
          habqc_network_btn=(ImageButton) findViewById(R.id.habqc_network_id);
        */issues_btn=(ImageButton) findViewById(R.id.issues_id);
        call_btn=(ImageButton) findViewById(R.id.call_id);
       // store_btn=(ImageButton) findViewById(R.id.store_audit_id);
        pole_btn=(ImageButton) findViewById(R.id.pole_id);
        manpower_btn=(ImageButton) findViewById(R.id.manpower_id);
       /* jmc_btn=(ImageButton) findViewById(R.id.jmc_id);
        mat_recv_btn=(ImageButton) findViewById(R.id.mat_recv_id);
       */ safety_btn=(ImageButton) findViewById(R.id.safety_id);




        attendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Attendance_Activity1.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        sor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Site_Inspection_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

       /* habqc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Habqc_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        habqc_network_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Habqc_Network_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });*/
        issues_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Issues_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Call_Mgmt_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
      /*  store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Store_Audit_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });*/
        pole_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Pole_Recv_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        manpower_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Manpower_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
      /*  jmc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, JMC_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        mat_recv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Material_Recv_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });*/
        safety_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_BajajActivity.this, Safety_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });




    }


    private void isDualSimOrNot(){
        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
        imeiSIM1 = telephonyInfo.getImeiSIM1();
        imeiSIM2 = telephonyInfo.getImeiSIM2();

    }






}