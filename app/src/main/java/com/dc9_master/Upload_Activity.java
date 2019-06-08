package com.dc9_master;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by nitinb on 28-01-2016.
 */
public class Upload_Activity extends Activity {
    ImageButton meter_insp_btn,pole_insp_btn,span_insp_btn,upload_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.upload);

        upload_btn=(ImageButton) findViewById(R.id.upload_btn);

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(Upload_Activity.this, Home_Activity.class);
            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }


}