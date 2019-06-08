package com.dc9_master.material_mgmt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.dc9_master.R;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.camera.CameraMultiPicAll_Activity;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.remark.Remark_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by nitinb on 24-12-2018.
 */
public class Material_Receiving_Item_Activity extends Activity {
    private LinearLayout mContainerView;
    private View mExclusiveEmptyView;
    Button addnew;
    View rowView;
    List<EditText> allEds = new ArrayList<EditText>();
    List<EditText> allPassEds = new ArrayList<EditText>();
    List<Spinner> spnr = new ArrayList<Spinner>();
    Button next_btn,btn_remark,btn_camera;
    Spinner spinner_item;
    ArrayList<String> item_code_list,item_name_list;
    ArrayAdapter<String> item_adapter;
    Cursor item_cursor;
    SQLiteAdapter1 sqLiteAdapter;
    EditText ed_qty,ed_passedqty;
    String item_code,item_code1;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_insp);
        addnew=(Button)findViewById(R.id.btnAddNewItem);
        mContainerView = (LinearLayout) findViewById(R.id.parentView);
        item_code_list=new ArrayList<String>();
        item_name_list=new ArrayList<String>();
        new Item_Value(Material_Receiving_Item_Activity.this).execute();
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflateEditRow();
            }
        });
        sessionManager = new SessionManager(this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        btn_camera=(Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(Material_Recv_Item_Activity.this, Camera_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                if (mLocation != null) {
                    //startActivity(new Intent(SurveyActivity.this, Camera_Activity.class));
                    Intent i = new Intent(Material_Receiving_Item_Activity.this, CameraMultiPicAll_Activity.class);
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
                Intent i = new Intent(Material_Receiving_Item_Activity.this, Remark_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
        });

        next_btn=(Button)findViewById(R.id.btn_finish);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //List<String> _out=new ArrayList<>();
                    if (mContainerView.getChildCount() > 0) {
                        String listString="";
                        for (int i = 0; i < allEds.size(); i++)
                        {
                            listString=listString+""+item_code_list.get(spnr.get(i).getSelectedItemPosition())+"_"+allEds.get(i).getText().toString()+",";
                            //   _out.add(item_code_list.get(spnr.get(i).getSelectedItemPosition())+"_"+allEds.get(i).getText().toString());
                            //  hm.put(item_code_list.get(spnr.get(i).getSelectedItemPosition()),allEds.get(i).getText().toString());
                        }
                        // JSONArray jsArray = new JSONArray(_out);
                        item_code=listString.substring(0,
                                listString.length() - 1);
                        //System.out.println("QTY: " + item_code);
                        DataHolder_workprogress.getInstance().setStr_item_code(item_code);
                        }
                        if (mContainerView.getChildCount() > 0) {
                        String listString1="";
                        for (int i = 0; i < allPassEds.size(); i++)
                        {
                            listString1=listString1+""+item_code_list.get(spnr.get(i).getSelectedItemPosition())+"_"+allPassEds.get(i).getText().toString()+",";

                            //   _out.add(item_code_list.get(spnr.get(i).getSelectedItemPosition())+"_"+allEds.get(i).getText().toString());

                            //  hm.put(item_code_list.get(spnr.get(i).getSelectedItemPosition()),allEds.get(i).getText().toString());
                        }
                        // JSONArray jsArray = new JSONArray(_out);
                        item_code1=listString1.substring(0,
                                listString1.length() - 1);

                        //System.out.println("PassedQTY: " + item_code1);
                       // Toast.makeText(getApplicationContext(),""+item_code1,Toast.LENGTH_LONG).show();

                        DataHolder_workprogress.getInstance().setStr_item_code1(item_code1);
                        }
                    }catch (Exception ex)
                {
                    Log.e("err",ex.getMessage().toString());
                }

                permit_id= sessionManager.GET_EMP_ID();
                str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                auto_number = "" + permit_id + "" + str_timestamp;

                DataHolder_workprogress.getInstance().setAuto_number(auto_number);

                Intent i = new Intent(Material_Receiving_Item_Activity.this, MaterialReceived_UploadActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
        }


    @Override
    public void onBackPressed() {
        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    private void inflateEditRow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.rows_fact, null);
        /*final ImageButton deleteButton = (ImageButton) rowView
                .findViewById(R.id.buttonDelete);*/
        int count=mContainerView.getChildCount();
        ed_qty=(EditText)rowView.findViewById(R.id.ed_qty);
        ed_passedqty=(EditText)rowView.findViewById(R.id.ed_passed_qty);

        spinner_item = (Spinner) rowView.findViewById(R.id.spinner_item_id);
        ed_qty.setId(count);
        spinner_item.setId(count);
        ed_passedqty.setId(count);
        allEds.add(ed_qty);
        allPassEds.add(ed_passedqty);
        spnr.add(spinner_item);
        mExclusiveEmptyView = rowView;

        item_adapter = new ArrayAdapter<String>(
                getApplicationContext(), R.layout.custom_spinner, R.id.textView1,item_name_list);
        spinner_item.setAdapter(item_adapter);
        // deleteButton.setVisibility(View.INVISIBLE);
        addnew.setVisibility(View.INVISIBLE);
        // A TextWatcher to control the visibility of the "Add new" button and
        // handle the exclusive empty view.
        ed_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    addnew.setVisibility(View.GONE);
                    //       deleteButton.setVisibility(View.INVISIBLE);
                    if (mExclusiveEmptyView != null
                            && mExclusiveEmptyView != rowView) {
                            mContainerView.removeView(mExclusiveEmptyView);
                    }
                    mExclusiveEmptyView = rowView;
                } else {
                    if (mExclusiveEmptyView == rowView) {
                        mExclusiveEmptyView = null;
                    }
                    addnew.setVisibility(View.VISIBLE);
                    //     deleteButton.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });
        // Inflate at the end of all rows but before the "Add new" button
        mContainerView.addView(rowView, mContainerView.getChildCount() - 1);
    }
    //...........Item Class...........................................//
    public class Item_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        Context _context;
        Item_Value(Context ctx) { _context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // ShowAlertagain();
            super.onPreExecute();
            pd = new ProgressDialog(Material_Receiving_Item_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}
            @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Material_Receiving_Item_Activity.this);
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                item_cursor=sqLiteAdapter.select_item_all();
                if(item_cursor!=null && item_cursor.moveToFirst()){
                    item_code_list.add("Select Item Name");
                    item_name_list.add("Select Item Name");

                    do {
                        String item_code=item_cursor.getString(1);
                        String item_name=item_cursor.getString(2);
                       /* Log.e("dist_code",dist_code);
                        Log.e("dist_name",dist_name);*/

                        item_code_list.add(item_code);
                        item_name_list.add(item_name);
                    } while (item_cursor.moveToNext());
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
               /* project_adapter=new ArrayAdapter<String>(SiteInspection_Activity2.this,android.R.layout.simple_spinner_item,project_name_list);
                project_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                inflateEditRow();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Material_Receiving_Item_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* latt = 0;
                                    longg = 0;
                                    */
                                    startActivity(new Intent(Material_Receiving_Item_Activity.this, CameraMultiPicAll_Activity.class));
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
            startActivity(new Intent(Material_Receiving_Item_Activity.this, CameraMultiPicAll_Activity.class));
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
