package com.app.gymbuztrainer.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.AllRequestSupportEnt;
import com.app.gymbuztrainer.helpers.BasePreferenceHelper;
import com.app.gymbuztrainer.helpers.DialogHelper;
import com.app.gymbuztrainer.interfaces.RequestInterface;
import com.app.gymbuztrainer.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 5/29/2018.
 */

public class BinderRequests extends RecyclerViewBinder<AllRequestSupportEnt> {

    private Context context;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;
    private RequestInterface requestInterface;
    private  ViewHolder globleHolder;

    public BinderRequests(Context context, BasePreferenceHelper prefHelper,RequestInterface requestInterface) {
        super(R.layout.fragment_support_request_item);
        this.context = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
        this.requestInterface=requestInterface;

    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(final AllRequestSupportEnt entity, final int position, Object holder, Context context) {

        ViewHolder viewHolder = (ViewHolder)holder;
        globleHolder= (ViewHolder)holder;

        viewHolder.llbtnSupport.setVisibility(View.VISIBLE);
        viewHolder.llDateTime.setVisibility(View.GONE);

        if (entity.getUser().getProfileImagePath() != null && !entity.getUser().getProfileImagePath().equals("")) {
            imageLoader.displayImage(entity.getUser().getProfileImagePath(), viewHolder.ivNotification);
        }

        viewHolder.txtName.setText(entity.getUser().getFullName() + "");
        viewHolder.txtZone.setText(entity.getGymMachine().getZone().getZoneName() + "");
        viewHolder.txtMachine.setText(entity.getGymMachine().getMachine().getName() + "");
        //   viewHolder.tvTime.setText(DateHelper.getLocalTimeDateRequst(entity.getCreatedDate()));

        if (entity.getAccepted()) {
            viewHolder.btnSupport.setBackground(context.getResources().getDrawable(R.drawable.btn));
            viewHolder.btnSupport.setTextColor(context.getResources().getColor(R.color.white));
            viewHolder.btnSupport.setText(R.string.job_done);
        } else {
            viewHolder.btnSupport.setBackground(context.getResources().getDrawable(R.drawable.btn_transparent));
            viewHolder.btnSupport.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.btnSupport.setText(R.string.support);
        }

        viewHolder.btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!entity.getAccepted()) {
                   jobRequestDialoge(viewHolder,entity,position);
                } else {
                   jobDoneDialoge(viewHolder,entity,position);
                }
            }
        });

    }

    private void jobDoneDialoge(ViewHolder viewHolder, AllRequestSupportEnt entity, int position) {

        DialogHelper dialogHelper = new DialogHelper(context);
        dialogHelper.initJobDoneDialoge(R.layout.job_done_dialoge, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestInterface.onJobDoneClick(entity,position);
                dialogHelper.hideDialog();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelper.hideDialog();
            }
        });
        dialogHelper.setCancelable(false);
        dialogHelper.showDialog();
    }

    private void jobRequestDialoge(ViewHolder viewHolder, AllRequestSupportEnt entity, int position) {

        DialogHelper dialogHelper = new DialogHelper(context);
        dialogHelper.initRequestDialoge(R.layout.request_dialoge, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestInterface.onRequestClick(entity,position);
                dialogHelper.hideDialog();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelper.hideDialog();
            }
        });
        dialogHelper.setCancelable(false);
        dialogHelper.showDialog();
    }

  /*  @Override
    public void onSuccess(String message) {

        globleHolder.btnSupport.setBackground(context.getResources().getDrawable(R.drawable.btn));
        globleHolder.btnSupport.setTextColor(context.getResources().getColor(R.color.white));
        globleHolder.btnSupport.setText(R.string.job_done);
        //entity.setAccepted(true);

    }*/


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
