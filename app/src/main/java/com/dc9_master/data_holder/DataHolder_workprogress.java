package com.dc9_master.data_holder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nitinb on 10-02-2016.
 */
public class DataHolder_workprogress {

    private static DataHolder_workprogress dataObject = null;


    public DataHolder_workprogress() {
        // left blank intentionally
    }

    public static DataHolder_workprogress getInstance() {
        if (dataObject == null)
            dataObject = new DataHolder_workprogress();
        return dataObject;
    }

    public Integer int_count;

    public Integer getInt_count() {
        return int_count;
    }

    public void setInt_count(Integer int_count) {
        this.int_count = int_count;
    }

    String geographoic_id,subareaone,subareatwo,subareathree;
    String geographoic_id1,subareaone1,subareatwo1,subareathree1;

    String selectedDate,str_insp_date,str_di_no,str_lr_no,str_bel_rsp,str_fact_rsp,str_pma_rsp,str_po_no,str_factorylocation;

    public String getStr_po_no() {
        return str_po_no;
    }

    public void setStr_po_no(String str_po_no) {
        this.str_po_no = str_po_no;
    }

    public String getStr_factorylocation() {
        return str_factorylocation;
    }

    public void setStr_factorylocation(String str_factorylocation) {
        this.str_factorylocation = str_factorylocation;
    }

    public String getStr_lr_no() {
        return str_lr_no;
    }

