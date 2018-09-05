package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.ui.views.AnyEditTextView;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class CalculateBmiFragment extends BaseFragment {


    @BindView(R.id.edtHeight)
    AnyEditTextView edtHeight;
    @BindView(R.id.tilFullName)
    TextInputLayout tilFullName;
    @BindView(R.id.edtWeight)
    AnyEditTextView edtWeight;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.btnComputeBmi)
    Button btnComputeBmi;
    @BindView(R.id.txtHead)
    AnyTextView txtHead;
    @BindView(R.id.txtBmi)
    AnyTextView txtBmi;
    Unbinder unbinder;

    public static CalculateBmiFragment newInstance() {
        return new CalculateBmiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compute_bmi, container, false);


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
        titleBar.setSubHeading(getString(R.string.calculate_bmi));
    }

    boolean validate() {

        if (edtHeight.getText().toString().length() > 0) {
            if (edtWeight.getText().toString().length() > 0) {
                return true;
            } else {
                edtWeight.setError(getString(R.string.please_enter_weight));
            }
        } else {
            edtHeight.setError(getString(R.string.please_enter_height));
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // unbinder.unbind();
    }

    @OnClick(R.id.btnComputeBmi)
    public void onViewClicked() {

        if(validate()){

            float weight;
            float height;
            float bMI;

            weight = Float.parseFloat(edtWeight.getText().toString());
            height = Float.parseFloat(edtHeight.getText().toString());

            bMI = (weight * 703) / (height * height);

            txtBmi.setText(bMI+"");


            edtWeight.setText("");
            edtHeight.setText("");

        }
    }
}
