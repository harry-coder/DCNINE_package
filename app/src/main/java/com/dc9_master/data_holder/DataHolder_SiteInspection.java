package com.dc9_master.data_holder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nitinb on 10-02-2016.
 */
public class DataHolder_SiteInspection {

    private static DataHolder_SiteInspection dataObject = null;

    public DataHolder_SiteInspection() {
        // left blank intentionally
    }

    public static DataHolder_SiteInspection getInstance() {
        if (dataObject == null)
            dataObject = new DataHolder_SiteInspection();
        return dataObject;
    }



    public void setSelectedItems(ArrayList<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public ArrayList<String> getSelectedItems() {
        return selectedItems;
    }

    private ArrayList<String> selectedItems;
    private ArrayList<String> selectedItems2;
    private ArrayList<String> selectedItems_value;
    private ArrayList<String> selectedItems_na;

    String selectedDate,str_insp_date,str_di_no,str_bel_rsp,str_fact_rsp,str_pma_rsp;

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getStr_insp_date() {
        return str_insp_date;
    }

    public void setStr_insp_date(String str_insp_date) {
        this.str_insp_date = str_insp_date;
    }

    public String getStr_di_no() {
        return str_di_no;
    }

    public void setStr_di_no(String str_di_no) {
        this.str_di_no = str_di_no;
    }

    public String getStr_bel_rsp() {
        return str_bel_rsp;
    }

    public void setStr_bel_rsp(String str_bel_rsp) {
        this.str_bel_rsp = str_bel_rsp;
    }

    public String getStr_fact_rsp() {
        return str_fact_rsp;
    }

    public void setStr_fact_rsp(String str_fact_rsp) {
        this.str_fact_rsp = str_fact_rsp;
    }

    public String getStr_pma_rsp() {
        return str_pma_rsp;
    }

    public void setStr_pma_rsp(String str_pma_rsp) {
        this.str_pma_rsp = str_pma_rsp;
    }

    public ArrayList<String> getCableselectedItems() {
        return cableselectedItems;
    }

    public void setCableselectedItems(ArrayList<String> cableselectedItems) {
        this.cableselectedItems = cableselectedItems;
    }

    private ArrayList<String> cableselectedItems;

    public ArrayList<String> getSelectedItems_na() {
        return selectedItems_na;
    }

    public void setSelectedItems_na(ArrayList<String> selectedItems_na) {
        this.selectedItems_na = selectedItems_na;
    }

    public ArrayList<String> getSelectedItems_value() {
        return selectedItems_value;
    }

    public void setSelectedItems_value(ArrayList<String> selectedItems_value) {
        this.selectedItems_value = selectedItems_value;
    }

    public ArrayList<String> getSelectedItems2() {
        return selectedItems2;
    }

    public void setSelectedItems2(ArrayList<String> selectedItems2) {
        this.selectedItems2 = selectedItems2;
    }

    //------------ for SiteInspection Module------------------//
    private String str_project_id;
    private String str_project_name;
    private String str_geographic_id;
    private String str_geographic_name;
    private String str_subareaone_name;
    private String item_name;
    private String item_id;

    private String item_mainname;
    private String str_mainpole;
    private String str_item_code;
    private String str_ncr_item_id;
    private String str_house_reowrk_autoid;
    private String str_cable_reowrk_autoid;
    private String str_team_type;

    public String getStr_team_type() {
        return str_team_type;
    }

    public void setStr_team_type(String str_team_type) {
        this.str_team_type = str_team_type;
    }

    public String getStr_house_reowrk_autoid() {
        return str_house_reowrk_autoid;
    }

    public void setStr_house_reowrk_autoid(String str_house_reowrk_autoid) {
        this.str_house_reowrk_autoid = str_house_reowrk_autoid;
    }

    public String getStr_cable_reowrk_autoid() {
        return str_cable_reowrk_autoid;
    }

    public void setStr_cable_reowrk_autoid(String str_cable_reowrk_autoid) {
        this.str_cable_reowrk_autoid = str_cable_reowrk_autoid;
    }

    public String getStr_ncr_item_id() {
        return str_ncr_item_id;
    }

    public void setStr_ncr_item_id(String str_ncr_item_id) {
        this.str_ncr_item_id = str_ncr_item_id;
    }

    public String getStr_item_code() {
        return str_item_code;
    }

    public void setStr_item_code(String str_item_code) {
        this.str_item_code = str_item_code;
    }

    public String getItem_mainname() {
        return item_mainname;
    }

    public void setItem_mainname(String item_mainname) {
        this.item_mainname = item_mainname;
    }

    public String getStr_mainpole() {
        return str_mainpole;
    }

    public void setStr_mainpole(String str_mainpole) {
        this.str_mainpole = str_mainpole;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    HashMap<String, String> meMap ;

    public HashMap<String, String> getMeMap() {
        return meMap;
    }

    public void setMeMap(HashMap<String, String> meMap) {
        this.meMap = meMap;
    }

    HashMap<String, String> meMap1 ;

    public HashMap<String, String> getMeMap1() {
        return meMap1;
    }

    public void setMeMap1(HashMap<String, String> meMap1) {
        this.meMap1 = meMap1;
    }

    public String str_pole;

    public String getStr_pole() {
        return str_pole;
    }

    public void setStr_pole(String str_pole) {
        this.str_pole = str_pole;
    }

    public String getStr_cable() {
        return str_cable;
    }

    public void setStr_cable(String str_cable) {
        this.str_cable = str_cable;
    }

    public String getStr_dtStructure() {
        return str_dtStructure;
    }

    public void setStr_dtStructure(String str_dtStructure) {
        this.str_dtStructure = str_dtStructure;
    }

    public String getStr_conductor() {
        return str_conductor;
    }

    public void setStr_conductor(String str_conductor) {
        this.str_conductor = str_conductor;
    }

    public String getStr_bplkit() {
        return str_bplkit;
    }

    public void setStr_bplkit(String str_bplkit) {
        this.str_bplkit = str_bplkit;
    }

    public String getStr_substation() {
        return str_substation;
    }

    public void setStr_substation(String str_substation) {
        this.str_substation = str_substation;
    }

    public String str_cable;
    public String str_dtStructure;
    public String str_conductor;
    public String str_bplkit;
    public String str_substation;
    public String str_panel;

    public String loc_distance;
    private String str_punch_status;


    public String cable_lat1;
    public String cable_long1;
    public String cable_lat2;
    public String cable_long2;
    public String cable_time;
    public String cable_nearest1;
    public String cable_nearest2;


    public String consumer_name;
    public String phone_no;
    public String address1;
    public String address2;
    public String address3;
    public String other_address;
    public String consumer_no;
    public String meter_no;
    public String str_rework_remark;
    public String Str_aadhar_no;

    public String getStr_aadhar_no() {
        return Str_aadhar_no;
    }

    public void setStr_aadhar_no(String str_aadhar_no) {
        Str_aadhar_no = str_aadhar_no;
    }

    public String getStr_rework_remark() {
        return str_rework_remark;
    }

    public void setStr_rework_remark(String str_rework_remark) {
        this.str_rework_remark = str_rework_remark;
    }

    public String current_date;

    public  int c_date;
    public  int r_date;

    public int getR_date() {
        return r_date;
    }

    public void setR_date(int r_date) {
        this.r_date = r_date;
    }

    public int getC_date() {
        return c_date;
    }

    public void setC_date(int c_date) {
        this.c_date = c_date;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getConsumer_no() {
        return consumer_no;
    }

    public void setConsumer_no(String consumer_no) {
        this.consumer_no = consumer_no;
    }

    public String getMeter_no() {
        return meter_no;
    }

    public void setMeter_no(String meter_no) {
        this.meter_no = meter_no;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getOther_address() {
        return other_address;
    }

    public void setOther_address(String other_address) {
        this.other_address = other_address;
    }

    public String getCable_lat1() {
        return cable_lat1;
    }

    public void setCable_lat1(String cable_lat1) {
        this.cable_lat1 = cable_lat1;
    }

    public String getCable_long1() {
        return cable_long1;
    }

    public void setCable_long1(String cable_long1) {
        this.cable_long1 = cable_long1;
    }

    public String getCable_lat2() {
        return cable_lat2;
    }

    public void setCable_lat2(String cable_lat2) {
        this.cable_lat2 = cable_lat2;
    }

    public String getCable_long2() {
        return cable_long2;
    }

    public void setCable_long2(String cable_long2) {
        this.cable_long2 = cable_long2;
    }

    public String getCable_time() {
        return cable_time;
    }

    public void setCable_time(String cable_time) {
        this.cable_time = cable_time;
    }

    public String getCable_nearest1() {
        return cable_nearest1;
    }

    public void setCable_nearest1(String cable_nearest1) {
        this.cable_nearest1 = cable_nearest1;
    }

    public String getCable_nearest2() {
        return cable_nearest2;
    }

    public void setCable_nearest2(String cable_nearest2) {
        this.cable_nearest2 = cable_nearest2;
    }

    public String getCable_total_distance() {
        return cable_total_distance;
    }

    public void setCable_total_distance(String cable_total_distance) {
        this.cable_total_distance = cable_total_distance;
    }

    public String cable_total_distance;


    public String getLoc_distance() {
        return loc_distance;
    }

    public void setLoc_distance(String loc_distance) {
        this.loc_distance = loc_distance;
    }

    public String getStr_punch_status() {
        return str_punch_status;
    }

    public void setStr_punch_status(String str_punch_status) {
        this.str_punch_status = str_punch_status;
    }

    public String getStr_panel() {
        return str_panel;
    }

    public void setStr_panel(String str_panel) {
        this.str_panel = str_panel;
    }

    public String getStr_tkc() {
        return str_tkc;
    }

    public void setStr_tkc(String str_tkc) {
        this.str_tkc = str_tkc;
    }

    public String getStr_utility() {
        return str_utility;
    }

    public void setStr_utility(String str_utility) {
        this.str_utility = str_utility;
    }

    public String getStr_contractor() {
        return str_contractor;
    }

    public void setStr_contractor(String str_contractor) {
        this.str_contractor = str_contractor;
    }

    private String imgbitmap1;
    private String imgbitmap2;
    private String imgbitmap3;
    private String imgbitmap4;
    private String imgbitmap5;
    private String imgbitmap6;
    private String imgbitmap_file;

    private String imglrgbitmap1;
    private String imglrgbitmap2;
    private String imglrgbitmap3;
    private String imglrgbitmap4;
    private String imglrgbitmap5;

    public String getImgbitmap_file() {
        return imgbitmap_file;
    }

    public void setImgbitmap_file(String imgbitmap_file) {
        this.imgbitmap_file = imgbitmap_file;
    }

    public String getImglrgbitmap_file() {
        return imglrgbitmap_file;
    }

    public void setImglrgbitmap_file(String imglrgbitmap_file) {
        this.imglrgbitmap_file = imglrgbitmap_file;
    }

    private String imglrgbitmap6;
    private String imglrgbitmap_file;


    private String imageNmae1;
    private String imageNmae2;
    private String imageNmae3;
    private String imageNmae4;
    private String imageNmae5;
    private String imageNmae6;
    private String imageNmae_file;

    public String getImageNmae_file() {
        return imageNmae_file;
    }

    public void setImageNmae_file(String imageNmae_file) {
        this.imageNmae_file = imageNmae_file;
    }



    public String getImageNmae1() {
        return imageNmae1;
    }

    public void setImageNmae1(String imageNmae1) {
        this.imageNmae1 = imageNmae1;
    }

    public String getImageNmae2() {
        return imageNmae2;
    }

    public void setImageNmae2(String imageNmae2) {
        this.imageNmae2 = imageNmae2;
    }

    public String getImageNmae3() {
        return imageNmae3;
    }

    public void setImageNmae3(String imageNmae3) {
        this.imageNmae3 = imageNmae3;
    }

    public String getImageNmae4() {
        return imageNmae4;
    }

    public void setImageNmae4(String imageNmae4) {
        this.imageNmae4 = imageNmae4;
    }

    public String getImageNmae5() {
        return imageNmae5;
    }

    public void setImageNmae5(String imageNmae5) {
        this.imageNmae5 = imageNmae5;
    }

    public String getImageNmae6() {
        return imageNmae6;
    }

    public void setImageNmae6(String imageNmae6) {
        this.imageNmae6 = imageNmae6;
    }

    private ArrayList<String> selectedStrings;
    public void setSelectedStrings(ArrayList<String> selectedStrings) {
        this.selectedStrings = selectedStrings;
    }

    public ArrayList<String> getSelectedStrings() {
        return selectedStrings;
    }


    public void setImg6(String imgbitmap6) {
        this.imgbitmap6 = imgbitmap6;
    }
    public String getImg6() {
        return imgbitmap6;
    }

    public void setImg1(String imgbitmap1) {
        this.imgbitmap1 = imgbitmap1;
    }
    public void setImg_file(String imgbitmap_file) {
        this.imgbitmap_file = imgbitmap_file;
    }
    public void setImg2(String imgbitmap2) {
        this.imgbitmap2 = imgbitmap2;
    }
    public void setImg3(String imgbitmap3) {
        this.imgbitmap3 = imgbitmap3;
    }
    public String getImg1() {
        return imgbitmap1;
    }
    public String getImg_file() {
        return imgbitmap_file;
    }
    public String getImg2() {
        return imgbitmap2;
    }
    public String getImg3() {
        return imgbitmap3;
    }

    public void setImg4(String imgbitmap4) {
        this.imgbitmap4 = imgbitmap4;
    }

    public void setImg5(String imgbitmap5) {
        this.imgbitmap5 = imgbitmap5;
    }

    public String getImg4() {
        return imgbitmap4;
    }

    public String getImg5() {
        return imgbitmap5;
    }



    public void setlrgImg1(String imglrgbitmap1) {
        this.imglrgbitmap1 = imglrgbitmap1;
    }

    public void setlrgImg_file(String imglrgbitmap_file) {
        this.imglrgbitmap_file = imglrgbitmap_file;
    }

    public void setlrgImg2(String imglrgbitmap2) {
        this.imglrgbitmap2 = imglrgbitmap2;
    }

    public void setlrgImg3(String imglrgbitmap3) {
        this.imglrgbitmap3 = imglrgbitmap3;
    }

    public void setlrgImg4(String imglrgbitmap4) {
        this.imglrgbitmap4 = imglrgbitmap4;
    }

    public void setlrgImg5(String imglrgbitmap5) {
        this.imglrgbitmap5 = imglrgbitmap5;
    }
    public void setlrgImg6(String imglrgbitmap6) {
        this.imglrgbitmap6 = imglrgbitmap6;
    }

    public String getlrgImg6() {
        return imglrgbitmap6;
    }
    public String getlrgImg1() {
        return imglrgbitmap1;
    }

    public String getlrgImg_file() {
        return imglrgbitmap_file;
    }

    public String getlrgImg2() {
        return imglrgbitmap2;
    }

    public String getlrgImg3() {
        return imglrgbitmap3;
    }

    public String getlrgImg4() {
        return imglrgbitmap4;
    }

    public String getlrgImg5() {
        return imglrgbitmap5;
    }


    private String str_tkc;
    private String str_utility;
    private String str_contractor;

    public String str_remark;

    public String camera_lat;
    public String camera_long;
    public String camera_time;

    public String lat1;
    public String long1;
    public String lat2;
    public String long2;
    public String time;
    public String jmc_status;
    String auto_number;

    public String getAuto_number() {
        return auto_number;
    }

    public void setAuto_number(String auto_number) {
        this.auto_number = auto_number;
    }

    public String getJmc_status() {
        return jmc_status;
    }

    public void setJmc_status(String jmc_status) {
        this.jmc_status = jmc_status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLong1() {
        return long1;
    }

    public void setLong1(String long1) {
        this.long1 = long1;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLong2() {
        return long2;
    }

    public void setLong2(String long2) {
        this.long2 = long2;
    }

    public String getCamera_lat() {
        return camera_lat;
    }

    public void setCamera_lat(String camera_lat) {
        this.camera_lat = camera_lat;
    }

    public String getCamera_long() {
        return camera_long;
    }

    public void setCamera_long(String camera_long) {
        this.camera_long = camera_long;
    }

    public String getCamera_time() {
        return camera_time;
    }

    public void setCamera_time(String camera_time) {
        this.camera_time = camera_time;
    }

    public String getStr_remark() {
        return str_remark;
    }

    public void setStr_remark(String str_remark) {
        this.str_remark = str_remark;
    }






    public static DataHolder_SiteInspection getDataObject() {
        return dataObject;
    }

    public static void setDataObject(DataHolder_SiteInspection dataObject) {
        DataHolder_SiteInspection.dataObject = dataObject;
    }

    public String getStr_project_id() {
        return str_project_id;
    }

    public void setStr_project_id(String str_project_id) {
        this.str_project_id = str_project_id;
    }

    public String getStr_project_name() {
        return str_project_name;
    }

    public void setStr_project_name(String str_project_name) {
        this.str_project_name = str_project_name;
    }

    public String getStr_geographic_id() {
        return str_geographic_id;
    }

    public void setStr_geographic_id(String str_geographic_id) {
        this.str_geographic_id = str_geographic_id;
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

    public String getStr_subareaone_id() {
        return str_subareaone_id;
    }

    public void setStr_subareaone_id(String str_subareaone_id) {
        this.str_subareaone_id = str_subareaone_id;
    }

    public String getStr_subareatwo_id() {
        return str_subareatwo_id;
    }

    public void setStr_subareatwo_id(String str_subareatwo_id) {
        this.str_subareatwo_id = str_subareatwo_id;
    }

    public String getStr_subareathree_id() {
        return str_subareathree_id;
    }

    public void setStr_subareathree_id(String str_subareathree_id) {
        this.str_subareathree_id = str_subareathree_id;
    }

    public String getStr_inspection_id() {
        return str_inspection_id;
    }

    public void setStr_inspection_id(String str_inspection_id) {
        this.str_inspection_id = str_inspection_id;
    }

    private String str_subareaone_id;

    private String str_subareatwo_id;
    private String str_subareathree_id;
    private String str_inspection_id;

    public String getStr_pole_name() {
        return str_pole_name;
    }

    public void setStr_pole_name(String str_pole_name) {
        this.str_pole_name = str_pole_name;
    }

    private String str_pole_name;

    public String getStr_pole_code_Array() {
        return str_pole_code_Array;
    }

    public void setStr_pole_code_Array(String str_pole_code_Array) {
        this.str_pole_code_Array = str_pole_code_Array;
    }

    private String str_pole_code_Array;



    public String getStr_pole_item_id() {
        return str_pole_item_id;
    }

    public void setStr_pole_item_id(String str_pole_item_id) {
        this.str_pole_item_id = str_pole_item_id;
    }

    private String str_pole_item_id;
    private String str_pole_item_cancel_id;
    private String str_checkbox_inputValue;
    private String str_checkbox_inputValue1;

    public String getStr_checkbox_inputValue1() {
        return str_checkbox_inputValue1;
    }

    public void setStr_checkbox_inputValue1(String str_checkbox_inputValue1) {
        this.str_checkbox_inputValue1 = str_checkbox_inputValue1;
    }

    private String str_item_na;



    public String getStr_item_na() {
        return str_item_na;
    }

    public void setStr_item_na(String str_item_na) {
        this.str_item_na = str_item_na;
    }

    public String getStr_checkbox_inputValue() {
        return str_checkbox_inputValue;
    }

    public void setStr_checkbox_inputValue(String str_checkbox_inputValue) {
        this.str_checkbox_inputValue = str_checkbox_inputValue;
    }

    public String getStr_pole_item_cancel_id() {
        return str_pole_item_cancel_id;
    }

    public void setStr_pole_item_cancel_id(String str_pole_item_cancel_id) {
        this.str_pole_item_cancel_id = str_pole_item_cancel_id;
    }

    public String str_ipc,str_dead,str_suspension,str_singlephase,str_threephase,str_giwire,str_spike,str_fasteners,str_gi_struct,str_eyehook,str_forged_eightm,str_forged_ninem;
    public String str_item_qty;

    public String getStr_ipc() {
        return str_ipc;
    }

    public String getStr_item_qty() {
        return str_item_qty;
    }

    public void setStr_item_qty(String str_item_qty) {
        this.str_item_qty = str_item_qty;
    }

    public void setStr_ipc(String str_ipc) {
        this.str_ipc = str_ipc;
    }

    public String getStr_dead() {
        return str_dead;
    }

    public void setStr_dead(String str_dead) {
        this.str_dead = str_dead;
    }

    public String getStr_suspension() {
        return str_suspension;
    }

    public void setStr_suspension(String str_suspension) {
        this.str_suspension = str_suspension;
    }

    public String getStr_singlephase() {
        return str_singlephase;
    }

    public void setStr_singlephase(String str_singlephase) {
        this.str_singlephase = str_singlephase;
    }

    public String getStr_threephase() {
        return str_threephase;
    }

    public void setStr_threephase(String str_threephase) {
        this.str_threephase = str_threephase;
    }

    public String getStr_giwire() {
        return str_giwire;
    }

    public void setStr_giwire(String str_giwire) {
        this.str_giwire = str_giwire;
    }

    public String getStr_spike() {
        return str_spike;
    }

    public void setStr_spike(String str_spike) {
        this.str_spike = str_spike;
    }

    public String getStr_fasteners() {
        return str_fasteners;
    }

    public void setStr_fasteners(String str_fasteners) {
        this.str_fasteners = str_fasteners;
    }

    public String getStr_gi_struct() {
        return str_gi_struct;
    }

    public void setStr_gi_struct(String str_gi_struct) {
        this.str_gi_struct = str_gi_struct;
    }

    public String getStr_eyehook() {
        return str_eyehook;
    }

    public void setStr_eyehook(String str_eyehook) {
        this.str_eyehook = str_eyehook;
    }

    public String getStr_forged_eightm() {
        return str_forged_eightm;
    }

    public void setStr_forged_eightm(String str_forged_eightm) {
        this.str_forged_eightm = str_forged_eightm;
    }

    public String getStr_forged_ninem() {
        return str_forged_ninem;
    }

    public void setStr_forged_ninem(String str_forged_ninem) {
        this.str_forged_ninem = str_forged_ninem;
    }

    public void nullify_DataHolder_SiteInspection() {

        dataObject=null;
        imglrgbitmap1=null;
        imglrgbitmap2=null;
        imglrgbitmap3=null;
        imglrgbitmap4=null;
        imglrgbitmap5=null;
        imglrgbitmap6=null;
        imglrgbitmap_file=null;

         imgbitmap1=null;
         imgbitmap2=null;
         imgbitmap3=null;
         imgbitmap4=null;
         imgbitmap5=null;
         imgbitmap6=null;
         imgbitmap_file=null;

        imageNmae1=null;
        imageNmae2=null;
        imageNmae3=null;
        imageNmae4=null;
        imageNmae5=null;
        imageNmae6=null;
        imageNmae_file=null;

        str_bpl_item_id=null;
        str_bpl_item_cancel_id=null;
        str_pass=null;
        str_rework=null;
        str_pole_item_id=null;
        str_pole_item_cancel_id=null;
        str_cable_item_cancel_id=null;
        str_dt_item_id=null;
        str_dt_item_cancel_id=null;
        str_cable=null;
        str_dtStructure=null;
        str_conductor=null;
        str_bplkit=null;
        str_substation=null;
        str_panel=null;
        str_pole=null;
        str_ncrIns=null;

        str_geographiclevel=null;
        str_level1=null;
        str_level2=null;
        str_level3=null;
        str_level4=null;
        c_date=0;

        jmc_status=null;
        cableselectedItems=null;
        str_cable_item_id=null;
        str_house_reowrk_autoid=null;
        str_cable_reowrk_autoid=null;
        Str_aadhar_no=null;
        auto_number=null;

        str_ipc=null;
        str_dead=null;
        str_suspension=null;
        str_singlephase=null;
        str_threephase=null;
        str_giwire=null;
        str_spike=null;
        str_fasteners=null;
        str_gi_struct=null;
        str_eyehook=null;
        str_forged_eightm=null;
        str_forged_ninem=null;
        str_item_qty=null;
        str_team_type=null;

        //---for bajaj----------//
        selectedDate=null;
        str_insp_date=null;
        str_di_no=null;
        str_bel_rsp=null;
        str_fact_rsp=null;
        str_pma_rsp=null;
        meMap=null;
        str_checkbox_inputValue=null;
        meMap1=null;
        str_checkbox_inputValue1=null;



    }


    private ArrayList<String> selectedCableItems;

    public ArrayList<String> getSelectedCableItems() {
        return selectedCableItems;
    }

    public void setSelectedCableItems(ArrayList<String> selectedCableItems) {
        this.selectedCableItems = selectedCableItems;
    }

    public ArrayList<String> getSelectedCableItems2() {
        return selectedCableItems2;
    }

    public void setSelectedCableItems2(ArrayList<String> selectedCableItems2) {
        this.selectedCableItems2 = selectedCableItems2;
    }

    public String getStr_cable_item_id() {
        return str_cable_item_id;
    }

    public void setStr_cable_item_id(String str_cable_item_id) {
        this.str_cable_item_id = str_cable_item_id;
    }

    public String getStr_cable_item_cancel_id() {
        return str_cable_item_cancel_id;
    }

    public void setStr_cable_item_cancel_id(String str_cable_item_cancel_id) {
        this.str_cable_item_cancel_id = str_cable_item_cancel_id;
    }

    private ArrayList<String> selectedCableItems2;
    private String str_cable_item_id;
    private String str_cable_item_cancel_id;





    private ArrayList<String> selectedDTItems;
    private ArrayList<String> selectedDTItems2;
    private String str_dt_item_id;

    public ArrayList<String> getSelectedDTItems() {
        return selectedDTItems;
    }

    public void setSelectedDTItems(ArrayList<String> selectedDTItems) {
        this.selectedDTItems = selectedDTItems;
    }

    public ArrayList<String> getSelectedDTItems2() {
        return selectedDTItems2;
    }

    public void setSelectedDTItems2(ArrayList<String> selectedDTItems2) {
        this.selectedDTItems2 = selectedDTItems2;
    }

    public String getStr_dt_item_id() {
        return str_dt_item_id;
    }

    public void setStr_dt_item_id(String str_dt_item_id) {
        this.str_dt_item_id = str_dt_item_id;
    }

    public String getStr_dt_item_cancel_id() {
        return str_dt_item_cancel_id;
    }

    public void setStr_dt_item_cancel_id(String str_dt_item_cancel_id) {
        this.str_dt_item_cancel_id = str_dt_item_cancel_id;
    }

    public ArrayList<String> getSelectedConductorItems() {
        return selectedConductorItems;
    }

    public void setSelectedConductorItems(ArrayList<String> selectedConductorItems) {
        this.selectedConductorItems = selectedConductorItems;
    }

    public ArrayList<String> getSelectedConductorItems2() {
        return selectedConductorItems2;
    }

    public void setSelectedConductorItems2(ArrayList<String> selectedConductorItems2) {
        this.selectedConductorItems2 = selectedConductorItems2;
    }

    public String getStr_conductor_item_id() {
        return str_conductor_item_id;
    }

    public void setStr_conductor_item_id(String str_conductor_item_id) {
        this.str_conductor_item_id = str_conductor_item_id;
    }

    public String getStr_conductor_item_cancel_id() {
        return str_conductor_item_cancel_id;
    }

    public void setStr_conductor_item_cancel_id(String str_conductor_item_cancel_id) {
        this.str_conductor_item_cancel_id = str_conductor_item_cancel_id;
    }

    private String str_dt_item_cancel_id;




    private ArrayList<String> selectedConductorItems;
    private ArrayList<String> selectedConductorItems2;
    private String str_conductor_item_id;
    private String str_conductor_item_cancel_id;

    private ArrayList<String> selectedBplItems;
    private ArrayList<String> selectedBplItems2;
    private String str_bpl_item_id;
    private String str_bpl_item_cancel_id;

    public ArrayList<String> getSelectedBplItems() {
        return selectedBplItems;
    }

    public void setSelectedBplItems(ArrayList<String> selectedBplItems) {
        this.selectedBplItems = selectedBplItems;
    }

    public ArrayList<String> getSelectedBplItems2() {
        return selectedBplItems2;
    }

    public void setSelectedBplItems2(ArrayList<String> selectedBplItems2) {
        this.selectedBplItems2 = selectedBplItems2;
    }

    public String getStr_bpl_item_id() {
        return str_bpl_item_id;
    }

    public void setStr_bpl_item_id(String str_bpl_item_id) {
        this.str_bpl_item_id = str_bpl_item_id;
    }

    public String getStr_bpl_item_cancel_id() {
        return str_bpl_item_cancel_id;
    }

    public void setStr_bpl_item_cancel_id(String str_bpl_item_cancel_id) {
        this.str_bpl_item_cancel_id = str_bpl_item_cancel_id;
    }

    public ArrayList<String> getSelectedPowerItems() {
        return selectedPowerItems;
    }

    public void setSelectedPowerItems(ArrayList<String> selectedPowerItems) {
        this.selectedPowerItems = selectedPowerItems;
    }

    public ArrayList<String> getSelectedPowerItems2() {
        return selectedPowerItems2;
    }

    public void setSelectedPowerItems2(ArrayList<String> selectedPowerItems2) {
        this.selectedPowerItems2 = selectedPowerItems2;
    }

    public String getStr_power_item_id() {
        return str_power_item_id;
    }

    public void setStr_power_item_id(String str_power_item_id) {
        this.str_power_item_id = str_power_item_id;
    }

    public String getStr_power_item_cancel_id() {
        return str_power_item_cancel_id;
    }

    public void setStr_power_item_cancel_id(String str_power_item_cancel_id) {
        this.str_power_item_cancel_id = str_power_item_cancel_id;
    }

    public ArrayList<String> getSelectedPanelItems() {
        return selectedPanelItems;
    }

    public void setSelectedPanelItems(ArrayList<String> selectedPanelItems) {
        this.selectedPanelItems = selectedPanelItems;
    }

    public ArrayList<String> getSelectedPanelItems2() {
        return selectedPanelItems2;
    }

    public void setSelectedPanelItems2(ArrayList<String> selectedPanelItems2) {
        this.selectedPanelItems2 = selectedPanelItems2;
    }

    public String getStr_panel_item_id() {
        return str_panel_item_id;
    }

    public void setStr_panel_item_id(String str_panel_item_id) {
        this.str_panel_item_id = str_panel_item_id;
    }

    public String getStr_panel_item_cancel_id() {
        return str_panel_item_cancel_id;
    }

    public void setStr_panel_item_cancel_id(String str_panel_item_cancel_id) {
        this.str_panel_item_cancel_id = str_panel_item_cancel_id;
    }

    private ArrayList<String> selectedPowerItems;
    private ArrayList<String> selectedPowerItems2;
    private String str_power_item_id;
    private String str_power_item_cancel_id;

    private ArrayList<String> selectedPanelItems;
    private ArrayList<String> selectedPanelItems2;
    private String str_panel_item_id;
    private String str_panel_item_cancel_id;


    public String str_cable_remark;
    public String str_dt_remark;
    public String str_conductor_remark;
    public String str_bpl_remark;
    public String str_power_remark;

    public String getStr_cable_remark() {
        return str_cable_remark;
    }

    public void setStr_cable_remark(String str_cable_remark) {
        this.str_cable_remark = str_cable_remark;
    }

    public String getStr_dt_remark() {
        return str_dt_remark;
    }

    public void setStr_dt_remark(String str_dt_remark) {
        this.str_dt_remark = str_dt_remark;
    }

    public String getStr_conductor_remark() {
        return str_conductor_remark;
    }

    public void setStr_conductor_remark(String str_conductor_remark) {
        this.str_conductor_remark = str_conductor_remark;
    }

    public String getStr_bpl_remark() {
        return str_bpl_remark;
    }

    public void setStr_bpl_remark(String str_bpl_remark) {
        this.str_bpl_remark = str_bpl_remark;
    }

    public String getStr_power_remark() {
        return str_power_remark;
    }

    public void setStr_power_remark(String str_power_remark) {
        this.str_power_remark = str_power_remark;
    }

    public String getStr_controlPanel_remark() {
        return str_controlPanel_remark;
    }

    public void setStr_controlPanel_remark(String str_controlPanel_remark) {
        this.str_controlPanel_remark = str_controlPanel_remark;
    }

    public String str_controlPanel_remark;


    public String str_pass;

    public String getStr_pass() {
        return str_pass;
    }

    public void setStr_pass(String str_pass) {
        this.str_pass = str_pass;
    }

    public String getStr_rework() {
        return str_rework;
    }

    public void setStr_rework(String str_rework) {
        this.str_rework = str_rework;
    }

    public String str_rework;

    // ncr reports-----------------------//
    private String str_ncrIns;
    private String str_ncrDistrict;
    private String str_ncrBlock;
    private String str_ncrVillage;
    private String str_ncrItem;
    private String str_ncrDate;
    private String str_ncrReamrks;
    private String str_ncrName;

    public String getStr_ncrName() {
        return str_ncrName;
    }

    public void setStr_ncrName(String str_ncrName) {
        this.str_ncrName = str_ncrName;
    }

    private String str_geographiclevel;
    private String str_level1;
    private String str_level2;
    private String str_level3;
    private String str_level4;

    public String getStr_geographiclevel() {
        return str_geographiclevel;
    }

    public void setStr_geographiclevel(String str_geographiclevel) {
        this.str_geographiclevel = str_geographiclevel;
    }

    public String getStr_level1() {
        return str_level1;
    }

    public void setStr_level1(String str_level1) {
        this.str_level1 = str_level1;
    }

    public String getStr_level2() {
        return str_level2;
    }

    public void setStr_level2(String str_level2) {
        this.str_level2 = str_level2;
    }

    public String getStr_level3() {
        return str_level3;
    }

    public void setStr_level3(String str_level3) {
        this.str_level3 = str_level3;
    }

    public String getStr_level4() {
        return str_level4;
    }

    public void setStr_level4(String str_level4) {
        this.str_level4 = str_level4;
    }

    public String getStr_ncrIns() {
        return str_ncrIns;
    }

    public void setStr_ncrIns(String str_ncrIns) {
        this.str_ncrIns = str_ncrIns;
    }

    public String getStr_ncrDistrict() {
        return str_ncrDistrict;
    }

    public void setStr_ncrDistrict(String str_ncrDistrict) {
        this.str_ncrDistrict = str_ncrDistrict;
    }

    public String getStr_ncrBlock() {
        return str_ncrBlock;
    }

    public void setStr_ncrBlock(String str_ncrBlock) {
        this.str_ncrBlock = str_ncrBlock;
    }

    public String getStr_ncrVillage() {
        return str_ncrVillage;
    }

    public void setStr_ncrVillage(String str_ncrVillage) {
        this.str_ncrVillage = str_ncrVillage;
    }

    public String getStr_ncrItem() {
        return str_ncrItem;
    }

    public void setStr_ncrItem(String str_ncrItem) {
        this.str_ncrItem = str_ncrItem;
    }

    public String getStr_ncrDate() {
        return str_ncrDate;
    }

    public void setStr_ncrDate(String str_ncrDate) {
        this.str_ncrDate = str_ncrDate;
    }

    public String getStr_ncrReamrks() {
        return str_ncrReamrks;
    }

    public void setStr_ncrReamrks(String str_ncrReamrks) {
        this.str_ncrReamrks = str_ncrReamrks;
    }
}
