package com.dc9_master.attendance_module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.data_holder.ScopeDTO;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by nitinb on 09-02-2016.
 */
public class AttendanceLeave_Activity extends Activity {
     Button next_btn;

     TextView tvfrom_date,tvto_date;
    EditText ed_reason;
    String permit_id,str_timestamp,auto_number,str_from_date,str_to_date,str_reason;
    SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;
    String selectedDate;
    Date from_date,to_date,today_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_leave);
        sessionManager = new SessionManager(AttendanceLeave_Activity.this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        next_btn = (Button) findViewById(R.id.next_btn);
        ed_reason = (EditText) findViewById(R.id.ed_reason);
        tvfrom_date = (TextView)findViewById(R.id.ed_fromdate);
        tvto_date =(TextView) findViewById(R.id.ed_todate);

        tvfrom_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker(tvfrom_date);
            }
        });

        tvto_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker(tvto_date);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permit_id = sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault()).format(new Date());

                str_from_date = tvfrom_date.getText().toString().trim();
                str_to_date = tvto_date.getText().toString().trim();
                str_reason = ed_reason.getText().toString().trim();

                try {

                    from_date = new SimpleDateFormat("yyyy:MM:dd", Locale.ENGLISH).parse(str_from_date);
                    to_date = new SimpleDateFormat("yyyy:MM:dd", Locale.ENGLISH).parse(str_to_date);
                    today_date = new SimpleDateFormat("yyyy:MM:dd", Locale.ENGLISH).parse(str_timestamp);

                    if (from_date.compareTo(today_date) == 0) {
                        System.out.println("s is after end");
                    } else if (to_date.compareTo(today_date) == 0) {
                        System.out.println("start is before end");
                    } else if (from_date.compareTo(to_date) < 0 ) {
                        System.out.println("start is equal to end");
                    }

                   /* if (TextUtils.isEmpty(str_from_date) || TextUtils.isEmpty(str_to_date) || TextUtils.isEmpty(str_reason) || from_date.compareTo(today_date) == 0 || to_date.compareTo(today_date) == 0 || from_date.compareTo(to_date) < 0 ) {
                        // System.out.println("start is equal to current");
                        final Dialog dialog = new Dialog(AttendanceLeave_Activity.this);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        dialog.setContentView(R.layout.custom);
                        TextView text = dialog.findViewById(R.id.text);
                        text.setText("Enter all mandatory fields");
                        Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                            }
                        });

                        dialog.show();
                    }*/
                    else {

                        DataHolder_workprogress.getInstance().setFrom_date(str_from_date);
                        DataHolder_workprogress.getInstance().setTo_date(str_to_date);
                        DataHolder_workprogress.getInstance().setReason(str_reason);

                        startActivity(new Intent(AttendanceLeave_Activity.this, LeaveUpload.class));
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        }

                    System.out.println(from_date);
                    System.out.println(to_date);
                    System.out.println(today_date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }




            }
        });


    }


    @Override
    public void onBackPressed() {
        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

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

                          /*  if (selectedDate.compareTo(str_timestamp) > 0) {
                                System.out.println("from date must be greater than Current date");
                            }else {
                                textView.setText(selectedDate);
                            }*/
                            textView.setText(selectedDate);
                            System.out.println("This is the calendder date " + selectedDate);

                        }
                    }, mYear, mMonth, mDay);
        }
        datePickerDialog.show();
    }

}
