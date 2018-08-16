package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class HomeMenuFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.ivNotification)
    ImageView ivNotification;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.glLogo)
    Guideline glLogo;
    @BindView(R.id.glskeletons)
    Guideline glskeletons;
    @BindView(R.id.btnFloorPlan)
    AnyTextView btnFloorPlan;
    @BindView(R.id.btnSupportPlan)
    AnyTextView btnSupportPlan;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.btnTraineeProfile)
    AnyTextView btnTraineeProfile;

    public static HomeMenuFragment newInstance() {
        return new HomeMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.view_profile));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @OnClick({R.id.ivNotification, R.id.btnFloorPlan, R.id.btnSupportPlan, R.id.btnTraineeProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ivNotification:
                UIHelper.showShortToastInCenter(getMainActivity(),getString(R.string.will_be_imp_beta));
                break;

            case R.id.btnFloorPlan:
                UIHelper.showShortToastInCenter(getMainActivity(),getString(R.string.will_be_imp_beta));
                break;

            case R.id.btnSupportPlan:
                getDockActivity().replaceDockableFragment(SupportRequestFragment.newInstance(), SupportRequestFragment.class.getSimpleName());
                break;

            case R.id.btnTraineeProfile:
                getDockActivity().replaceDockableFragment(ViewProfileFragment.newInstance(), ViewProfileFragment.class.getSimpleName());
                break;
                case R.id.btnSettings:
                getDockActivity().replaceDockableFragment(SettingFragment.newInstance(), SettingFragment.class.getSimpleName());
                break;
        }
    }
}
