package com.dc9_master.store_audit;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.camera.Camera_Activity;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.remark.Remark_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Store_Audit_Item_Activity extends Activity {
     ImageButton btn_camera;
     Button btn_next,btn_remark,btn_finish;
     EditText ed_bookqty,ed_physicalqty;
     String tkc_id,str_bookqty,str_physicalqty;
     Spinner spinner_tkc;
     ArrayList<String> tkc_name_list,tkc_id_list;
     Cursor tkc_cursor;
     ArrayAdapter<String> tkc_adapter;
     SQLiteAdapter1 sqLiteAdapter;
     String permit_id, str_timestamp,auto_number;
     SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_audit_item);

        spinner_tkc = findViewById(R.id.item_id);
        ed_bookqty = findViewById(R.id.ed_bookqty);
        ed_physicalqty = findViewById(R.id.ed_physicalqty);

        btn_next = findViewById(R.id.btn_next);
        btn_remark = findViewById(R.id.btn_remark);
        btn_finish = findViewById(R.id.btn_finish);

        tkc_name_list=new ArrayList<String>();
        tkc_id_list=new ArrayList<String>();

        sqLiteAdapter=new SQLiteAdapter1(Store_Audit_Item_Activity.this);
        sessionManager = new SessionManager(this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();


        new TKC_Value(Store_Audit_Item_Activity.this).execute();
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

        btn_camera=findViewById(R.id.btnpic);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(Material_Recv_Item_Activity.this, Camera_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                if (mLocation != null) {
                    //startActivity(new Intent(SurveyActivity.this, Camera_Activity.class));
                    Intent i = new Intent(Store_Audit_Item_Activity.this, Camera_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {

                    ShowAlertagain();
                }
            }
        });

        btn_remark=(Button)findViewById(R.id.btn_remark);
        btn_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Store_Audit_Item_Activity.this, Remark_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });

        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_bookqty = ed_bookqty.getText().toString().trim();
                str_physicalqty = ed_physicalqty.getText().toString().trim();

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                if (spinner_tkc.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_bookqty) || TextUtils.isEmpty(str_physicalqty)) {
                    final Dialog dialog = new Dialog(Store_Audit_Item_Activity.this);
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
                        DataHolder_workprogress.getInstance().setAuto_number(auto_number);
                        DataHolder_workprogress.getInstance().setStr_store_id(tkc_id);
                        DataHolder_workprogress.getInstance().setStr_bookqty(str_bookqty);
                        DataHolder_workprogress.getInstance().setStr_physicalqty(str_physicalqty);

                    Intent i = new Intent(Store_Audit_Item_Activity.this, Store_Audit_Item_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }


            }
        });


        btn_finish=findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_bookqty = ed_bookqty.getText().toString().trim();
                str_physicalqty = ed_physicalqty.getText().toString().trim();

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                if (spinner_tkc.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_bookqty)) {
                    final Dialog dialog = new Dialog(Store_Audit_Item_Activity.this);
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
                    DataHolder_workprogress.getInstance().setAuto_number(auto_number);
                    DataHolder_workprogress.getInstance().setItem_id(tkc_id);
                    DataHolder_workprogress.getInstance().setStr_bookqty(str_bookqty);
                    DataHolder_workprogress.getInstance().setStr_physicalqty(str_physicalqty);

                    Intent i = new Intent(Store_Audit_Item_Activity.this, Store_Audit_ReportUpload_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }


            }
        });

        }

    public class TKC_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        TKC_Value(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Store_Audit_Item_Activity.this);
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

                tkc_cursor = sqLiteAdapter.select_item_all();
                //count_tkc=tkc_cursor.getCount();
                tkc_id_list.clear();
                tkc_name_list.clear();
                if(tkc_cursor!=null && tkc_cursor.moveToFirst()){
                    tkc_id_list.add("Select Item Name");
                    tkc_name_list.add("Select Item Name");
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


    public void ShowAlertagain() {
        if (!isLocationServiceEnabled()) {
            // Toast.makeText(getApplicationContext(), "please wait while location is fetching", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Store_Audit_Item_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* latt = 0;
                                    longg = 0;
                                    */
                                    startActivity(new Intent(Store_Audit_Item_Activity.this, Camera_Activity.class));
                                    // finish();
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                    dialog.cancel();
                                }
                            });
                    builder.setNegativeButton("SETTINGS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        } else {

            startActivity(new Intent(Store_Audit_Item_Activity.this, Camera_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);

            //   Log.e("Latitude", "" + mLocation.getLatitude());
            //   Log.e("Longitude", "" + mLocation.getLongitude());
        }
    }


    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true).trim();
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals(provider));
    }



}

