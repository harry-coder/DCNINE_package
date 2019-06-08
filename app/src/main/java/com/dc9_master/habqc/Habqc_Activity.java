package com.dc9_master.habqc;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nitinb on 09-02-2016.
 */
public class Habqc_Activity extends Activity {

    Integer count_subareathree;
    Button next_btn;
    Spinner spinner_geographic, spinner_subarea_one, spinner_subarea_two, spinner_subarea_three;
    ArrayList<String> geographic_project_code_list,geographic_name_list,geographic_code_list;
    ArrayList<String> subareaone_geographic_code_list,subareaone_name_list,subareaone_code_list;
    ArrayList<String> subareatwo_one_code_list,subareatwo_name_list,subareatwo_code_list;
    ArrayList<String> subareathree_two_code_list,subareathree_name_list,subareathree_code_list;
    ArrayAdapter<String> geographic_adapter,subareaone_adapter,subareatwo_adapter,subareathree_adapter;
    String geographic_project_code, geographic_id,geographic_name,
            subareaone_geographic_code,subarea_one_code,subarea_one_name,subareatwo_one_code,subarea_two_code,subarea_two_name,
            subareathree_two_code,subarea_three_code,subarea_three_name;

    Cursor geographic_cursor,subareaone_cursor,subareatwo_cursor,subareathree_cursor;
    SQLiteAdapter1 sqLiteAdapter;
    EditText ed_meterno;
    String str_meterno;

    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;
    boolean check=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habitation_qc);

        sessionManager = new SessionManager(Habqc_Activity.this);
        spinner_geographic = findViewById(R.id.geographical_id);


        geographic_id= DataHolder_workprogress.getInstance().getGeographoic_id();
        subarea_one_code= DataHolder_workprogress.getInstance().getSubareaone();
        subarea_two_code=DataHolder_workprogress.getInstance().getSubareatwo();
        subarea_three_code=DataHolder_workprogress.getInstance().getSubareathree();


        spinner_subarea_one = findViewById(R.id.subarea_one_id);
        spinner_subarea_one.setVisibility(View.GONE);

        spinner_subarea_two = findViewById(R.id.subarea_two_id);
        spinner_subarea_two.setVisibility(View.GONE);

        spinner_subarea_three = findViewById(R.id.subarea_three_id);
        spinner_subarea_three.setVisibility(View.GONE);

        ed_meterno=findViewById(R.id.ed_meterno);

        next_btn=findViewById(R.id.btn_next);

        geographic_project_code_list=new ArrayList<String>();
        geographic_name_list=new ArrayList<String>();
        geographic_code_list=new ArrayList<String>();

        subareaone_geographic_code_list=new ArrayList<String>();
        subareaone_name_list=new ArrayList<String>();
        subareaone_code_list=new ArrayList<String>();

        subareatwo_one_code_list=new ArrayList<String>();
        subareatwo_name_list=new ArrayList<String>();
        subareatwo_code_list=new ArrayList<String>();

        subareathree_two_code_list=new ArrayList<String>();
        subareathree_name_list=new ArrayList<String>();
        subareathree_code_list=new ArrayList<String>();


        new Geographic_Value(Habqc_Activity.this).execute();
        spinner_geographic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                geographic_project_code=geographic_project_code_list.get(position).toString();
                geographic_id=geographic_code_list.get(position).toString();
                geographic_name=geographic_name_list.get(position).toString();
