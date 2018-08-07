package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.RequestEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.ui.adapters.ArrayListAdapter;
import com.app.gymbuzz.ui.binders.BinderRequests;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/29/2018.
 */

public class SupportRequestFragment extends BaseFragment {

    @BindView(R.id.lvRequests)
    ListView lvRequests;
    @BindView(R.id.btnViewSupportLog)
    Button btnViewSupportLog;
    Unbinder unbinder;
    private List<RequestEnt> notificationCollection;
    private ArrayListAdapter<RequestEnt> adapter;

    public static SupportRequestFragment newInstance() {
        return new SupportRequestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayListAdapter<>(getDockActivity(), new BinderRequests(getDockActivity(),prefHelper));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_support_request, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindData();

        btnViewSupportLog.setText(getString(R.string.view_support_log));

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.support_req));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnViewSupportLog)
    public void onViewClicked() {
        getDockActivity().replaceDockableFragment(SupportLogFragment.newInstance(), SupportLogFragment.class.getSimpleName());
    }

    public void bindData() {

        notificationCollection = new ArrayList<>();
        notificationCollection.add(new RequestEnt(""));
        notificationCollection.add(new RequestEnt(""));
        notificationCollection.add(new RequestEnt(""));
        notificationCollection.add(new RequestEnt(""));


        adapter.clearList();
        adapter.addAll(notificationCollection);
        lvRequests.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
