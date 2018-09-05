package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class AboutUsFragment extends BaseFragment {

    private static String AboutUs = "";
    @BindView(R.id.anyTextView)
    AnyTextView anyTextView;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    Unbinder unbinder;

    public static AboutUsFragment newInstance(String aboutUs) {
        AboutUs = aboutUs;
        return new AboutUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtAboutUs.setText(AboutUs.equals("")?"-":AboutUs);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading("");//getString(R.string.about_us)
    }


}
