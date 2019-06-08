package com.dc9_master.sqlite_adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nitinb on 09-02-2016.
 */
public class SQLiteAdapterWorkprogress {

    public static final String MYDATABASE_NAME ="WMPMC";
    public static final int MYDATABASE_VERSION = 3;

    // Create site inspection table
    public static final String SI_TABLE = "SI_TABLE";
    public static final String SI_TABLE_ID="id";
    public static final String SI_EMP_ID="emp_id";
    public static final String SI_PROJECT = "si_project";
    public static final String SI_GEOGRAPHIC = "si_geographic";
    public static final String SI_SUBAREAONE = "si_subareaone";
    public static final String SI_SUBAREATWO = "si_subareatwo";
    public static final String SI_SUBAREATHREE = "si_subareathree";
    public static final String SI_ITEMID = "si_itemid";
    public static final String SI_ITEM_YES = "si_item_yes";
    public static final String SI_ITEM_CANCEL = "si_item_cancel";
    public static final String SI_REMARK = "si_remark";
    public static final String SI_LANDMARK = "si_landmark";
    public static final String SI_CAMERA_LAT = "si_camera_lat";
    public static final String SI_CAMERA_LONG = "si_camera_long";
    public static final String SI_CAMERA_TIME = "si_camera_time";
    public static final String SI_IMGNAME1 = "si_imgname1";
    public static final String SI_IMGNAME2 = "si_imgname2";
    public static final String SI_IMGNAME3 = "si_imgname3";
    public static final String SI_IMG1 = "si_img1";
    public static final String SI_IMG2 = "si_img2";
    public static final String SI_IMG3 = "si_img3";
    public static final String SI_STATUS = "si_status";
    public static final String SI_AUTO_NUMBER = "si_auto_number";


    // Create safety inspection table
    public static final String SA_TABLE = "SA_TABLE";
    public static final String SA_TABLE_ID="id";
    public static final String SA_EMP_ID="emp_id";
    public static final String SA_PROJECT = "sa_project";
    public static final String SA_GEOGRAPHIC = "sa_geographic";
    public static final String SA_SUBAREAONE = "sa_subareaone";
    public static final String SA_SUBAREATWO = "sa_subareatwo";
    public static final String SA_SUBAREATHREE = "sa_subareathree";
    public static final String SA_ITEMID = "sa_itemid";
    public static final String SA_ITEM_YES = "sa_item_yes";
    public static final String SA_ITEM_CANCEL = "sa_item_cancel";
    public static final String SA_REMARK = "sa_remark";
    public static final String SA_CAMERA_LAT = "sa_camera_lat";
    public static final String SA_CAMERA_LONG = "sa_camera_long";
    public static final String SA_CAMERA_TIME = "sa_camera_time";
    public static final String SA_IMGNAME1 = "sa_imgname1";
    public static final String SA_IMGNAME2 = "sa_imgname2";
    public static final String SA_IMGNAME3 = "sa_imgname3";
    public static final String SA_IMG1 = "sa_img1";
    public static final String SA_IMG2 = "sa_img2";
    public static final String SA_IMG3 = "sa_img3";
    public static final String SA_AUTO_NUMBER = "sa_auto_number";

    // Create WORK PROGRESS table
    public static final String WI_TABLE = "WI_TABLE";
    public static final String WI_TABLE_ID="id";
    public static final String WI_EMP_ID="wiemp_id";
    public static final String WI_PROJECT = "wi_project";
    public static final String WI_GEOGRAPHIC = "wi_geographic";
    public static final String WI_SUBAREAONE = "wi_subareaone";
    public static final String WI_SUBAREATWO = "wi_subareatwo";
    public static final String WI_SUBAREATHREE = "wi_subareathree";
    public static final String WI_ITEMQTY = "wi_itemqty";
    public static final String WI_REMARK = "wi_remark";
    public static final String WI_CAMERA_LAT = "wi_camera_lat";
    public static final String WI_CAMERA_LONG = "wi_camera_long";
    public static final String WI_CAMERA_TIME = "wi_camera_time";
    public static final String WI_IMGNAME1 = "wi_imgname1";
    public static final String WI_IMG1 = "wi_img1";
    public static final String WI_AUTO_NUMBER = "wi_auto_number";


  //----------------For HHSC-------------------------------//

    public static final String HHSC_TABLE = "HHSC_TABLE";
    public static final String HHSC_TABLE_ID="id";
    public static final String HHSC_EMP_ID="hemp_id";
    public static final String HHSC_PROJECT = "hhsc_project";
    public static final String HHSC_GEOGRAPHIC = "hhsc_geographic";
    public static final String HHSC_SUBAREAONE = "hhsc_subareaone";
    public static final String HHSC_SUBAREATWO = "hhsc_subareatwo";
    public static final String HHSC_SUBAREATHREE = "hhsc_subareathree";
    public static final String HHSC_METERNO= "hhsc_meterno";
    public static final String HHSC_CHECKLIST_YES= "hhsc_checklist_yes";
    public static final String HHSC_CHECKLIST_CANCEL= "hhsc_checklist_cancel";
    public static final String HHSC_REMARK= "hhsc_remark";
    public static final String HHSC_INSP_QTY= "hhsc_insp_qty";
    public static final String HHSC_PASSED_QTY= "hhsc_passed_qty";
    public static final String HHSC_PASS_REMARK_STATUS= "hhsc_pass_rework_status";
    public static final String HHSC_CAMERA_LAT = "hhsc_camera_lat";
    public static final String HHSC_CAMERA_LONG = "hhsc_camera_long";
    public static final String HHSC_CAMERA_TIME = "hhsc_camera_time";
    public static final String HHSC_IMGNAME1 = "hhsc_imgname1";
    public static final String HHSC_IMG1 = "hhsc_img1";
    public static final String HHSC_AUTO_NUMBER = "hhsc_auto_number";


    private static final String CREATE_HHSC_TABLE =
            "create table " + HHSC_TABLE + " ("
                    + HHSC_TABLE_ID + " integer primary key autoincrement, "
                    + HHSC_EMP_ID + " text, "
                    + HHSC_PROJECT + " text, "
                    + HHSC_GEOGRAPHIC + " text, "
                    + HHSC_SUBAREAONE + " text, "
                    + HHSC_SUBAREATWO + " text, "
                    + HHSC_SUBAREATHREE + " text, "
                    + HHSC_METERNO + " text, "
                    + HHSC_CHECKLIST_YES + " text, "
                    + HHSC_CHECKLIST_CANCEL + " text, "
                    + HHSC_REMARK + " text, "
                    + HHSC_INSP_QTY + " text, "
                    + HHSC_PASSED_QTY + " text, "
                    + HHSC_PASS_REMARK_STATUS + " text, "
                    + HHSC_CAMERA_LAT + " text, "
                    + HHSC_CAMERA_LONG + " text, "
                    + HHSC_CAMERA_TIME + " text, "
                    + HHSC_IMGNAME1 + " text, "
                    + HHSC_IMG1 + " text, "
                    + HHSC_AUTO_NUMBER + " text  ) ; ";

