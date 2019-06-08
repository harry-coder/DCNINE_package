package com.dc9_master.store_audit;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
/**
 * Created by nitinb on 27-01-2016.
 */
public class Store_Audit_Activity extends Activity {
     ImageButton btn_next;
     EditText ed_pmaName,ed_storekeeperName;
     String selectedDate,str_insp_date,tkc_id,str_pmaName,str_storekeeperName;
     TextView tv_insp_date;
     Spinner spinner_tkc;
     ArrayList<String> tkc_name_list,tkc_id_list;
     Cursor tkc_cursor;
     ArrayAdapter<String> tkc_adapter;
     SQLiteAdapter1 sqLiteAdapter;
     String permit_id, str_timestamp,auto_number;
     SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_audit);

        tv_insp_date = findViewById(R.id.ed_insp_date);
        spinner_tkc = findViewById(R.id.store_id);
        ed_pmaName = findViewById(R.id.ed_pmaName);
        ed_storekeeperName = findViewById(R.id.ed_storeKeeperName);

        sessionManager = new SessionManager(Store_Audit_Activity.this);

        sqLiteAdapter=new SQLiteAdapter1(Store_Audit_Activity.this);

        tkc_name_list=new ArrayList<String>();
        tkc_id_list=new ArrayList<String>();


        new TKC_Value(Store_Audit_Activity.this).execute();
        spinner_tkc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                tkc_id = tkc_id_list.get(position).toString();
             }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        tv_insp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker(tv_insp_date);
            }
        });

        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_insp_date = tv_insp_date.getText().toString().trim();
                str_pmaName = ed_pmaName.getText().toString().trim();
                str_storekeeperName = ed_storekeeperName.getText().toString().trim();

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                if (spinner_tkc.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_insp_date)) {
                    final Dialog dialog = new Dialog(Store_Audit_Activity.this);
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
                    DataHolder_workprogress.getInstance().setStr_store_id(tkc_id);
                    DataHolder_workprogress.getInstance().setStrpmaName(str_pmaName);
                    DataHolder_workprogress.getInstance().setStr_storeKeepeName(str_storekeeperName);
                    Intent i = new Intent(Store_Audit_Activity.this, Store_Audit_Item1_Activity.class);
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



    public class TKC_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        TKC_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Store_Audit_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(tkc_cursor!=null){
                    tkc_cursor=null;
                }

                //tkc_cursor = sqLiteAdapter.select_store_all();
                tkc_cursor = sqLiteAdapter.select_store_all();
                tkc_id_list.clear();
                tkc_name_list.clear();
                if(tkc_cursor!=null && tkc_cursor.moveToFirst()){
                    tkc_id_list.add("Select Store Location");
                    tkc_name_list.add("Select Store Location");

                    do {
                        String team_id=tkc_cursor.getString(1);
                        String team_name=tkc_cursor.getString(2);
                       /* Log.e("code",code);
                          Log.e("name",name);*/

                        tkc_id_list.add(team_id);
                        tkc_name_list.add(team_name);

                    } while (tkc_cursor.moveToNext());
                }
            }catch (Exception e){
                e.printStackTrace();
                // Log.e("exception",e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();
            try {

                spinner_tkc.setVisibility(View.VISIBLE);
                tkc_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1, tkc_name_list);
                spinner_tkc.setAdapter(tkc_adapter);
                sqLiteAdapter.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}

