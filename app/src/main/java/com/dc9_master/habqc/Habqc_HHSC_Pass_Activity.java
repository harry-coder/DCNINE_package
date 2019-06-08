package com.dc9_master.habqc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Habqc_HHSC_Pass_Activity extends Activity {


    Button btn_finish,next_item_btn,btn_pass,btn_rework,alert_pass_btn,alert_rework_btn,alert_cancel_btn;
    EditText ed_inspqty,ed_passedqty;
    LinearLayout alert_pop;
    TextView alert_text;
    String str_inspqty,str_passedqty;
    Location mLocation = null;
    FusedLocationService fusedLocationService;
    SQLiteAdapterWorkprogress sqLiteAdapterw;
    ConnectionDetector connectionDetector;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habqc_hhsc_passrework2);

        ed_inspqty=findViewById(R.id.ed_insp_qty);
        ed_passedqty=findViewById(R.id.ed_passed_qty);

        btn_pass=(Button)findViewById(R.id.passId);
        btn_rework=(Button)findViewById(R.id.reworkId);
        next_item_btn=(Button)findViewById(R.id.next_item_id);
        btn_finish=(Button)findViewById(R.id.finish_btn);

        alert_pop=(LinearLayout)findViewById(R.id.alert_pop1);
        alert_text=(TextView)findViewById(R.id.text);
        alert_pass_btn=(Button)findViewById(R.id.dialogButtonOK);
        alert_rework_btn=(Button)findViewById(R.id.dialogButtonOKRe);
        alert_cancel_btn=(Button)findViewById(R.id.dialogButtonNo);

        sessionManager = new SessionManager(this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        connectionDetector=new ConnectionDetector(Habqc_HHSC_Pass_Activity.this);
        sqLiteAdapterw= new SQLiteAdapterWorkprogress(Habqc_HHSC_Pass_Activity.this);

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DataHolder_workprogress.getInstance().setHhsc_pass_rework_status("Pass");
                alert_pop.setVisibility(View.VISIBLE);
                alert_text.setText("Are you sure you want to pass this QC ?");
                alert_pass_btn.setVisibility(View.VISIBLE);
                alert_rework_btn.setVisibility(View.GONE);
            }
        });

        btn_rework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DataHolder_workprogress.getInstance().setHhsc_pass_rework_status("Rework");
                alert_pop.setVisibility(View.VISIBLE);
                alert_text.setText("Are you sure you want to Rework this QC ?");
                alert_pass_btn.setVisibility(View.GONE);
                alert_rework_btn.setVisibility(View.VISIBLE);
            }
        });
        alert_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DataHolder_workprogress.getInstance().setHhsc_pass_rework_status("Rework");
                alert_pop.setVisibility(View.GONE);
            }
        });
        alert_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setHhsc_pass_rework_status("Pass");
                alert_pop.setVisibility(View.GONE);
            }
        });
        alert_rework_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setHhsc_pass_rework_status("Rework");
                alert_pop.setVisibility(View.GONE);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_inspqty=ed_inspqty.getText().toString();
                str_passedqty=ed_passedqty.getText().toString();

                if (TextUtils.isEmpty(DataHolder_workprogress.getInstance().getGeographoic_id()) || TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareaone())
                        || TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareatwo())|| TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareathree())
                        || TextUtils.isEmpty(str_inspqty) || TextUtils.isEmpty(str_passedqty) ||TextUtils.isEmpty(DataHolder_workprogress.getInstance().getHhsc_pass_rework_status())) {

                    final Dialog dialog = new Dialog(Habqc_HHSC_Pass_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Enter all mandatory fields");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });

                    dialog.show();

                }else {

                    DataHolder_workprogress.getInstance().setStr_inspectedQty(str_inspqty);
                    DataHolder_workprogress.getInstance().setStr_passedQTY(str_passedqty);
                    Intent i = new Intent(Habqc_HHSC_Pass_Activity.this, HHSCUpload.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        });
        next_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                if (TextUtils.isEmpty(DataHolder_workprogress.getInstance().getGeographoic_id()) || TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareaone())|| TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareatwo())|| TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareathree())) {

                    final Dialog dialog = new Dialog(Habqc_HHSC_Pass_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Enter all mandatory fields");
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

                else
                {

                    sqLiteAdapterw.openToRead();
                    sqLiteAdapterw.openToWrite();
                    sqLiteAdapterw.insert_valueHHSC(sessionManager.GET_EMP_ID(),
                            sessionManager.GET_PROJECT(),
                            DataHolder_workprogress.getInstance().getGeographoic_id(),
                            DataHolder_workprogress.getInstance().getSubareaone(),
                            DataHolder_workprogress.getInstance().getSubareatwo(),
                            DataHolder_workprogress.getInstance().getSubareathree(),
                            DataHolder_workprogress.getInstance().getMeter_no(),
                            DataHolder_workprogress.getInstance().getStr_item_yes(),
                            DataHolder_workprogress.getInstance().getStr_item_cancel(),
                            DataHolder_workprogress.getInstance().getStr_remark(),
                            str_inspqty,
                            str_passedqty,
                            DataHolder_workprogress.getInstance().getHhsc_pass_rework_status(),
                            DataHolder_workprogress.getInstance().getCamera_lat(),
                            DataHolder_workprogress.getInstance().getCamera_long(),
                            DataHolder_workprogress.getInstance().getCamera_time(),
                            DataHolder_workprogress.getInstance().getImageNmae1(),
                            DataHolder_workprogress.getInstance().getImg1(),
                            auto_number);
                    sqLiteAdapterw.close();


                    final Dialog dialog = new Dialog(Habqc_HHSC_Pass_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Sending Data To Server");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            //DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress();
                            startActivity(new Intent(Habqc_HHSC_Pass_Activity.this, Habqc_Activity.class));

                        }
                    });

                    dialog.show();
                    }

               /* Intent i = new Intent(Habqc_HHSC_Pass_Activity.this, Habqc_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        });

    }

}

