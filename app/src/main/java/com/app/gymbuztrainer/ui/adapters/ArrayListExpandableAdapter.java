package com.app.gymbuztrainer.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.app.gymbuztrainer.ui.viewbinders.abstracts.ExpandableListViewBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayListExpandableAdapter<T, E> extends BaseExpandableListAdapter {

    protected Activity mContext;

    protected ExpandableListViewBinder<T, E> viewBinder;

    protected ExpandableListView SettingsList;


    private ArrayList<T> headerCollection = new ArrayList<>();
    private HashMap<T, ArrayList<E>> ChildCollection = new HashMap<>();

    public ArrayListExpandableAdapter(Activity context, ArrayList<T> headerCollection, HashMap<T, ArrayList<E>> listDataChild,
                                      ExpandableListViewBinder<T, E> viewBinder, ExpandableListView SettingsList) {
        mContext = context;
        this.headerCollection = headerCollection;
        this.ChildCollection = listDataChild;
        this.viewBinder = viewBinder;
        this.SettingsList=SettingsList;
    }

    public HashMap<T, ArrayList<E>> getChildCollection(){
        return ChildCollection;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return this.ChildCollection.get(this.headerCollection.get(groupPosition)).size();
    }

    @Override
    public int getGroupCount() {
        return headerCollection.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerCollection.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.ChildCollection.get(this.headerCollection.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {

        View convertView = view;
        if (convertView == null) {
            convertView = viewBinder.createChildView(mContext);
        }

        final E childItem = (E) getChild(groupPosition, childPosition);

        viewBinder.bindChildView(childItem, childPosition, 0, convertView, mContext);

        return convertView;


    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View view, ViewGroup parent) {

        View convertView = view;

        if (convertView == null) {
            convertView = viewBinder.createGroupView(mContext);
        }

        T groupItem = (T) getGroup(groupPosition);
        //SettingsList.expandGroup(groupPosition);
        viewBinder.bindGroupView(groupItem, groupPosition, 0, convertView, mContext,isExpanded);

        return convertView;


    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void clearList() {
        this.ChildCollection.clear();
        notifyDataSetChanged();
    }

    /**
     * Adds a entity to the list and calls {@link #notifyDataSetChanged()}.
     * Should not be used if lots of NotificationDummy are added.
     *
     * @se addAll(List)
     */
    public void add(T entity, ArrayList<E> Childs) {
        this.ChildCollection.put(entity, Childs);
        notifyDataSetChanged();
    }

    /**
     * Adds a NotificationDummy to the list and calls
     * {@link #notifyDataSetChanged()}. Can be used {
     * {@link List#subList(int, int)}.
     *
     * @se addAll(List)
     */
    public void addAll(HashMap<T, ArrayList<E>> listHashMap) {
        this.ChildCollection.putAll(listHashMap);
        notifyDataSetChanged();
    }

}
