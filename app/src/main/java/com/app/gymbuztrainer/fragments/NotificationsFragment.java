package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.NotificationEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.WebServiceConstants;
import com.app.gymbuztrainer.ui.adapters.ArrayListAdapter;
import com.app.gymbuztrainer.ui.binders.BinderNotification;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by khan_muhammad on 9/15/2017.
 */

public class NotificationsFragment extends BaseFragment {

    @BindView(R.id.lv_notification)
    ListView lvNotification;
    Unbinder unbinder;
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    private List<NotificationEnt> collection;
    private ArrayListAdapter<NotificationEnt> adapter;

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayListAdapter<>(getDockActivity(), new BinderNotification(getDockActivity(), prefHelper));
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getMainActivity().getResourceString(R.string.notification1));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        serviceHelper.enqueueCall(headerWebService.getNotifications(), WebServiceConstants.NOTIFICATIONS);

        lvNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.NOTIFICATIONS:
                bindData((ArrayList<NotificationEnt>) result);
                break;

        }
    }

    public void bindData(ArrayList<NotificationEnt> result) {

        collection = new ArrayList<>();
        for (NotificationEnt item : result) {

            if (item.getNotification().getMessage() != null && !item.getNotification().getMessage().equals("")) {
                collection.add(item);
            }
        }

        if (collection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvNotification.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvNotification.setVisibility(View.VISIBLE);

        }
        adapter.clearList();
        lvNotification.setAdapter(adapter);
        adapter.addAll(collection);

    }

}
