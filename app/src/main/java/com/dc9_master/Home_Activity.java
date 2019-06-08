package com.dc9_master;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.dc9_master.attendance_module.Attendance_Activity1;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;

import com.dc9_master.dpr.DPR_Activity;
import com.dc9_master.factory_inspection.Factory_Insp_Activity;
import com.dc9_master.field_issue.Field_Material_Issue_Activity;
import com.dc9_master.issues.Issues_Activity;
import com.dc9_master.jmc.JMC_Activity;
import com.dc9_master.manpower.Manpower_Activity;
import com.dc9_master.material_mgmt.Material_In_Out_Activity;
import com.dc9_master.material_mgmt.Material_Transfer_Activity;
import com.dc9_master.material_recv1.Material_Recv_Activity;
import com.dc9_master.notification.Notification_Activity;
import com.dc9_master.pole_recv.Pole_Recv_Activity;
import com.dc9_master.safety_check.Safety_Activity;
import com.dc9_master.sor.Site_Inspection_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

/**
 * Created by nitinb on 28-01-2016.
 */
public class Home_Activity extends Activity {

    ImageButton attendance_btn,sor_btn,dpr_btn,issues_btn,store_btn,manpower_btn,jmc_btn,mat_recv_btn,habqc_network_btn,safety_btn,pole_btn,call_btn,fat_btn,field_issue_btn,mat_transfer_btn,notific_btn;
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
        setContentView(R.layout.activity_home);

        tvView = (TextView) findViewById(R.id.tvVer);

        attendance_btn=(ImageButton) findViewById(R.id.attendanceid1);
        sor_btn=(ImageButton) findViewById(R.id.sorId);
        dpr_btn=(ImageButton) findViewById(R.id.dpr_id);
//        habqc_btn=(ImageButton) findViewById(R.id.habqc_id);
//        habqc_network_btn=(ImageButton) findViewById(R.id.habqc_network_id);
        issues_btn=(ImageButton) findViewById(R.id.issues_id);
        call_btn=(ImageButton) findViewById(R.id.call_id);
        store_btn=(ImageButton) findViewById(R.id.store_audit_id);
        pole_btn=(ImageButton) findViewById(R.id.pole_id);
        manpower_btn=(ImageButton) findViewById(R.id.manpower_id);
        jmc_btn=(ImageButton) findViewById(R.id.jmc_id);
        mat_recv_btn=(ImageButton) findViewById(R.id.mat_recv_id);
        safety_btn=(ImageButton) findViewById(R.id.safety_id);
        fat_btn=(ImageButton) findViewById(R.id.fat_id);
        field_issue_btn=(ImageButton) findViewById(R.id.field_issue_id);
        mat_transfer_btn=(ImageButton) findViewById(R.id.mat_transfer_id);
        notific_btn=(ImageButton) findViewById(R.id.notific_id);




    attendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Attendance_Activity1.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        sor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Site_Inspection_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        dpr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, DPR_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

//        habqc_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home_Activity.this, Habqc_Activity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.right_in, R.anim.left_out);
//            }
//        });
//        habqc_network_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home_Activity.this, Habqc_Network_Activity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.right_in, R.anim.left_out);
//            }
//        });
        issues_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Issues_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
//        call_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home_Activity.this, Call_Mgmt_Activity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.right_in, R.anim.left_out);
//            }
//        });
//        store_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Home_Activity.this, Store_Audit_Activity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.right_in, R.anim.left_out);
//            }
//        });
        pole_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Pole_Recv_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        manpower_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Manpower_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        jmc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, JMC_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        mat_recv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Material_Recv_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        safety_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Safety_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        fat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Factory_Insp_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        field_issue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Field_Material_Issue_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        mat_transfer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Material_In_Out_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        notific_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home_Activity.this, Notification_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


    }


}