    //............ insert hhsc ..............//
    public long insert_valueHHSC(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16, String content17, String content18){

        ContentValues contentValues = new ContentValues();
        contentValues.put(HHSC_EMP_ID, content0);
        contentValues.put(HHSC_PROJECT, content1);
        contentValues.put(HHSC_GEOGRAPHIC, content2);
        contentValues.put(HHSC_SUBAREAONE, content3);
        contentValues.put(HHSC_SUBAREATWO, content4);
        contentValues.put(HHSC_SUBAREATHREE, content5);
        contentValues.put(HHSC_METERNO, content6);
        contentValues.put(HHSC_CHECKLIST_YES, content7);
        contentValues.put(HHSC_CHECKLIST_CANCEL, content8);
        contentValues.put(HHSC_REMARK, content9);
        contentValues.put(HHSC_INSP_QTY, content10);
        contentValues.put(HHSC_PASSED_QTY, content11);
        contentValues.put(HHSC_PASS_REMARK_STATUS, content12);
        contentValues.put(HHSC_CAMERA_LAT, content13);
        contentValues.put(HHSC_CAMERA_LONG, content14);
        contentValues.put(HHSC_CAMERA_TIME, content15);
        contentValues.put(HHSC_IMGNAME1, content16);
        contentValues.put(HHSC_IMG1, content17);
        contentValues.put(HHSC_AUTO_NUMBER, content18);
       // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(HHSC_TABLE, null, contentValues);
    }


    public void delete_value_byIDHHSC(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(HHSC_TABLE, HHSC_TABLE_ID + "=" + id1, null);
    }

