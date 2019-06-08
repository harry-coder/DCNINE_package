package com.dc9_master.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dc9_master.R;

/**
 * Created by nitinb on 02-02-2016.
 */
public class Notification_Activity extends Activity {

    //public String EMP_ID = "emp_id";

    Button grn, grn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grn_list);



        grn=(Button)findViewById(R.id.grn1);
        grn2=(Button)findViewById(R.id.grn2);
        grn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(Notification_Activity.this, GrnDetailsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);



            }
        });
        grn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(Notification_Activity.this, GrnDetailsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);



            }
        });



    }


   /* public void onBackPressed() {
        startActivity(new Intent(FactoryInspection_Activity.this, Home_Activity.class));
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }*/




}
