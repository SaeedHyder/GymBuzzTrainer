package com.app.gymbuzz.ui.binders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.RequestEnt;
import com.app.gymbuzz.helpers.BasePreferenceHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.viewbinders.abstracts.ViewBinder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created on 5/29/2018.
 */

public class BinderRequests extends ViewBinder<RequestEnt> {

    private Context context;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;

    public BinderRequests(Context context, BasePreferenceHelper prefHelper) {
        super(R.layout.fragment_support_request_item);
        this.context = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new BinderRequests.ViewHolder(view);
    }

    @Override
    public void bindView(RequestEnt entity, int position, int grpPosition, View view, Activity activity) {

        BinderRequests.ViewHolder viewHolder = (BinderRequests.ViewHolder) view.getTag();

        viewHolder.llbtnSupport.setVisibility(View.VISIBLE);
        viewHolder.llDateTime.setVisibility(View.GONE);

        viewHolder.btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInCenter(context,context.getString(R.string.will_be_imp_beta));
            }
        });

    }

    public static class ViewHolder extends BaseViewHolder {

        LinearLayout llbtnSupport;
        LinearLayout llDateTime;
        Button btnSupport;

        public ViewHolder(View view) {

            llbtnSupport = (LinearLayout) view.findViewById(R.id.llbtnSupport);
            llDateTime = (LinearLayout) view.findViewById(R.id.llDateTime);
            btnSupport = (Button) view.findViewById(R.id.btnSupport);

        }
    }
}