    public int countDataHHSC(){

        String countQuery = "SELECT  * FROM " + HHSC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataHHSC(){

        String countQuery = "SELECT  * FROM " + HHSC_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        return cursor;
    }

    public Cursor queueAllHHSC(){

        String countQuery = "SELECT  * FROM " + HHSC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //----------------For NETWORK ------------------------------//
    public static final String NETWORK_TABLE = "NETWORK_TABLE";
    public static final String HHSCN_TABLE_ID="id";
    public static final String HHSCN_EMP_ID="nemp_id";
    public static final String HHSCN_PROJECT = "hhscn_project";
    public static final String HHSCN_GEOGRAPHIC = "hhscn_geographic";
    public static final String HHSCN_SUBAREAONE = "hhscn_subareaone";
    public static final String HHSCN_SUBAREATWO = "hhscn_subareatwo";
    public static final String HHSCN_SUBAREATHREE = "hhscn_subareathree";
    public static final String HHSCN_INSPECTEDID= "hhscn_inspected_id";
    public static final String HHSCN_ITEM_ID= "hhscn_item_id";
    public static final String HHSCN_CHECKLIST_YES= "hhscn_checklist_yes";
    public static final String HHSCN_CHECKLIST_CANCEL= "hhscn_checklist_cancel";
    public static final String HHSCN_REMARK= "hhscn_remark";
    public static final String HHSCN_INSP_QTY= "hhscn_insp_qty";
    public static final String HHSCN_PASSED_QTY= "hhscn_passed_qty";
    public static final String HHSCN_PASS_REMARK_STATUS= "hhscn_pass_rework_status";
    public static final String HHSCN_CAMERA_LAT = "hhscn_camera_lat";
    public static final String HHSCN_CAMERA_LONG = "hhscn_camera_long";
    public static final String HHSCN_CAMERA_TIME = "hhscn_camera_time";
    public static final String HHSCN_IMGNAME1 = "hhscn_imgname1";
    public static final String HHSCN_IMG1 = "hhscn_img1";
    public static final String HHSCN_AUTO_NUMBER = "hhscn_auto_number";


    private static final String CREATE_NETWORK_TABLE =
            "create table " + NETWORK_TABLE + " ("
                    + HHSCN_TABLE_ID + " integer primary key autoincrement, "
                    + HHSCN_EMP_ID + " text, "
                    + HHSCN_PROJECT + " text, "
                    + HHSCN_GEOGRAPHIC + " text, "
                    + HHSCN_SUBAREAONE + " text, "
                    + HHSCN_SUBAREATWO + " text, "
                    + HHSCN_SUBAREATHREE + " text, "
                    + HHSCN_INSPECTEDID + " text, "
                    + HHSCN_ITEM_ID + " text, "
                    + HHSCN_CHECKLIST_YES + " text, "
                    + HHSCN_CHECKLIST_CANCEL + " text, "
                    + HHSCN_REMARK + " text, "
                    + HHSCN_INSP_QTY + " text, "
                    + HHSCN_PASSED_QTY + " text, "
                    + HHSCN_PASS_REMARK_STATUS + " text, "
                    + HHSCN_CAMERA_LAT + " text, "
                    + HHSCN_CAMERA_LONG + " text, "
                    + HHSCN_CAMERA_TIME + " text, "
                    + HHSCN_IMGNAME1 + " text, "
                    + HHSCN_IMG1 + " text, "
                    + HHSCN_AUTO_NUMBER + " text  ) ; ";

    //............ insert NETWORK ..............//
    public long insert_valueNETWORK(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16, String content17, String content18, String content19){

        ContentValues contentValues = new ContentValues();
        contentValues.put(HHSCN_EMP_ID, content0);
        contentValues.put(HHSCN_PROJECT, content1);
        contentValues.put(HHSCN_GEOGRAPHIC, content2);
        contentValues.put(HHSCN_SUBAREAONE, content3);
        contentValues.put(HHSCN_SUBAREATWO, content4);
        contentValues.put(HHSCN_SUBAREATHREE, content5);
        contentValues.put(HHSCN_INSPECTEDID, content6);
        contentValues.put(HHSCN_ITEM_ID, content7);
        contentValues.put(HHSCN_CHECKLIST_YES, content8);
        contentValues.put(HHSCN_CHECKLIST_CANCEL, content9);
        contentValues.put(HHSCN_REMARK, content10);
        contentValues.put(HHSCN_INSP_QTY, content11);
        contentValues.put(HHSCN_PASSED_QTY, content12);
        contentValues.put(HHSCN_PASS_REMARK_STATUS, content13);
        contentValues.put(HHSCN_CAMERA_LAT, content14);
        contentValues.put(HHSCN_CAMERA_LONG, content15);
        contentValues.put(HHSCN_CAMERA_TIME, content16);
        contentValues.put(HHSCN_IMGNAME1, content17);
        contentValues.put(HHSCN_IMG1, content18);
        contentValues.put(HHSCN_AUTO_NUMBER, content19);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(NETWORK_TABLE, null, contentValues);
    }


    public void delete_value_byIDNETWORK(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(NETWORK_TABLE, HHSCN_TABLE_ID + "=" + id1, null);
    }

    public int countDataNETWORK(){

        String countQuery = "SELECT  * FROM " + NETWORK_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataNETWORK(){

        String countQuery = "SELECT  * FROM " + NETWORK_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        return cursor;
    }

    public Cursor queueAllNETWORK(){

        String countQuery = "SELECT  * FROM " + NETWORK_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //--------------for MATERIAL RECEIVING-----------------//
    public static final String RI_TABLE = "RI_TABLE";
    public static final String RI_TABLE_ID="id";
    public static final String RI_EMP_ID="remp_id";
    public static final String RI_PROJECT = "ri_project";
    public static final String RI_DISTRICT_ID = "ri_districtid";
    public static final String RI_STORE_ID = "ri_storeid";
    public static final String RI_DI_NO = "ri_dino";
    public static final String RI_VENDOR = "ri_vendorname";
    public static final String RI_STOREKEEP = "ri_storekeeper";
    public static final String RI_ITEM_ID = "ri_itemid";
    public static final String RI_DI_QTY = "ri_diqty";
    public static final String RI_RECEV_QTY = "ri_recevqty";
    public static final String RI_DAMAGED_QTY = "ri_damagedqty";
    public static final String RI_ACCEPTED_QTY = "ri_acceptedqty";
    public static final String RI_ITEM_YES = "ri_itemyes";
    public static final String RI_ITEM_CANCEL = "ri_itemcancel";
    public static final String RI_REMARK = "ri_remark";
    public static final String RI_LAT = "ri_lat";
    public static final String RI_LONG = "ri_long";
    public static final String RI_TIME = "ri_time";
    public static final String RI_IMGNAME1 = "ri_imgname1";
    public static final String RI_IMGNAME2 = "ri_imgname2";
    public static final String RI_IMG1 = "ri_img1";
    public static final String RI_IMG2 = "ri_img2";
    public static final String RI_AUTO_NUMBER = "ri_auto_number";

    private static final String CREATE_RI_TABLE =
            "create table " + RI_TABLE + " ("
                    + RI_TABLE_ID + " integer primary key autoincrement, "
                    + RI_EMP_ID + " text, "
                    + RI_PROJECT + " text, "
                    + RI_DISTRICT_ID + " text, "
                    + RI_STORE_ID + " text, "
                    + RI_DI_NO + " text, "
                    + RI_VENDOR + " text, "
                    + RI_STOREKEEP + " text, "
                    + RI_ITEM_ID + " text, "
                    + RI_DI_QTY + " text, "
                    + RI_RECEV_QTY + " text, "
                    + RI_DAMAGED_QTY + " text, "
                    + RI_ACCEPTED_QTY + " text, "
                    + RI_ITEM_YES + " text, "
                    + RI_ITEM_CANCEL + " text, "
                    + RI_REMARK + " text, "
                    + RI_LAT + " text, "
                    + RI_LONG + " text, "
                    + RI_TIME + " text, "
                    + RI_IMGNAME1 + " text, "
                    + RI_IMGNAME2 + " text, "
                    + RI_IMG1 + " text, "
                    + RI_IMG2 + " text, "
                    + RI_AUTO_NUMBER + " text  ) ; ";



    //............ insert material receiving .............//
    public long insert_valueR(String content0,String content1,String content2,String content3,String content4,String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15,String content16,String content17,String content18,String content19,String content20,String content21,String content22){
        ContentValues contentValues = new ContentValues();
        contentValues.put(RI_EMP_ID, content0);
        contentValues.put(RI_PROJECT, content1);
        contentValues.put(RI_DISTRICT_ID, content2);
        contentValues.put(RI_STORE_ID, content3);
        contentValues.put(RI_DI_NO, content4);
        contentValues.put(RI_VENDOR, content5);
        contentValues.put(RI_STOREKEEP, content6);
        contentValues.put(RI_ITEM_ID, content7);
        contentValues.put(RI_DI_QTY, content8);
        contentValues.put(RI_RECEV_QTY, content9);
        contentValues.put(RI_DAMAGED_QTY, content10);
        contentValues.put(RI_ACCEPTED_QTY, content11);
        contentValues.put(RI_ITEM_YES, content12);
        contentValues.put(RI_ITEM_CANCEL, content13);
        contentValues.put(RI_REMARK, content14);
        contentValues.put(RI_LAT, content15);
        contentValues.put(RI_LONG, content16);
        contentValues.put(RI_TIME, content17);
        contentValues.put(RI_IMGNAME1, content18);
        contentValues.put(RI_IMGNAME2, content19);
        contentValues.put(RI_IMG1, content20);
        contentValues.put(RI_IMG2, content21);
        contentValues.put(RI_AUTO_NUMBER, content22);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(RI_TABLE, null, contentValues);
    }


    public void delete_value_byIDR(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(RI_TABLE, RI_TABLE_ID + "=" + id1, null);
    }

    public int countDataR(){

        String countQuery = "SELECT  * FROM " + RI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataR(){

        String countQuery = "SELECT  * FROM " + RI_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllR(){

        String countQuery = "SELECT  * FROM " + RI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //--------------for Store Audit---------------//
    public static final String STORE_TABLE = "STORE_TABLE";
    public static final String STORE_TABLE_ID="id";
    public static final String STORE_EMP_ID="emp_id";
    public static final String STORE_PROJECT = "store_project";
    public static final String STORE_AUDITDATE = "store_auditdate";
    public static final String STORE_STOREID = "store_storeid";
    public static final String STORE_PMANAME = "store_pmaname";
    public static final String STORE_STOREKEEPER = "store_storekeeper";
    public static final String STORE_BOOKQTY = "store_bookqty";
    public static final String STORE_PHYSICALQTY = "store_phsicalqty";
    public static final String STORE_REMARK = "store_remark";
    public static final String STORE_AUTO_NUMBER = "store_auto_number";

    private static final String CREATE_STORE_TABLE =
            "create table " + STORE_TABLE + " ("
                    + STORE_TABLE_ID + " integer primary key autoincrement, "
                    + STORE_EMP_ID + " text, "
                    + STORE_PROJECT + " text, "
                    + STORE_AUDITDATE + " text, "
                    + STORE_STOREID + " text, "
                    + STORE_PMANAME + " text, "
                    + STORE_STOREKEEPER + " text, "
                    + STORE_BOOKQTY + " text, "
                    + STORE_PHYSICALQTY + " text, "
                    + STORE_REMARK + " text, "
                    + STORE_AUTO_NUMBER + " text  ) ; ";

   //............ insert STORE AUDIT..............//
    public long insert_valueSTORE(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9){

        ContentValues contentValues = new ContentValues();
        contentValues.put(STORE_EMP_ID, content0);
        contentValues.put(STORE_PROJECT, content1);
        contentValues.put(STORE_AUDITDATE, content2);
        contentValues.put(STORE_STOREID, content3);
        contentValues.put(STORE_PMANAME, content4);
        contentValues.put(STORE_STOREKEEPER, content5);
        contentValues.put(STORE_BOOKQTY, content6);
        contentValues.put(STORE_PHYSICALQTY, content7);
        contentValues.put(STORE_REMARK, content8);
        contentValues.put(STORE_AUTO_NUMBER, content9);
       // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(STORE_TABLE, null, contentValues);
    }


    public void delete_value_byIDSTORE(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(STORE_TABLE, STORE_TABLE_ID + "=" + id1, null);
    }

    public int countDataSTORE(){

        String countQuery = "SELECT  * FROM " + STORE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataSTORE(){

        String countQuery = "SELECT  * FROM " + STORE_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        return cursor;
    }

    public Cursor queueAllSTORE(){

        String countQuery = "SELECT  * FROM " + STORE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //----------NATURE CALL MGMT-------------//
    public static final String CALL_TABLE = "CALL_TABLE";
    public static final String CALL_TABLE_ID="id";
    public static final String CALL_EMP_ID="emp_id";
    public static final String CALL_PROJECT = "call_project";
    public static final String CALL_DIST = "call_geographic";
    public static final String CALL_BLOCK = "call_subareaone";
    public static final String CALL_VILLAGE = "call_subareatwo";
    public static final String CALL_HABITA = "call_subareathree";
    public static final String CALL_NATURECALL = "call_nature";
    public static final String CALL_DETAILS = "call_details";
    public static final String CALL_AUTO_NUMBER = "call_auto_number";

    private static final String CREATE_CALL_TABLE =
            "create table " + CALL_TABLE + " ("
                    + CALL_TABLE_ID + " integer primary key autoincrement, "
                    + CALL_EMP_ID + " text, "
                    + CALL_PROJECT + " text, "
                    + CALL_DIST + " text, "
                    + CALL_BLOCK + " text, "
                    + CALL_VILLAGE + " text, "
                    + CALL_HABITA + " text, "
                    + CALL_NATURECALL + " text, "
                    + CALL_DETAILS + " text, "
                    + CALL_AUTO_NUMBER + " text  ) ; ";

    //............ insert CALL DETAILS..............//
    public long insert_valueCALL(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CALL_EMP_ID, content0);
        contentValues.put(CALL_PROJECT, content1);
        contentValues.put(CALL_DIST, content2);
        contentValues.put(CALL_BLOCK, content3);
        contentValues.put(CALL_VILLAGE, content4);
        contentValues.put(CALL_HABITA, content5);
        contentValues.put(CALL_NATURECALL, content6);
        contentValues.put(CALL_DETAILS, content7);
        contentValues.put(CALL_AUTO_NUMBER, content8);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(CALL_TABLE, null, contentValues);
    }


    public void delete_value_byIDCALL(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(CALL_TABLE, CALL_TABLE_ID + "=" + id1, null);
    }

    public int countDataCALL(){

        String countQuery = "SELECT  * FROM " + CALL_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataCALL(){

        String countQuery = "SELECT  * FROM " + CALL_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        return cursor;
    }

    public Cursor queueAllCALL(){

        String countQuery = "SELECT  * FROM " + CALL_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }


    //..........Site Inspection table............................//
    private static final String CREATE_SI_TABLE =
            "create table " + SI_TABLE + " ("
                    + SI_TABLE_ID + " integer primary key autoincrement, "
                    + SI_EMP_ID + " text, "
                    + SI_PROJECT + " text, "
                    + SI_GEOGRAPHIC + " text, "
                    + SI_SUBAREAONE + " text, "
                    + SI_SUBAREATWO + " text, "
                    + SI_SUBAREATHREE + " text, "
                    + SI_ITEMID + " text, "
                    + SI_ITEM_YES + " text, "
                    + SI_ITEM_CANCEL + " text, "
                    + SI_REMARK + " text, "
                    + SI_LANDMARK + " text, "
                    + SI_CAMERA_LAT + " text, "
                    + SI_CAMERA_LONG + " text, "
                    + SI_CAMERA_TIME + " text, "
                    + SI_IMGNAME1 + " text, "
                    + SI_IMGNAME2 + " text, "
                    + SI_IMGNAME3 + " text, "
                    + SI_IMG1 + " text, "
                    + SI_IMG2 + " text, "
                    + SI_IMG3 + " text, "
                    + SI_STATUS + " text, "
                    + SI_AUTO_NUMBER + " text) ; ";

    //............ insert SI..............//
    public long insert_value(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16, String content17, String content18,String content19,String content20,String content21){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SI_EMP_ID, content0);
        contentValues.put(SI_PROJECT, content1);
        contentValues.put(SI_GEOGRAPHIC, content2);
        contentValues.put(SI_SUBAREAONE, content3);
        contentValues.put(SI_SUBAREATWO, content4);
        contentValues.put(SI_SUBAREATHREE, content5);
        contentValues.put(SI_ITEMID, content6);
        contentValues.put(SI_ITEM_YES, content7);
        contentValues.put(SI_ITEM_CANCEL, content8);
        contentValues.put(SI_REMARK, content9);
        contentValues.put(SI_LANDMARK, content10);
        contentValues.put(SI_CAMERA_LAT, content11);
        contentValues.put(SI_CAMERA_LONG, content12);
        contentValues.put(SI_CAMERA_TIME, content13);
        contentValues.put(SI_IMGNAME1, content14);
        contentValues.put(SI_IMGNAME2, content15);
        contentValues.put(SI_IMGNAME3, content16);
        contentValues.put(SI_IMG1, content17);
        contentValues.put(SI_IMG2, content18);
        contentValues.put(SI_IMG3, content19);
        contentValues.put(SI_STATUS, content20);
        contentValues.put(SI_AUTO_NUMBER, content21);

        Log.e("content value sor", "" + contentValues);
        return sqLiteDatabase.insert(SI_TABLE, null, contentValues);
    }

    public void delete_value_byID(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id1, null);
    }

    public int countData(){

        String countQuery = "SELECT  * FROM " + SI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdata(){

        String countQuery = "SELECT  * FROM " + SI_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAll(){

        String countQuery = "SELECT  * FROM " + SI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //-----safety inspection----------------//
   private static final String CREATE_SA_TABLE =
            "create table " + SA_TABLE + " ("
                    + SA_TABLE_ID + " integer primary key autoincrement, "
                    + SA_EMP_ID + " text, "
                    + SA_PROJECT + " text, "
                    + SA_GEOGRAPHIC + " text, "
                    + SA_SUBAREAONE + " text, "
                    + SA_SUBAREATWO + " text, "
                    + SA_SUBAREATHREE + " text, "
                    + SA_ITEMID + " text, "
                    + SA_ITEM_YES + " text, "
                    + SA_ITEM_CANCEL + " text, "
                    + SA_REMARK + " text, "
                    + SA_CAMERA_LAT + " text, "
                    + SA_CAMERA_LONG + " text, "
                    + SA_CAMERA_TIME + " text, "
                    + SA_IMGNAME1 + " text, "
                    + SA_IMGNAME2 + " text, "
                    + SA_IMGNAME3 + " text, "
                    + SA_IMG1 + " text, "
                    + SA_IMG2 + " text, "
                    + SA_IMG3 + " text, "
                    + SA_AUTO_NUMBER + " text  ) ; ";

    //............ insert SAFETY..............//
    public long insert_valueSA(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16,String content17,String content18,String content19){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SA_EMP_ID, content0);
        contentValues.put(SA_PROJECT, content1);
        contentValues.put(SA_GEOGRAPHIC, content2);
        contentValues.put(SA_SUBAREAONE, content3);
        contentValues.put(SA_SUBAREATWO, content4);
        contentValues.put(SA_SUBAREATHREE, content5);
        contentValues.put(SA_ITEMID, content6);
        contentValues.put(SA_ITEM_YES, content7);
        contentValues.put(SA_ITEM_CANCEL, content8);
        contentValues.put(SA_REMARK, content9);
        contentValues.put(SA_CAMERA_LAT, content10);
        contentValues.put(SA_CAMERA_LONG, content11);
        contentValues.put(SA_CAMERA_TIME, content12);
        contentValues.put(SA_IMGNAME1, content13);
        contentValues.put(SA_IMGNAME2, content14);
        contentValues.put(SA_IMGNAME3, content15);
        contentValues.put(SA_IMG1, content16);
        contentValues.put(SA_IMG2, content17);
        contentValues.put(SA_IMG3, content18);
        contentValues.put(SA_AUTO_NUMBER, content19);
        Log.e("content value safety", "" + contentValues);
        return sqLiteDatabase.insert(SA_TABLE, null, contentValues);
    }

    public void delete_value_byIDSA(int id){
        //sqLiteDatabase.delete(S_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(SA_TABLE, SA_TABLE_ID + "=" + id1, null);
    }

    public int countDataSA(){
        String countQuery = "SELECT  * FROM " + SA_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataSA(){

        String countQuery = "SELECT  * FROM " + SA_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllSA(){

        String countQuery = "SELECT  * FROM " + SA_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //.......... Ring define table............................//
    private static final String CREATE_WI_TABLE =
            "create table " + WI_TABLE + " ("
                    + WI_TABLE_ID + " integer primary key autoincrement, "
                    + WI_EMP_ID + " text, "
                    + WI_PROJECT + " text, "
                    + WI_GEOGRAPHIC + " text, "
                    + WI_SUBAREAONE + " text, "
                    + WI_SUBAREATWO + " text, "
                    + WI_SUBAREATHREE + " text, "
                    + WI_ITEMQTY + " text, "
                    + WI_REMARK + " text, "
                    + WI_CAMERA_LAT + " text, "
                    + WI_CAMERA_LONG + " text, "
                    + WI_CAMERA_TIME + " text, "
                    + WI_IMGNAME1 + " text, "
                    + WI_IMG1 + " text, "
                    + WI_AUTO_NUMBER + " text  ) ; ";


    //............ insert WORK PROGRESS..............//
    public long insert_valueWI(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13){

        ContentValues contentValues = new ContentValues();
        contentValues.put(WI_EMP_ID, content0);
        contentValues.put(WI_PROJECT, content1);
        contentValues.put(WI_GEOGRAPHIC, content2);
        contentValues.put(WI_SUBAREAONE, content3);
        contentValues.put(WI_SUBAREATWO, content4);
        contentValues.put(WI_SUBAREATHREE, content5);
        contentValues.put(WI_ITEMQTY, content6);
        contentValues.put(WI_REMARK, content7);
        contentValues.put(WI_CAMERA_LAT, content8);
        contentValues.put(WI_CAMERA_LONG, content9);
        contentValues.put(WI_CAMERA_TIME, content10);
        contentValues.put(WI_IMGNAME1, content11);
        contentValues.put(WI_IMG1, content12);
        contentValues.put(WI_AUTO_NUMBER, content13);

        Log.e("content value WORK", "" + contentValues);
        return sqLiteDatabase.insert(WI_TABLE, null, contentValues);
    }

    public void delete_value_byIDWI(int id){
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(WI_TABLE, WI_TABLE_ID + "=" + id1, null);
    }

    public int countDataWI(){
        String countQuery = "SELECT  * FROM " + WI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataWI(){
        String countQuery = "SELECT  * FROM " + WI_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();
        return cursor;
    }

    public Cursor queueAllWI(){

        String countQuery = "SELECT  * FROM " + WI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //-------For factory Inspection--------------//
    public static final String FI_TABLE = "FI_TABLE";
    public static final String FI_TABLE_ID="id";
    public static final String FI_EMP_ID="femp_id";
    public static final String FI_PROJECT = "fi_project";
    public static final String FI_INSP_DATE = "fi_inspdate";
    public static final String FI_PO_NO = "fi_po_no";
    public static final String FI_DI_NO = "fi_di_no";
    public static final String FI_FACT_LOC= "fi_factloc";
    public static final String FI_VENDOR_RSP = "fi_vendorrsp";
    public static final String FI_FACT_RSP="fi_factrsp";
    public static final String FI_ITEM_QTY = "fi_itemqty";
    public static final String FI_REAMRK = "fi_remark";
    public static final String FI_CLAT = "fi_clat";
    public static final String FI_CLONG = "fi_clong";
    public static final String FI_CTIME = "fi_ctime";
    public static final String FI_IMGNAME1 = "fi_imgname1";
    public static final String FI_IMGNAME2 = "fi_imgname2";
    public static final String FI_IMG1 = "fi_img1";
    public static final String FI_IMG2 = "fi_img2";
    public static final String FI_AUTO_NUMBER = "fi_auto_number";

    private static final String CREATE_FI_TABLE =
            "create table " + FI_TABLE + " ("
                    + FI_TABLE_ID + " integer primary key autoincrement, "
                    + FI_EMP_ID + " text, "
                    + FI_PROJECT + " text, "
                    + FI_INSP_DATE + " text, "
                    + FI_PO_NO + " text, "
                    + FI_DI_NO + " text, "
                    + FI_FACT_LOC + " text, "
                    + FI_VENDOR_RSP + " text, "
                    + FI_FACT_RSP + " text, "
                    + FI_ITEM_QTY + " text, "
                    + FI_REAMRK + " text, "
                    + FI_CLAT + " text, "
                    + FI_CLONG + " text, "
                    + FI_CTIME + " text, "
                    + FI_IMGNAME1 + " text, "
                    + FI_IMGNAME2 + " text, "
                    + FI_IMG1 + " text, "
                    + FI_IMG2 + " text, "
                    + FI_AUTO_NUMBER + " text  ) ; ";

    //............ insert FACTORY INSP..............//
    public long insert_valueFACT(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16, String content17){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FI_EMP_ID, content0);
        contentValues.put(FI_PROJECT, content1);
        contentValues.put(FI_INSP_DATE, content2);
        contentValues.put(FI_PO_NO, content3);
        contentValues.put(FI_DI_NO, content4);
        contentValues.put(FI_FACT_LOC, content5);
        contentValues.put(FI_VENDOR_RSP, content6);
        contentValues.put(FI_FACT_RSP, content7);
        contentValues.put(FI_ITEM_QTY, content8);
        contentValues.put(FI_REAMRK, content9);
        contentValues.put(FI_CLAT, content10);
        contentValues.put(FI_CLONG, content11);
        contentValues.put(FI_CTIME, content12);
        contentValues.put(FI_IMGNAME1, content13);
        contentValues.put(FI_IMGNAME2, content14);
        contentValues.put(FI_IMG1, content15);
        contentValues.put(FI_IMG2, content16);
        contentValues.put(FI_AUTO_NUMBER, content17);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(FI_TABLE, null, contentValues);
    }


    public void delete_value_byIDFACT(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(FI_TABLE, FI_TABLE_ID + "=" + id1, null);
    }

    public int countDataFACT(){

        String countQuery = "SELECT  * FROM " + FI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataFACT(){

        String countQuery = "SELECT  * FROM " + FI_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllFACT(){

        String countQuery = "SELECT  * FROM " + FI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }


   //--------------FOR JMC ----------------//
    public static final String JMC_TABLE = "JMC_TABLE";
    public static final String JMC_TABLE_ID="id";
    public static final String JMC_EMP_ID="jmcemp_id";
    public static final String JMC_PROJECT = "jmc_project";
    public static final String JMC_GEOGRAPHIC = "jmc_geographic";
    public static final String JMC_SUBAREAONE = "jmc_subareaone";
    public static final String JMC_SUBAREATWO = "jmc_subareatwo";
    public static final String JMC_SUBAREATHREE = "jmc_subareathree";
    public static final String JMC_TKC = "jmc_tkc";
    public static final String JMC_CHECKLIST_INPUT = "jmc_checklist_input";
    public static final String JMC_REMARK = "jmc_remark";
    public static final String JMC_CLAT = "jmc_clat";
    public static final String JMC_CLONG = "jmc_clong";
    public static final String JMC_CTIME = "jmc_ctime";
    public static final String JMC_IMGNAME1 = "jmc_imgname1";
    public static final String JMC_IMGNAME2 = "jmc_imgname2";
    public static final String JMC_IMG1 = "jmc_img1";
    public static final String JMC_IMG2 = "jmc_img2";
    public static final String JMC_AUTO_NUMBER = "jmc_auto_number";


    private static final String CREATE_JMC_TABLE =
            "create table " + JMC_TABLE + " ("
                    + JMC_TABLE_ID + " integer primary key autoincrement, "
                    + JMC_EMP_ID + " text, "
                    + JMC_PROJECT + " text, "
                    + JMC_GEOGRAPHIC + " text, "
                    + JMC_SUBAREAONE + " text, "
                    + JMC_SUBAREATWO + " text, "
                    + JMC_SUBAREATHREE + " text, "
                    + JMC_TKC + " text, "
                    + JMC_CHECKLIST_INPUT + " text, "
                    + JMC_REMARK + " text, "
                    + JMC_CLAT + " text, "
                    + JMC_CLONG + " text, "
                    + JMC_CTIME + " text, "
                    + JMC_IMGNAME1 + " text, "
                    + JMC_IMGNAME2 + " text, "
                    + JMC_IMG1 + " text, "
                    + JMC_IMG2 + " text, "
                    + JMC_AUTO_NUMBER + " text  ) ; ";

    //............ insert JMC............../
    public long insert_valueJMC(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JMC_EMP_ID, content0);
        contentValues.put(JMC_PROJECT, content1);
        contentValues.put(JMC_GEOGRAPHIC, content2);
        contentValues.put(JMC_SUBAREAONE, content3);
        contentValues.put(JMC_SUBAREATWO, content4);
        contentValues.put(JMC_SUBAREATHREE, content5);
        contentValues.put(JMC_TKC, content6);
        contentValues.put(JMC_CHECKLIST_INPUT, content7);
        contentValues.put(JMC_REMARK, content8);
        contentValues.put(JMC_CLAT, content9);
        contentValues.put(JMC_CLONG, content10);
        contentValues.put(JMC_CTIME, content11);
        contentValues.put(JMC_IMGNAME1, content12);
        contentValues.put(JMC_IMGNAME2, content13);
        contentValues.put(JMC_IMG1, content14);
        contentValues.put(JMC_IMG2, content15);
        contentValues.put(JMC_AUTO_NUMBER, content16);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(JMC_TABLE, null, contentValues);
    }


    public void delete_value_byIDJMC(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(JMC_TABLE, JMC_TABLE_ID + "=" + id1, null);
    }

    public int countDataJMC(){

        String countQuery = "SELECT  * FROM " + JMC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataJMC(){

        String countQuery = "SELECT  * FROM " + JMC_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllJMC(){
        String countQuery = "SELECT  * FROM " + JMC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //--------------FOR Issue----------------//
    public static final String ISSUE_TABLE = "ISSUE_TABLE";
    public static final String ISSUE_TABLE_ID="id";
    public static final String ISSUE_EMP_ID="issueemp_id";
    public static final String ISSUE_PROJECT = "issue_project";
    public static final String ISSUE_GEOGRAPHIC = "issue_geographic";
    public static final String ISSUE_SUBAREAONE = "issue_subareaone";
    public static final String ISSUE_SUBAREATWO = "issue_subareatwo";
    public static final String ISSUE_SUBAREATHREE = "issue_subareathree";
    public static final String ISSUE_TYPE = "issue_type";
    public static final String ISSUE_OTHER = "issue_other";
    public static final String ISSUE_REMARK = "issue_remark";
    public static final String ISSUE_CLAT = "issue_clat";
    public static final String ISSUE_CLONG = "issue_clong";
    public static final String ISSUE_CTIME = "issue_ctime";
    public static final String ISSUE_IMGNAME1 = "issue_imgname1";
    public static final String ISSUE_IMGNAME2 = "issue_imgname2";
    public static final String ISSUE_IMG1 = "issue_img1";
    public static final String ISSUE_IMG2 = "issue_img2";
    public static final String ISSUE_AUTO_NUMBER = "issue_auto_number";

    private static final String CREATE_ISSUE_TABLE =
            "create table " + ISSUE_TABLE + " ("
                    + ISSUE_TABLE_ID + " integer primary key autoincrement, "
                    + ISSUE_EMP_ID + " text, "
                    + ISSUE_PROJECT + " text, "
                    + ISSUE_GEOGRAPHIC + " text, "
                    + ISSUE_SUBAREAONE + " text, "
                    + ISSUE_SUBAREATWO + " text, "
                    + ISSUE_SUBAREATHREE + " text, "
                    + ISSUE_TYPE + " text, "
                    + ISSUE_OTHER + " text, "
                    + ISSUE_REMARK + " text, "
                    + ISSUE_CLAT + " text, "
                    + ISSUE_CLONG + " text, "
                    + ISSUE_CTIME + " text, "
                    + ISSUE_IMGNAME1 + " text, "
                    + ISSUE_IMGNAME2 + " text, "
                    + ISSUE_IMG1 + " text, "
                    + ISSUE_IMG2 + " text, "
                    + ISSUE_AUTO_NUMBER + " text  ) ; ";


    //............ insert ISSUE............../
    public long insert_valueISSUE(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13,String content14,String content15,String content16){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ISSUE_EMP_ID, content0);
        contentValues.put(ISSUE_PROJECT, content1);
        contentValues.put(ISSUE_GEOGRAPHIC, content2);
        contentValues.put(ISSUE_SUBAREAONE, content3);
        contentValues.put(ISSUE_SUBAREATWO, content4);
        contentValues.put(ISSUE_SUBAREATHREE, content5);
        contentValues.put(ISSUE_TYPE, content6);
        contentValues.put(ISSUE_OTHER, content7);
        contentValues.put(ISSUE_REMARK, content8);
        contentValues.put(ISSUE_CLAT, content9);
        contentValues.put(ISSUE_CLONG, content10);
        contentValues.put(ISSUE_CTIME, content11);
        contentValues.put(ISSUE_IMGNAME1, content12);
        contentValues.put(ISSUE_IMGNAME2, content13);
        contentValues.put(ISSUE_IMG1, content14);
        contentValues.put(ISSUE_IMG2, content15);
        contentValues.put(ISSUE_AUTO_NUMBER, content16);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ISSUE_TABLE, null, contentValues);
    }


    public void delete_value_byIDISSUE(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(ISSUE_TABLE, ISSUE_TABLE_ID + "=" + id1, null);
    }

    public int countDataISSUE(){

        String countQuery = "SELECT  * FROM " + ISSUE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataISSUE(){

        String countQuery = "SELECT  * FROM " + ISSUE_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllISSUE(){

        String countQuery = "SELECT  * FROM " + ISSUE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }



    //----------For Daily Work Progress------------//
    public static final String DPR_TABLE = "DPR_TABLE";
    public static final String DPR_TABLE_ID="id";
    public static final String DPR_EMP_ID="emp_id";
    public static final String DPR_PROJECT = "dpr_project";
    public static final String DPR_GEOGRAPHIC = "dpr_geographic";
    public static final String DPR_SUBAREAONE = "dpr_subareaone";
    public static final String DPR_SUBAREATWO = "dpr_subareatwo";
    public static final String DPR_SUBAREATHREE = "dpr_subareathree";
    public static final String DPR_INPUTVALUE = "dpr_inputvalue";
    public static final String DPR_REMARK = "dpr_remark";
    public static final String DPR_CLAT = "dpr_clat";
    public static final String DPR_CLONG = "dpr_clong";
    public static final String DPR_CTIME = "dpr_ctime";
    public static final String DPR_IMGNAME1 = "dpr_imgname1";
    public static final String DPR_IMGNAME2 = "dpr_imgname2";
    public static final String DPR_IMG1 = "dpr_img1";
    public static final String DPR_IMG2 = "dpr_img2";
    public static final String DPR_AUTO_NUMBER = "dpr_auto_number";

    private static final String CREATE_DPR_TABLE =
            "create table " + DPR_TABLE + " ("
                    + DPR_TABLE_ID + " integer primary key autoincrement, "
                    + DPR_EMP_ID + " text, "
                    + DPR_PROJECT + " text, "
                    + DPR_GEOGRAPHIC + " text, "
                    + DPR_SUBAREAONE + " text, "
                    + DPR_SUBAREATWO + " text, "
                    + DPR_SUBAREATHREE + " text, "
                    + DPR_INPUTVALUE + " text, "
                    + DPR_REMARK + " text, "
                    + DPR_CLAT + " text, "
                    + DPR_CLONG + " text, "
                    + DPR_CTIME + " text, "
                    + DPR_IMGNAME1 + " text, "
                    + DPR_IMGNAME2 + " text, "
                    + DPR_IMG1 + " text, "
                    + DPR_IMG2 + " text, "
                    + DPR_AUTO_NUMBER + " text  ) ; ";


    //............ insert DPR............../
    public long insert_valueDPR(String content0,String content1,String content2,String content3,String content4,String content5,String content6,String content7,String content8, String content9, String content10,String content11,String content12,String content13,String content14,String content15){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DPR_EMP_ID, content0);
        contentValues.put(DPR_PROJECT, content1);
        contentValues.put(DPR_GEOGRAPHIC, content2);
        contentValues.put(DPR_SUBAREAONE, content3);
        contentValues.put(DPR_SUBAREATWO, content4);
        contentValues.put(DPR_SUBAREATHREE, content5);
        contentValues.put(DPR_INPUTVALUE, content6);
        contentValues.put(DPR_REMARK, content7);
        contentValues.put(DPR_CLAT, content8);
        contentValues.put(DPR_CLONG, content9);
        contentValues.put(DPR_CTIME, content10);
        contentValues.put(DPR_IMGNAME1, content11);
        contentValues.put(DPR_IMGNAME2, content12);
        contentValues.put(DPR_IMG1, content13);
        contentValues.put(DPR_IMG2, content14);
        contentValues.put(DPR_AUTO_NUMBER, content15);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(DPR_TABLE, null, contentValues);
    }


    public void delete_value_byIDDPR(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(DPR_TABLE, DPR_TABLE_ID + "=" + id1, null);
    }

    public int countDataDPR(){
        String countQuery = "SELECT  * FROM " + DPR_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataDPR(){
        String countQuery = "SELECT  * FROM " + DPR_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllDPR(){
        String countQuery = "SELECT  * FROM " + DPR_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }


    //=======Material issue to tkc--------------------//
    public static final String MITKC_TABLE = "MITKC_TABLE";
    public static final String MITKC_TABLE_ID="id";
    public static final String MITKC_EMP_ID="emp_id";
    public static final String MITKC_PROJECT = "mitkc_project";
    public static final String MITKC_GEOGRAPHIC = "mitkc_geographic";
    public static final String MITKC_SUBAREAONE = "mitkc_subareaone";
    public static final String MITKC_TKC = "mitkc_subareatwo";
    public static final String MITKC_ISP = "mitkc_subareathree";
    public static final String MITKC_STOREKEEPER= "mitkc_inputvalue";
    public static final String MITKC_INPUTVALUE= "mitkc_inputvalue";
    public static final String MITKC_REMARK = "mitkc_remark";
    public static final String MITKC_CLAT = "mitkc_clat";
    public static final String MITKC_CLONG = "mitkc_clong";
    public static final String MITKC_CTIME = "mitkc_ctime";
    public static final String MITKC_IMGNAME1 = "mitkc_imgname1";
    public static final String MITKC_IMGNAME2 = "mitkc_imgname2";
    public static final String MITKC_IMG1 = "mitkc_img1";
    public static final String MITKC_IMG2 = "mitkc_img2";
    public static final String MITKC_AUTO_NUMBER = "mitkc_auto_number";

    private static final String CREATE_MITKC_TABLE =
            "create table " + MITKC_TABLE + " ("
                    + MITKC_TABLE_ID + " integer primary key autoincrement, "
                    + MITKC_EMP_ID + " text, "
                    + MITKC_PROJECT + " text, "
                    + MITKC_GEOGRAPHIC + " text, "
                    + MITKC_SUBAREAONE + " text, "
                    + MITKC_TKC + " text, "
                    + MITKC_ISP + " text, "
                    + MITKC_STOREKEEPER + " text, "
                    + MITKC_INPUTVALUE + " text, "
                    + MITKC_REMARK + " text, "
                    + MITKC_CLAT + " text, "
                    + MITKC_CLONG + " text, "
                    + MITKC_CTIME + " text, "
                    + MITKC_IMGNAME1 + " text, "
                    + MITKC_IMGNAME2 + " text, "
                    + MITKC_IMG1 + " text, "
                    + MITKC_IMG2 + " text, "
                    + MITKC_AUTO_NUMBER + " text  ) ; ";


    //............ insert Material Issue to contractor............../
    public long insert_valueMITKC(String content0,String content1,String content2,String content3,String content4,String content5,String content6,String content7,String content8, String content9, String content10,String content11,String content12,String content13,String content14,String content15,String content16){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MITKC_EMP_ID, content0);
        contentValues.put(MITKC_PROJECT, content1);
        contentValues.put(MITKC_GEOGRAPHIC, content2);
        contentValues.put(MITKC_SUBAREAONE, content3);
        contentValues.put(MITKC_TKC, content4);
        contentValues.put(MITKC_ISP, content5);
        contentValues.put(MITKC_STOREKEEPER, content6);
        contentValues.put(MITKC_INPUTVALUE, content7);
        contentValues.put(MITKC_REMARK, content8);
        contentValues.put(MITKC_CLAT, content9);
        contentValues.put(MITKC_CLONG, content10);
        contentValues.put(MITKC_CTIME, content11);
        contentValues.put(MITKC_IMGNAME1, content12);
        contentValues.put(MITKC_IMGNAME2, content13);
        contentValues.put(MITKC_IMG1, content14);
        contentValues.put(MITKC_IMG2, content15);
        contentValues.put(MITKC_AUTO_NUMBER, content16);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(MITKC_TABLE, null, contentValues);
    }


    public void delete_value_byIDMITKC(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(MITKC_TABLE, MITKC_TABLE_ID + "=" + id1, null);
    }

    public int countDataMITKC(){
        String countQuery = "SELECT  * FROM " + MITKC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataMITKC(){
        String countQuery = "SELECT  * FROM " + MITKC_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAllMITKC(){
        String countQuery = "SELECT  * FROM " + MITKC_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }




    //-----for manpower Loading---------------//
    public static final String LOADING_TABLE = "LOADING_TABLE";
    public static final String LOADING_TABLE_ID="id";
    public static final String LOADING_EMP_ID="emp_id";
    public static final String LOADING_PROJECT = "loading_project";
    public static final String LOADING_GEOGRAPHIC = "loading_geographic";
    public static final String LOADING_SUBAREAONE = "loading_subareaone";
    public static final String LOADING_SUBAREATWO = "loading_subareatwo";
    public static final String LOADING_SUBAREATHREE = "loading_subareathree";
    public static final String LOADING_HHSC = "loading_hhsc";
    public static final String LOADING_NETWORK = "loading_network";
    public static final String LOADING_AUTO_NUMBER = "loading_auto_number";

    private static final String CREATE_LOADING_TABLE =
            "create table " + LOADING_TABLE + " ("
                    + LOADING_TABLE_ID + " integer primary key autoincrement, "
                    + LOADING_EMP_ID + " text, "
                    + LOADING_PROJECT + " text, "
                    + LOADING_GEOGRAPHIC + " text, "
                    + LOADING_SUBAREAONE + " text, "
                    + LOADING_SUBAREATWO + " text, "
                    + LOADING_SUBAREATHREE + " text, "
                    + LOADING_HHSC + " text, "
                    + LOADING_NETWORK + " text, "
                    + LOADING_AUTO_NUMBER + " text  ) ; ";

    //............ insert MANPOWER lOADING..............//
    public long insert_valueLOADING(String content0, String content1, String content2, String content3,String content4,String content5,String content6,String content7,String content8){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOADING_EMP_ID, content0);
        contentValues.put(LOADING_PROJECT, content1);
        contentValues.put(LOADING_GEOGRAPHIC, content2);
        contentValues.put(LOADING_SUBAREAONE, content3);
        contentValues.put(LOADING_SUBAREATWO, content4);
        contentValues.put(LOADING_SUBAREATHREE, content5);
        contentValues.put(LOADING_HHSC, content6);
        contentValues.put(LOADING_NETWORK, content7);
        contentValues.put(LOADING_AUTO_NUMBER, content8);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(LOADING_TABLE, null, contentValues);
    }


    public void delete_value_byIDLOADING(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(LOADING_TABLE, LOADING_TABLE_ID + "=" + id1, null);
    }

    public int countDataLOADING(){

        String countQuery = "SELECT  * FROM " + LOADING_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
       //	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataLOADING(){

        String countQuery = "SELECT  * FROM " + LOADING_TABLE+ " limit 0 , 10";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
     //	    int cnt = cursor.getCount();
    //	    cursor.close();

        return cursor;
    }

    public Cursor queueAllLOADING(){

        String countQuery = "SELECT  * FROM " + LOADING_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }

    //-------for POLe Receiving--------------------------------//

    public static final String POLE_TABLE = "POLE_TABLE";
    public static final String POLE_TABLE_ID="id";
    public static final String POLE_EMP_ID="pemp_id";
    public static final String POLE_PROJECT = "pole_project";
    public static final String POLE_GEOGRAPHIC = "pole_geographic";
    public static final String POLE_SUBAREAONE = "pole_subareaone";
    public static final String POLE_SUBAREATWO = "pole_subareatwo";
    public static final String POLE_SUBAREATHREE = "pole_subareathree";
    public static final String POLE_VENDORNAME= "pole_vendorname";
    public static final String POLE_lANDMARK= "pole_landmark";
    public static final String POLE_ITEMID= "pole_itemid";
    public static final String POLE_PONO= "pole_pono";
    public static final String POLE_DINO= "pole_dino";
    public static final String POLE_DIQTY= "pole_diqty";
    public static final String POLE_RECEIVEDQTY= "pole_receivedqty";
    public static final String POLE_REMARK= "pole_remark";
    public static final String POLE_CAMERA_LAT = "pole_camera_lat";
    public static final String POLE_CAMERA_LONG = "pole_camera_long";
    public static final String POLE_CAMERA_TIME = "pole_camera_time";
    public static final String POLE_IMGNAME1 = "pole_imgname1";
    public static final String POLE_IMGNAME2 = "pole_imgname2";
    public static final String POLE_IMGNAME3 = "pole_imgname3";
    public static final String POLE_IMGNAME4 = "pole_imgname4";
    public static final String POLE_IMGNAME5 = "pole_imgname5";
    public static final String POLE_IMG1 = "pole_img1";
    public static final String POLE_IMG2 = "pole_img2";
    public static final String POLE_IMG3 = "pole_img3";
    public static final String POLE_IMG4 = "pole_img4";
    public static final String POLE_IMG5 = "pole_img5";
    public static final String POLE_AUTO_NUMBER = "pole_auto_number";


    private static final String CREATE_POLE_TABLE =
            "create table " + POLE_TABLE + " ("
                    + POLE_TABLE_ID + " integer primary key autoincrement, "
                    + POLE_EMP_ID + " text, "
                    + POLE_PROJECT + " text, "
                    + POLE_GEOGRAPHIC + " text, "
                    + POLE_SUBAREAONE + " text, "
                    + POLE_SUBAREATWO + " text, "
                    + POLE_SUBAREATHREE + " text, "
                    + POLE_VENDORNAME + " text, "
                    + POLE_lANDMARK + " text, "
                    + POLE_ITEMID + " text, "
                    + POLE_PONO + " text, "
                    + POLE_DINO + " text, "
                    + POLE_DIQTY + " text, "
                    + POLE_RECEIVEDQTY + " text, "
                    + POLE_REMARK + " text, "
                    + POLE_CAMERA_LAT + " text, "
                    + POLE_CAMERA_LONG + " text, "
                    + POLE_CAMERA_TIME + " text, "
                    + POLE_IMGNAME1 + " text, "
                    + POLE_IMGNAME2 + " text, "
                    + POLE_IMGNAME3 + " text, "
                    + POLE_IMGNAME4 + " text, "
                    + POLE_IMGNAME5 + " text, "
                    + POLE_IMG1 + " text, "
                    + POLE_IMG2 + " text, "
                    + POLE_IMG3 + " text, "
                    + POLE_IMG4 + " text, "
                    + POLE_IMG5 + " text, "
                    + POLE_AUTO_NUMBER + " text  ) ; ";

    //............ insert POLE RECEIVING ..............//
    public long insert_valuePOLE(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11, String content12, String content13,String content14,String content15,String content16,String content17,String content18,String content19,String content20,String content21,String content22,String content23,String content24,String content25,String content26,String content27){
        ContentValues contentValues = new ContentValues();
        contentValues.put(POLE_EMP_ID, content0);
        contentValues.put(POLE_PROJECT, content1);
        contentValues.put(POLE_GEOGRAPHIC, content2);
        contentValues.put(POLE_SUBAREAONE, content3);
        contentValues.put(POLE_SUBAREATWO, content4);
        contentValues.put(POLE_SUBAREATHREE, content5);
        contentValues.put(POLE_VENDORNAME, content6);
        contentValues.put(POLE_lANDMARK, content7);
        contentValues.put(POLE_ITEMID, content8);
        contentValues.put(POLE_PONO, content9);
        contentValues.put(POLE_DINO, content10);
        contentValues.put(POLE_DIQTY, content11);
        contentValues.put(POLE_RECEIVEDQTY, content12);
        contentValues.put(POLE_REMARK, content13);
        contentValues.put(POLE_CAMERA_LAT, content14);
        contentValues.put(POLE_CAMERA_LONG, content15);
        contentValues.put(POLE_CAMERA_TIME, content16);
        contentValues.put(POLE_IMGNAME1, content17);
        contentValues.put(POLE_IMGNAME2, content18);
        contentValues.put(POLE_IMGNAME3, content19);
        contentValues.put(POLE_IMGNAME4, content20);
        contentValues.put(POLE_IMGNAME5, content21);
        contentValues.put(POLE_IMG1, content22);
        contentValues.put(POLE_IMG2, content23);
        contentValues.put(POLE_IMG3, content24);
        contentValues.put(POLE_IMG4, content25);
        contentValues.put(POLE_IMG5, content26);
        contentValues.put(POLE_AUTO_NUMBER, content27);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(POLE_TABLE, null, contentValues);
    }


    public void delete_value_byIDPOLE(int id){
        //sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
        String id1= Integer.toString(id);
        sqLiteDatabase.delete(POLE_TABLE, POLE_TABLE_ID + "=" + id1, null);
    }

    public int countDataPOLE(){

        String countQuery = "SELECT  * FROM " + POLE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdataPOLE(){

        String countQuery = "SELECT  * FROM " + POLE_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        return cursor;
    }

    public Cursor queueAllPOLE(){

        String countQuery = "SELECT  * FROM " + POLE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }




    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SQLiteAdapterWorkprogress(Context c)
    {
        context = c;

    }

    public SQLiteAdapterWorkprogress openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapterWorkprogress openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        sqLiteHelper.close();
    }

    //#########################################  select ####################################################//

    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL(CREATE_RI_TABLE);
            db.execSQL(CREATE_STORE_TABLE);
            db.execSQL(CREATE_LOADING_TABLE);
            db.execSQL(CREATE_SI_TABLE);
            db.execSQL(CREATE_CALL_TABLE);
            db.execSQL(CREATE_HHSC_TABLE);
            db.execSQL(CREATE_NETWORK_TABLE);
            db.execSQL(CREATE_JMC_TABLE);
            db.execSQL(CREATE_ISSUE_TABLE);
            db.execSQL(CREATE_SA_TABLE);
            db.execSQL(CREATE_POLE_TABLE);
            db.execSQL(CREATE_WI_TABLE);
            db.execSQL(CREATE_FI_TABLE);
            db.execSQL(CREATE_DPR_TABLE);
            db.execSQL(CREATE_MITKC_TABLE);
            }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_RI_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_STORE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_LOADING_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SI_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_CALL_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_HHSC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_NETWORK_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_JMC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ISSUE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SA_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_POLE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_FI_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_WI_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_DPR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MITKC_TABLE);
            onCreate(db);

        }
    }





}
