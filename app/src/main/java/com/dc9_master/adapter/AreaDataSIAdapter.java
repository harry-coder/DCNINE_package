package com.dc9_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dc9_master.R;
import com.dc9_master.data_holder.DataHolder_workprogress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitinb on 07-03-2016.
 */
public class AreaDataSIAdapter extends ArrayAdapter<String> {
    private List<String> areaList;
    private List<String> areaCodeList;
    private Context context;
    ArrayList<Boolean> positionArray,positionArray2,positionArray3;
    ArrayList<String> selectedItems = new ArrayList<String>();
    ArrayList<String> selectedItems2 = new ArrayList<String>();
    ArrayList<String> selectedItems_value = new ArrayList<String>();
    ArrayList<String> selectedItems3 = new ArrayList<String>();

    public AreaDataSIAdapter(Context context, int textViewResourceId,
                           List<String> offersAreaList) {
        super(context, textViewResourceId, offersAreaList);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.areaList = offersAreaList;

        positionArray = new ArrayList<Boolean>(offersAreaList.size());
        positionArray2 = new ArrayList<Boolean>(offersAreaList.size());
        for(int i =0;i<offersAreaList.size();i++){
            positionArray.add(false);
            positionArray2.add(false);
        }

    }

    public AreaDataSIAdapter(Context context, List<String> offersAreaList, List<String> offersAreaCodeList) {
        super(context, R.layout.listview_with_checkbox2, offersAreaList);
        this.context=context;
        this.areaList=offersAreaList;
        this.areaCodeList=offersAreaCodeList;

        positionArray = new ArrayList<Boolean>(offersAreaList.size());
        positionArray2 = new ArrayList<Boolean>(offersAreaList.size());
        positionArray3 = new ArrayList<Boolean>(offersAreaList.size());
        for(int i =0;i<offersAreaList.size();i++){
            positionArray.add(false);
            positionArray2.add(false);
            positionArray3.add(false);
        }

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row=convertView;
        final ViewHolder holder;
        if (row==null) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;

            //row=inflater.inflate(R.layout.listview_with_checkbox2, parent, false);
            row=inflater.inflate(R.layout.listview_with_inputcheckbox, parent, false);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox)row.findViewById(R.id.checkBox1);
            holder.checkBox2 = (CheckBox)row.findViewById(R.id.checkBox2);
            holder.textInListView = (TextView)row.findViewById(R.id.textView1);
           //            holder.checkBox.setTypeface(fontFace);

            row.setTag(holder);
        } else {
            //holder = (View) row;
            holder = (ViewHolder) row.getTag();

            /* When a listview recycles views , it recycles its present state as well as listeners attached to it.
             * if the checkbox was checked and has a onCheckedChangeListener set, both will remain a part of
             * recycled view based on position. So it is our responsibility to reset all states and remove
             *  previous listeners.
             *  The listener was removed as below:-
             */
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox2.setOnCheckedChangeListener(null);

        }

        holder.textInListView.setText(areaList.get(position));
        holder.checkBox.setFocusable(false);
        holder.checkBox2.setFocusable(false);
        holder.checkBox2.setChecked(positionArray2.get(position));
        holder.checkBox.setChecked(positionArray.get(position));
        holder.textInListView.setText(areaList.get(position));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                List filterOffersByThisAreaList = new ArrayList();
                if (isChecked) {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray.set(position, true);
                    positionArray2.set(position,false);
                    holder.checkBox2.setChecked(false);
                    holder.checkBox.setTag("zero");
                    //add the checked item in to area list which used to filter offers.
                    filterOffersByThisAreaList.add(areaList.get(position));
                    System.out.println("Position" + position);
                    System.out.println("Position2" + areaList.get(position));
                    System.out.println("CODE IS " + areaCodeList.get(position));
                    selectedItems.add(String.valueOf(areaCodeList.get(position)));
                    DataHolder_workprogress.getInstance().setSelectedItems(selectedItems);
                    System.out.println("ffff" + position);
                } else {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray.set(position, false);
                    selectedItems.remove(String.valueOf(areaCodeList.get(position)));
                    DataHolder_workprogress.getInstance().setSelectedItems(selectedItems);
                    //remove the unchecked item in to area list which used to filter offers.
                    //filterOffersByThisAreaList.remove(areaList.get(position));
                    filterOffersByThisAreaList.remove(areaCodeList.get(position));

                }
            }
        });
        holder.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                List filterOffersByThisAreaList2 =new ArrayList();
                if (isChecked) {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray2.set(position, true);
                    positionArray.set(position,false);
                    //add the checked item in to area list which used to filter offers.
                    filterOffersByThisAreaList2.add(areaList.get(position));
                    holder.checkBox.setChecked(false);
                    //selectedItems2.add(String.valueOf(position));
                    System.out.println("CODE IS cancel " + areaCodeList.get(position));
                    selectedItems2.add(String.valueOf(areaCodeList.get(position)));
                    DataHolder_workprogress.getInstance().setSelectedItems2(selectedItems2);
//                    String str = (String) holder.checkBox.getTag();
//
//                    if( str.equals("zero") ){
//                        holder.checkBox.setChecked(true);
//                        System.out.println("in ifclick");
//                    }
                } else {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray2.set(position, false);
                    selectedItems2.remove(String.valueOf(areaCodeList.get(position)));
                    DataHolder_workprogress.getInstance().setSelectedItems2(selectedItems2);
                    //remove the unchecked item in to area list which used to filter offers.
                    //filterOffersByThisAreaList2.remove(areaList.get(position));
                    filterOffersByThisAreaList2.remove(areaCodeList.get(position));

                }
            }
        });

