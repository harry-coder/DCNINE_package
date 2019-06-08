package com.dc9_master.attendance_module;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.Home_BajajActivity;
import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_SiteInspection;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.selfiecamera.CameraActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LeaveUpload extends Activity {
    Context con;
	int count = 0;
	SessionManager sessionManager;
	ImageButton upload_btn;
	ConnectionDetector connectionDetector;
	Location mLocation = null;
	FusedLocationService fusedLocationService;
	String permit_id;
	String response;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);
		con=this;
		sessionManager = new SessionManager(LeaveUpload.this);
		permit_id= sessionManager.GET_EMP_ID();
		fusedLocationService = new FusedLocationService(this);
		mLocation = fusedLocationService.getLocation();
		connectionDetector = new ConnectionDetector(LeaveUpload.this);

		upload_btn = (ImageButton) findViewById(R.id.upload_btn);
		upload_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                if (connectionDetector.isConnectingToInternet()) {

					new SendToServer().execute();
				}
				else {

						final Dialog dialog = new Dialog(LeaveUpload.this);
						dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
						dialog.getWindow().setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
						dialog.setContentView(R.layout.custom);
						TextView text = (TextView) dialog.findViewById(R.id.text);
						text.setText("Internet not connecting");
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

			}
		});
		}


	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	//---------- for sending punch in data to the server------------------//

	public class SendToServer extends AsyncTask<String, String, String> {

		ProgressDialog pd;

		// public SendToServer() {
		public SendToServer() {
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(LeaveUpload.this);
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
					HttpPost httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"insert_leave");
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

					nameValuePairs.add(new BasicNameValuePair("permit_id", sessionManager.GET_EMP_ID()));
					nameValuePairs.add(new BasicNameValuePair("project_id", sessionManager.GET_PROJECT()));

					/*nameValuePairs.add(new BasicNameValuePair("from_date", DataHolder_workprogress.getInstance().getFrom_date()));
					nameValuePairs.add(new BasicNameValuePair("to_date", DataHolder_workprogress.getInstance().getTo_date()));
					nameValuePairs.add(new BasicNameValuePair("reason", DataHolder_workprogress.getInstance().getReason()));
                    */
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					Log.e("name value pair", "" + nameValuePairs);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					response = httpclient.execute(httppost, responseHandler);

				} catch (Exception e) {
					e.printStackTrace();
					Log.e("response", "" + response);
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

			try {
				response = response.trim();
				Log.e("response", "" + response);
				response = response.trim();

				if (response != null && response.equals("1")) {

					// custom dialog
					final Dialog dialog = new Dialog(LeaveUpload.this);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
					dialog.setContentView(R.layout.custom);
//                              dialog.setTitle("Title...");

					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText("Send Successfully");
					Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							pd.hide();
							pd.dismiss();
							dialog.dismiss();
							    startActivity( new Intent(getApplicationContext(),Home_Activity.class));
								overridePendingTransition(R.anim.right_in, R.anim.left_out);
								finish();
						}
					});

					dialog.show();


				} else {

					// Toast.makeText(getApplicationContext(), "record saved due to server error", Toast.LENGTH_SHORT).show();

				}
			} catch (Exception e) {
				//Toast.makeText(getApplicationContext(), "Due to low internet connectivity this data would be saved in database", Toast.LENGTH_SHORT).show();
				response = null;
				Log.e("response exception", "" + e.getMessage());
			}
			response = null;
		}

	}

	
}
