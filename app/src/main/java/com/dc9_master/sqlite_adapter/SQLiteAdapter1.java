package com.dc9_master.sqlite_adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nitinb on 09-02-2016.
 */
public class SQLiteAdapter1 {

    public static final String MYDATABASE_NAME ="EPMC";
    public static final int MYDATABASE_VERSION = 3;

    public static final String PROJECT_TABLE = "PROJECT_TABLE";
    public static final String PROJECT_TABLE_ID="id";
    public static final String PROJECT_ID="project_id";
    public static final String PROJECT_CODE = "project_code";
    public static final String PROJECT_NAME = "project_name";

    public static final String GEOGRAPHIC_TABLE = "GEOGRAPHIC_TABLE";
    public static final String GEOGRAPHIC_TABLE_ID="id";
    public static final String GEOGRAPHIC_PROJECT_CODE = "geographic_project_code";
    public static final String GEOGRAPHIC_CODE = "geographic_code";
    public static final String GEOGRAPHIC_NAME = "geographic_name";

    public static final String SUBAREAONE_TABLE = "SUBAREAONE_TABLE";
    public static final String SUBAREAONE_TABLE_ID="id";
    public static final String SUBAREAONE_GEOGRAPHIC_CODE="subareaone_geographic_code";
    public static final String SUBAREAONE_CODE = "subareaone_code";
    public static final String SUBAREAONE_NAME = "subareaone_name";

    public static final String SUBAREATWO_TABLE = "SUBAREATWO_TABLE";
    public static final String SUBAREATWO_TABLE_ID="id";
    public static final String SUBAREATWO_ONE_CODE="subareatwo_one_code";
    public static final String SUBAREATWO_CODE = "subareatwo_code";
    public static final String SUBAREATWO_NAME = "subareatwo_name";

    public static final String SUBAREATHREE_TABLE = "SUBAREATHREE_TABLE";
    public static final String SUBAREATHREE_TABLE_ID="id";
    public static final String SUBAREATHREE_DIST_CODE="subareathree_dist_code";
    public static final String SUBAREATHREE_DIST_NAME="subareathree_dist_name";
    public static final String SUBAREATHREE_ONE_CODE="subareathree_one_code";
    public static final String SUBAREATHREE_ONE_NAME="subareathree_one_name";
    public static final String SUBAREATHREE_TWO_CODE="subareathree_two_code";
    public static final String SUBAREATHREE_TWO_NAME="subareathree_two_name";
    public static final String SUBAREATHREE_CODE = "subareathree_code";
    public static final String SUBAREATHREE_NAME = "subareathree_name";

    public static final String MATERIAL_STORE_TABLE = "MATERIAL_STORE_TABLE";
    public static final String MATERIAL_STORE_TABLE_ID="id";
    public static final String MATERIAL_STORE_ID="material_store_id";
    public static final String MATERIAL_STORE_NAME = "material_store_name";
    public static final String MATERIAL_DISTRICT_CODE="material_district_code";

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String ITEM_TABLE_ID="id";
    public static final String ITEM_ID="item_id";
    public static final String ITEM_NAME = "item_name";

    public static final String ITEM_PARAMETER_TABLE = "ITEM_PARAMETER_TABLE";
    public static final String ITEM_PARAMETER_TABLE_ID="id";
    public static final String ITEM_PARAMETER_ID="parameter_id";
    public static final String ITEM_PARAMETER_NAME = "parameter_name";
    public static final String ITEM_PARAMETER_MAPPINGCODE = "item_mapping_code";

    public static final String SORITEM_TABLE = "SORITEM_TABLE";
    public static final String SORITEM_TABLE_ID="id";
    public static final String SORITEM_ID="soritem_id";
    public static final String SORITEM_NAME = "soritem_name";

