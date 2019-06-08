package com.dc9_master.attendance_module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.R;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by nitinb on 29-01-2016.
 */
public class AttendanceINCamera_Activity extends Activity {

    Button next_btn, click_image_btn, discard_btn;
    final Context context = this;
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


    ConnectionDetector connectionDetector;
    String internet_interrupt = null;
    Location mLocation = null;
    FusedLocationService fusedLocationService;
    String sending_latt, sending_longg;
    String permit_id, str_timestamp;
    double latt, longg;


    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "PMC";

    Uri fileUri; // file url to store image/video

    ImageView imgPreview, imgPreview2, imgPreview3;
    TextView locationtxt;
    int count = 0;
    private File mFileTemp;
    SQLiteDatabase SD;
    SessionManager sessionManager;
    private ProgressDialog pDialog;
    private JSONObject json;
    private int success=0;
    private HTTPURLConnection service;
    SQLiteAdapter1 sqLiteAdapter;
     String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        // setContentView(R.layout.camera);
        setContentView ( R.layout.comon_camera );

        //Toast.makeText(getApplicationContext(), "First Photo must be selfie with the inspecting work", Toast.LENGTH_SHORT).show();
        //cameraAlert();

        service=new HTTPURLConnection();
        sessionManager = new SessionManager(AttendanceINCamera_Activity.this);
        permit_id= sessionManager.GET_EMP_ID();
        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();
        connectionDetector = new ConnectionDetector(AttendanceINCamera_Activity.this);
        sqLiteAdapter= new SQLiteAdapter1(AttendanceINCamera_Activity.this);

        imgPreview = (ImageView) this.findViewById ( R.id.imageView1 );

        locationtxt = (TextView) this.findViewById ( R.id.TextViewLocation );

        //-------------for next button------------------//
        next_btn = (Button) findViewById ( R.id.use_picture_button );
        next_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

               // onBackPressed ( );
                if(TextUtils.isEmpty(DataHolder_SiteInspection.getInstance().getImageNmae6()))
                {
                    final Dialog dialog = new Dialog(AttendanceINCamera_Activity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
        } );

        click_image_btn = (Button) findViewById ( R.id.take_picture_button );
        click_image_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                //captureImage();

                try {
                    if (imgPreview.getDrawable ( ).equals ( null )) {

                        Toast.makeText ( context, "No image found", Toast.LENGTH_SHORT ).show ( );
                    }


                } catch (RuntimeException e) {
                    System.out.print ( "RuntimeException: " );
                }



