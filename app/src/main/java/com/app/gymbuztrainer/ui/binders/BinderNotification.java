package com.app.gymbuztrainer.ui.binders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.NotificationEnt;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.helpers.BasePreferenceHelper;
import com.app.gymbuztrainer.helpers.DateHelper;
import com.app.gymbuztrainer.ui.viewbinders.abstracts.ViewBinder;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khan_muhammad on 9/15/2017.
 */

public class BinderNotification extends ViewBinder<NotificationEnt> {

    private Context context;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;

    public BinderNotification(Context context, BasePreferenceHelper prefHelper) {
        super(R.layout.fragment_notification_item);
        this.context = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(NotificationEnt entity, int position, int grpPosition, View view, Activity activity) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        if (entity.getNotification() != null && !entity.getNotification().equals("")) {
            viewHolder.tvMsg.setText(entity.getNotification().getMessage() + "");
        } else {
            viewHolder.tvMsg.setText("-");
        }

        if (entity.getNotification().getCreatedDate() != null) {
            viewHolder.tvDate.setVisibility(View.VISIBLE);
            viewHolder.tvDate.setText(DateHelper.getLocalTimeDateRequst(entity.getNotification().getCreatedDate()));
        }
        else {
            viewHolder.tvDate.setVisibility(View.GONE);
        }

    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.ivNotification)
        ImageView ivNotification;
        @BindView(R.id.tv_msg)
        AnyTextView tvMsg;
        @BindView(R.id.tv_date)
        AnyTextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
