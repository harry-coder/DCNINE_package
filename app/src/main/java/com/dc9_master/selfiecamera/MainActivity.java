package com.dc9_master.selfiecamera;

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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.Home_BajajActivity;
import com.dc9_master.R;
import com.dc9_master.attendance_module.LeaveUpload;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.HTTPURLConnection;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_SiteInspection;
import com.dc9_master.location.FusedLocationService;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends Activity {
	ImageView iv;
	//to call our own custom cam
	private final static int CAMERA_PIC_REQUEST1 = 0;
	Context con;
	int count = 0;
	SessionManager sessionManager;
	SQLiteAdapter1 sqLiteAdapter;
	//String permit_id;
	Button use_picture;

	ConnectionDetector connectionDetector;
	String internet_interrupt = null;

	Location mLocation = null;
	FusedLocationService fusedLocationService;
	String sending_latt, sending_longg;
	String permit_id, str_timestamp;
	double latt, longg;
	String response;
	ProgressDialog pd;

	private ProgressDialog pDialog;
	private JSONObject json;
	private int success=0;
	private HTTPURLConnection service;
	private String strname ="", strMobile ="",strAddress="";
	//Initialize webservice URL
	//private String path = "http://monitorpm.feedbackinfra.com/ntpc_saubhagya/embc_app/punch_out1";
	//private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView)findViewById(R.id.imageView1);
		con=this;
		service=new HTTPURLConnection();
		sessionManager = new SessionManager(MainActivity.this);
		permit_id= sessionManager.GET_EMP_ID();
		fusedLocationService = new FusedLocationService(this);
		mLocation = fusedLocationService.getLocation();
		connectionDetector = new ConnectionDetector(MainActivity.this);
		sqLiteAdapter= new SQLiteAdapter1(MainActivity.this);
		//sessionManager = new SessionManager(Attendance_Activity.this);
		//permit_id = sessionManager.GET_EMP_ID();

        use_picture = (Button) findViewById(R.id.use_picture_button1);
		use_picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//onBackPressed();
				if(TextUtils.isEmpty(DataHolder_SiteInspection.getInstance().getImageNmae6()))
				{
					final Dialog dialog = new Dialog(MainActivity.this);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.getWindow().setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
					dialog.setContentView(R.layout.custom);
					TextView text = dialog.findViewById(R.id.text);
					text.setText("*Take Picture is mandatory");
					Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();

						}
					});
					dialog.show();

				}

				else {
					new PostDataTOServer().execute();
				}

			}
		});


	}

	public void onClick(View view) {
		if (getFrontCameraId() == -1) {
			Toast.makeText(getApplicationContext(),
					"Front Camera Not Detected", Toast.LENGTH_SHORT).show();
		} else {
			Intent cameraIntent = new Intent();
			cameraIntent.setClass(this, CameraActivity.class);
			startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST1);

			// startActivity(new
			// Intent(MainActivity.this,CameraActivity.class));
		}
	}

	int getFrontCameraId() {
		CameraInfo ci = new CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == CameraInfo.CAMERA_FACING_FRONT)
				return i;
		}
		return -1; // No front-facing camera found
	}

	Bitmap bitmapFrontCam;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST1) {
			if (resultCode == RESULT_OK) {
				try {

					bitmapFrontCam = (Bitmap) data.getParcelableExtra("BitmapImage");

					/*final Bitmap bitmapunpro = BitmapFactory.decodeFile(fileUri.getPath(),
							options);

					final Bitmap bitmap = getResizedBitmap(BitmapFactory.decodeFile(fileUri.getPath(),
							options), 80, 110);*/

					String lrgbitmapstring;
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					bitmapFrontCam.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

					byte[] byteArray = bytes.toByteArray();
					lrgbitmapstring = Base64.encodeToString(byteArray, Base64.DEFAULT);
					if (DataHolder_SiteInspection.getInstance().getlrgImg1() == null) {
						DataHolder_SiteInspection.getInstance().setlrgImg6(lrgbitmapstring);
					} else {
						Toast.makeText(getApplicationContext(), "Maximum 1 Images", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
				}

				iv.setImageBitmap(bitmapFrontCam);
				previewCapturedImage(count, bitmapFrontCam);
			}

		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/*
    * Display image from a path to ImageView
   */
	private void previewCapturedImage(int count, Bitmap data) {
		String bitmapstring;
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");data
		Bitmap bm = data.copy(Bitmap.Config.RGB_565, true);
//        bitmapstring=bm.toString();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		data.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
		byte[] byteArray = bytes.toByteArray();
		bitmapstring = Base64.encodeToString(byteArray, Base64.DEFAULT);
		//System.out.println("hyyyy" + count);
		//str_currenttimestamp = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
		//System.out.println("AMIN " + DataHolder_SiteInspection.getInstance().getImg6());
		String str_timestamp1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
		if (DataHolder_SiteInspection.getInstance().getImg6() == null) {
			File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Attendance/",
					permit_id+"_"+str_timestamp1+"."+"jpg");
			DataHolder_SiteInspection.getInstance().setImageNmae6(permit_id+"_"+str_timestamp1+"."+"jpg");
			//System.out.println("IMAGE NAME 1 " + permit_id+"_"+str_timestamp1+"."+"jpg");
			FileOutputStream fo;
			try {
				destination.createNewFile();
				fo = new FileOutputStream(destination);
				fo.write(bytes.toByteArray());
				fo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			DataHolder_SiteInspection.getInstance().setImg6(bitmapstring);
			System.out.println("AMIN1");

		}  else {
			Toast.makeText(getApplicationContext(), "Maximum 1 Images", Toast.LENGTH_LONG).show();
		   }
		}
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	//---------- for sending punch in data to the server------------------//
	private class PostDataTOServer extends AsyncTask<Void, Void, Void> {
		//String response = "";
		//Create hashmap Object to send parameters to web service
		HashMap<String, String> postDataParams;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			postDataParams=new HashMap<String, String>();
			postDataParams.put("permit_id", sessionManager.GET_EMP_ID());
			postDataParams.put("project_id", sessionManager.GET_PROJECT());
			postDataParams.put("mobile_date", DataHolder_SiteInspection.getInstance().getCamera_time());
			postDataParams.put("latt", DataHolder_SiteInspection.getInstance().getCamera_lat());
			postDataParams.put("longg", DataHolder_SiteInspection.getInstance().getCamera_long());
			postDataParams.put("distance_in", DataHolder_SiteInspection.getInstance().getLoc_distance());
			postDataParams.put("selfie_name", DataHolder_SiteInspection.getInstance().getImageNmae6());
			postDataParams.put("selfie", DataHolder_SiteInspection.getInstance().getImg6());
			postDataParams.put("signature", DataHolder_SiteInspection.getInstance().getImg1());
			postDataParams.put("signature_name", DataHolder_SiteInspection.getInstance().getImageNmae1());
			//Call ServerData() method to call webservice and store result in response
			response= service.ServerData(sessionManager.GET_CURRENT_URL()+"insert_punch_in2",postDataParams);
			try {
				json = new JSONObject(response);
				//Get Values from JSONobject
				System.out.println("success=" + json.get("response"));
				//success = json.getInt("resp");
				//JSONArray contacts = json.getJSONArray("resp");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			try {

				if (response != null && response.equals("1")) {
					// custom dialog
					final Dialog dialog = new Dialog(MainActivity.this);
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

				// Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_LONG).show();
				finish();
			}

		}
	}


	
}
