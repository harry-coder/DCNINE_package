package com.dc9_master.attendance_module;

/**
 * Created by nitinb on 14-07-2018.
 */

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class GlobalClass extends Application {
    static GlobalClass application = null;
    @Override
    public void onCreate() {
        super.onCreate ( );
        application = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder ( );
        StrictMode.setVmPolicy ( builder.build ( ) );

        /*new Instabug.Builder ( this, "eb89193bc5dcffe7062a8a8732c3cd07" )
                .setInvocationEvents ( InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT )
                .build ( );*/
    }
    public static GlobalClass getInstance() {
        return application;
        }
        public static Context getContext() {

        return application.getApplicationContext ( );
    }

}

