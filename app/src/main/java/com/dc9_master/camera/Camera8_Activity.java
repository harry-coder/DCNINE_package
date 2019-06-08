package com.dc9_master.camera;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.R;
import com.dc9_master.authentication.ConnectionDetector;
import com.dc9_master.authentication.SessionManager;
import com.dc9_master.data_holder.DataHolder_workprogress;
import com.dc9_master.location.FusedLocationService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by nitinb on 29-01-2016.
 */
public class Camera8_Activity extends Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        // setContentView(R.layout.camera);
        setContentView ( R.layout.comon_camera );

        //Toast.makeText(getApplicationContext(), "First Photo must be selfie with the inspecting work", Toast.LENGTH_SHORT).show();
        //cameraAlert();

        sessionManager = new SessionManager ( Camera8_Activity.this );
        fusedLocationService = new FusedLocationService ( this );
        mLocation = fusedLocationService.getLocation ( );
        connectionDetector = new ConnectionDetector ( Camera8_Activity.this );

        imgPreview = (ImageView) this.findViewById ( R.id.imageView1 );

        locationtxt = (TextView) this.findViewById ( R.id.TextViewLocation );

        //-------------for next button------------------//
        next_btn = (Button) findViewById ( R.id.use_picture_button );
        next_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                onBackPressed ( );

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
//                    Bitmap imageviewbm = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
//
//                    previewCapturedImage(count, imageviewbm);

                } catch (RuntimeException e) {
                    System.out.print ( "RuntimeException: " );
                }

                //Bitmap imageviewbm = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                //previewCapturedImage(count, imageviewbm);

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
                    String tview1 = "" + sending_latt + "," + sending_longg + "," + str_timestamp;
                    locationtxt.setText ( tview1 );

                    sending_latt = String.valueOf ( mLocation.getLatitude ( ) );
                    sending_longg = String.valueOf ( mLocation.getLongitude ( ) );
                    String tview = "" + sending_latt + "," + sending_longg + "," + str_timestamp;
                    locationtxt.setText ( tview );

                    DataHolder_workprogress.getInstance ( ).setCamera_lat ( sending_latt );
                    DataHolder_workprogress.getInstance ( ).setCamera_long ( sending_longg );
                    DataHolder_workprogress.getInstance ( ).setCamera_time ( str_timestamp );

                    captureImage ( );

                } else {
                    //ShowAlertagain();

                    captureImage ( );
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


                Bitmap convertedBitmap = combineBitMap ( bitmapunpro, getCurrentDate ( ), ""+String.valueOf ( mLocation.getLatitude ( ) )+","+ String.valueOf ( mLocation.getLongitude ( ) ));


                String lrgbitmapstring;
                ByteArrayOutputStream bytes = new ByteArrayOutputStream ( );
                convertedBitmap.compress ( Bitmap.CompressFormat.JPEG, 50, bytes );


                byte[] byteArray = bytes.toByteArray ( );
                lrgbitmapstring = Base64.encodeToString ( byteArray, Base64.DEFAULT );
                DataHolder_workprogress.getInstance ( ).setlrgImg1 ( lrgbitmapstring );
               /* if (DataHolder_workprogress.getInstance ( ).getlrgImg1 ( ) == null) {

                    DataHolder_workprogress.getInstance ( ).setlrgImg1 ( lrgbitmapstring );
                } else {
                    Toast.makeText ( getApplicationContext ( ), "Maximum 1 Images", Toast.LENGTH_LONG ).show ( );
                }*/
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

        System.out.println ("This is the width "+(src.getWidth ( ) - (locWidth + 150)) );
        System.out.println ("This is the height "+(src.getHeight ( ) - 300) );

        System.out.println ("This is width "+src.getWidth ( ) );
        System.out.println ("This is cal width "+locWidth + 150 );
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


        System.out.println ( "AMIN " + DataHolder_workprogress.getInstance ( ).getImg1 ( ) );

        String str_timestamp1 = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.getDefault ( ) ).format ( new Date ( ) );
        DataHolder_workprogress.getInstance ( ).setImageNmae1 ( permit_id + "_" + str_timestamp1 + "." + "jpg" );

        DataHolder_workprogress.getInstance ( ).setImg1 ( bitmapstring );
        /*if (DataHolder_workprogress.getInstance ( ).getImg1 ( ) == null) {
            File destination = new File ( Environment.getExternalStoragePublicDirectory ( Environment.DIRECTORY_PICTURES ) + "/PMC/",
                    permit_id + "_" + str_timestamp1 + "." + "jpg" );
            DataHolder_workprogress.getInstance ( ).setImageNmae1 ( permit_id + "_" + str_timestamp1 + "." + "jpg" );
            *//*System.out.println("IMAGE NAME 1 " + permit_id+"_"+str_timestamp1+"."+"jpg");
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
            }*//*
            DataHolder_workprogress.getInstance ( ).setImg1 ( bitmapstring );
            //System.out.println("AMIN1");

        } else {
            Toast.makeText ( getApplicationContext ( ), "Maximum 1 Images", Toast.LENGTH_LONG ).show ( );
        }*/


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
                    AlertDialog.Builder builder = new AlertDialog.Builder ( Camera8_Activity.this );
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


        AlertDialog.Builder builder = new AlertDialog.Builder ( Camera8_Activity.this );
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


    public void cameraAlert() {


        AlertDialog.Builder builder = new AlertDialog.Builder ( Camera8_Activity.this );
        builder.setMessage ( "First Photo must be selfie with the inspecting work" );
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


}