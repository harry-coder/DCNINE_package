package com.dc9_master.clearapp_data;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dc9_master.R;
import java.io.File;
/**
 * Created by nitinb on 15-11-2018.
 */
public class ClearAppDataActivity extends Activity {
    // ImageButton next_btn;
    ImageButton btn_clear_data;
    private static ClearAppDataActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clear_app_data);
        instance = this;
        btn_clear_data = (ImageButton)findViewById(R.id.btn_cleardata);
        btn_clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            final Dialog dialog = new Dialog(ClearAppDataActivity.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            dialog.setContentView(R.layout.custom);
                            TextView text = (TextView) dialog.findViewById(R.id.text);
                            text.setText(" All of this application's data will be deleted permanently." +
                                          "This includes all files,setting,accounts,databases etc. ");
                            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                            Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNo);
                            dialogButton.setText("DELETE");
                            dialogButtonNO.setText("CANCEL");
                            dialogButtonNO.setVisibility(View.VISIBLE);

                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                    clearApplicationData();

                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);


                                }
                            });
                            dialogButtonNO.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();

                          }
        });
    }

    @Override
    public void onBackPressed() {
        finish(); // finish activity
        this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }



    public static ClearAppDataActivity getInstance(){
        return instance;
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if(appDir.exists()){
            String[] children = appDir.list();
            for(String s : children){
                if(!s.equals("lib")){
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

}
