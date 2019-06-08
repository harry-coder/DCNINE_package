package com.dc9_master.material_mgmt;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * Created by nitinb on 09-02-2016.
 */
public class Material_Receiving_Activity extends Activity {
    Button next_btn;
    Spinner spinner_geographic, spinner_subarea_one,spinner_tkc;
    ArrayList<String> geographic_name_list,geographic_code_list;
    ArrayList<String> subareaone_name_list,subareaone_code_list;
    ArrayAdapter<String> geographic_adapter,subareaone_adapter,tkc_adapter;

    Spinner spinner_geographic1, spinner_subarea_one1;
    ArrayList<String> geographic_name_list1,geographic_code_list1;
    ArrayList<String> subareaone_name_list1,subareaone_code_list1;
    ArrayAdapter<String> geographic_adapter1,subareaone_adapter1;

    String geographic_id,geographic_name,
            subarea_one_code,subarea_one_name,geographic_id1,geographic_name1,
            subarea_one_code1,subarea_one_name1,str_insp_date;
     EditText ed_receivingNoteNo,ed_storeKeeperRsp;
     String  selectedDate,str_receivingNoteNo,str_storeKeeperRsp;
    TextView tv_insp_date;
    Cursor geographic_cursor,subareaone_cursor,geographic_cursor1,subareaone_cursor1;
    SQLiteAdapter1 sqLiteAdapter;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_transfer_recv);
        spinner_geographic = (Spinner)findViewById(R.id.from_district_id);
        spinner_subarea_one = (Spinner)findViewById(R.id.from_storeid);
        spinner_subarea_one.setVisibility(View.GONE);

        spinner_geographic1 = (Spinner)findViewById(R.id.to_district_id);
        spinner_subarea_one1 = (Spinner)findViewById(R.id.to_storeid);
        spinner_subarea_one1.setVisibility(View.GONE);

        tv_insp_date = (TextView)findViewById(R.id.ed_insp_date);

        ed_receivingNoteNo=(EditText)findViewById(R.id.ed_receiving_note);
        ed_storeKeeperRsp=(EditText)findViewById(R.id.ed_storekeepername);

        next_btn=(Button)findViewById(R.id.btn_next);

        geographic_name_list=new ArrayList<String>();
        geographic_code_list=new ArrayList<String>();

        subareaone_name_list=new ArrayList<String>();
        subareaone_code_list=new ArrayList<String>();

        geographic_name_list1=new ArrayList<String>();
        geographic_code_list1=new ArrayList<String>();

        subareaone_name_list1=new ArrayList<String>();
        subareaone_code_list1=new ArrayList<String>();

        tv_insp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(tv_insp_date);
            }
        });


        new Geographic_Value(Material_Receiving_Activity.this).execute();
        spinner_geographic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                geographic_id=geographic_code_list.get(position).toString();
                geographic_name=geographic_name_list.get(position).toString();
                //   Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
                if (position > 0) {
                    new SubAreaOne_Value(Material_Receiving_Activity.this).execute();
                    } else {
                    subareaone_code_list.clear();
                    subareaone_name_list.clear();
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinner_subarea_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                subarea_one_code=subareaone_code_list.get(position).toString();
                subarea_one_name=subareaone_name_list.get(position).toString();
                //  Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //-----------To District and store------------//
        new Geographic_Value1(Material_Receiving_Activity.this).execute();
        spinner_geographic1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                geographic_id1=geographic_code_list1.get(position).toString();
                geographic_name1=geographic_name_list1.get(position).toString();
                //   Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
                if (position > 0) {
                    new SubAreaOne_Value1(Material_Receiving_Activity.this).execute();
                    } else {
                    subareaone_code_list1.clear();
                    subareaone_name_list1.clear();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinner_subarea_one1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                subarea_one_code1=subareaone_code_list1.get(position).toString();
                subarea_one_name1=subareaone_name_list1.get(position).toString();
                //  Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_receivingNoteNo=ed_receivingNoteNo.getText().toString();
                str_storeKeeperRsp=ed_storeKeeperRsp.getText().toString();
                str_insp_date = tv_insp_date.getText().toString().trim();

                if(spinner_geographic.getSelectedItemPosition() == 0 ||
                        spinner_subarea_one.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_receivingNoteNo)|| TextUtils.isEmpty(str_storeKeeperRsp)) {
                    final Dialog dialog = new Dialog(Material_Receiving_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView)dialog.findViewById(R.id.text);
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
                    DataHolder_workprogress.getInstance().setGeographoic_id(geographic_id);
                    DataHolder_workprogress.getInstance().setSubareaone(subarea_one_code);
                    DataHolder_workprogress.getInstance().setGeographoic_id1(geographic_id1);
                    DataHolder_workprogress.getInstance().setSubareaone1(subarea_one_code1);
                    DataHolder_workprogress.getInstance().setStr_recev_date(str_insp_date);
                    DataHolder_workprogress.getInstance().setStr_receiving_note_no(str_insp_date);
                    DataHolder_workprogress.getInstance().setStr_storeKeepeName(str_insp_date);

                    DataHolder_workprogress.getInstance().setAuto_number(auto_number);
                    startActivity(new Intent(Material_Receiving_Activity.this, Material_Receiving_Item_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                 }
        });

    }

    public class Geographic_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        Context _context;
        Geographic_Value(Context ctx) { _context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // ShowAlertagain();
            super.onPreExecute();
            pd = new ProgressDialog(Material_Receiving_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}
            @Override
           protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Material_Receiving_Activity.this);
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                //geographic_cursor=sqLiteAdapter.select_geographic_all();
                geographic_cursor=sqLiteAdapter.select_geographic_all();
                if(geographic_cursor!=null && geographic_cursor.moveToFirst()){
                    geographic_code_list.add("Select District Name");
                    geographic_name_list.add("Select District Name");
                    do {
                        String geographic_code=geographic_cursor.getString(1);
                        String geographic_name=geographic_cursor.getString(2);
                       /* Log.e("dist_code",dist_code);
                        Log.e("dist_name",dist_name);*/
                        geographic_code_list.add(geographic_code);
                        geographic_name_list.add(geographic_name);
                    } while (geographic_cursor.moveToNext());
                }
            }catch (Exception e){
                e.printStackTrace();
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
               /* geographic_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,project_name_list);
                geographic_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                geographic_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,geographic_name_list);
                spinner_geographic.setAdapter(geographic_adapter);
                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //..................... From store.................................................//
    public class SubAreaOne_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaOne_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Material_Receiving_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(subareaone_cursor!=null){
                    subareaone_cursor=null;
                }
                subareaone_cursor = sqLiteAdapter.select_store_district_all(Integer.parseInt(geographic_id));
                subareaone_code_list.clear();
                subareaone_name_list.clear();
                if(subareaone_cursor!=null && subareaone_cursor.moveToFirst()){
                    subareaone_code_list.add("Select Store Name");
                    subareaone_name_list.add("Select Store Name");
                    do {
                        String subarea_one_code=subareaone_cursor.getString(1);
                        String subarea_one_name=subareaone_cursor.getString(2);
                       /* Log.e("code",code);
                        Log.e("name",name);*/
                        subareaone_code_list.add(subarea_one_code);
                        subareaone_name_list.add(subarea_one_name);
                        } while (subareaone_cursor.moveToNext());
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
                spinner_subarea_one.setVisibility(View.VISIBLE);
               /* subareaone_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,subareaone_name_list);
                subareaone_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                subareaone_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,subareaone_name_list);
                spinner_subarea_one.setAdapter(subareaone_adapter);
                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class Geographic_Value1 extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        Context _context;
        Geographic_Value1(Context ctx) { _context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // ShowAlertagain();
            super.onPreExecute();
            pd = new ProgressDialog(Material_Receiving_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Material_Receiving_Activity.this);
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                //geographic_cursor=sqLiteAdapter.select_geographic_all();
                geographic_cursor1=sqLiteAdapter.select_geographic_all();
                if(geographic_cursor1!=null && geographic_cursor1.moveToFirst()){
                    geographic_code_list1.add("Select District Name");
                    geographic_name_list1.add("Select District Name");
                    do {
                        String geographic_code1=geographic_cursor1.getString(1);
                        String geographic_name1=geographic_cursor1.getString(2);
                       /* Log.e("dist_code",dist_code);
                        Log.e("dist_name",dist_name);*/
                        geographic_code_list1.add(geographic_code1);
                        geographic_name_list1.add(geographic_name1);
                    } while (geographic_cursor1.moveToNext());
                }
            }catch (Exception e){
                e.printStackTrace();
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
               /* geographic_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,project_name_list);
                geographic_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                geographic_adapter1 = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,geographic_name_list1);
                spinner_geographic1.setAdapter(geographic_adapter1);
                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //..................... To store .................................................//
    public class SubAreaOne_Value1 extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaOne_Value1(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Material_Receiving_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(subareaone_cursor1!=null){
                    subareaone_cursor1=null;
                }

                subareaone_cursor1 = sqLiteAdapter.select_store_district_all(Integer.parseInt(geographic_id1));
                subareaone_code_list1.clear();
                subareaone_name_list1.clear();
                if(subareaone_cursor1!=null && subareaone_cursor1.moveToFirst()){
                    subareaone_code_list1.add("Select To Store Name");
                    subareaone_name_list1.add("Select To Store Name");
                    do {
                        String subarea_one_code1=subareaone_cursor1.getString(1);
                        String subarea_one_name1=subareaone_cursor1.getString(2);
                       /* Log.e("code",code);
                        Log.e("name",name);*/
                        subareaone_code_list1.add(subarea_one_code1);
                        subareaone_name_list1.add(subarea_one_name1);
                        } while (subareaone_cursor1.moveToNext());
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
                spinner_subarea_one1.setVisibility(View.VISIBLE);
               /* subareaone_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,subareaone_name_list);
                subareaone_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                subareaone_adapter1 = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,subareaone_name_list1);
                spinner_subarea_one1.setAdapter(subareaone_adapter1);
                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
