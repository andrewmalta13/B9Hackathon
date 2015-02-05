package com.example.book;
 
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
 
    private Context context;
    private ArrayList<String> semesterNames;
 
    public ExpandableListViewAdapter(Context context, ArrayList<String> semesterNames) {
        this.context = context;
        this.semesterNames = semesterNames;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.semesterNames.get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final String childText = (String) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
            convertView = infalInflater.inflate(android.R.layout.simple_list_item_activated_1, null);
        }
 
        TextView childTextView = (TextView) convertView
                .findViewById(android.R.id.text1);
 
        childTextView.setText(childText);
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.semesterNames.size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return "Semesters";
    }
 
    @Override
    public int getGroupCount() {
        return 1;
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_list_header, null);
        }
 
        TextView listHeaderView = (TextView) convertView
                .findViewById(R.id.list_header);
        listHeaderView.setText("Semesters");
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}