    public void setStr_lr_no(String str_lr_no) {
        this.str_lr_no = str_lr_no;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
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

    public String getGeographoic_id1() {
        return geographoic_id1;
    }

    public void setGeographoic_id1(String geographoic_id1) {
        this.geographoic_id1 = geographoic_id1;
    }

    public String getSubareaone1() {
        return subareaone1;
    }

    public void setSubareaone1(String subareaone1) {
        this.subareaone1 = subareaone1;
    }

    public String getSubareatwo1() {
        return subareatwo1;
    }

    public void setSubareatwo1(String subareatwo1) {
        this.subareatwo1 = subareatwo1;
    }

    public String getSubareathree1() {
        return subareathree1;
    }

    public void setSubareathree1(String subareathree1) {
        this.subareathree1 = subareathree1;
    }

    String str_remark,str_lat,str_long,camera_lat,camera_long,camera_time,str_team_working,str_gp_completed,str_wip_block,str_survey;
    String str_trenching_length,str_trenching_depth,str_ducting_length,str_hdd,str_jcb,str_excavator,str_soil_type,str_team_type,str_cable_progress;
    String str_input_manhole,str_output_manhole,str_chainage_from,str_chainage_to,str_cable_length,
            str_loop_length,str_total_length,strcable_blowing_progress;
    String str_qty,str_date,str_vendor_rspName,str_vendor_inspCode,str_tpa_rsp,str_chips_rsp,
            str_vendor_id,str_item_qty,str_store_id,str_network_inspection,str_safety_pass_rework,
            str_bookqty,str_physicalqty,str_inspectedQty,str_passedQTY,str_item_code,str_item_code1,from_date,to_date,reason,str_receQty,str_damagedQty,str_acceptedQty,str_recev_date;

    public String getStr_recev_date() {
        return str_recev_date;
    }

    public void setStr_recev_date(String str_recev_date) {
        this.str_recev_date = str_recev_date;
    }

    public String getStr_item_code1() {
        return str_item_code1;
    }

    public String getStr_receQty() {
        return str_receQty;
    }

    public void setStr_receQty(String str_receQty) {
        this.str_receQty = str_receQty;
    }

    public String getStr_damagedQty() {
        return str_damagedQty;
    }

    public void setStr_damagedQty(String str_damagedQty) {
        this.str_damagedQty = str_damagedQty;
    }

    public String getStr_acceptedQty() {
        return str_acceptedQty;
    }

    public void setStr_acceptedQty(String str_acceptedQty) {
        this.str_acceptedQty = str_acceptedQty;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStr_item_code1(String str_item_code1) {
        this.str_item_code1 = str_item_code1;
    }

    public String getStr_inspectedQty() {
        return str_inspectedQty;
    }

    public void setStr_inspectedQty(String str_inspectedQty) {
        this.str_inspectedQty = str_inspectedQty;
    }

    public String getStr_passedQTY() {
        return str_passedQTY;
    }

    public void setStr_passedQTY(String str_passedQTY) {
        this.str_passedQTY = str_passedQTY;
    }

    public String getStr_bookqty() {
        return str_bookqty;
    }

    public void setStr_bookqty(String str_bookqty) {
        this.str_bookqty = str_bookqty;
    }

    public String getStr_physicalqty() {
        return str_physicalqty;
    }

    public void setStr_physicalqty(String str_physicalqty) {
        this.str_physicalqty = str_physicalqty;
    }

    public String getStr_safety_pass_rework() {
        return str_safety_pass_rework;
    }

    public void setStr_safety_pass_rework(String str_safety_pass_rework) {
        this.str_safety_pass_rework = str_safety_pass_rework;
    }

    public String getStr_network_inspection() {
        return str_network_inspection;
    }

    public void setStr_network_inspection(String str_network_inspection) {
        this.str_network_inspection = str_network_inspection;
    }

    public String getStr_store_id() {
        return str_store_id;
    }

    public void setStr_store_id(String str_store_id) {
        this.str_store_id = str_store_id;
    }

    public String getStr_trenching_depth() {
        return str_trenching_depth;
    }

    public void setStr_trenching_depth(String str_trenching_depth) {
        this.str_trenching_depth = str_trenching_depth;
    }

    public String getStr_item_qty() {
        return str_item_qty;
    }

    public void setStr_item_qty(String str_item_qty) {
        this.str_item_qty = str_item_qty;
    }

    public String getStr_vendor_id() {
        return str_vendor_id;
    }

    public void setStr_vendor_id(String str_vendor_id) {
        this.str_vendor_id = str_vendor_id;
    }

    public String getStr_qty() {
        return str_qty;
    }

    public void setStr_qty(String str_qty) {
        this.str_qty = str_qty;
    }

    public String getStr_date() {
        return str_date;
    }

    public void setStr_date(String str_date) {
        this.str_date = str_date;
    }

    public String getStr_insp_date() {
        return str_insp_date;
    }

    public void setStr_insp_date(String str_insp_date) {
        this.str_insp_date = str_insp_date;
    }

    public String getStr_vendor_rspName() {
        return str_vendor_rspName;
    }

    public void setStr_vendor_rspName(String str_vendor_rspName) {
        this.str_vendor_rspName = str_vendor_rspName;
    }

    public String getStr_vendor_inspCode() {
        return str_vendor_inspCode;
    }

    public void setStr_vendor_inspCode(String str_vendor_inspCode) {
        this.str_vendor_inspCode = str_vendor_inspCode;
    }

    public String getStr_tpa_rsp() {
        return str_tpa_rsp;
    }

    public void setStr_tpa_rsp(String str_tpa_rsp) {
        this.str_tpa_rsp = str_tpa_rsp;
    }

    public String getStr_chips_rsp() {
        return str_chips_rsp;
    }

    public void setStr_chips_rsp(String str_chips_rsp) {
        this.str_chips_rsp = str_chips_rsp;
    }

    public String getStr_item_code() {
        return str_item_code;
    }

    public void setStr_item_code(String str_item_code) {
        this.str_item_code = str_item_code;
    }

    public String getStrcable_blowing_progress() {
        return strcable_blowing_progress;
    }

    public void setStrcable_blowing_progress(String strcable_blowing_progress) {
        this.strcable_blowing_progress = strcable_blowing_progress;
    }

    public String getStr_input_manhole() {
        return str_input_manhole;
    }

    public void setStr_input_manhole(String str_input_manhole) {
        this.str_input_manhole = str_input_manhole;
    }

    public String getStr_output_manhole() {
        return str_output_manhole;
    }

    public void setStr_output_manhole(String str_output_manhole) {
        this.str_output_manhole = str_output_manhole;
    }

    public String getStr_chainage_from() {
        return str_chainage_from;
    }

    public void setStr_chainage_from(String str_chainage_from) {
        this.str_chainage_from = str_chainage_from;
    }

    public String getStr_chainage_to() {
        return str_chainage_to;
    }

    public void setStr_chainage_to(String str_chainage_to) {
        this.str_chainage_to = str_chainage_to;
    }

    public String getStr_cable_length() {
        return str_cable_length;
    }

    public void setStr_cable_length(String str_cable_length) {
        this.str_cable_length = str_cable_length;
    }

    public String getStr_loop_length() {
        return str_loop_length;
    }

    public void setStr_loop_length(String str_loop_length) {
        this.str_loop_length = str_loop_length;
    }

    public String getStr_total_length() {
        return str_total_length;
    }

    public void setStr_total_length(String str_total_length) {
        this.str_total_length = str_total_length;
    }

    public String getStr_hdd() {
        return str_hdd;
    }

    public String getStr_cable_progress() {
        return str_cable_progress;
    }

    public void setStr_cable_progress(String str_cable_progress) {
        this.str_cable_progress = str_cable_progress;
    }

    public void setStr_hdd(String str_hdd) {
        this.str_hdd = str_hdd;
    }

    public String getStr_jcb() {
        return str_jcb;
    }

    public void setStr_jcb(String str_jcb) {
        this.str_jcb = str_jcb;
    }

    public String getStr_excavator() {
        return str_excavator;
    }

    public void setStr_excavator(String str_excavator) {
        this.str_excavator = str_excavator;
    }

    public String getStr_soil_type() {
        return str_soil_type;
    }

    public void setStr_soil_type(String str_soil_type) {
        this.str_soil_type = str_soil_type;
    }

    public String getStr_team_type() {
        return str_team_type;
    }

    public void setStr_team_type(String str_team_type) {
        this.str_team_type = str_team_type;
    }

    public String getStr_trenching_length() {
        return str_trenching_length;
    }

    public void setStr_trenching_length(String str_trenching_length) {
        this.str_trenching_length = str_trenching_length;
    }

    public String getStr_ducting_length() {
        return str_ducting_length;
    }

    public void setStr_ducting_length(String str_ducting_length) {
        this.str_ducting_length = str_ducting_length;
    }

    public String getStr_team_working() {
        return str_team_working;
    }

    public void setStr_team_working(String str_team_working) {
        this.str_team_working = str_team_working;
    }

    public String getStr_gp_completed() {
        return str_gp_completed;
    }

    public void setStr_gp_completed(String str_gp_completed) {
        this.str_gp_completed = str_gp_completed;
    }

    public String getStr_wip_block() {
        return str_wip_block;
    }

    public void setStr_wip_block(String str_wip_block) {
        this.str_wip_block = str_wip_block;
    }

    public String getStr_survey() {
        return str_survey;
    }

    public void setStr_survey(String str_survey) {
        this.str_survey = str_survey;
    }


    private ArrayList<String> selectedItems;
    private ArrayList<String> selectedItems_value;
    private String str_pole_item_id;
    private String str_item_cancel;

    public String getStr_item_cancel() {
        return str_item_cancel;
    }

    public void setStr_item_cancel(String str_item_cancel) {
        this.str_item_cancel = str_item_cancel;
    }

    public String getStr_item_yes() {
        return str_item_yes;
    }

    public void setStr_item_yes(String str_item_yes) {
        this.str_item_yes = str_item_yes;
    }

    private String str_item_yes;
    private String str_checkbox_inputValue;
    private String item_name,item_id;
    private String str_checkbox_inputValue1;
    HashMap<String, String> meMap ;


    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public HashMap<String, String> getMeMap() {
        return meMap;
    }

    public void setMeMap(HashMap<String, String> meMap) {
        this.meMap = meMap;
    }

    HashMap<String, String> meMap1 ;
    private ArrayList<String> selectedItems2;

    public ArrayList<String> getSelectedItems2() {
        return selectedItems2;
    }

    public void setSelectedItems2(ArrayList<String> selectedItems2) {
        this.selectedItems2 = selectedItems2;
    }

    public HashMap<String, String> getMeMap1() {
        return meMap1;
    }

    public void setMeMap1(HashMap<String, String> meMap1) {
        this.meMap1 = meMap1;
    }

    public String getStr_checkbox_inputValue1() {
        return str_checkbox_inputValue1;
    }

    public void setStr_checkbox_inputValue1(String str_checkbox_inputValue1) {
        this.str_checkbox_inputValue1 = str_checkbox_inputValue1;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getStr_pole_item_id() {
        return str_pole_item_id;
    }

    public void setStr_pole_item_id(String str_pole_item_id) {
        this.str_pole_item_id = str_pole_item_id;
    }

    public String getStr_checkbox_inputValue() {
        return str_checkbox_inputValue;
    }

    public void setStr_checkbox_inputValue(String str_checkbox_inputValue) {
        this.str_checkbox_inputValue = str_checkbox_inputValue;
    }

    public ArrayList<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public ArrayList<String> getSelectedItems_value() {
        return selectedItems_value;
    }

    public void setSelectedItems_value(ArrayList<String> selectedItems_value) {
        this.selectedItems_value = selectedItems_value;
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

    String auto_number;
    String str_feature_id;
    String str_approved_id,str_lastlat,str_lastlong,str_chamber_type,str_chamber_no,str_drum_no;
    String hhsc_manpower,network_manpower;
    String network_habitation_btnstatus,meter_status,str_tkc_id,network_pass_rework_status,str_otherissue,str_issueType;

    public String getNetwork_pass_rework_status() {
        return network_pass_rework_status;
    }

    public String getStr_otherissue() {
        return str_otherissue;
    }

    public void setStr_otherissue(String str_otherissue) {
        this.str_otherissue = str_otherissue;
    }

    public String getStr_issueType() {
        return str_issueType;
    }

    public void setStr_issueType(String str_issueType) {
        this.str_issueType = str_issueType;
    }

    public void setNetwork_pass_rework_status(String network_pass_rework_status) {
        this.network_pass_rework_status = network_pass_rework_status;
    }

    public String getStr_tkc_id() {
        return str_tkc_id;
    }

    public void setStr_tkc_id(String str_tkc_id) {
        this.str_tkc_id = str_tkc_id;
    }

    public String getMeter_status() {
        return meter_status;
    }

    public void setMeter_status(String meter_status) {
        this.meter_status = meter_status;
    }

    public String getNetwork_habitation_btnstatus() {
        return network_habitation_btnstatus;
    }

    public void setNetwork_habitation_btnstatus(String network_habitation_btnstatus) {
        this.network_habitation_btnstatus = network_habitation_btnstatus;
    }

    public String getHhsc_manpower() {
        return hhsc_manpower;
    }

    public void setHhsc_manpower(String hhsc_manpower) {
        this.hhsc_manpower = hhsc_manpower;
    }

    public String getNetwork_manpower() {
        return network_manpower;
    }

    public void setNetwork_manpower(String network_manpower) {
        this.network_manpower = network_manpower;
    }

    public String getStr_drum_no() {
        return str_drum_no;
    }

    public void setStr_drum_no(String str_drum_no) {
        this.str_drum_no = str_drum_no;
    }

    public String getStr_chamber_no() {
        return str_chamber_no;
    }

    public void setStr_chamber_no(String str_chamber_no) {
        this.str_chamber_no = str_chamber_no;
    }

    public String getStr_chamber_type() {
        return str_chamber_type;
    }

    public void setStr_chamber_type(String str_chamber_type) {
        this.str_chamber_type = str_chamber_type;
    }

    public String getStr_approved_id() {
        return str_approved_id;
    }

    public void setStr_approved_id(String str_approved_id) {
        this.str_approved_id = str_approved_id;
    }

    public String getStr_lastlat() {
        return str_lastlat;
    }

    public void setStr_lastlat(String str_lastlat) {
        this.str_lastlat = str_lastlat;
    }

    public String getStr_lastlong() {
        return str_lastlong;
    }

    public void setStr_lastlong(String str_lastlong) {
        this.str_lastlong = str_lastlong;
    }

    public String getStr_feature_id() {
        return str_feature_id;
    }

    public void setStr_feature_id(String str_feature_id) {
        this.str_feature_id = str_feature_id;
    }

    public String getStr_remark() {
        return str_remark;
    }

    public void setStr_remark(String str_remark) {
        this.str_remark = str_remark;
    }

    public String getStr_lat() {
        return str_lat;
    }

    public void setStr_lat(String str_lat) {
        this.str_lat = str_lat;
    }

    public String getStr_long() {
        return str_long;
    }

    public void setStr_long(String str_long) {
        this.str_long = str_long;
    }


    public String getAuto_number() {
        return auto_number;
    }

    public void setAuto_number(String auto_number) {
        this.auto_number = auto_number;
    }



    public String getGeographoic_id() {
        return geographoic_id;
    }

    public void setGeographoic_id(String geographoic_id) {
        this.geographoic_id = geographoic_id;
    }

    public String getSubareaone() {
        return subareaone;
    }

    public void setSubareaone(String subareaone) {
        this.subareaone = subareaone;
    }

    public String getSubareatwo() {
        return subareatwo;
    }

    public void setSubareatwo(String subareatwo) {
        this.subareatwo = subareatwo;
    }

    public String getSubareathree() {
        return subareathree;
    }

    public void setSubareathree(String subareathree) {
        this.subareathree = subareathree;
    }

    String str_other_gp,str_challan_no,hhsc_pass_rework_status,meter_no;
    String str_diNumber,str_vendorName,str_storeKeepeName,strDiQty,strRecvQty,strpmaName,strCallType,strCallDetails,
            strcontractorName,strDamagedQty,strAccpetedQty,strInvoiceNo,str_landmark,str_isp_no,str_receiving_note_no,str_issue_note_no;

    public String getStr_receiving_note_no() {
        return str_receiving_note_no;
    }

    public void setStr_receiving_note_no(String str_receiving_note_no) {
        this.str_receiving_note_no = str_receiving_note_no;
    }

    public String getStr_issue_note_no() {
        return str_issue_note_no;
    }

    public void setStr_issue_note_no(String str_issue_note_no) {
        this.str_issue_note_no = str_issue_note_no;
    }

    public String getStr_isp_no() {
        return str_isp_no;
    }

    public void setStr_isp_no(String str_isp_no) {
        this.str_isp_no = str_isp_no;
    }

    public String getStrInvoiceNo() {
        return strInvoiceNo;
    }

    public String getStr_landmark() {
        return str_landmark;
    }

    public void setStr_landmark(String str_landmark) {
        this.str_landmark = str_landmark;
    }

    public void setStrInvoiceNo(String strInvoiceNo) {
        this.strInvoiceNo = strInvoiceNo;
    }

    public String getStrDamagedQty() {
        return strDamagedQty;
    }

    public void setStrDamagedQty(String strDamagedQty) {
        this.strDamagedQty = strDamagedQty;
    }

    public String getStrAccpetedQty() {
        return strAccpetedQty;
    }

    public void setStrAccpetedQty(String strAccpetedQty) {
        this.strAccpetedQty = strAccpetedQty;
    }

    public String getStrCallType() {
        return strCallType;
    }

    public String getStrcontractorName() {
        return strcontractorName;
    }

    public void setStrcontractorName(String strcontractorName) {
        this.strcontractorName = strcontractorName;
    }

    public void setStrCallType(String strCallType) {
        this.strCallType = strCallType;
    }

    public String getStrCallDetails() {
        return strCallDetails;
    }

    public void setStrCallDetails(String strCallDetails) {
        this.strCallDetails = strCallDetails;
    }

    public String getStrpmaName() {
        return strpmaName;
    }

    public void setStrpmaName(String strpmaName) {
        this.strpmaName = strpmaName;
    }

    public String getStr_diNumber() {
        return str_diNumber;

    }

    public void setStr_diNumber(String str_diNumber) {
        this.str_diNumber = str_diNumber;
    }

    public String getStr_vendorName() {
        return str_vendorName;
    }

    public void setStr_vendorName(String str_vendorName) {
        this.str_vendorName = str_vendorName;
    }

    public String getStr_storeKeepeName() {
        return str_storeKeepeName;
    }

    public void setStr_storeKeepeName(String str_storeKeepeName) {
        this.str_storeKeepeName = str_storeKeepeName;
    }

    public String getStrDiQty() {
        return strDiQty;
    }

    public void setStrDiQty(String strDiQty) {
        this.strDiQty = strDiQty;
    }

    public String getStrRecvQty() {
        return strRecvQty;
    }

    public void setStrRecvQty(String strRecvQty) {
        this.strRecvQty = strRecvQty;
    }

    public String getMeter_no() {
        return meter_no;
    }

    public void setMeter_no(String meter_no) {
        this.meter_no = meter_no;
    }

    public String getHhsc_pass_rework_status() {
        return hhsc_pass_rework_status;
    }

    public void setHhsc_pass_rework_status(String hhsc_pass_rework_status) {
        this.hhsc_pass_rework_status = hhsc_pass_rework_status;
    }

    public String getStr_challan_no() {
        return str_challan_no;
    }

    public void setStr_challan_no(String str_challan_no) {
        this.str_challan_no = str_challan_no;
    }

    public String getStr_other_gp() {
        return str_other_gp;
    }

    public void setStr_other_gp(String str_other_gp) {
        this.str_other_gp = str_other_gp;
    }




    public void nullify_DataHolder_workprogress() {

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

      
        geographoic_id=null;
        subareaone=null;
        subareatwo=null;
        subareathree=null;
        str_remark=null;
        str_lat=null;
        str_long=null;
        camera_lat=null;
        camera_long=null;
        camera_time=null;
        auto_number=null;
        str_lastlat=null;
        str_lastlong=null;
        str_approved_id=null;
        str_remark=null;
        str_other_gp=null;
        str_team_type=null;
        str_qty=null;
        str_date=null;
        str_insp_date=null;
        str_vendor_rspName=null;
        str_vendor_inspCode=null;
        str_item_code=null;
        str_vendor_id=null;
        str_item_qty=null;
        selectedItems=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue=null;
        item_name=null;
        //-----for Bajaj----------//

        hhsc_manpower=null;
        network_manpower=null;

        selectedDate=null;
        str_insp_date=null;
        str_di_no=null;
        str_lr_no=null;
        str_bel_rsp=null;
        str_fact_rsp=null;
        str_pma_rsp=null;

        selectedItems=null;
        selectedItems2=null;
        str_item_yes=null;
        str_item_cancel=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue1=null;
        str_store_id=null;
        str_challan_no=null;
        str_tkc_id=null;
        network_pass_rework_status=null;
        hhsc_pass_rework_status=null;
        meter_no=null;

        str_diNumber=null;
        str_vendor_rspName=null;
        str_storeKeepeName=null;
        item_id=null;
        strDiQty=null;
        strRecvQty=null;
        str_vendor_rspName=null;
        str_storeKeepeName=null;
        item_id=null;
        strDiQty=null;

        strpmaName=null;
        str_item_code=null;
        str_item_cancel=null;

        hhsc_manpower=null;
        network_manpower=null;
        strCallType=null;
        strCallDetails=null;

        str_inspectedQty=null;
        str_passedQTY=null;
        strCallDetails=null;
        network_pass_rework_status=null;
        strcontractorName=null;
        str_checkbox_inputValue=null;
        meMap=null;
        str_issueType=null;
        str_otherissue=null;
        strInvoiceNo=null;
        strRecvQty=null;
        strRecvQty=null;
        strDamagedQty=null;
        strAccpetedQty=null;
        str_landmark=null;


    }


    public void nullify_DataHolder_workprogress_network() {

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
        str_remark=null;
        str_lat=null;
        str_long=null;
        camera_lat=null;
        camera_long=null;
        camera_time=null;
        auto_number=null;
        str_remark=null;
        str_qty=null;
        str_date=null;
        str_insp_date=null;
        selectedItems=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue=null;
        item_name=null;
        selectedDate=null;
        str_insp_date=null;
        selectedItems=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue1=null;
        str_store_id=null;
        str_challan_no=null;
        str_tkc_id=null;
        network_pass_rework_status=null;
        hhsc_pass_rework_status=null;
        meter_no=null;
    }

    public void nullify_DataHolder_workprogress_hhsc() {

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


        str_remark=null;
        str_lat=null;
        str_long=null;
        camera_lat=null;
        camera_long=null;
        camera_time=null;
        auto_number=null;

        str_remark=null;

        str_item_qty=null;
        selectedItems=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue=null;
        item_name=null;

        //-----for Bajaj----------//
        selectedDate=null;
        str_insp_date=null;

        selectedItems=null;
        selectedItems_value=null;
        str_pole_item_id=null;
        str_checkbox_inputValue1=null;
        network_pass_rework_status=null;
        hhsc_pass_rework_status=null;
        meter_no=null;
    }


    String vpick_date;
    String vgeographoic_id;
    String vsubareaone;
    String vsubareatwo;
    String vsubareathree;
    String vpick_item;

    public String getVpick_date() {
        return vpick_date;
    }

    public void setVpick_date(String vpick_date) {
        this.vpick_date = vpick_date;
    }

    public String getVgeographoic_id() {
        return vgeographoic_id;
    }

    public void setVgeographoic_id(String vgeographoic_id) {
        this.vgeographoic_id = vgeographoic_id;
    }

    public String getVsubareaone() {
        return vsubareaone;
    }

    public void setVsubareaone(String vsubareaone) {
        this.vsubareaone = vsubareaone;
    }

    public String getVsubareatwo() {
        return vsubareatwo;
    }

    public void setVsubareatwo(String vsubareatwo) {
        this.vsubareatwo = vsubareatwo;
    }

    public String getVsubareathree() {
        return vsubareathree;
    }

    public void setVsubareathree(String vsubareathree) {
        this.vsubareathree = vsubareathree;
    }

    public String getVpick_item() {
        return vpick_item;
    }

    public void setVpick_item(String vpick_item) {
        this.vpick_item = vpick_item;
    }
}