    public static final String SORITEM_PARAMETER_TABLE = "SORITEM_PARAMETER_TABLE";
    public static final String SORITEM_PARAMETER_TABLE_ID="id";
    public static final String SORITEM_PARAMETER_ID="sorparameter_id";
    public static final String SORITEM_PARAMETER_NAME = "sorparameter_name";
    public static final String SORITEM_PARAMETER_MAPPINGCODE = "soritem_mapping_code";

    public static final String SAFITEM_TABLE = "SAFITEM_TABLE";
    public static final String SAFITEM_TABLE_ID="id";
    public static final String SAFITEM_ID="safitem_id";
    public static final String SAFITEM_NAME = "safitem_name";

    public static final String SAFITEM_PARAMETER_TABLE = "SAFITEM_PARAMETER_TABLE";
    public static final String SAFITEM_PARAMETER_TABLE_ID="id";
    public static final String SAFITEM_PARAMETER_ID="safparameter_id";
    public static final String SAFITEM_PARAMETER_NAME = "safparameter_name";
    public static final String SAFITEM_PARAMETER_MAPPINGCODE = "safitem_para_mapping_code";

    public static final String NATURE_TABLE = "NATURE_TABLE";
    public static final String NATURE_TABLE_ID="id";
    public static final String NATURE_ID="nature_id";
    public static final String NATURE_NAME = "nature_name";

    public static final String JMC_TABLE = "JMC_TABLE";
    public static final String JMC_TABLE_ID="id";
    public static final String JMC_ID="jmc_id";
    public static final String JMC_NAME = "jmc_name";

    public static final String ISSUE_TABLE = "ISSUE_TABLE";
    public static final String ISSUE_TABLE_ID="id";
    public static final String ISSUE_ID="issue_id";
    public static final String ISSUE_NAME = "issue_name";

    public static final String INFRA_TABLE = "INFRA_TABLE";
    public static final String INFRA_TABLE_ID="id";
    public static final String INFRA_NAME = "infra_name";


    //........ project table.......................//
    private static final String CREATE_PROJECT_TABLE =
            "create table " + PROJECT_TABLE + " ("
                    + PROJECT_TABLE_ID + " integer primary key autoincrement, "
                    + PROJECT_ID + " text, "
                    + PROJECT_CODE + " text, "
                    + PROJECT_NAME + " text  ) ; ";

    //.........geographic table.........................//
    private static final String CREATE_GEOGRAPHIC_TABLE =
            "create table " + GEOGRAPHIC_TABLE + " ("
                    + GEOGRAPHIC_TABLE_ID + " integer primary key autoincrement, "
                    + GEOGRAPHIC_PROJECT_CODE + " text, "
                    + GEOGRAPHIC_CODE + " text, "
                    + GEOGRAPHIC_NAME + " text  ) ; ";

    //.........subarea_one name.........................//
    private static final String CREATE_SUBAREAONE_TABLE =
            "create table " + SUBAREAONE_TABLE + " ("
                    + SUBAREAONE_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREAONE_GEOGRAPHIC_CODE + " text, "
                    + SUBAREAONE_CODE + " text, "
                    + SUBAREAONE_NAME + " text  ) ; ";

    //.........subarea_two .........................//
    private static final String CREATE_SUBAREATWO_TABLE =
            "create table " + SUBAREATWO_TABLE + " ("
                    + SUBAREATWO_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREATWO_ONE_CODE + " text, "
                    + SUBAREATWO_CODE + " text, "
                    + SUBAREATWO_NAME + " text  ) ; ";

    //.........subarea_three .........................//
    private static final String CREATE_SUBAREATHREE_TABLE =
            "create table " + SUBAREATHREE_TABLE + " ("
                    + SUBAREATHREE_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREATHREE_DIST_CODE + " text, "
                    + SUBAREATHREE_DIST_NAME + " text, "
                    + SUBAREATHREE_ONE_CODE + " text, "
                    + SUBAREATHREE_ONE_NAME + " text, "
                    + SUBAREATHREE_TWO_CODE + " text, "
                    + SUBAREATHREE_TWO_NAME + " text, "
                    + SUBAREATHREE_CODE + " text, "
                    + SUBAREATHREE_NAME + " text  ) ; ";

