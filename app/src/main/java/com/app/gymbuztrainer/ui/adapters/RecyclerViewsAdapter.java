package com.app.gymbuztrainer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.app.gymbuztrainer.ui.viewbinders.abstracts.RecyclerViewsBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 8/10/2017.
 */

public class RecyclerViewsAdapter<T> extends RecyclerView.Adapter<RecyclerViewsBinder.BaseViewHolder> {
    private List<T> collections;
    private RecyclerViewsBinder<T> viewBinder;
    private Context mContext;
    private int mLayoutResouceID;

    public RecyclerViewsAdapter(List<T> collections, RecyclerViewsBinder<T> viewBinder, Context context, int mLayoutResouceID) {
        this.collections = collections;
        this.viewBinder = viewBinder;
        this.mContext = context;
        this.mLayoutResouceID = mLayoutResouceID;
    }

    public <T> RecyclerViewsAdapter(ArrayList<T> dataColloction, RecyclerViewsBinder<T> viewBinder, Context context) {
    }

    @Override
    public RecyclerViewsBinder.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return (RecyclerViewsBinder.BaseViewHolder) this.viewBinder.createViewHolder(this.viewBinder.createView(this.mContext,this.mLayoutResouceID));
    }

    @Override
    public void onBindViewHolder(RecyclerViewsBinder.BaseViewHolder holder, int position) {
        T entity = (T)this.collections.get(position);
        this.viewBinder.bindView(entity,position,holder,this.mContext);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return this.collections.size();
    }
    public T getItemFromList(int index ) {
        return collections.get( index );
    }

    public List<T> getList() {
        return collections;
    }
    /**
     * Clears the internal list
     */
    public void clearList() {
        collections.clear();
        notifyDataSetChanged();
    }

    /**
     * Adds a entity to the list and calls {@link #notifyDataSetChanged()}.
     * Should not be used if lots of NotificationDummy are added.
     *
     * @see #addAll(List)
     */
    public void add( T entity ) {
        collections.add( entity );
        notifyDataSetChanged();
    }

    /**
     * Adds a NotificationDummy to the list and calls
     * {@link #notifyDataSetChanged()}. Can be used {
     * {@link List#subList(int, int)}.
     *
     * @see #addAll(List)
     */
    public void addAll( List<T> entityList ) {
        collections.addAll( entityList );
        notifyDataSetChanged();
    }
}
