package com.dc9_master.safety_check;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.camera.CameraLast_Activity;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Safety_PassRework extends Activity {

    ImageButton btn_item;
    Button btn_next_item,btn_camera,btn_finish,btn_pass,btn_rework,alert_pass_btn,alert_rework_btn,alert_cancel_btn;
    LinearLayout alert_pop;
    TextView alert_text;

    Location mLocation = null;
    FusedLocationService fusedLocationService;
    SQLiteAdapterWorkprogress sqLiteAdapterw;
    ConnectionDetector connectionDetector;
    SessionManager sessionManager;
    String permit_id, str_timestamp,auto_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_check_passrework);

        btn_pass=(Button)findViewById(R.id.passId);
        btn_rework=(Button)findViewById(R.id.reworkId);
        btn_camera=(Button)findViewById(R.id.btn_camera);
        btn_next_item=(Button)findViewById(R.id.next_item_id);
        btn_finish=(Button)findViewById(R.id.finish_btn);

        alert_pop=(LinearLayout)findViewById(R.id.alert_pop1);
        alert_text=(TextView)findViewById(R.id.text);
        alert_pass_btn=(Button)findViewById(R.id.dialogButtonOK);
        alert_rework_btn=(Button)findViewById(R.id.dialogButtonOKRe);
        alert_cancel_btn=(Button)findViewById(R.id.dialogButtonNo);

        sessionManager = new SessionManager(this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        connectionDetector=new ConnectionDetector(Safety_PassRework.this);
        sqLiteAdapterw= new SQLiteAdapterWorkprogress(Safety_PassRework.this);

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Pass");
            }
        });

        btn_rework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Rework");
            }
        });

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Pass");
                alert_pop.setVisibility(View.VISIBLE);
                alert_text.setText("Are you sure you want to pass this Safety Check ?");
                alert_pass_btn.setVisibility(View.VISIBLE);
                alert_rework_btn.setVisibility(View.GONE);
            }
        });

        btn_rework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Rework");
                alert_pop.setVisibility(View.VISIBLE);
                alert_text.setText("Are you sure you want to Fail this Safety Check ?");
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
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Pass");
                alert_pop.setVisibility(View.GONE);
            }
        });
        alert_rework_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setStr_safety_pass_rework("Rework");
                alert_pop.setVisibility(View.GONE);
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Safety_PassRework.this, CameraLast_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        btn_next_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                if (TextUtils.isEmpty(DataHolder_workprogress.getInstance().getGeographoic_id()) || TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareaone())|| TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareatwo())|| TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareathree())) {

                    final Dialog dialog = new Dialog(Safety_PassRework.this);
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
                    sqLiteAdapterw.insert_valueSA(sessionManager.GET_EMP_ID(),
                            sessionManager.GET_PROJECT(),
                            DataHolder_workprogress.getInstance().getGeographoic_id(),
                            DataHolder_workprogress.getInstance().getSubareaone(),
                            DataHolder_workprogress.getInstance().getSubareatwo(),
                            DataHolder_workprogress.getInstance().getSubareathree(),
                            DataHolder_workprogress.getInstance().getItem_id(),
                            DataHolder_workprogress.getInstance().getStr_item_yes(),
                            DataHolder_workprogress.getInstance().getStr_item_cancel(),
                            DataHolder_workprogress.getInstance().getStr_remark(),
                            DataHolder_workprogress.getInstance().getCamera_lat(),
                            DataHolder_workprogress.getInstance().getCamera_long(),
                            DataHolder_workprogress.getInstance().getCamera_time(),
                            DataHolder_workprogress.getInstance().getImageNmae1(),
                            DataHolder_workprogress.getInstance().getImageNmae2(),
                            DataHolder_workprogress.getInstance().getImageNmae3(),
                            DataHolder_workprogress.getInstance().getImg1(),
                            DataHolder_workprogress.getInstance().getImg2(),
                            DataHolder_workprogress.getInstance().getImg3(),
                            auto_number);
                    sqLiteAdapterw.close();

                    final Dialog dialog = new Dialog(Safety_PassRework.this);
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
                            DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress_hhsc();
                            startActivity(new Intent(Safety_PassRework.this, Safety_Item_Activity.class));

                        }
                    });

                    dialog.show();

                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Safety_PassRework.this, SafetyUpload.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

}

