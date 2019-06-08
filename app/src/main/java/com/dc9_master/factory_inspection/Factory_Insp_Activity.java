package com.dc9_master.factory_inspection;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;
import com.dc9_master.store_audit.Store_Audit_Item1_Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Factory_Insp_Activity extends Activity {
     ImageButton btn_next;
     EditText ed_poNo,ed_diNo,ed_factLoc,ed_vendorRsp,ed_factRsp;
     String selectedDate,str_insp_date,tkc_id,str_poNo,str_diNo,str_factLoc,str_vendorRsp,str_factRsp;
     TextView tv_insp_date;
     SQLiteAdapter1 sqLiteAdapter;
     String permit_id, str_timestamp,auto_number;
     SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factory_inspection);
        tv_insp_date = (TextView)findViewById(R.id.ed_insp_date);
        ed_poNo = (EditText)findViewById(R.id.ed_pono);
        ed_diNo = (EditText)findViewById(R.id.ed_dino);
        ed_factLoc = (EditText)findViewById(R.id.ed_factory_loc);
        ed_vendorRsp = (EditText)findViewById(R.id.ed_vendorrsp);
        ed_factRsp = (EditText)findViewById(R.id.ed_factory_rsp_name);

        sessionManager = new SessionManager(Factory_Insp_Activity.this);
        sqLiteAdapter=new SQLiteAdapter1(Factory_Insp_Activity.this);

        tv_insp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(tv_insp_date);
            }
        });

        btn_next=(ImageButton)findViewById(R.id.next_btn);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_insp_date = tv_insp_date.getText().toString().trim();
                str_poNo = ed_poNo.getText().toString().trim();
                str_diNo = ed_diNo.getText().toString().trim();
                str_factLoc = ed_factLoc.getText().toString().trim();
                str_vendorRsp = ed_vendorRsp.getText().toString().trim();
                str_factRsp = ed_factRsp.getText().toString().trim();
                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;
                if (TextUtils.isEmpty(str_insp_date) || TextUtils.isEmpty(str_poNo) || TextUtils.isEmpty(str_diNo) || TextUtils.isEmpty(str_factLoc) || TextUtils.isEmpty(str_vendorRsp)) {
                    final Dialog dialog = new Dialog(Factory_Insp_Activity.this);
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
                    //DataHolder_workprogress.getInstance().setAuto_number(auto_number);
                        DataHolder_workprogress.getInstance().setStr_insp_date(str_insp_date);
                        DataHolder_workprogress.getInstance().setStr_po_no(str_poNo);
                        DataHolder_workprogress.getInstance().setStr_di_no(str_diNo);
                        DataHolder_workprogress.getInstance().setStr_factorylocation(str_factLoc);
                        DataHolder_workprogress.getInstance().setStr_vendor_rspName(str_vendorRsp);
                        DataHolder_workprogress.getInstance().setStr_fact_rsp(str_factRsp);

                    Intent i = new Intent(Factory_Insp_Activity.this, Factory_Insp_Item_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                   }
                }
        });
        }

    private void datePicker(final TextView textView) {
       // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            datePickerDialog = new DatePickerDialog(Objects.requireNonNull(this),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            textView.setText(selectedDate);
                            //System.out.println("This is the calendder date " + selectedDate);

                        }
                    }, mYear, mMonth, mDay);
        }
        datePickerDialog.show();
    }


}

