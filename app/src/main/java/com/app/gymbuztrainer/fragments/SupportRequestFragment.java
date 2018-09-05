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

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.AllRequestSupportEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.interfaces.RequestInterface;
import com.app.gymbuztrainer.ui.adapters.ArrayListAdapter;
import com.app.gymbuztrainer.ui.binders.BinderRequests;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.CustomRecyclerView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.gymbuztrainer.global.WebServiceConstants.ACCEPTREQUEST;
import static com.app.gymbuztrainer.global.WebServiceConstants.ALLREQUESTSUPPORT;
import static com.app.gymbuztrainer.global.WebServiceConstants.MARKJOBDONE;

/**
 * Created on 5/29/2018.
 */

public class SupportRequestFragment extends BaseFragment implements RequestInterface {

    @BindView(R.id.lvRequests)
    CustomRecyclerView lvRequests;
    @BindView(R.id.btnViewSupportLog)
    Button btnViewSupportLog;
    Unbinder unbinder;
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.mainFrame)
    LinearLayout mainFrame;
    private ArrayList<AllRequestSupportEnt> collection = new ArrayList<>();
    private ArrayListAdapter<AllRequestSupportEnt> adapter;
    private String jobDonePosition;


    public static SupportRequestFragment newInstance() {
        return new SupportRequestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  adapter = new ArrayListAdapter<>(getDockActivity(), new BinderRequests(getDockActivity(), prefHelper, this));
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
        serviceHelper.enqueueCall(headerWebService.GetAllRequestSupport(), ALLREQUESTSUPPORT);

        btnViewSupportLog.setText(getMainActivity().getResourceString(R.string.view_support_log));

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        super.ResponseSuccess(result, Tag, message);
        switch (Tag) {
            case ALLREQUESTSUPPORT:
                bindData((ArrayList<AllRequestSupportEnt>) result);
                mainFrame.setVisibility(View.VISIBLE);
                break;

            case ACCEPTREQUEST:
                UIHelper.showShortToastInCenter(getDockActivity(), message);
                break;

            case MARKJOBDONE:
                if (jobDonePosition != null) {
                    collection.remove(Integer.parseInt(jobDonePosition));
                    lvRequests.getAdapter().notifyItemRemoved(Integer.parseInt(jobDonePosition));
                }

                UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.job_done_succesfully));
                //   serviceHelper.enqueueCall(headerWebService.GetAllRequestSupport(), ALLREQUESTSUPPORT);
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getMainActivity().getResourceString(R.string.support_req));
    }


    @OnClick(R.id.btnViewSupportLog)
    public void onViewClicked() {
        getDockActivity().replaceDockableFragment(SupportLogFragment.newInstance(), SupportLogFragment.class.getSimpleName());
    }

    public void bindData(ArrayList<AllRequestSupportEnt> result) {

        collection = new ArrayList<>();
        collection.addAll(result);

        if (collection.size() > 0) {
            lvRequests.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        } else {
            lvRequests.setVisibility(View.GONE);
            txtNoData.setVisibility(View.VISIBLE);
        }

        lvRequests.BindRecyclerView(new BinderRequests(getDockActivity(), prefHelper, this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
       /* lvRequests.setAdapter(adapter);
        adapter.clearList();
        adapter.addAll(collection);*/


    }


    @Override
    public void onRequestClick(AllRequestSupportEnt entity, int position) {

        serviceHelper.enqueueCall(headerWebService.AcceptRequest(entity.getRequestSupportID()), ACCEPTREQUEST);
    }

    @Override
    public void onJobDoneClick(AllRequestSupportEnt entity, int position) {
        jobDonePosition = String.valueOf(position);
        serviceHelper.enqueueCall(headerWebService.markJobDone(entity.getRequestSupportID()), MARKJOBDONE);
    }


}
