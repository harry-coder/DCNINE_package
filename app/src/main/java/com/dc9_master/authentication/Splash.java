package com.dc9_master.authentication;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dc9_master.Home_Activity;
import com.dc9_master.Home_BajajActivity;
import com.dc9_master.R;
import com.dc9_master.sqlite_adapter.SQLiteAdapter1;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Splash extends Activity {

    String response=null;
    SQLiteAdapter1 sqLiteAdapter;
    String network_interrupt=null;
    ConnectionDetector connectionDetector;
    SessionManager sessionManager;
    //UrlSessionManager urlsessionManager;
    String project_id;
    String district_id;
    String status;


    HttpClient httpclient;
    HttpPost httppost;

    boolean responseProject, responseGeographic, responseSubAreaOne,responseSubAreaTwo,
            responseSubAreaThree,respMatrialStore,responseMaterial,responseMaterialPara,responseNature,
            responseIssue,responseJMC,responseSor,responseSorPara,responseSafety,responseSafetyPara,responseInfraInsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sqLiteAdapter=new SQLiteAdapter1(Splash.this);
        connectionDetector=new ConnectionDetector(Splash.this);
        //urlsessionManager=new UrlSessionManager(Splash.this);

        sessionManager=new SessionManager(Splash.this);
        project_id=sessionManager.GET_PROJECT();
        district_id=sessionManager.GET_DISTRICT();


        if(sessionManager.getdownload_completed()){
            //startActivity( new Intent(Splash.this,Login_Activity.class));
            startActivity( new Intent(Splash.this,Home_Activity.class));
            finish();

           /* if(Integer.valueOf(sessionManager.GET_STATUS())==1) {
                //.makeText(getApplicationContext(), sessionManager.GET_STATUS(), Toast.LENGTH_SHORT).show();
                startActivity( new Intent(Splash.this,Home_BajajActivity.class));
                finish();
                } else if(Integer.valueOf(sessionManager.GET_STATUS())==2) {
               // Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                startActivity( new Intent(Splash.this,Home_Activity.class));
                finish();
            }*/
           }else {
            if (connectionDetector.isConnectingToInternet()) {
                Log.e("download", "" + sessionManager.getdownload_completed());
                // new Configuration().execute();
                new Configuration().execute();
                sessionManager.download_completed();
                } else {
                Toast.makeText(getApplicationContext(), "Internet not connected", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public  class Configuration extends AsyncTask<String, String,String>
    {

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            // sqlAdapter=new SQLiteAdapter(Splash.this);
            try
            {
                //  sqlAdapter.openToRead();
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();
                httpclient = new DefaultHttpClient();
                // String jsonStr = sh.makeServiceCall(url + "/" + id, ServiceHandler.GET);
                httppost = new HttpPost("http://monitorpm.feedbackinfra.com/dcnine_demo/api/download");
                // httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"/DCNINE/embc_app/download");
                getProject();
                getGeographic();
                getSubAreaOne();
                getSubAreaTwo();
                getSubAreaThree();
                getSorItem();
                getSorItemPara();
                getSafetyItem();
                getSafetyItemPara();
                getMaterialItem();
                getMaterialItemPara();
                getIssue();
                getMaterialStore();
               /* getMaterialStore();
                getMaterialItem();
                getMaterialItemPara();
                getSorItem();
                getSorItemPara();
                getSafetyItem();
                getSafetyItemPara();
                getNatureCall();
                getJMC();
                getIssue();
                getInfraInsp();*/

            }
            catch(Exception e)
            {
                //  Log.e("log_tag", "Error in http connection " + e.toString());

            }
            return response;
        }
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(responseGeographic== true && responseSubAreaOne==true && responseSubAreaTwo== true  && responseSubAreaThree== true) {
                 startActivity( new Intent(Splash.this,Home_Activity.class));
                 finish();
                 /*  if(Integer.valueOf(sessionManager.GET_STATUS())==1) {
                    //.makeText(getApplicationContext(), sessionManager.GET_STATUS(), Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(Splash.this,Home_BajajActivity.class));
                    finish();
                } else if(Integer.valueOf(sessionManager.GET_STATUS())==2) {
                    // Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(Splash.this,Home_Activity.class));
                    finish();
                }*/
            }
            else{
                Toast.makeText(getApplicationContext(), "Unable to start application", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void getProject() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
       /* project_id=sessionManager.GET_PROJECT();
        district_id=sessionManager.GET_DISTRICT();*/
        nameValuePairs.add(new BasicNameValuePair("tag", "project"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        Log.e("NameValuepro", "" + nameValuePairs);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseProject=false;
            }
            else {
                //   Log.e("district", response);
                JSONObject json_project = new JSONObject(response);
                JSONArray array_project = json_project.getJSONArray("resp");
                Log.e("array project", "" + array_project);
                for (int i = 0; i < array_project.length(); i++) {
                    JSONObject json_proj = array_project.getJSONObject(i);
                    String scheme_id = json_proj.getString("scheme_id");
                    String project_id = json_proj.getString("project_id");
                    String scheme_name = json_proj.getString("scheme_name");
                    sqLiteAdapter.insert_project(scheme_id, project_id, scheme_name);
                    // Log.e("dist id", dist_code);
                    responseProject = true;
                }
              }
            } catch (Exception e) {
            //   Log.e("log_tag", "Error in http connection " + e.toString());
           }
        }
    public void getGeographic() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "geographic"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseGeographic=false;
            }
            else {
                //   Log.e("block", response);
                JSONObject json_geographic = new JSONObject(response);
                JSONArray array_geographic = json_geographic.getJSONArray("resp");
                Log.e("array geographic", "" + array_geographic);
                for (int i = 0; i < array_geographic.length(); i++) {
                    JSONObject json_geographic_obj = array_geographic.getJSONObject(i);
                    String geographic_project_code = json_geographic_obj.getString("project_code");
                    String geographic_code = json_geographic_obj.getString("geographic_id");
                    String geographic_name = json_geographic_obj.getString("geographic_name");
                    sqLiteAdapter.insert_geographic(geographic_project_code, geographic_code, geographic_name);
                    responseGeographic=true;
                }
              }
            } catch (Exception e) {
            //  Log.e("log_tag", "Error in http connection " + e.toString());
        }

    }
    public void getSubAreaOne() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "subarea_one"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseSubAreaOne=false;
            }
            else {
                //   Log.e("block", response);
                JSONObject json_sub_area_one = new JSONObject(response);
                JSONArray array_sub_area_one = json_sub_area_one.getJSONArray("resp");
                Log.e("array sub_area_one", "" + array_sub_area_one);
                for (int i = 0; i < array_sub_area_one.length(); i++) {
                    JSONObject json_sub_area_one_obj = array_sub_area_one.getJSONObject(i);
                    String subareaone_geographic_code = json_sub_area_one_obj.getString("geographic_code");
                    String subarea_one_code = json_sub_area_one_obj.getString("subarea_one_id");
                    String subarea_one_name = json_sub_area_one_obj.getString("subarea_one_name");
                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_subarea_one(subareaone_geographic_code, subarea_one_code, subarea_one_name);
                    responseSubAreaOne=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    public void getSubAreaTwo() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "subarea_two"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseSubAreaTwo=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sub_area_two = new JSONObject(response);
                JSONArray array_sub_area_two = json_sub_area_two.getJSONArray("resp");
                Log.e("array subarea_two", "" + array_sub_area_two);

                for (int i = 0; i < array_sub_area_two.length(); i++) {
                    JSONObject json_sub_area_two_obj = array_sub_area_two.getJSONObject(i);
                    String subareatwo_one_code = json_sub_area_two_obj.getString("sub_area1_id");
                    String subarea_two_code = json_sub_area_two_obj.getString("id");
                    String subarea_two_name = json_sub_area_two_obj.getString("sub_area2");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_subarea_two(subareatwo_one_code, subarea_two_code, subarea_two_name);

                    responseSubAreaTwo=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    public void getSubAreaThree() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "subarea_three"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseSubAreaThree=false;
            }
            else {
                //   Log.e("block", response);
                JSONObject json_sub_area_three = new JSONObject(response);
                JSONArray array_sub_area_three = json_sub_area_three.getJSONArray("resp");
                Log.e("array sub_area_three", "" + array_sub_area_three);

                for (int i = 0; i < array_sub_area_three.length(); i++) {
                    JSONObject json_sub_area_three_obj = array_sub_area_three.getJSONObject(i);
                    String district_id = json_sub_area_three_obj.getString("district_id");
                    String district_name = json_sub_area_three_obj.getString("district_name");
                    String block_id = json_sub_area_three_obj.getString("block_id");
                    String block_name = json_sub_area_three_obj.getString("block_name");
                    String village_id = json_sub_area_three_obj.getString("village_id");
                    String village_name = json_sub_area_three_obj.getString("village_name");
                    String majra_id = json_sub_area_three_obj.getString("majra_id");
                    String majra_name = json_sub_area_three_obj.getString("majra_name");
                    sqLiteAdapter.insert_subarea_three(district_id,district_name,block_id,block_name,village_id,village_name,majra_id,majra_name);
                    responseSubAreaThree=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for get Material Store--------------------------------//
    public void getMaterialStore() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "material_store"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                respMatrialStore=false;
            }
            else {
                //Log.e("material", response);
                JSONObject json_materialstore = new JSONObject(response);
                JSONArray array_materialstore = json_materialstore.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array material_store", "" + array_materialstore);

                for (int i = 0; i < array_materialstore.length(); i++) {
                    JSONObject json_materialstore_obj = array_materialstore.getJSONObject(i);
                    String store_id = json_materialstore_obj.getString("store_id");
                    String stores_location = json_materialstore_obj.getString("stores_location");
                    String district_id = json_materialstore_obj.getString("district_id");
                    sqLiteAdapter.insert_material_store(store_id, stores_location, district_id);
                    respMatrialStore=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for material Item--------------------------------//
    public void getMaterialItem() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "material_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseMaterial=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveysubs = new JSONObject(response);
                JSONArray array_Surveysubs = json_surveysubs.getJSONArray("resp");
                Log.e("array material_item", "" + array_Surveysubs);

                for (int i = 0; i < array_Surveysubs.length(); i++) {
                    JSONObject json_surveysubs_obj = array_Surveysubs.getJSONObject(i);
                    String id = json_surveysubs_obj.getString("id");
                    String item_name = json_surveysubs_obj.getString("item_name");
                    // String unit = json_surveysubs_obj.getString("unit");
                    sqLiteAdapter.insert_item(id, item_name);
                    responseMaterial=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for material Item Parameter--------------------------------//
    public void getMaterialItemPara() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "material_itemPara"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseMaterialPara=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_itempara = new JSONObject(response);
                JSONArray array_itempara = json_itempara.getJSONArray("resp");
                Log.e("array itempara", "" + array_itempara);

                for (int i = 0; i < array_itempara.length(); i++) {
                    JSONObject json_itempara_obj = array_itempara.getJSONObject(i);
                    String id = json_itempara_obj.getString("id");
                    String paraitem_name = json_itempara_obj.getString("paraitem_name");
                    String item_code = json_itempara_obj.getString("item_code");
                    sqLiteAdapter.insert_item_parameter(id, paraitem_name,item_code);
                    responseMaterial=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    //-------------for Sor/Qc--------------------//
       public void getSorItem() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "sor_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSor=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sor = new JSONObject(response);
                JSONArray array_Sor = json_sor.getJSONArray("resp");
                Log.e("array sor_item", "" + array_Sor);

                for (int i = 0; i < array_Sor.length(); i++) {
                    JSONObject json_sor_obj = array_Sor.getJSONObject(i);
                    String id = json_sor_obj.getString("id");
                    String sor_name = json_sor_obj.getString("sor_name");
                    // String unit = json_surveysubs_obj.getString("unit");
                    sqLiteAdapter.insert_sor_item(id, sor_name);
                    responseSor=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for SOR/QC Item Parameter--------------------------------//
    public void getSorItemPara() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "sor_itemPara"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSorPara=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sorpara = new JSONObject(response);
                JSONArray array_sorpara = json_sorpara.getJSONArray("resp");
                Log.e("array sorpara", "" + array_sorpara);

                for (int i = 0; i < array_sorpara.length(); i++) {
                    JSONObject json_sorpara_obj = array_sorpara.getJSONObject(i);
                    String id = json_sorpara_obj.getString("id");
                    String paraitem_name = json_sorpara_obj.getString("paraitem_name");
                    String item_code = json_sorpara_obj.getString("sor_code");
                    sqLiteAdapter.insert_sor_item_parameter(id, paraitem_name,item_code);
                    responseSorPara=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    //-----------SAFETY -------------------//
    public void getSafetyItem() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "safety_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSafety=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_safety = new JSONObject(response);
                JSONArray array_Safety = json_safety.getJSONArray("resp");
                Log.e("array safety_item", "" + array_Safety);

                for (int i = 0; i < array_Safety.length(); i++) {
                    JSONObject json_safety_obj = array_Safety.getJSONObject(i);
                    String id = json_safety_obj.getString("id");
                    String safety_name = json_safety_obj.getString("safety_name");
                    // String unit = json_surveysubs_obj.getString("unit");
                    sqLiteAdapter.insert_safety_item(id, safety_name);
                    responseSafety=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Safety Parameter--------------------------------//
    public void getSafetyItemPara() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "safety_itemPara"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSafetyPara=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_safetypara = new JSONObject(response);
                JSONArray array_safetypara = json_safetypara.getJSONArray("resp");
                Log.e("array safetypara", "" + array_safetypara);

                for (int i = 0; i < array_safetypara.length(); i++) {
                    JSONObject json_safetypara_obj = array_safetypara.getJSONObject(i);
                    String id = json_safetypara_obj.getString("id");
                    String parameter_name = json_safetypara_obj.getString("parameter_name");
                    String safety_code = json_safetypara_obj.getString("safety_code");
                    sqLiteAdapter.insert_safety_item_parameter(id, parameter_name,safety_code);
                    responseSafetyPara=true;
                     /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/
                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    public void getNatureCall() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "nature"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseNature=false;
            }
            else {
                JSONObject json_nature = new JSONObject(response);
                JSONArray array_nature = json_nature.getJSONArray("resp");
                Log.e("array Nature", "" + array_nature);

                for (int i = 0; i < array_nature.length(); i++) {
                    JSONObject json_nature_obj = array_nature.getJSONObject(i);
                    String nature_id = json_nature_obj.getString("nature_id");
                    String nature_name = json_nature_obj.getString("nature_name");
                    sqLiteAdapter.insert_nature(nature_id, nature_name);
                    responseNature=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Issue Type--------------------------------//
    public void getIssue() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "issue_type"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseIssue=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_issue = new JSONObject(response);
                JSONArray array_issue = json_issue.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array issue", "" + array_issue);

                for (int i = 0; i < array_issue.length(); i++) {
                    JSONObject json_issue_obj = array_issue.getJSONObject(i);
                    String id = json_issue_obj.getString("id");
                    String issue = json_issue_obj.getString("issue_type");
                    sqLiteAdapter.insert_issue(id, issue);
                    responseIssue=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    public void getJMC() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "jmc_type"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseJMC=false;
            }
            else {
                JSONObject json_jmc = new JSONObject(response);
                JSONArray array_jmc = json_jmc.getJSONArray("resp");
                Log.e("array jmc", "" + array_jmc);
                for (int i = 0; i < array_jmc.length(); i++) {
                    JSONObject json_jmc_obj = array_jmc.getJSONObject(i);
                    String id = json_jmc_obj.getString("id");
                    String jmc_name = json_jmc_obj.getString("jmc_type");
                    sqLiteAdapter.insert_jmc(id, jmc_name);
                    responseJMC=true;
                    }
                }

             } catch (Exception e) {
          }

    }


    public void getInfraInsp() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "infra_inspection"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                responseInfraInsp=false;
            }
            else {

                JSONObject json_jmc = new JSONObject(response);
                JSONArray array_jmc = json_jmc.getJSONArray("resp");
                Log.e("array infra_insp", "" + array_jmc);
                for (int i = 0; i < array_jmc.length(); i++) {
                    JSONObject json_jmc_obj = array_jmc.getJSONObject(i);
                    String insp_type = json_jmc_obj.getString("infra");
                    sqLiteAdapter.insert_infra(insp_type);
                    responseInfraInsp=true;
                }
            }

        } catch (Exception e) {
        }

    }


}



