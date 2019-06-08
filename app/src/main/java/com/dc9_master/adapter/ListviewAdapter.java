package com.dc9_master.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dc9_master.R;
import com.dc9_master.data_holder.DataHolder_workprogress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListviewAdapter extends BaseAdapter {

    public Context context;
    public List<String> areaList;
    public List<String> areaCodeList;
    public List<String> areaqtyList;
    public List<String> areaqtyActualList;
    public List<String> areainputlist;
    //private List list;
    private List elist;

    //Integer count_list=18;
    Integer count_list= DataHolder_workprogress.getInstance().getInt_count();

    public ArrayList<String> selectedItems_value = new ArrayList<String>();
    HashMap<String, String> meMap=new HashMap<String, String>();

    LayoutInflater mInflater;
    public ListviewAdapter(Context context,List<String> list, List<String> offersAreaCodeList, List elist){
        this.context = context;
        this.areaList  =list;
        this.areaCodeList=offersAreaCodeList;
        /*this.areaqtyList=offersAreaQtyList;
        this.areaqtyActualList=offersAreaQtyActualList;*/
        this.elist  =elist;

        for(int i=0;i<count_list;i++){
            elist.add("");
        }
        /*for (int i = 0; i < 20; i++) {
            ListItem listItem = new ListItem();
            listItem.caption = "Caption" + i;
            elist.add(listItem);
        }*/
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return areaCodeList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        convertView=null;
        if (convertView == null) {
            holder = new ViewHolder();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_with_inputcheckbox1, null);
            holder.textInListView = (TextView)convertView.findViewById(R.id.textView1);


            holder.caption = (EditText) convertView.findViewById(R.id.edtinput);

            holder.caption.setTag(position);
            holder.textInListView.setText(areaList.get(position).toString());
            holder.caption.setText(elist.get(position).toString());


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        int tag_position=(Integer) holder.caption.getTag();
        holder.caption.setId(tag_position);

        holder.caption.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                selectedItems_value.clear();
            }

            @Override
            public void afterTextChanged(Editable s) {

                final int position2 = holder.caption.getId();
                final EditText Caption = (EditText) holder.caption;
                if(Caption.getText().toString().length()>0){
                    try
                    {
                        //elist.set(position2,Integer.parseInt(Caption.getText().toString()));
                        elist.set(position2,Float.parseFloat(Caption.getText().toString()));
                        System.out.println("Input value IS:  " + String.valueOf(areaCodeList.get(position2))+ "__" +Caption.getText().toString());

                        if( meMap.containsKey(areaCodeList.get(position2))){

                            meMap.remove(areaCodeList.get(position2));
                            meMap.put(areaCodeList.get(position2),areaCodeList.get(position2)+"_"+Caption.getText().toString());
                        }else{
                            meMap.put(areaCodeList.get(position2),areaCodeList.get(position2)+"_"+Caption.getText().toString());
                        }


                        selectedItems_value.add(String.valueOf(areaCodeList.get(position2))+ "_" +Caption.getText().toString());
                        DataHolder_workprogress.getInstance().setMeMap(meMap);

                    }
                    catch (NumberFormatException nfe)
                    {
                        nfe.printStackTrace();
                    }

                }else{
                    Toast.makeText(context, "Please enter some value", Toast.LENGTH_SHORT).show();
                }


            }

        });

        return convertView;
    }

}

class ViewHolder {
    TextView textInListView;
    EditText caption;
}

