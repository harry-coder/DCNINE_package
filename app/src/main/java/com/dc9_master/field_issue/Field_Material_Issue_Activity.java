package com.dc9_master.field_issue;

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
import com.dc9_master.material_recv1.Material_Recv_Item_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.util.ArrayList;
/**
 * Created by nitinb on 09-02-2016.
 */
public class Field_Material_Issue_Activity extends Activity {
    Button next_btn;
    Spinner spinner_geographic, spinner_subarea_one,spinner_tkc;
    ArrayList<String> geographic_name_list,geographic_code_list;
    ArrayList<String> subareaone_name_list,subareaone_code_list;
    ArrayAdapter<String> geographic_adapter,subareaone_adapter,tkc_adapter;
    ArrayList<String> tkc_name_list,tkc_code_list;
    String geographic_id,geographic_name,
            subarea_one_code,subarea_one_name,tkc_id,tkc_name;
     EditText ed_ispNo,ed_storeKeeperRsp;
     String str_ispNo,str_storeKeeperRsp;
    Cursor geographic_cursor,subareaone_cursor,tkc_cursor;
    SQLiteAdapter1 sqLiteAdapter;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_material_issue);

        spinner_geographic = (Spinner)findViewById(R.id.geographical_id);
        spinner_subarea_one = (Spinner)findViewById(R.id.store_id);
        spinner_subarea_one.setVisibility(View.GONE);

        spinner_tkc = (Spinner)findViewById(R.id.tkc_id);
        spinner_tkc.setVisibility(View.GONE);

        ed_ispNo=(EditText)findViewById(R.id.ed_ispno);
        ed_storeKeeperRsp=(EditText)findViewById(R.id.ed_storeKeeperRspName);

        next_btn=findViewById(R.id.btn_next);

        geographic_name_list=new ArrayList<String>();
        geographic_code_list=new ArrayList<String>();

        subareaone_name_list=new ArrayList<String>();
        subareaone_code_list=new ArrayList<String>();

        tkc_code_list=new ArrayList<String>();
        tkc_name_list=new ArrayList<String>();

        new Geographic_Value(Field_Material_Issue_Activity.this).execute();
        spinner_geographic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                geographic_id=geographic_code_list.get(position).toString();
                geographic_name=geographic_name_list.get(position).toString();
                //   Toast.makeText(getApplicationContext(),""+subdivision_code,Toast.LENGTH_LONG).show();
                if (position > 0) {
                    new SubAreaOne_Value(Field_Material_Issue_Activity.this).execute();
                    new TKC_Value(Field_Material_Issue_Activity.this).execute();

                } else {
                    subareaone_code_list.clear();
                    subareaone_name_list.clear();
                    tkc_code_list.clear();
                    tkc_name_list.clear();
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

        spinner_tkc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                tkc_id=tkc_code_list.get(position).toString();
                tkc_name=tkc_name_list.get(position).toString();
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
                str_ispNo=ed_ispNo.getText().toString();
                str_storeKeeperRsp=ed_storeKeeperRsp.getText().toString();

                if(spinner_geographic.getSelectedItemPosition() == 0 ||
                        spinner_subarea_one.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_ispNo) || TextUtils.isEmpty(str_storeKeeperRsp)) {
                    final Dialog dialog = new Dialog(Field_Material_Issue_Activity.this);
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
                    DataHolder_workprogress.getInstance().setStr_tkc_id(tkc_id);
                    DataHolder_workprogress.getInstance().setStr_isp_no(str_ispNo);
                    DataHolder_workprogress.getInstance().setStr_storeKeepeName(str_storeKeeperRsp);
                    DataHolder_workprogress.getInstance().setAuto_number(auto_number);

                    startActivity(new Intent(Field_Material_Issue_Activity.this, Field_Material_Issue_Item_Activity.class));
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
            pd = new ProgressDialog(Field_Material_Issue_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}
            @Override
           protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Field_Material_Issue_Activity.this);
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

    //..................... SubAreaOne_Value .................................................//
    public class SubAreaOne_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        SubAreaOne_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Field_Material_Issue_Activity.this);
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


    //..................... TKC .................................................//
    public class TKC_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        TKC_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Field_Material_Issue_Activity.this);
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

                tkc_cursor = sqLiteAdapter.select_store_district_all(Integer.parseInt(geographic_id));
                tkc_code_list.clear();
                tkc_name_list.clear();
                if(tkc_cursor!=null && tkc_cursor.moveToFirst()){
                    tkc_code_list.add("Select Contractor Name");
                    tkc_name_list.add("Select Contractor Name");
                    do {
                        String tkc_one_code=tkc_cursor.getString(1);
                        String tkc_one_name=tkc_cursor.getString(2);
                       /* Log.e("code",code);
                        Log.e("name",name);*/
                        tkc_code_list.add(tkc_one_code);
                        tkc_name_list.add(tkc_one_name);

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
               /* subareaone_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,subareaone_name_list);
                subareaone_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                tkc_adapter = new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.custom_spinner, R.id.textView1,tkc_name_list);
                spinner_tkc.setAdapter(tkc_adapter);

                sqLiteAdapter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
