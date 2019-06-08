package com.dc9_master.sor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import java.util.Date;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Site_Inspection_Parameters extends Activity {

    Button btn_item,btn_uprep,btn_pics;
    Button back_btn, pending_btn,od_cancel,od_punchin;
    LinearLayout pop_od;
    ScrollView multiPic_Scroll;
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
    FrameLayout layout_punchOut,layout_punchIn;

    //String string_last_date,string_current_date;
    Date last_date,current_date;
    //int a=00000000,b=00000000,c=00000000;
    String  string_current_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_inspection_parameters2);

//        btn_uprep=(Button) findViewById(R.id.imageButton5);
//        multiPic_Scroll=(ScrollView) findViewById(R.id.multiPic);
//        btn_pics=(Button) findViewById(R.id.done_pics);
//
//
//        btn_uprep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                multiPic_Scroll.setVisibility(View.VISIBLE);
//
//            }
//        });
//        btn_pics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                multiPic_Scroll.setVisibility(View.GONE);
//            }
//        });
////        sqLiteAdapter = new SQLiteAdapter1(Safety_Check_Parameters.this);
////        sqLiteAdapterWorkprogress = new SQLiteAdapterWorkprogress(Safety_Check_Parameters.this);
////
        btn_item=(Button)findViewById(R.id.imageButton7);
        btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Site_Inspection_Parameters.this, Site_Inspection_PassRework.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

}