/*        holder.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                List filterOffersByThisAreaList3 = new ArrayList();
                if (isChecked) {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray3.set(position, true);
                    holder.checkBox2.setChecked(false);
                    holder.checkBox.setChecked(false);
                    holder.checkBox3.setTag("zero");
                    //add the checked item in to area list which used to filter offers.
                    filterOffersByThisAreaList3.add(areaList.get(position));
                    System.out.println("Position" + position);
                    System.out.println("Position2" + areaList.get(position));
                    System.out.println("CODE IS " + areaCodeList.get(position));
                    selectedItems3.add(String.valueOf(areaCodeList.get(position)));
                    DataHolder_workprogress.getInstance().setSelectedItems_na(selectedItems3);
                    System.out.println("ffff" + position);
                } else {
                    //change state of concern item in 'positionArray' array to 'True'
                    positionArray.set(position, false);
                    //remove the unchecked item in to area list which used to filter offers.
                    //filterOffersByThisAreaList.remove(areaList.get(position));
                    filterOffersByThisAreaList3.remove(areaCodeList.get(position));

                }
            }
        });

        holder.inputBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                *//*final Dialog dialog = new Dialog(Site_Pole_Check_Activity1.getsActivity());
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
//               ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                image.setImageResource(R.drawable.ic_launcher); //line 115

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();*//*

                //final Dialog dialog = new Dialog(Site_Pole_Check_Activity1.getsActivity());
                List filterOffersByThisAreaList = new ArrayList();
                filterOffersByThisAreaList.add(areaList.get(position));
                //System.out.println("Input value position IS:  " + areaCodeList.get(position));
                final Dialog dialog = new Dialog(SiteInspectionChecklist_Activity.getsActivity());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                dialog.setContentView(R.layout.custom);

                dialog.setTitle(areaList.get(position));
                // final TextView test = (TextView) dialog.findViewById(R.id.test);
                final EditText edt = (EditText) dialog.findViewById(R.id.edit1);

                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                //Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        System.out.println("Input value position IS:  " + areaCodeList.get(position));
                        System.out.println("Input value IS:  " + edt.getText().toString());
                        System.out.println("Input value IS Nitin :  " + String.valueOf(areaCodeList.get(position))+ "__" +edt.getText().toString());

                        selectedItems_value.add(String.valueOf(areaCodeList.get(position))+ "_" +edt.getText().toString());
                        //selectedItems_value.add(String.valueOf(areaCodeList.get(position)));
                        DataHolder_SiteInspection.getInstance().setSelectedItems_value(selectedItems_value);
                        dialog.dismiss();

                    }
                });

                dialog.show();

                *//*AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Site_Pole_Check_Activity1.getsActivity());
                LayoutInflater inflater = Site_Pole_Check_Activity1.getsActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                dialogBuilder.setView(dialogView);

                final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

                dialogBuilder.setTitle(areaList.get(position));
                dialogBuilder.setMessage("Enter Value(if any)");
                dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        System.out.println("Input value position IS:  " + areaCodeList.get(position));
                        System.out.println("Input value IS:  " + edt.getText().toString());
                        System.out.println("Input value IS Nitin :  " + String.valueOf(areaCodeList.get(position))+ "__" +edt.getText().toString());

                        selectedItems_value.add(String.valueOf(areaCodeList.get(position))+ "_" +edt.getText().toString());
                        //selectedItems_value.add(String.valueOf(areaCodeList.get(position)));
                        DataHolder_SiteInspection.getInstance().setSelectedItems_value(selectedItems_value);

                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();*//*

                // Toast.makeText(context, "Enter Input value", Toast.LENGTH_SHORT).show();

            }
        });*/
        return row;
    }




    static class ViewHolder{

        TextView textInListView;
        CheckBox checkBox;
        CheckBox checkBox2;

    }
}