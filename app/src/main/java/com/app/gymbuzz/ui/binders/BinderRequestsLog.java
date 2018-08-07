package com.app.gymbuzz.ui.binders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.RequestEnt;
import com.app.gymbuzz.helpers.BasePreferenceHelper;
import com.app.gymbuzz.ui.viewbinders.abstracts.ViewBinder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created on 5/29/2018.
 */

public class BinderRequestsLog extends ViewBinder<RequestEnt> {

    private Context context;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;

    public BinderRequestsLog(Context context, BasePreferenceHelper prefHelper) {
        super(R.layout.fragment_support_request_item);
        this.context = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new BinderRequestsLog.ViewHolder(view);
    }

    @Override
    public void bindView(RequestEnt entity, int position, int grpPosition, View view, Activity activity) {

        BinderRequestsLog.ViewHolder viewHolder = (BinderRequestsLog.ViewHolder) view.getTag();

        viewHolder.llbtnSupport.setVisibility(View.GONE);
        viewHolder.llDateTime.setVisibility(View.VISIBLE);

    }

    public static class ViewHolder extends BaseViewHolder {

        LinearLayout llbtnSupport;
        LinearLayout llDateTime;

        public ViewHolder(View view) {

            llbtnSupport = (LinearLayout) view.findViewById(R.id.llbtnSupport);
            llDateTime = (LinearLayout) view.findViewById(R.id.llDateTime);

        }
    }
}