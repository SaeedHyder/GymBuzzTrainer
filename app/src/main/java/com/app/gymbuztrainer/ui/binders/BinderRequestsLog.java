package com.app.gymbuztrainer.ui.binders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.AllRequestSupportEnt;
import com.app.gymbuztrainer.entities.RequestEnt;
import com.app.gymbuztrainer.helpers.BasePreferenceHelper;
import com.app.gymbuztrainer.helpers.DateHelper;
import com.app.gymbuztrainer.helpers.DialogHelper;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.interfaces.RequestInterface;
import com.app.gymbuztrainer.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.gymbuztrainer.ui.viewbinders.abstracts.ViewBinder;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 5/29/2018.
 */

public class BinderRequestsLog extends RecyclerViewBinder<AllRequestSupportEnt> {

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
        return new ViewHolder(view);
    }

    @Override
    public void bindView(final AllRequestSupportEnt entity, final int position, Object holder, Context context) {

        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.llbtnSupport.setVisibility(View.VISIBLE);
        viewHolder.llDateTime.setVisibility(View.VISIBLE);

        viewHolder.btnSupport.setText(context.getResources().getString(R.string.completed));

        if (entity.getUser().getProfileImagePath() != null && !entity.getUser().getProfileImagePath().equals("")) {
            imageLoader.displayImage(entity.getUser().getProfileImagePath(), viewHolder.ivNotification);
        }


        viewHolder.txtName.setText(entity.getUser().getFullName() + "");
        viewHolder.txtZone.setText(entity.getGymMachine().getZone().getZoneName() + "");
        viewHolder.txtMachine.setText(entity.getGymMachine().getMachine().getName() + "");
        viewHolder.tvTime.setText(DateHelper.getLocalTimeDateRequst(entity.getAcceptedTime()));



        viewHolder.btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.ivNotification)
        CircleImageView ivNotification;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtZone)
        AnyTextView txtZone;
        @BindView(R.id.txtMachine)
        AnyTextView txtMachine;
        @BindView(R.id.tv_time)
        AnyTextView tvTime;
        @BindView(R.id.tv_date)
        AnyTextView tvDate;
        @BindView(R.id.llDateTime)
        LinearLayout llDateTime;
        @BindView(R.id.btnSupport)
        Button btnSupport;
        @BindView(R.id.llbtnSupport)
        LinearLayout llbtnSupport;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}