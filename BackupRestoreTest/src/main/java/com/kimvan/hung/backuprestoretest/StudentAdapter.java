package com.kimvan.hung.backuprestoretest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by h on 17/01/2018.
 */

public class StudentAdapter extends BaseAdapter {
    ArrayList<Student> rowItem;
    Context context;

    public StudentAdapter(ArrayList<Student> rowItem, Context context) {
        this.rowItem = rowItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItem.indexOf(getItem(position));
    }

    private class ViewHolder{
        TextView name;
        TextView date;
        TextView number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view,null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView)convertView.findViewById(R.id.name_item);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date_item);
            viewHolder.number = (TextView)convertView.findViewById(R.id.telephone_number_item);

            Student student_row = rowItem.get(position);

            viewHolder.name.setText(student_row.getName());
            viewHolder.date.setText(student_row.getBornDate());
            viewHolder.number.setText(student_row.getTelephoneNumber());

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }
}
