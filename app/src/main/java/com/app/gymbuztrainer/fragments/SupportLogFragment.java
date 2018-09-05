package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.AllRequestSupportEnt;
import com.app.gymbuztrainer.entities.RequestEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.interfaces.RequestInterface;
import com.app.gymbuztrainer.ui.adapters.ArrayListAdapter;
import com.app.gymbuztrainer.ui.binders.BinderRequests;
import com.app.gymbuztrainer.ui.binders.BinderRequestsLog;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.CustomRecyclerView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.gymbuztrainer.global.WebServiceConstants.ALLLOGSUPPORT;

/**
 * Created on 5/29/2018.
 */

public class SupportLogFragment extends BaseFragment {

    @BindView(R.id.lvRequests)
    CustomRecyclerView lvRequests;
    @BindView(R.id.btnViewSupportLog)
    Button btnViewSupportLog;
    Unbinder unbinder;
    private List<RequestEnt> notificationCollection;
    private ArrayListAdapter<AllRequestSupportEnt> adapter;
    @BindView(R.id.mainFrame)
    LinearLayout mainFrame;
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;

    public static SupportLogFragment newInstance() {
        return new SupportLogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   adapter = new ArrayListAdapter<>(getDockActivity(), new BinderRequestsLog(getDockActivity(), prefHelper));
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

        mainFrame.setVisibility(View.GONE);
        serviceHelper.enqueueCall(headerWebService.GetAllSupportLog(1), ALLLOGSUPPORT);

        btnViewSupportLog.setText(getString(R.string.view_support_req));

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        super.ResponseSuccess(result, Tag, message);
        switch (Tag) {
            case ALLLOGSUPPORT:
                bindData((ArrayList<AllRequestSupportEnt>) result);
                mainFrame.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.support_Log));
    }


    @OnClick(R.id.btnViewSupportLog)
    public void onViewClicked() {

        getDockActivity().popFragment();

    }

    public void bindData(ArrayList<AllRequestSupportEnt> result) {

        if (result.size() > 0) {
            lvRequests.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        } else {
            lvRequests.setVisibility(View.GONE);
            txtNoData.setText(R.string.no_completed_job);
            txtNoData.setVisibility(View.VISIBLE);
        }

        lvRequests.BindRecyclerView(new BinderRequestsLog(getDockActivity(), prefHelper), result,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());

     /*   adapter.clearList();
        adapter.addAll(result);
        lvRequests.setAdapter(adapter);*/

    }

}
