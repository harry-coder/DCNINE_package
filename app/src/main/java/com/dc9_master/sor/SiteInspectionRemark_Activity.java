package com.dc9_master.sor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.data_holder.DataHolder_workprogress;

/**
 * Created by nitinb on 29-01-2016.
 */
public class SiteInspectionRemark_Activity extends Activity {

    ImageButton next_btn;
    Button back_btn,Button_down;
    EditText ed_remark;
    String str_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_remark);
        ed_remark=(EditText)findViewById(R.id.editextid);


        //-------------for Pole Remark-----------------//
        next_btn=(ImageButton)findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_remark = ed_remark.getText().toString().trim();

                if(TextUtils.isEmpty(str_remark)) {

                    //Toast.makeText(Site_Pole_Remark_Activity.this, "Enter all mandatory fields", Toast.LENGTH_SHORT).show();
                    final Dialog dialog = new Dialog(SiteInspectionRemark_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Enter Item Observation fields");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });

                    dialog.show();


                } else {


                    DataHolder_workprogress.getInstance().setStr_remark(str_remark);

                    onBackPressed();
                }


            }
        });


        //-------------for back Page-----------------//
        back_btn=(Button)findViewById(R.id.backpageId2);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i = new Intent(Site_Pole_Remark_Activity.this, Site_Pole_Check_Activity1.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        });
        Button_down = (Button) findViewById(R.id.met);
        ed_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button_down.setVisibility(View.VISIBLE);
            }

//
        });

        Button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when play is clicked show stop button and hide play button
                Button_down.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }
        });
    }

    @Override
    public void onBackPressed() {

        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }



}
