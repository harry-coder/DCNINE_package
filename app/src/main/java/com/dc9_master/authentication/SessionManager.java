package com.dc9_master.authentication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint({ "NewApi", "CommitPrefEdits" })
public class SessionManager {
	SharedPreferences session_pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String SESSSION_PREF = "session_pref";
	private static final String IS_LOGIN = "IsLoggedIn";
    private static final String SESSSION_URL = "session_url";
    private static final String DOWNLOAD = "download";
	public static final String EMP_ID = "emp_id";
	public static final String DISTRICT = "district";
	public static final String CURRENT_URL = "current_url";
	public static final String PROJECT_ID = "project_id";
	public static final String DISTANCE_ID = "distance_id";
	public static final String STATUS_ID = "status_id";
	public static final String VERSION_ID = "version_id";
	public SessionManager(Context context){
	this._context = context;
	session_pref = _context.getSharedPreferences(SESSSION_PREF, PRIVATE_MODE);
	editor = session_pref.edit();
	}
    public void createLoginSession(String emp_id, String district, String block, String project_id, String status_id){
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(EMP_ID, emp_id);
		editor.putString(DISTRICT, district);
		editor.putString(CURRENT_URL, block);
		editor.putString(PROJECT_ID, project_id);
		editor.putString(STATUS_ID, status_id);
		//editor.putString(DISTANCE_ID, distance_id);
		editor.commit();
	}



	public void logoutUser(){
		editor.clear();
		editor.commit();
	}


	public boolean isLoggedIn()
	{
		return session_pref.getBoolean(IS_LOGIN, false);	
	}

	public String GET_EMP_ID(){

		return session_pref.getString(EMP_ID, null);
	}


	public String GET_DISTRICT(){

		return session_pref.getString(DISTRICT, null);
	}

	public String GET_STATUS(){

		return session_pref.getString(STATUS_ID, null);
	}

	public String GET_CURRENT_URL(){

		return session_pref.getString(CURRENT_URL, null);
	}

	public String GET_PROJECT(){

		return session_pref.getString(PROJECT_ID, null);
	}


	public String GET_DISTANCE(){

		return session_pref.getString(DISTANCE_ID, null);
	}


	public String GET_VERSION(){

		return session_pref.getString(VERSION_ID, null);
	}

	public void change_url(String url)
    {
        editor.putString(SESSSION_URL, url);
        editor.commit();
    }
    public String GET_URL(){
        return session_pref.getString(SESSSION_URL,"http://monitorpm.feedbackinfra.com/dcnine_bajaj/embc_app/");
    }


    public void download_completed(){
        editor.putBoolean(DOWNLOAD, true);
        editor.commit();
    }

    public boolean getdownload_completed(){

        return session_pref.getBoolean(DOWNLOAD, false);
    }

	public void createVersionSession(String version_id){
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(VERSION_ID, version_id);

		//editor.putString(DISTANCE_ID, distance_id);
		editor.commit();
	}


	public void clearSpecificPref() {
		// Clearing all data from Shared Preferences
//		editor.clear();
		editor.remove(PROJECT_ID);
		editor.remove(CURRENT_URL);

	}




}