package com.dc9_master.habqc;

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
public class Habqc_Network_QC_Activity extends Activity {


    Button back_btn, pending_btn,od_cancel,od_punchin,btn_next,btn_next_home;
    LinearLayout pop_od;
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
        setContentView(R.layout.habitation_network_qc);


        btn_next=(Button)findViewById(R.id.imageButton7);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Habqc_Network_QC_Activity.this, Habqc_Network_Pass_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

}

