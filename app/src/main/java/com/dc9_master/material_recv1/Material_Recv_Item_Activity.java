package com.dc9_master.material_recv1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.Upload_Activity;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.camera.CameraMultiPicAll_Activity;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import java.util.ArrayList;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Material_Recv_Item_Activity extends Activity {
    Button btn_next,btn_camera,btn_finish,btn_checklist;
    EditText ed_diqty,ed_recvqty,ed_damagedqty,ed_acceptedqty,ed_remark;
    String tkc_id,str_diqty,str_recvqty,str_damagedqty,str_acceptedqty,str_remark;
    Spinner spinner_tkc;
    ArrayList<String> tkc_name_list,tkc_id_list;
    Cursor tkc_cursor;
    ArrayAdapter<String> tkc_adapter;
    SQLiteAdapter1 sqLiteAdapter;
    SQLiteAdapterWorkprogress sqLiteAdapterw;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_inspection_item);
        spinner_tkc = (Spinner)findViewById(R.id.spinner_item_id);
        tkc_name_list=new ArrayList<String>();
        tkc_id_list=new ArrayList<String>();
        sqLiteAdapter=new SQLiteAdapter1(Material_Recv_Item_Activity.this);
        sqLiteAdapterw=new SQLiteAdapterWorkprogress(Material_Recv_Item_Activity.this);
        sessionManager = new SessionManager(this);
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        ed_diqty=(EditText)findViewById(R.id.ed_diqty);
        ed_recvqty=(EditText)findViewById(R.id.ed_receivedqty);
        ed_damagedqty=(EditText)findViewById(R.id.ed_qty_damaged);
        ed_acceptedqty=(EditText)findViewById(R.id.ed_qty_accepted);
        ed_remark=(EditText)findViewById(R.id.ed_remark);

        new TKC_Value(Material_Recv_Item_Activity.this).execute();
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


        btn_checklist=(Button)findViewById(R.id.acptqc);
        btn_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder_workprogress.getInstance().setItem_id(tkc_id);
                Intent i = new Intent(Material_Recv_Item_Activity.this, RecvInspectionChecklist_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });

        btn_camera=(Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(Material_Recv_Item_Activity.this, Camera_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                if (mLocation != null) {
                    //startActivity(new Intent(SurveyActivity.this, Camera_Activity.class));
                    Intent i = new Intent(Material_Recv_Item_Activity.this, CameraMultiPicAll_Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {

                    ShowAlertagain();
                }
            }
        });


        btn_next=(Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_diqty=ed_diqty.getText().toString();
                str_recvqty=ed_recvqty.getText().toString();
                str_damagedqty=ed_damagedqty.getText().toString();
                str_acceptedqty=ed_acceptedqty.getText().toString();
                str_remark=ed_remark.getText().toString();

                if (spinner_tkc.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_diqty) || TextUtils.isEmpty(str_recvqty) || TextUtils.isEmpty(str_damagedqty) || TextUtils.isEmpty(str_acceptedqty)) {

                    final Dialog dialog = new Dialog(Material_Recv_Item_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                    // onBackPressed();
                    sqLiteAdapterw.openToRead();
                    sqLiteAdapterw.openToWrite();
                    sqLiteAdapterw.insert_valueR(sessionManager.GET_EMP_ID(),
                            sessionManager.GET_PROJECT(),
                            DataHolder_workprogress.getInstance().getGeographoic_id(),
                            DataHolder_workprogress.getInstance().getSubareaone(),
                            DataHolder_workprogress.getInstance().getStr_diNumber(),
                            DataHolder_workprogress.getInstance().getStr_vendor_rspName(),
                            DataHolder_workprogress.getInstance().getStr_storeKeepeName(),
                            DataHolder_workprogress.getInstance().getItem_id(),
                            DataHolder_workprogress.getInstance().getStrDiQty(),
                            DataHolder_workprogress.getInstance().getStrRecvQty(),
                            DataHolder_workprogress.getInstance().getStrDamagedQty(),
                            DataHolder_workprogress.getInstance().getStrAccpetedQty(),
                            DataHolder_workprogress.getInstance().getStr_item_yes(),
                            DataHolder_workprogress.getInstance().getStr_item_cancel(),
                            DataHolder_workprogress.getInstance().getStr_remark(),
                            DataHolder_workprogress.getInstance().getCamera_lat(),
                            DataHolder_workprogress.getInstance().getCamera_long(),
                            DataHolder_workprogress.getInstance().getCamera_time(),
                            DataHolder_workprogress.getInstance().getImageNmae1(),
                            DataHolder_workprogress.getInstance().getImageNmae2(),
                            DataHolder_workprogress.getInstance().getImg1(),
                            DataHolder_workprogress.getInstance().getImg2(),
                            DataHolder_workprogress.getInstance().getAuto_number());
                    sqLiteAdapterw.close();

                    final Dialog dialog = new Dialog(Material_Recv_Item_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                            //DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress_hhsc();
                            startActivity(new Intent(Material_Recv_Item_Activity.this, Material_Recv_Item_Activity.class));
                            }
                    });
                    dialog.show();
                    }

            }
        });

        btn_finish=(Button)findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_diqty = ed_diqty.getText().toString();
                str_recvqty = ed_recvqty.getText().toString();
                str_damagedqty = ed_damagedqty.getText().toString();
                str_acceptedqty = ed_acceptedqty.getText().toString();

                if (spinner_tkc.getSelectedItemPosition() == 0 || TextUtils.isEmpty(str_diqty) || TextUtils.isEmpty(str_recvqty) || TextUtils.isEmpty(str_damagedqty) || TextUtils.isEmpty(str_acceptedqty)) {

                    final Dialog dialog = new Dialog(Material_Recv_Item_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

                } else {

                    DataHolder_workprogress.getInstance().setItem_id(tkc_id);
                    DataHolder_workprogress.getInstance().setStrDiQty(str_diqty);
                    DataHolder_workprogress.getInstance().setStrRecvQty(str_recvqty);
                    DataHolder_workprogress.getInstance().setStrDamagedQty(str_damagedqty);
                    DataHolder_workprogress.getInstance().setStrAccpetedQty(str_acceptedqty);
                    DataHolder_workprogress.getInstance().setStr_remark(str_remark);

                    Intent i = new Intent(Material_Recv_Item_Activity.this, Upload_Activity.class);
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
            pd = new ProgressDialog(Material_Recv_Item_Activity.this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Material_Recv_Item_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* latt = 0;
                                    longg = 0;
                                    */
                                    startActivity(new Intent(Material_Recv_Item_Activity.this, CameraMultiPicAll_Activity.class));
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
            startActivity(new Intent(Material_Recv_Item_Activity.this, CameraMultiPicAll_Activity.class));
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

