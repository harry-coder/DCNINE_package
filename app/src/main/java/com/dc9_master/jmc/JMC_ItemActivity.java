package com.dc9_master.jmc;

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
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.Upload_Activity;
import com.dc9_master.adapter.ItemObject;
import com.dc9_master.adapter.ListviewAdapter;
import com.dc9_master.adapter.Model;
import com.dc9_master.camera.Camera1_Activity;
import com.dc9_master.data_holder.DataHolder_SiteInspection;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nitinb on 29-01-2016.
 */
public class JMC_ItemActivity extends Activity {
    private static JMC_ItemActivity sActivity;
    public static JMC_ItemActivity getsActivity(){return sActivity;}
    Button remark_btn,village_completed,finish_btn,btn_camera;
    Button back_btn;
    SparseBooleanArray a;
    String my_sel_items;
    ListView listView;
    ArrayAdapter<Model> adapter2;
    SQLiteAdapter1 sqLiteAdapter;
    // private ArrayList<RowObject> mSource;
    private List<Model> modelsource;
    private TextView mCountTextView;
    ArrayList<String> item_qty_list,item_code_list,item_name_list,item_qtyactual_list;
    private List number_list;
    ArrayAdapter<String> item_adapter;
    Cursor item_cursor;
    TextView tv_text;
    private List list;
    Integer count_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workprogress_item);
        //setContentView(R.layout.list_view);
        sActivity=this;
        listView = (ListView) findViewById(R.id.listView);
        tv_text = (TextView) findViewById(R.id.text_logo);
        // Getting the reference to the listview object of the layout
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        tv_text.setText(DataHolder_SiteInspection.getInstance().getItem_name());
        // Setting adapter to the listview
        item_code_list=new ArrayList<String>();
        item_name_list=new ArrayList<String>();
        list = new ArrayList<Integer>();
        //-------------for Pole Activity------------------//
        new Item_Value(JMC_ItemActivity.this).execute();
       //-------------for Pole Remark-----------------//
       /* remark_btn=(Button)findViewById(R.id.btn_remark);
        remark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Workprogress_Item_Activity1.this, Workprogress_Remark.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });*/

      /*  village_completed=(Button)findViewById(R.id.btn_comp_vlg);
        village_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataHolder_workprogress.getInstance().setStr_village_status("1");

            }
        });*/


        //--- Back Button
        back_btn=(Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_camera=(Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkitem= String.valueOf(DataHolder_SiteInspection.getInstance().getSelectedItems());
                //System.out.println(String.valueOf(DataHolder_SiteInspection.getInstance().getSelectedItems()));
                if(TextUtils.isEmpty(checkitem)) {
                    final Dialog dialog = new Dialog(JMC_ItemActivity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Enter Consumptions value");
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

            }
        });

        //----- Finish Button
        finish_btn=(Button)findViewById(R.id.btn_finish1);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try{
                        String checkeditems3 = null;
                        HashMap<String, String> meMap=new HashMap<String, String>();
                        meMap=DataHolder_SiteInspection.getInstance().getMeMap();
                        Iterator myVeryOwnIterator = meMap.keySet().iterator();;
                        while(myVeryOwnIterator.hasNext()) {
                            String key=(String)myVeryOwnIterator.next();
                            String value=(String)meMap.get(key);
                            checkeditems3 = checkeditems3 + "," + value;
                            System.out.println("Input value IS memap :  " + "Key: "+key+" Value: "+value);
                        }

                        System.out.println("getvalueInput" + checkeditems3);
                        //DataHolder_SiteInspection.getInstance().setChechked_item_list(checkeditems);
                        DataHolder_SiteInspection.getInstance().setStr_checkbox_inputValue(checkeditems3);
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                Intent i = new Intent(JMC_ItemActivity.this, Upload_Activity.class);
                //Intent i = new Intent(PoleInspectionParameter.this, SpanInspectionPassRework.class);

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(JMC_ItemActivity.this);
                    builder.setMessage("Do you want to on location Setting?");
                    builder.setCancelable(true);

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

            Intent i = new Intent(JMC_ItemActivity.this, Camera1_Activity.class);
            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);

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
            pd = new ProgressDialog(JMC_ItemActivity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(JMC_ItemActivity.this);
            try {

                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(item_cursor!=null){
                    item_cursor=null;
                }
               // item_cursor=sqLiteAdapter.select_item_all_id(2);
                item_cursor=sqLiteAdapter.select_item_all();
                count_list=item_cursor.getCount();
                DataHolder_workprogress.getInstance().setInt_count(count_list);
                //Log.e("count:",count_list);

                item_code_list.clear();
                item_name_list.clear();
                if(item_cursor!=null && item_cursor.moveToFirst()){
                   /* item_code_list.add("select");
                      item_name_list.add("select");*/
                    do {
                        String code=item_cursor.getString(1);
                        String name=item_cursor.getString(2);

                       /* String code=item_cursor.getString(2);
                        String name=item_cursor.getString(3);*/
                         Log.e("scopeid",code);
                          //Log.e("name",name);
                        item_code_list.add(code);
                        item_name_list.add(name);

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
                final ListviewAdapter adapter= new ListviewAdapter(getApplicationContext(), item_name_list,item_code_list,list);
                //final ListviewAdapter adapter= new ListviewAdapter(getApplicationContext(), item_name_list,list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // make Toast when click
                       // Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                    }
                });
                // spinner_block.setAdapter(block_adapter);
                sqLiteAdapter.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * Returns the number of checked items
     */
    private int getCheckedItemCount(){
        int cnt = 0;
        SparseBooleanArray positions = listView.getCheckedItemPositions();
        int itemCount = listView.getCount();

        for(int i=0;i<itemCount;i++){
            if(positions.get(i))
                cnt++;
        }
        return cnt;
    }

}
