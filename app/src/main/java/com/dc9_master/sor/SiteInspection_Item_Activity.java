package com.dc9_master.sor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.dc9_master.R;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import java.util.ArrayList;

/**
 * Created by nitinb on 29-01-2016.
 */
public class SiteInspection_Item_Activity extends Activity {
    SQLiteAdapter1 sqLiteAdapter;
    ArrayList<String> item_pole_code_list,item_code_list,item_name_list;
    Cursor item_cursor;
    GridView gridView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_inspection_item);
        //GridView
        gridView=(GridView) findViewById(R.id.gridView);
        item_pole_code_list=new ArrayList<String>();
        item_code_list=new ArrayList<String>();
        item_name_list=new ArrayList<String>();
        new ChooseItemValue(SiteInspection_Item_Activity.this).execute();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                //Toast.makeText(getApplicationContext(), item_name_list.get(position).toString(), Toast.LENGTH_SHORT).show();
                DataHolder_workprogress.getInstance().setItem_name(item_name_list.get(position).toString());
                DataHolder_workprogress.getInstance().setItem_id(item_code_list.get(position).toString());
                //Log.e("Pole code : ","IS - "+item_code_list.get(position).toString());
                Intent i = new Intent(SiteInspection_Item_Activity.this, SiteInspectionChecklist_Activity.class);
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

    //......................... Item Value ................................................//
    public class ChooseItemValue extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        Context _context;
        ChooseItemValue(Context ctx) {_context=ctx;}
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(SiteInspection_Item_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();}
            @Override
          protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            sqLiteAdapter=new SQLiteAdapter1(SiteInspection_Item_Activity.this);
            try {
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                if(item_cursor!=null){
                    item_cursor=null;
                }
                item_cursor=sqLiteAdapter.select_sor_item_all();
                //item_cursor=sqLiteAdapter.select_surveycategory_all();
                item_code_list.clear();
                item_name_list.clear();
                if(item_cursor!=null && item_cursor.moveToFirst()){
                    //item_code_list.add("select");
                    // item_name_list.add("select");
                    do {
                        String code=item_cursor.getString(1);
                        String name=item_cursor.getString(2);
                       /* Log.e("code",code);
                        Log.e("name",name);*/
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
//                item_adapter=new ArrayAdapter<String>(SiteInspection_Item_Activity.this,android.R.layout.simple_spinner_item,item_name_list);
//                item_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // spinner_block.setAdapter(block_adapter);
                adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.grid_view,item_name_list);
                gridView.setAdapter(adapter);
                sqLiteAdapter.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