    //........ Material STORE  table.......................//
    private static final String CREATE_MATERIAL_STORE_TABLE =
            "create table " + MATERIAL_STORE_TABLE + " ("
                    + MATERIAL_STORE_TABLE_ID + " integer primary key autoincrement, "
                    + MATERIAL_STORE_ID + " text, "
                    + MATERIAL_STORE_NAME + " text, "
                    + MATERIAL_DISTRICT_CODE + " text  ) ; ";

    //........ ITEM table.......................//
    private static final String CREATE_ITEM_TABLE =
            "create table " + ITEM_TABLE + " ("
                    + ITEM_TABLE_ID + " integer primary key autoincrement, "
                    + ITEM_ID + " text, "
                    + ITEM_NAME + " text  ) ; ";

    //........ ITEM PARAMETER table.......................//
    private static final String CREATE_ITEM_PARAMETER_TABLE =
            "create table " + ITEM_PARAMETER_TABLE + " ("
                    + ITEM_PARAMETER_TABLE_ID + " integer primary key autoincrement, "
                    + ITEM_PARAMETER_ID + " text, "
                    + ITEM_PARAMETER_NAME + " text, "
                    + ITEM_PARAMETER_MAPPINGCODE + " text  ) ; ";

    //........ SOR ITEM table.......................//
    private static final String CREATE_SORITEM_TABLE =
            "create table " + SORITEM_TABLE + " ("
                    + SORITEM_TABLE_ID + " integer primary key autoincrement, "
                    + SORITEM_ID + " text, "
                    + SORITEM_NAME + " text  ) ; ";

    //........ SOR ITEM PARAMETER table.......................//
    private static final String CREATE_SORITEM_PARAMETER_TABLE =
            "create table " + SORITEM_PARAMETER_TABLE + " ("
                    + SORITEM_PARAMETER_TABLE_ID + " integer primary key autoincrement, "
                    + SORITEM_PARAMETER_ID + " text, "
                    + SORITEM_PARAMETER_NAME + " text, "
                    + SORITEM_PARAMETER_MAPPINGCODE + " text  ) ; ";

    //........ SAFETY ITEM table.......................//
    private static final String CREATE_SAFITEM_TABLE =
            "create table " + SAFITEM_TABLE + " ("
                    + SAFITEM_TABLE_ID + " integer primary key autoincrement, "
                    + SAFITEM_ID + " text, "
                    + SAFITEM_NAME + " text  ) ; ";

    //........ SAFETY ITEM PARAMETER table.......................//
    private static final String CREATE_SAFITEM_PARAMETER_TABLE =
            "create table " + SAFITEM_PARAMETER_TABLE + " ("
                    + SAFITEM_PARAMETER_TABLE_ID + " integer primary key autoincrement, "
                    + SAFITEM_PARAMETER_ID + " text, "
                    + SAFITEM_PARAMETER_NAME + " text, "
                    + SAFITEM_PARAMETER_MAPPINGCODE + " text  ) ; ";

    //........ Nature call table.......................//
    private static final String CREATE_NATURE_TABLE =
            "create table " + NATURE_TABLE + " ("
                    + NATURE_TABLE_ID + " integer primary key autoincrement, "
                    + NATURE_ID + " text, "
                    + NATURE_NAME + " text  ) ; ";

    //........ JMC table.......................//
    private static final String CREATE_JMC_TABLE =
            "create table " + JMC_TABLE + " ("
                    + JMC_TABLE_ID + " integer primary key autoincrement, "
                    + JMC_ID + " text, "
                    + JMC_NAME + " text  ) ; ";

