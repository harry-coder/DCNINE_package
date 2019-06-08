package com.dc9_master.material_mgmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
public class Material_In_Out_Activity extends Activity {

    Button back_btn, pending_btn,od_cancel,od_punchin,btn_mat_receive,btn_mat_issue;
    LinearLayout pop_od;
    EditText ed_remark;

    FrameLayout layout_punchOut,layout_punchIn;

    //String string_last_date,string_current_date;
    Date last_date,current_date;
    //int a=00000000,b=00000000,c=00000000;
    String  string_current_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_in_out);

        btn_mat_receive=(Button)findViewById(R.id.btn_mat_receive);
        btn_mat_issue=(Button)findViewById(R.id.btn_mat_issue);

        btn_mat_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Material_In_Out_Activity.this, Material_Receiving_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        btn_mat_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Material_In_Out_Activity.this, Material_Transfer_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

}

