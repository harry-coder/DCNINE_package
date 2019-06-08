package com.dc9_master.material_recv1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.Home_BajajActivity;
import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.sqlite_adapter.SQLiteAdapterWorkprogress;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MaterialUpload extends Activity {

    ImageButton upload_btn;
    Button back_btn;
    String response;
    ProgressDialog pd;
    SessionManager sessionManager;
    ConnectionDetector connectionDetector;
    SQLiteAdapterWorkprogress sqLiteAdapter;
    String str_timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        connectionDetector=new ConnectionDetector(MaterialUpload.this);
        sessionManager =new SessionManager(MaterialUpload.this);
        sqLiteAdapter= new SQLiteAdapterWorkprogress(MaterialUpload.this);
        str_timestamp = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        back_btn = (Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(WorkprogressUpload.this, Home_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        });

        upload_btn=(ImageButton)findViewById(R.id.upload_btn);
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectionDetector.isConnectingToInternet()) {
                    //Log.e("Latitude", "" + mLocation.getLatitude());
                    //Log.e("Longitude", "" + mLocation.getLongitude());
                     new SendToServer().execute();
                    //new SendToServer().execute();
                } else {
                    // Toast.makeText(getApplicationContext(), "record saved", Toast.LENGTH_SHORT).show();
                    // custom dialog
                    final Dialog dialog = new Dialog(MaterialUpload.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Internet not connecting,record saved");
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress();
                            startActivity( new Intent(getApplicationContext(),Home_Activity.class));
                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
                            finish();
                            }
                    });
                    dialog.show();
                    sqLiteAdapter.openToRead();
                    sqLiteAdapter.openToWrite();
                    sqLiteAdapter.insert_valueR(sessionManager.GET_EMP_ID(),
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
                    sqLiteAdapter.close();
                    response = null;
                    }
                }
        });
        }
    //---------- for sending punch in data to the server------------------//

    public class SendToServer extends AsyncTask<String, String, String>
    {

        ProgressDialog pd;
        // public SendToServer() {
        public SendToServer() {
            // TODO Auto-generated constructor stub

//            Log.e("from", "sendtoserver");
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(MaterialUpload.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_material_receiving");
                    //HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/ntpc_demo/embc_app/insert_workprogress_data");
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("permit_id", sessionManager.GET_EMP_ID()));
                    nameValuePairs.add(new BasicNameValuePair("project", sessionManager.GET_PROJECT()));
                    nameValuePairs.add(new BasicNameValuePair("district_id", DataHolder_workprogress.getInstance().getGeographoic_id()));
                    nameValuePairs.add(new BasicNameValuePair("store_id", DataHolder_workprogress.getInstance().getSubareaone()));
                    nameValuePairs.add(new BasicNameValuePair("poNo", DataHolder_workprogress.getInstance().getStr_po_no()));
                    nameValuePairs.add(new BasicNameValuePair("diNo", DataHolder_workprogress.getInstance().getStr_di_no()));
                    nameValuePairs.add(new BasicNameValuePair("vendorName", DataHolder_workprogress.getInstance().getStr_vendor_rspName()));
                    nameValuePairs.add(new BasicNameValuePair("storeKeeperName", DataHolder_workprogress.getInstance().getStr_storeKeepeName()));
                    nameValuePairs.add(new BasicNameValuePair("item_id", DataHolder_workprogress.getInstance().getItem_id()));
                    nameValuePairs.add(new BasicNameValuePair("diQty", DataHolder_workprogress.getInstance().getStrDiQty()));
                    nameValuePairs.add(new BasicNameValuePair("RecvQty", DataHolder_workprogress.getInstance().getStrRecvQty()));
                    nameValuePairs.add(new BasicNameValuePair("damagedQty", DataHolder_workprogress.getInstance().getStrDamagedQty()));
                    nameValuePairs.add(new BasicNameValuePair("AcceptedQty", DataHolder_workprogress.getInstance().getStrAccpetedQty()));
                    nameValuePairs.add(new BasicNameValuePair("item_yes", DataHolder_workprogress.getInstance().getStr_item_yes()));
                    nameValuePairs.add(new BasicNameValuePair("item_cancel", DataHolder_workprogress.getInstance().getStr_item_cancel()));
                    nameValuePairs.add(new BasicNameValuePair("remark", DataHolder_workprogress.getInstance().getStr_remark()));
                    nameValuePairs.add(new BasicNameValuePair("camera_lat", DataHolder_workprogress.getInstance().getCamera_lat()));
                    nameValuePairs.add(new BasicNameValuePair("camera_long", DataHolder_workprogress.getInstance().getCamera_long()));
                    nameValuePairs.add(new BasicNameValuePair("camera_time", DataHolder_workprogress.getInstance().getCamera_time()));
                    nameValuePairs.add(new BasicNameValuePair("imgname1", DataHolder_workprogress.getInstance().getImageNmae1()));
                    nameValuePairs.add(new BasicNameValuePair("imgname2", DataHolder_workprogress.getInstance().getImageNmae2()));
                    nameValuePairs.add(new BasicNameValuePair("img1", DataHolder_workprogress.getInstance().getImg1()));
                    nameValuePairs.add(new BasicNameValuePair("img2", DataHolder_workprogress.getInstance().getImg2()));
                    nameValuePairs.add(new BasicNameValuePair("auto_number", DataHolder_workprogress.getInstance().getAuto_number()));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    Log.e("name value material", "" + nameValuePairs);
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = httpclient.execute(httppost, responseHandler);
                    } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("response", ""+response);
                    //   Log.e("response", response + "+" + e.getMessage().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.hide();
            pd.dismiss();

            try
            {
                //response = response.trim();
               // Log.e("response",""+response);
                response = response.trim();
                if (response != null && response.equals("1")) {
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    // custom dialog
                    final Dialog dialog = new Dialog(MaterialUpload.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
//                              dialog.setTitle("Title...");
                    // set the custom dialog components - text, image and button
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Send Successfully");
//                              ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                              image.setImageResource(R.drawable.ic_launcher);
                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pd.hide();
                            pd.dismiss();
                            dialog.dismiss();
                            DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress();
                            startActivity( new Intent(getApplicationContext(),Home_Activity.class));
                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
                            finish();
                        }
                    });
                    dialog.show();
                    } else {
                    sqLiteAdapter.openToRead();
                    sqLiteAdapter.openToWrite();
                    sqLiteAdapter.insert_valueR(sessionManager.GET_EMP_ID(),
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
                    sqLiteAdapter.close();
                    DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress();
                    startActivity( new Intent(getApplicationContext(),Home_Activity.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();
                    Toast.makeText(getApplicationContext(), "record saved due to server error", Toast.LENGTH_SHORT).show();
                    }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Due to low internet connectivity this data would be saved in database", Toast.LENGTH_SHORT).show();
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                sqLiteAdapter.insert_valueR(sessionManager.GET_EMP_ID(),
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
                sqLiteAdapter.close();

                DataHolder_workprogress.getInstance().nullify_DataHolder_workprogress();
                startActivity( new Intent(getApplicationContext(),Home_Activity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
                response = null;
                //Log.e("response exception", ""+e.getMessage());
            }
            response = null;
        }

    }
    @Override
    public void onBackPressed() {

        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

}