    //........ ISSUE table.......................//
    private static final String CREATE_ISSUE_TABLE =
            "create table " + ISSUE_TABLE + " ("
                    + ISSUE_TABLE_ID + " integer primary key autoincrement, "
                    + ISSUE_ID + " text, "
                    + ISSUE_NAME + " text  ) ; ";

    private static final String CREATE_INFRA_TABLE =
            "create table " + INFRA_TABLE + " ("
                    + INFRA_TABLE_ID + " integer primary key autoincrement, "
                    + INFRA_NAME + " text  ) ; ";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SQLiteAdapter1(Context c)
    {
        context = c;

    }

    public SQLiteAdapter1 openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }
    public SQLiteAdapter1 openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        sqLiteHelper.close();
    }

    //#########################################  select ####################################################//
    public Cursor select_project_all(){

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+PROJECT_TABLE,null);
        // Log.e("Select district", ":" + cursor);

        return cursor;
    }


    public Cursor select_geographic_all(){
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+GEOGRAPHIC_TABLE+"  ORDER BY GEOGRAPHIC_NAME ASC",null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+SUBAREATHREE_TABLE+" GROUP BY SUBAREATHREE_DIST_NAME ORDER BY SUBAREATHREE_DIST_NAME ASC",null);
        return cursor;
    }

    public Cursor select_geographic_id_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT  * from "+GEOGRAPHIC_TABLE+" where geographic_project_code = "+"'"+id1+"'",null);
        return cursor;
    }


    public Cursor select_subarea_one_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+SUBAREAONE_TABLE+" where subareaone_geographic_code = "+"'"+id1+"' ORDER BY SUBAREAONE_NAME ASC",null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+SUBAREATHREE_TABLE+" where subareathree_dist_code = "+"'"+id1+"' GROUP BY SUBAREATHREE_ONE_NAME ORDER BY SUBAREATHREE_ONE_NAME ASC",null);
        return cursor;
    }

    public Cursor select_subarea_two_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
       // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATWO_TABLE+" where subareatwo_one_code = "+"'"+id1+"' ORDER BY SUBAREATWO_NAME ASC",null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATHREE_TABLE+" where subareathree_one_code = "+"'"+id1+"' GROUP BY SUBAREATHREE_TWO_NAME ORDER BY SUBAREATHREE_ONE_NAME ASC",null);

        return cursor;
    }


    public Cursor select_subarea_three_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
      //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATHREE_TABLE+" where subareathree_two_code = "+"'"+id1+"' ORDER BY SUBAREATHREE_NAME ASC",null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATHREE_TABLE+" where subareathree_two_code = "+"'"+id1+"' ORDER BY SUBAREATHREE_NAME ASC",null);
        return cursor;
    }


    public Cursor select_store_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MATERIAL_STORE_TABLE,null);
        return cursor;
    }

    public Cursor select_store_district_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+MATERIAL_STORE_TABLE+" where material_district_code = "+"'"+id1+"'",null);
       return cursor;
    }

    public Cursor select_item_all(){
         Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+ITEM_TABLE,null);
        return cursor;
    }

    public Cursor select_item_parameter_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ITEM_PARAMETER_TABLE+" where item_mapping_code = "+"'"+id1+"'",null);
        // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+ITEM_PARAMETER_TABLE,null);
        return cursor;
    }


    public Cursor select_sor_item_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SORITEM_TABLE,null);
        return cursor;
    }

    public Cursor select_sor_item_parameter_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+SORITEM_PARAMETER_TABLE+" where soritem_mapping_code = "+"'"+id1+"'",null);
        // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SORITEM_PARAMETER_TABLE,null);
        return cursor;
    }


    public Cursor select_safety_item_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SAFITEM_TABLE,null);
        return cursor;
    }

    public Cursor select_safety_item_parameter_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+SAFITEM_PARAMETER_TABLE+" where safitem_para_mapping_code = "+"'"+id1+"'",null);
        // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SAFITEM_PARAMETER_TABLE,null);
        return cursor;
    }

    public Cursor select_nature_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NATURE_TABLE,null);
        return cursor;
    }

    public Cursor select_jmc_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+JMC_TABLE,null);
        return cursor;
    }

    public Cursor select_issue_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+ISSUE_TABLE,null);
        return cursor;
    }


    public Cursor select_infra_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INFRA_TABLE,null);
        return cursor;
    }

    //............ insert project..............//
    public long insert_project(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_ID, content0);
        contentValues.put(PROJECT_CODE, content1);
        contentValues.put(PROJECT_NAME, content2);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(PROJECT_TABLE, null, contentValues);
    }

    public int delete_project_all(){
        return sqLiteDatabase.delete(PROJECT_TABLE, null, null);
    }

    public void delete_project_byID(int id){
        sqLiteDatabase.delete(PROJECT_TABLE, PROJECT_ID+"="+id, null);
    }

    //............ insert geographic..............//
    public long insert_geographic(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(GEOGRAPHIC_PROJECT_CODE, content0);
        contentValues.put(GEOGRAPHIC_CODE, content1);
        contentValues.put(GEOGRAPHIC_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(GEOGRAPHIC_TABLE, null, contentValues);
    }

    public int delete_geographic_one_All(){
        return sqLiteDatabase.delete(GEOGRAPHIC_TABLE, null, null);
    }

    public void delete_geographic_one_byID(int id){
        sqLiteDatabase.delete(GEOGRAPHIC_TABLE, GEOGRAPHIC_TABLE_ID+"="+id, null);
    }

    //............ insert SUB AREA ONE..............//
    public long insert_subarea_one(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREAONE_GEOGRAPHIC_CODE, content0);
        contentValues.put(SUBAREAONE_CODE, content1);
        contentValues.put(SUBAREAONE_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(SUBAREAONE_TABLE, null, contentValues);
    }

    public int delete_subarea_one_All(){
        return sqLiteDatabase.delete(SUBAREAONE_TABLE, null, null);
    }

    public void delete_subarea_one_byID(int id){
        sqLiteDatabase.delete(SUBAREAONE_TABLE, SUBAREAONE_TABLE_ID+"="+id, null);
    }
    //............ insert SUB AREA TWO..............//
    public long insert_subarea_two(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREATWO_ONE_CODE, content0);
        contentValues.put(SUBAREATWO_CODE, content1);
        contentValues.put(SUBAREATWO_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(SUBAREATWO_TABLE, null, contentValues);
    }

    public int delete_subarea_two_All(){
        return sqLiteDatabase.delete(SUBAREATWO_TABLE, null, null);
    }

    public void delete_subarea_two_byID(int id){
        sqLiteDatabase.delete(SUBAREATWO_TABLE, SUBAREATWO_TABLE_ID+"="+id, null);
    }

    //............ insert SUB AREA Three..............//
    public long insert_subarea_three(String content0,String content1,String content2,String content3,String content4,String content5,String content6,String content7){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREATHREE_DIST_CODE, content0);
        contentValues.put(SUBAREATHREE_DIST_NAME, content1);
        contentValues.put(SUBAREATHREE_ONE_CODE, content2);
        contentValues.put(SUBAREATHREE_ONE_NAME, content3);
        contentValues.put(SUBAREATHREE_TWO_CODE, content4);
        contentValues.put(SUBAREATHREE_TWO_NAME, content5);
        contentValues.put(SUBAREATHREE_CODE, content6);
        contentValues.put(SUBAREATHREE_NAME, content7);
       //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(SUBAREATHREE_TABLE, null, contentValues);
    }

    public int delete_subarea_three_All(){
        return sqLiteDatabase.delete(SUBAREATHREE_TABLE, null, null);
    }

    public void delete_subarea_three_byID(int id){
        sqLiteDatabase.delete(SUBAREATHREE_TABLE, SUBAREATHREE_TABLE_ID+"="+id, null);
    }

    //............ insert Master STORE ..............//
    public long insert_material_store(String content0, String content1, String content2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MATERIAL_STORE_ID, content0);
        contentValues.put(MATERIAL_STORE_NAME, content1);
        contentValues.put(MATERIAL_DISTRICT_CODE, content2);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(MATERIAL_STORE_TABLE, null, contentValues);
    }

    public int delete_material_store_all(){
        return sqLiteDatabase.delete(MATERIAL_STORE_TABLE, null, null);
    }

    public void delete_material_store_byID(int id){
        sqLiteDatabase.delete(MATERIAL_STORE_TABLE, MATERIAL_STORE_TABLE_ID+"="+id, null);
    }

    //............ insert ITEM ..............//
    public long insert_item(String content0, String content1){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_ID, content0);
        contentValues.put(ITEM_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ITEM_TABLE, null, contentValues);
    }

    public int delete_item_all(){
        return sqLiteDatabase.delete(ITEM_TABLE, null, null);
    }

    public void delete_item_byID(int id){
        sqLiteDatabase.delete(ITEM_TABLE, ITEM_ID+"="+id, null);
    }

    //............ insert ITEM parameter mapping..............//
    public long insert_item_parameter(String content0, String content1, String content3){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_PARAMETER_ID, content0);
        contentValues.put(ITEM_PARAMETER_NAME, content1);
        contentValues.put(ITEM_PARAMETER_MAPPINGCODE, content3);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ITEM_PARAMETER_TABLE, null, contentValues);
    }

    public int delete_item__parameter_all(){
        return sqLiteDatabase.delete(ITEM_PARAMETER_TABLE, null, null);
    }

    public void delete_item_parameter_byID(int id){
        sqLiteDatabase.delete(ITEM_PARAMETER_TABLE, ITEM_PARAMETER_ID+"="+id, null);
    }
    //............ insert SOR ITEM ..............//
    public long insert_sor_item(String content0, String content1){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SORITEM_ID, content0);
        contentValues.put(SORITEM_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SORITEM_TABLE, null, contentValues);
    }

    public int delete_sor_item_all(){
        return sqLiteDatabase.delete(SORITEM_TABLE, null, null);
    }

    public void delete_sor_item_byID(int id){
        sqLiteDatabase.delete(SORITEM_TABLE, SORITEM_ID+"="+id, null);
    }

    //............ insert SOR ITEM parameter mapping..............//
    public long insert_sor_item_parameter(String content0, String content1, String content3){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SORITEM_PARAMETER_ID, content0);
        contentValues.put(SORITEM_PARAMETER_NAME, content1);
        contentValues.put(SORITEM_PARAMETER_MAPPINGCODE, content3);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SORITEM_PARAMETER_TABLE, null, contentValues);
    }

    public int delete_sor_item__parameter_all(){
        return sqLiteDatabase.delete(SORITEM_PARAMETER_TABLE, null, null);
    }

    public void delete_sor_item_parameter_byID(int id){
        sqLiteDatabase.delete(SORITEM_PARAMETER_TABLE, SORITEM_PARAMETER_ID+"="+id, null);
    }


    //............ insert SAFETY ITEM ..............//
    public long insert_safety_item(String content0, String content1){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SAFITEM_ID, content0);
        contentValues.put(SAFITEM_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SAFITEM_TABLE, null, contentValues);
    }

    public int delete_safety_item_all(){
        return sqLiteDatabase.delete(SAFITEM_TABLE, null, null);
    }

    public void delete_safety_item_byID(int id){
        sqLiteDatabase.delete(SAFITEM_TABLE, SAFITEM_ID+"="+id, null);
    }

    //............ insert SAFETY ITEM parameter mapping..............//
    public long insert_safety_item_parameter(String content0, String content1, String content3){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAFITEM_PARAMETER_ID, content0);
        contentValues.put(SAFITEM_PARAMETER_NAME, content1);
        contentValues.put(SAFITEM_PARAMETER_MAPPINGCODE, content3);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SAFITEM_PARAMETER_TABLE, null, contentValues);
    }

    public int delete_safety_item__parameter_all(){
        return sqLiteDatabase.delete(SAFITEM_PARAMETER_TABLE, null, null);
    }

    public void delete_safety_item_parameter_byID(int id){
        sqLiteDatabase.delete(SAFITEM_PARAMETER_TABLE, SAFITEM_PARAMETER_ID+"="+id, null);
    }

    //............ insert NATURE ..............//
    public long insert_nature(String content0, String content1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NATURE_ID, content0);
        contentValues.put(NATURE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(NATURE_TABLE, null, contentValues);
    }

    public int delete_nature_all(){
        return sqLiteDatabase.delete(NATURE_TABLE, null, null);
    }

    public void delete_nature_byID(int id){
        sqLiteDatabase.delete(NATURE_TABLE, NATURE_ID+"="+id, null);
    }

    //............ insert jmc ..............//
    public long insert_jmc(String content0, String content1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JMC_ID, content0);
        contentValues.put(JMC_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(JMC_TABLE, null, contentValues);
    }

    public int delete_jmc_all(){
        return sqLiteDatabase.delete(JMC_TABLE, null, null);
    }

    public void delete_jmc_byID(int id){
        sqLiteDatabase.delete(JMC_TABLE, JMC_ID+"="+id, null);
    }


    //............ insert ISSUE ..............//
    public long insert_issue(String content0, String content1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ISSUE_ID, content0);
        contentValues.put(ISSUE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ISSUE_TABLE, null, contentValues);
    }

    public int delete_issue_all(){
        return sqLiteDatabase.delete(ISSUE_TABLE, null, null);
    }

    public void delete_issue_byID(int id){
        sqLiteDatabase.delete(ISSUE_TABLE, ISSUE_ID+"="+id, null);
    }


    //............ insert INFRA ..............//
    public long insert_infra(String content0){
        ContentValues contentValues = new ContentValues();
        contentValues.put(INFRA_NAME, content0);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(INFRA_TABLE, null, contentValues);
    }

    public int delete_infra_all(){
        return sqLiteDatabase.delete(INFRA_TABLE, null, null);
    }


    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_PROJECT_TABLE);
            db.execSQL(CREATE_GEOGRAPHIC_TABLE);
            db.execSQL(CREATE_SUBAREAONE_TABLE);
            db.execSQL(CREATE_SUBAREATWO_TABLE);
            db.execSQL(CREATE_SUBAREATHREE_TABLE);
            db.execSQL(CREATE_MATERIAL_STORE_TABLE);
            db.execSQL(CREATE_ITEM_TABLE);
            db.execSQL(CREATE_ITEM_PARAMETER_TABLE);
            db.execSQL(CREATE_SORITEM_TABLE);
            db.execSQL(CREATE_SORITEM_PARAMETER_TABLE);
            db.execSQL(CREATE_SAFITEM_TABLE);
            db.execSQL(CREATE_SAFITEM_PARAMETER_TABLE);
            db.execSQL(CREATE_NATURE_TABLE);
            db.execSQL(CREATE_JMC_TABLE);
            db.execSQL(CREATE_ISSUE_TABLE);
            db.execSQL(CREATE_INFRA_TABLE);

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            db.execSQL("DROP TABLE IF EXISTS " + CREATE_PROJECT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_GEOGRAPHIC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREAONE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREATWO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREATHREE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MATERIAL_STORE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ITEM_PARAMETER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SORITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SORITEM_PARAMETER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SAFITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SAFITEM_PARAMETER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_NATURE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_JMC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ISSUE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_INFRA_TABLE);
            onCreate(db);
        }
    }



}