                    try{

                        if (isMockSettingsON ( getApplicationContext ( ) ) == false) {
                            mLocation = fusedLocationService.getLocation ( );
                        } else {

                            showAlertMock ( );
                        }
                        permit_id = sessionManager.GET_EMP_ID ( );
                        mLocation = fusedLocationService.getLocation ( );
                        str_timestamp = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.getDefault ( ) ).format ( new Date ( ) );
                        if (mLocation != null) {
                            sending_latt = String.valueOf ( mLocation.getLatitude ( ) );
                            sending_longg = String.valueOf ( mLocation.getLongitude ( ) );
                            String tview = "" + sending_latt + "," + sending_longg + "," + str_timestamp;
                            locationtxt.setText ( tview );
                            DataHolder_SiteInspection.getInstance ( ).setCamera_lat ( sending_latt );
                            DataHolder_SiteInspection.getInstance ( ).setCamera_long ( sending_longg );
                            DataHolder_SiteInspection.getInstance ( ).setCamera_time ( str_timestamp );
                            captureImage ( );
                            } else {
                            captureImage ( );
                }

                     } catch (RuntimeException e) {
                             System.out.print ( "RuntimeException: " );
                    }
                // capture picture


            }
        } );
    }


    @Override
    public void onBackPressed() {
        finish ( );
        overridePendingTransition ( R.anim.right_in, R.anim.left_out );
    }

    /*
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState ( outState );

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable ( "file_uri", fileUri );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState ( savedInstanceState );

        // get the file url
        fileUri = savedInstanceState.getParcelable ( "file_uri" );
    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext ( ).getPackageManager ( ).hasSystemFeature (
                PackageManager.FEATURE_CAMERA )) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /*
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage() {
        Intent intent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );

        fileUri = getOutputMediaFileUri ( MEDIA_TYPE_IMAGE );

        intent.putExtra ( MediaStore.EXTRA_OUTPUT, fileUri );

        // start the image capture Intent
        startActivityForResult ( intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE );
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        count++;
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                // System.out.println("DATA IS " + data);
                // bimatp factory
                BitmapFactory.Options options = new BitmapFactory.Options ( );
                // downsizing image as it throws OutOfMemory Exception for larger
                // images
                options.inSampleSize = 2;

                final Bitmap bitmapunpro = BitmapFactory.decodeFile ( fileUri.getPath ( ),
                        options );

//                final Bitmap bitmap = getResizedBitmap(BitmapFactory.decodeFile(fileUri.getPath(),
//                        options), 80, 110);


                Bitmap convertedBitmap = combineBitMap ( bitmapunpro, getCurrentDate ( ), ""+String.valueOf (sending_latt)+","+ String.valueOf (sending_longg));


                String lrgbitmapstring;
                ByteArrayOutputStream bytes = new ByteArrayOutputStream ( );
                convertedBitmap.compress ( Bitmap.CompressFormat.JPEG, 50, bytes );

                byte[] byteArray = bytes.toByteArray ( );
                lrgbitmapstring = Base64.encodeToString ( byteArray, Base64.DEFAULT );
                DataHolder_SiteInspection.getInstance ( ).setlrgImg6 ( lrgbitmapstring );

                //imgPreview.setImageBitmap(bitmap);
                imgPreview.setImageBitmap ( convertedBitmap );
                previewCapturedImage ( count, convertedBitmap );

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText ( getApplicationContext ( ),
                        "User cancelled image capture", Toast.LENGTH_SHORT )
                        .show ( );
            } else {
                // failed to capture image
                Toast.makeText ( getApplicationContext ( ),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT )
                        .show ( );
            }
        }
    }

    public Bitmap combineBitMap(Bitmap src, String Date, String latlon) {

        // Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.yourimage); // the original file yourimage.jpg i added in resources
        Bitmap dest = Bitmap.createBitmap ( src.getWidth ( ), src.getHeight ( ), Bitmap.Config.ARGB_8888 );
        // Bitmap dest = Bitmap.createScaledBitmap ( src,src.getWidth ( ), src.getHeight ( ),false );

        String date = "Date " + Date;

        String loc = "Location " + latlon;
        Canvas cs = new Canvas ( dest );
        Paint tPaint = new Paint ( );
        tPaint.setTextSize ( 50 );
        tPaint.setColor ( Color.WHITE );
        tPaint.setStyle ( Paint.Style.FILL_AND_STROKE );
        cs.drawBitmap ( src, 0f, 100f, null );
        float height = tPaint.measureText ( "yY" );
        float width = tPaint.measureText ( date );
        float locWidth = tPaint.measureText ( loc );

    /*    System.out.println ("This is the width "+(src.getWidth ( ) - (locWidth + 150)) );
        System.out.println ("This is the height "+(src.getHeight ( ) - 300) );

        System.out.println ("This is width "+src.getWidth ( ) );
        System.out.println ("This is cal width "+locWidth + 150 );*/
        //float x_coord = (src.getWidth ( ) - (locWidth + 150));
        float x_coord = 100;

        float y_coord = (src.getHeight ( ) - 300);
        // cs.drawText ( yourText, x_coord, height + 30f, tPaint ); // 15f is to put space between top edge and the text, if you want to change it, you can
        cs.drawText ( date, x_coord, y_coord, tPaint ); // 15f is to put space between top edge and the text, if you want to change it, you can
        cs.drawText ( loc, x_coord, y_coord + 100, tPaint ); // 15f is to put space between top edge and the text, if you want to change it, you can

        try {
            dest.compress ( Bitmap.CompressFormat.JPEG, 100, new FileOutputStream ( getOutputMediaFile (MEDIA_TYPE_IMAGE,0 ) ) );
            // dest is Bitmap, if you want to preview the final image, you can display it on screen also before saving
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ( );
        }
        return dest;
    }

    public String getCurrentDate() {

        SimpleDateFormat formatter = new SimpleDateFormat ( "dd/MM/yyyy HH:mm:ss", Locale.getDefault ( ) );
        Date date = new Date ( );

        return formatter.format ( date );
    }


    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage(int count, Bitmap data) {
        String bitmapstring;
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");data
        Bitmap bm = data.copy ( Bitmap.Config.RGB_565, true );
//        bitmapstring=bm.toString();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream ( );
        data.compress ( Bitmap.CompressFormat.JPEG, 50, bytes );
        byte[] byteArray = bytes.toByteArray ( );
        bitmapstring = Base64.encodeToString ( byteArray, Base64.DEFAULT );
        System.out.println ( "hyyyy" + count );


        System.out.println ( "AMIN " + DataHolder_SiteInspection.getInstance ( ).getImg6 ( ) );

        String str_timestamp1 = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.getDefault ( ) ).format ( new Date ( ) );
        DataHolder_SiteInspection.getInstance ( ).setImageNmae6 ( permit_id + "_" + str_timestamp1 + "." + "jpg" );

        DataHolder_SiteInspection.getInstance ( ).setImg6 ( bitmapstring );

    }

    /**
     * ------------ Helper Methods ----------------------
     */

    /*
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        count++;
        return Uri.fromFile ( getOutputMediaFile ( type, count ) );
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type, int count) {

        // External sdcard location
        File mediaStorageDir = new File (
                Environment
                        .getExternalStoragePublicDirectory ( Environment.DIRECTORY_PICTURES ),
                IMAGE_DIRECTORY_NAME );

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists ( )) {
            if (!mediaStorageDir.mkdirs ( )) {
                Log.d ( IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory" );
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat ( "yyyyMMdd_HHmmss",
                Locale.getDefault ( ) ).format ( new Date ( ) );
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            System.out.println ( "hyout" + count );
            /*mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");*/
            mediaFile = new File ( mediaStorageDir.getPath ( ) + File.separator
                    + "IMG" + ".jpg" );
