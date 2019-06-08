package com.dc9_master.notification;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.Upload_Activity;


/**
 * Created by nitinb on 29-01-2016.
 */
public class GrnDetailsActivity extends Activity {

    Button btn_accepted,btn_rejected,btn_accepeted_pop,btn_rejected_pop,btn_dialogButtonNo;
    ImageButton finish_btn;
    SparseBooleanArray a;
    String my_sel_items;
    ListView listView;
    LinearLayout alert_pop1;

    Cursor item_cursor;
    TextView grn_view,po_view,grn_date,alert_text;
    EditText ed_remark;
    String str_remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grn_list_details);
//        listView = (ListView) findViewById(R.id.listView);
//        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//        ed_remark = (EditText) findViewById(R.id.ed_remark);
//
//
//
//        btn_accepeted_pop=(Button)findViewById(R.id.accepeted_pop);
//        btn_rejected_pop=(Button) findViewById(R.id.rejected_pop);
//
//
//        alert_pop1=(LinearLayout)findViewById(R.id.alert_pop1);
//        alert_text=(TextView)findViewById(R.id.text);
//
//        btn_accepeted_pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                alert_pop1.setVisibility(View.VISIBLE);
//                btn_accepted.setVisibility(View.VISIBLE);
//                btn_rejected.setVisibility(View.GONE);
//                alert_text.setText("Are you sure you want to accept this GRN ?");
//
//            }
//        });
//        btn_rejected_pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                alert_pop1.setVisibility(View.VISIBLE);
//                btn_accepted.setVisibility(View.GONE);
//                btn_rejected.setVisibility(View.VISIBLE);
//                alert_text.setText("Are you sure you want to reject this GRN ?");
//
//            }
//        });
//
//        btn_accepted = (Button) findViewById(R.id.accepeted);
//        btn_accepted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });
//
//        btn_rejected = (Button) findViewById(R.id.rejected);
//        btn_rejected.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//            }
//        });
//
//        btn_dialogButtonNo= (Button)findViewById(R.id.dialogButtonNo);
//        btn_dialogButtonNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                alert_pop1.setVisibility(View.GONE);
//
//            }
//        });

        //        back button initialized
        finish_btn=(ImageButton)findViewById(R.id.next_btn);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(GrnDetailsActivity.this, Upload_Activity.class));
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });



     }




}
