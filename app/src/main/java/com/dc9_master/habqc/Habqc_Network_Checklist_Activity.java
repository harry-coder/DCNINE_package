package com.dc9_master.habqc;

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
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.adapter.AreaDataSIAdapter;
import com.dc9_master.adapter.ItemObject;
import com.dc9_master.adapter.Model;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.camera.Camera_Activity;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sor.SiteInspectionRemark_Activity;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by nitinb on 29-01-2016.
 */
public class Habqc_Network_Checklist_Activity extends Activity {
    private static Habqc_Network_Checklist_Activity sActivity;

    public static Habqc_Network_Checklist_Activity getsActivity(){return sActivity;}
    Button remark_btn,camera_btn,finish_btn;
    Button back_btn;
    SparseBooleanArray a;
    String my_sel_items;
    ListView listView;
    ArrayAdapter<Model> adapter2;
    SQLiteAdapter1 sqLiteAdapter;
    // private ArrayList<RowObject> mSource;
    private List<Model> modelsource;
    private TextView mCountTextView;
    ArrayList<String> item_pole_code_list,item_code_list,item_name_list;
    ArrayAdapter<String> item_adapter;
    Cursor item_cursor;
    TextView tv_text;
    String permit_id, str_timestamp,auto_number;
    SessionManager sessionManager;
    Location mLocation = null;
    FusedLocationService fusedLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habqc_hhsc_checkbox);
        //setContentView(R.layout.list_view);
        sActivity=this;

        listView = findViewById(R.id.listView);
        tv_text =  findViewById(R.id.text_logo);

        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();

        // Getting the reference to the listview object of the layout
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        tv_text.setText(DataHolder_workprogress.getInstance().getItem_name());
        // Setting adapter to the listview
        item_code_list=new ArrayList<String>();
        item_name_list=new ArrayList<String>();

        //-------------for Pole Activity------------------//
        new Item_Value(Habqc_Network_Checklist_Activity.this).execute();
        sessionManager = new SessionManager(Habqc_Network_Checklist_Activity.this);

        //-------------for Pole Remark-----------------//
        remark_btn=(Button)findViewById(R.id.imageButton6);
        remark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Habqc_Network_Checklist_Activity.this, SiteInspectionRemark_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        camera_btn=(Button)findViewById(R.id.imageButton5);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLocation != null) {
                    startActivity(new Intent(Habqc_Network_Checklist_Activity.this, Camera_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                } else {
                    ShowAlertagain();
                }

            }
        });
      //-------------for Next Item Activity-----------------//
       /* next_item_btn=(Button)findViewById(R.id.imageButton5);
          next_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String checkitem= String.valueOf(DataHolder_SiteInspection.getInstance().getSelectedItems());
               //System.out.println(String.valueOf(DataHolder_SiteInspection.getInstance().getSelectedItems()));
                if(TextUtils.isEmpty(checkitem)) {

                    final Dialog dialog = new Dialog(GPLocationchecklistActivity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Select Checkbox value");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            //ShowAlertagain();
                        }
                    });

                    dialog.show();

                } else {

                   ShowAlertagain();

                }
               *//* DataHolder_SiteInspection.getInstance().getStr_pole_item_id()
                ShowAlertagain();*//*

            }
        });
*/
        //--- Back Button
        back_btn=(Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(Site_Pole_Check_Activity1.this, SiteInspection_Item_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        });


        //----- Finish Button
        finish_btn=(Button)findViewById(R.id.imageButton7);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //Getter Setter are working ...
                    ArrayList<String> op = new ArrayList<String>();
                    op = DataHolder_workprogress.getInstance().getSelectedItems();
                   // System.out.println("get listoput" + op.size());
                    String checkeditems = op.get(0);
                    for (int j = 1; j < op.size(); j++) {
                        //  op=DataHolder_SiteInspection.getInstance().getSelectedStrings();
                       // System.out.println("get list" + op.get(j));
                        checkeditems = checkeditems + "_" + op.get(j);
                    }
                    System.out.println("getvalue: " + checkeditems);
                    //DataHolder_SiteInspection.getInstance().setChechked_item_list(checkeditems);
                    DataHolder_workprogress.getInstance().setStr_item_yes(checkeditems);


                }catch (Exception e){
                    e.printStackTrace();
                }


                try{
                    //Getter Setter are working ...
                    ArrayList<String> op = new ArrayList<String>();
                    op = DataHolder_workprogress.getInstance().getSelectedItems2();
                    // System.out.println("get listoput" + op.size());
                    String checkeditems = op.get(0);
                    for (int j = 1; j < op.size(); j++) {
                        //  op=DataHolder_SiteInspection.getInstance().getSelectedStrings();
                        // System.out.println("get list" + op.get(j));
                        checkeditems = checkeditems + "_" + op.get(j);
                    }
                    System.out.println("getvalue: " + checkeditems);
                    //DataHolder_SiteInspection.getInstance().setChechked_item_list(checkeditems);
                    DataHolder_workprogress.getInstance().setStr_item_cancel(checkeditems);


                }catch (Exception e){
                    e.printStackTrace();
                }



              /*  try{

                    //----For Input value---------------------//
                    ArrayList<String> op3 = new ArrayList<String>();
                    op3 = DataHolder_workprogress.getInstance().getSelectedItems_value();
                    System.out.println("get listoput input" + op3.size());
                    String checkeditems3 = op3.get(0);
                    for (int j = 1; j < op3.size(); j++) {
//                        op=DataHolder_SiteInspection.getInstance().getSelectedStrings();
                        System.out.println("get list input" + op3.get(j));
                        checkeditems3 = checkeditems3 + "," + op3.get(j);
                    }
                    System.out.println("getvalueInput" + checkeditems3);
                    //DataHolder_SiteInspection.getInstance().setChechked_item_list(checkeditems);
                    DataHolder_workprogress.getInstance().setStr_checkbox_inputValue(checkeditems3);
                }catch (Exception e){
                    e.printStackTrace();
                }*/


                if (TextUtils.isEmpty(DataHolder_workprogress.getInstance().getGeographoic_id()) || TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareaone()) ||
                        TextUtils.isEmpty(DataHolder_workprogress.getInstance().getSubareatwo()) ) {

                    final Dialog dialog = new Dialog(Habqc_Network_Checklist_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Due to Force clsoe app District,Block & Village data entry fill again");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });

                    dialog.show();

                }else{

                    permit_id= sessionManager.GET_EMP_ID();
                    str_timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                    auto_number = "" + permit_id + "" + str_timestamp;
                    DataHolder_workprogress.getInstance().setAuto_number(auto_number);

                   // Intent i = new Intent(SiteInspectionChecklist_Activity.this, SiteInspectionUpload.class);
                    Intent i = new Intent(Habqc_Network_Checklist_Activity.this, Habqc_Network_Pass_Activity.class);
                    startActivity(i);
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

    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true).trim();
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals(provider));
    }


    public void ShowAlertagain() {
        if (!isLocationServiceEnabled()) {
            // Toast.makeText(getApplicationContext(), "please wait while location is fetching", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Habqc_Network_Checklist_Activity.this);
                    builder.setMessage("Do you want to Continue without location?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   /* latt = 0;
                                    longg = 0;
                                    */
                                    startActivity(new Intent(Habqc_Network_Checklist_Activity.this, Camera_Activity.class));
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

            startActivity(new Intent(Habqc_Network_Checklist_Activity.this, Camera_Activity.class));
            // finish();
            overridePendingTransition(R.anim.right_in, R.anim.left_out);

            //   Log.e("Latitude", "" + mLocation.getLatitude());
            //   Log.e("Longitude", "" + mLocation.getLongitude());
        }
    }


    //............................Item.................................................//

    public class Item_Value extends AsyncTask<String, String, String> {
        ProgressDialog pd;Context _context;
        Item_Value(Context ctx) {_context=ctx;}
        List<ItemObject> listViewItems;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(Habqc_Network_Checklist_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(Habqc_Network_Checklist_Activity.this);
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(item_cursor!=null){
                    item_cursor=null;
                }

               // item_cursor=sqLiteAdapter.select_sor_item_parameter_all(Integer.parseInt(DataHolder_workprogress.getInstance().getItem_id()));
                item_cursor=sqLiteAdapter.select_sor_item_parameter_all(Integer.parseInt(DataHolder_workprogress.getInstance().getItem_id()));
                item_code_list.clear();
                item_name_list.clear();
                if(item_cursor!=null && item_cursor.moveToFirst()){
                   /* item_code_list.add("select");
                    item_name_list.add("select");*/
                    do {
                        String code=item_cursor.getString(1);
                        String name=item_cursor.getString(2);
                       /* Log.e("code",code);
                        Log.e("name",name);*/
                        item_code_list.add(code);
                        item_name_list.add(name);

//                      listViewItems = new ArrayList<ItemObject>();
//                        listViewItems.add(new ItemObject(code,name));

                    } while (item_cursor.moveToNext());
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

                ////////////////////////////////by me
                // Save the ListView state (= includes scroll position) as a Parceble
                Parcelable state = listView.onSaveInstanceState();

               /* final AreaDataAdapter adapter= new AreaDataAdapter(getApplicationContext(), item_name_list,item_code_list);
                listView.setAdapter(adapter);*/
                final AreaDataSIAdapter adapter= new AreaDataSIAdapter(Habqc_Network_Checklist_Activity.this, item_name_list,item_code_list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // make Toast when click
                        //Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();

                    }
                });
                // spinner_block.setAdapter(block_adapter);
                sqLiteAdapter.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }








}