//        } else if (type == MEDIA_TYPE_VIDEO) {
////            mediaFile = new File(mediaStorageDir.getPath() + File.separator
////                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth ( );
        int height = bm.getHeight ( );
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix ( );
        // RESIZE THE BIT MAP
        matrix.postScale ( scaleWidth, scaleHeight );
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap (
                bm, 0, 0, width, height, matrix, false );
        bm.recycle ( );
        return resizedBitmap;
    }

    public static boolean isMockSettingsON(Context context) {
        // returns true if mock location enabled, false if not enabled.
        if (Settings.Secure.getString ( context.getContentResolver ( ),
                Settings.Secure.ALLOW_MOCK_LOCATION ).equals ( "0" ))
            return false;
        else
            return true;
    }

    public void ShowAlertagain() {
        if (!isLocationServiceEnabled ( )) {
            // Toast.makeText(getApplicationContext(), "please wait while location is fetching", Toast.LENGTH_SHORT).show();

            runOnUiThread ( new Runnable ( ) {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder ( AttendanceINCamera_Activity.this );
                    builder.setMessage ( "Do you want to Continue without location?" );
                    builder.setCancelable ( true );
                    builder.setPositiveButton ( "OK",
                            new DialogInterface.OnClickListener ( ) {
                                public void onClick(DialogInterface dialog, int id) {
                                    latt = 0;
                                    longg = 0;
                                    if (connectionDetector.isConnectingToInternet ( )) {
//                                        //new SendToServer(latt, longg).execute();
                                        captureImage ( );
                                    } else {
                                        captureImage ( );

                                    }
                                    dialog.cancel ( );
                                }
                            } );
                    builder.setNegativeButton ( "SETTINGS",
                            new DialogInterface.OnClickListener ( ) {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent ( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                                    startActivity ( intent );
                                    dialog.cancel ( );
                                }
                            } );

                    AlertDialog alert = builder.create ( );
                    alert.show ( );
                }
            } );
        } else {

            //   Log.e("Latitude", "" + mLocation.getLatitude());
            //   Log.e("Longitude", "" + mLocation.getLongitude());
        }
    }


    public void showAlertMock() {


        AlertDialog.Builder builder = new AlertDialog.Builder ( AttendanceINCamera_Activity.this );
        builder.setMessage ( "Please OFF Mock Location from setting" );
        //builder.setCancelable(true);


        builder
                //  .setMessage("Click yes to exit!")
                .setCancelable ( true )
                .setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
//                        startActivity(new Intent(Attendance_Activity.this, Home_Activity.class));
//                        Camera_Activity.this.finish();
                    }
                } );

        AlertDialog alert = builder.create ( );
        alert.show ( );

    }

    public boolean isLocationServiceEnabled() {
        LocationManager lm = (LocationManager) getSystemService ( Context.LOCATION_SERVICE );
        String provider = lm.getBestProvider ( new Criteria ( ), true ).trim ( );
        //  Log.e("provider" ,""+provider);
        //  Log.e("LocationManager" ,""+lm);
        return (provider != null &&
                !LocationManager.PASSIVE_PROVIDER.equals ( provider ));
    }


    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        //String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(AttendanceINCamera_Activity.this);
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

                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("resp");

                        int fileLength = contacts.length();

                        // for (int i = 0; i < contacts.length(); i++) {

                        if (fileLength == 0) {
                            //Toast.makeText(getApplicationContext(), " device not configured ", Toast.LENGTH_LONG).show();

                        } else {
                            JSONObject c = contacts.getJSONObject(0);
                            //String total_survey = c.getString("total_survey");
                            String max_ver = c.getString("max_ver");
                            sessionManager.createVersionSession(max_ver);

                            //Toast.makeText(getApplicationContext(), ""+max_ver, Toast.LENGTH_SHORT).show();

                            // custom dialog
                            final Dialog dialog = new Dialog(AttendanceINCamera_Activity.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
                            dialog.setContentView(R.layout.custom);
                            //                          dialog.setTitle("Title...");

                            // set the custom dialog components - text, image and button
                            TextView text = dialog.findViewById(R.id.text);
                            //text.setText("Total Survey: " + total_survey.toString() + " and  Total working hours " + total_time.toString() + "hrs");
                            //text.setText("Latest Version: " + max_ver.toString() + " hrs");
                            text.setText(" Punch In Marked ");


							/*String str_currenttimestamp1 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
							DataHolder_SiteInspection.getInstance().setCurrent_date(str_currenttimestamp1);*/

                            //Toast.makeText(getApplicationContext(), DataHolder_SiteInspection.getInstance().getCurrent_date(), Toast.LENGTH_SHORT).show();
                            //text.setText("Punch Out Successfully");
                            Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
                            // if button is clicked, close the custom dialog
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pDialog.hide();
                                    pDialog.dismiss();
                                    dialog.dismiss();
                                    DataHolder_SiteInspection.getInstance().nullify_DataHolder_SiteInspection();
                                    DataHolder_SiteInspection.getInstance().setStr_punch_status("1");

									/*moveTaskToBack(true);
									android.os.Process.killProcess(android.os.Process.myPid());
									System.exit(1);*/


                                        startActivity( new Intent(getApplicationContext(),Home_Activity.class));
                                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                        finish();


                                }
                            });

                            dialog.show();
                        }
                        // }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Toast.makeText(getApplicationContext(), " device not configured ", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    //  Log.e("ServiceHandler", "Couldn't get any data from the url");
                }

            } catch (Exception e) {

                // Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }




}