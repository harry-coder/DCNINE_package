package com.dc9_master.data_holder;

/**
 * Created by nitin on 21-08-2015.
 */
public class DataHolderClass {

    private static DataHolderClass dataObject = null;


    private DataHolderClass() {
        // left blank intentionally
    }

    public static DataHolderClass getInstance() {
        if (dataObject == null)
            dataObject = new DataHolderClass();
        return dataObject;
    }

    //------------- for Login Module-------//
    private String str_user_code;
    private String str_pin_code;

/*
//------------ for SiteInspection Module------------------//
    private String str_project_code;
    private String str_project_name;
    private String str_geographic_code;
    private String str_geographic_name;
    private String str_subareaone_name;
    private String str_subareaone_code;

    public String getStr_project_code() {
        return str_project_code;
    }

    public void setStr_project_code(String str_project_code) {
        this.str_project_code = str_project_code;
    }

    public String getStr_project_name() {
        return str_project_name;
    }

    public void setStr_project_name(String str_project_name) {
        this.str_project_name = str_project_name;
    }

    public String getStr_geographic_code() {
        return str_geographic_code;
    }

    public void setStr_geographic_code(String str_geographic_code) {
        this.str_geographic_code = str_geographic_code;
    }

    public String getStr_geographic_name() {
        return str_geographic_name;
    }

    public void setStr_geographic_name(String str_geographic_name) {
        this.str_geographic_name = str_geographic_name;
    }

    public String getStr_subareaone_name() {
        return str_subareaone_name;
    }

    public void setStr_subareaone_name(String str_subareaone_name) {
        this.str_subareaone_name = str_subareaone_name;
    }

    public String getStr_subareaone_code() {
        return str_subareaone_code;
    }

    public void setStr_subareaone_code(String str_subareaone_code) {
        this.str_subareaone_code = str_subareaone_code;
    }

    public String getStr_subareaone_id() {
        return str_subareaone_id;
    }

    public void setStr_subareaone_id(String str_subareaone_id) {
        this.str_subareaone_id = str_subareaone_id;
    }

    private String str_subareaone_id;

*/



    public String getStr_user_code() {
        return str_user_code;
    }

    public void setStr_user_code(String str_user_code) {
        this.str_user_code = str_user_code;
    }

    public String getStr_pin_code() {
        return str_pin_code;
    }

    public void setStr_pin_code(String str_pin_code) {
        this.str_pin_code = str_pin_code;
    }


}