//                Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();

                if (position > 0) {
                    new SubAreaOne_Value(Habqc_Activity.this).execute();
                } else {
                    subareaone_geographic_code_list.clear();
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
                subareaone_geographic_code = subareaone_geographic_code_list.get(position).toString();
                subarea_one_code=subareaone_code_list.get(position).toString();
                subarea_one_name=subareaone_name_list.get(position).toString();

               //  Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
                  if (position > 0) {
                    new SubAreaTwo_Value(Habqc_Activity.this).execute();

                } else {
                    spinner_subarea_two.setVisibility(View.GONE);
                    //spinner_subarea_three.setAdapter(null);
                    subareatwo_one_code_list.clear();
                    subareatwo_code_list.clear();
                    subareatwo_name_list.clear();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_subarea_two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                subareatwo_one_code = subareatwo_one_code_list.get(position).toString();
                subarea_two_code=subareatwo_code_list.get(position).toString();
                subarea_two_name=subareatwo_name_list.get(position).toString();
//                Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();

                if (position > 0) {
                    new SubAreaThree_Value(Habqc_Activity.this).execute();

                } else {
                    spinner_subarea_three.setVisibility(View.GONE);
                    //spinner_subarea_three.setAdapter(null);
                    subareathree_two_code_list.clear();
                    subareathree_code_list.clear();
                    subareathree_name_list.clear();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        spinner_subarea_three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                subareathree_two_code = subareathree_two_code_list.get(position).toString();
                subarea_three_code = subareathree_code_list.get(position).toString();
                subarea_three_name = subareathree_name_list.get(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_meterno=ed_meterno.getText().toString();

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;


                if (spinner_geographic.getSelectedItemPosition() == 0 ||
                        spinner_subarea_one.getSelectedItemPosition() == 0 ||
                        spinner_subarea_two.getSelectedItemPosition() == 0 ||
                        spinner_subarea_three.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_meterno)) {

                    final Dialog dialog = new Dialog(Habqc_Activity.this);
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

                        DataHolder_workprogress.getInstance().setGeographoic_id(geographic_id);
                        DataHolder_workprogress.getInstance().setSubareaone(subarea_one_code);
                        DataHolder_workprogress.getInstance().setSubareatwo(subarea_two_code);
                        DataHolder_workprogress.getInstance().setSubareathree(subarea_three_code);
                        DataHolder_workprogress.getInstance().setMeter_no(str_meterno);
                        DataHolder_workprogress.getInstance().setAuto_number(auto_number);

                       // startActivity(new Intent(Site_Inspection_Activity.this, Site_Inspection_Item.class));
                        startActivity(new Intent(Habqc_Activity.this, Habqc_hhsc_Checklist_Activity.class));
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);


                }

            }
        });


    }



    @Override
    public void onBackPressed() {
        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }


    //.....................Geographic Area.................................................//

    public class Geographic_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        Context _context;
        Geographic_Value(Context ctx) { _context=ctx;}

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // ShowAlertagain();
            super.onPreExecute();
            pd = new ProgressDialog(Habqc_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Habqc_Activity.this);
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                geographic_cursor=sqLiteAdapter.select_geographic_all();
                if(geographic_cursor!=null && geographic_cursor.moveToFirst()){
                    geographic_project_code_list.add("Select District Name");
                    geographic_code_list.add("Select District Name");
                    geographic_name_list.add("Select District Name");
                    do {
                        String geographic_project_code=geographic_cursor.getString(1);
                        String geographic_code=geographic_cursor.getString(2);
                        String geographic_name=geographic_cursor.getString(3);
                       /* Log.e("dist_code",dist_code);
                        Log.e("dist_name",dist_name);*/
                        geographic_project_code_list.add(geographic_project_code);
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
           /* try {
               *//* geographic_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,project_name_list);
                geographic_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*//*
               geographic_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,geographic_name_list);
                spinner_geographic.setAdapter(geographic_adapter);
                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }*/
            try {

                spinner_geographic.setVisibility(View.VISIBLE);
                 geographic_adapter = new ArrayAdapter<String>(
                 getApplicationContext(), R.layout.custom_spinner, R.id.textView1,geographic_name_list);
                spinner_geographic.setAdapter(geographic_adapter);
               sqLiteAdapter.close();

                if (check) {
                    int position = geographic_code_list.indexOf(geographic_id);
                    spinner_geographic.setSelection(position);
                    }

            }catch (Exception e){

                e.printStackTrace();

            }
        }
    }



    //..................... SubAreaOne_Value .................................................//

    public class SubAreaOne_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaOne_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Habqc_Activity.this);
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

                subareaone_cursor = sqLiteAdapter.select_subarea_one_all(Integer.parseInt(geographic_id));
                subareaone_code_list.clear();
                subareaone_name_list.clear();
                if(subareaone_cursor!=null && subareaone_cursor.moveToFirst()){
                    subareaone_geographic_code_list.add("Select Block");
                    subareaone_code_list.add("Select Block");
                    subareaone_name_list.add("Select Block");
                    do {
                        String subareaone_geographic_code=subareaone_cursor.getString(1);
                        String subarea_one_code=subareaone_cursor.getString(2);
                        String subarea_one_name=subareaone_cursor.getString(3);
                       /* Log.e("code",code);
                        Log.e("name",name);*/

                        subareaone_geographic_code_list.add(subareaone_geographic_code);
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
               subareaone_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,subareaone_name_list);
                spinner_subarea_one.setAdapter(subareaone_adapter);
                sqLiteAdapter.close();
                if (check) {
                    int position = subareaone_code_list.indexOf(subarea_one_code);
                    spinner_subarea_one.setSelection(position);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    //..................... SubAreaTwo_Value .................................................//

    public class SubAreaTwo_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaTwo_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Habqc_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(subareatwo_cursor!=null){
                    subareatwo_cursor=null;
                }

                subareatwo_cursor = sqLiteAdapter.select_subarea_two_all(Integer.parseInt(subarea_one_code));
                //subareatwo_cursor = sqLiteAdapter.select_subarea_two_all(57);
                subareatwo_code_list.clear();
                subareatwo_name_list.clear();
                if(subareatwo_cursor!=null && subareatwo_cursor.moveToFirst()){
                    subareatwo_one_code_list.add("Select Village");
                    subareatwo_code_list.add("Select Village");
                    subareatwo_name_list.add("Select Village");

                    do {
                        String subareatwo_one_code=subareatwo_cursor.getString(1);
                        String subarea_two_code=subareatwo_cursor.getString(2);
                        String subarea_two_name=subareatwo_cursor.getString(3);
                       /* Log.e("code",code);
                        Log.e("name",name);*/

                        subareatwo_one_code_list.add(subareatwo_one_code);
                        subareatwo_code_list.add(subarea_two_code);
                        subareatwo_name_list.add(subarea_two_name);

                    } while (subareatwo_cursor.moveToNext());
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
                spinner_subarea_two.setVisibility(View.VISIBLE);
               subareatwo_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,subareatwo_name_list);
                spinner_subarea_two.setAdapter(subareatwo_adapter);
                sqLiteAdapter.close();
                if (check) {
                    int position = subareatwo_code_list.indexOf(subarea_two_code);
                    spinner_subarea_two.setSelection(position);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    //------------------------ sub area three --------------------------------------//
    public class SubAreaThree_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaThree_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Habqc_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(subareathree_cursor!=null){
                    subareathree_cursor=null;
                }

                subareathree_cursor = sqLiteAdapter.select_subarea_three_all(Integer.parseInt(subarea_two_code));
                //subareathree_cursor = sqLiteAdapter.select_features_all();
                count_subareathree=subareathree_cursor.getCount();
                subareathree_code_list.clear();
                subareathree_name_list.clear();
                if(subareathree_cursor!=null && subareathree_cursor.moveToFirst()){
                    subareathree_two_code_list.add("Select Majra");
                    subareathree_code_list.add("Select Majra");
                    subareathree_name_list.add("Select Majra");

                    do {
                        String subareathree_two_code=subareathree_cursor.getString(1);
                        String subarea_three_code=subareathree_cursor.getString(2);
                        String subarea_three_name=subareathree_cursor.getString(3);
                       /* Log.e("code",code);
                          Log.e("name",name);*/

                        subareathree_two_code_list.add(subareathree_two_code);
                        subareathree_code_list.add(subarea_three_code);
                        subareathree_name_list.add(subarea_three_name);

                    } while (subareathree_cursor.moveToNext());
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
                spinner_subarea_three.setVisibility(View.VISIBLE);
                    subareathree_adapter = new ArrayAdapter<String>(
                            getApplicationContext(), R.layout.custom_spinner, R.id.textView1, subareathree_name_list);
                    spinner_subarea_three.setAdapter(subareathree_adapter);
                    sqLiteAdapter.close();
                if (check) {
                    int position = subareathree_code_list.indexOf(subarea_three_code);
                    spinner_subarea_three.setSelection(position);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
