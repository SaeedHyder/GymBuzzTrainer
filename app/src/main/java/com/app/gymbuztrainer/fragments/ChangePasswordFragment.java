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
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.ui.views.AnyEditTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.gymbuztrainer.global.WebServiceConstants.CHANGEPASSWORD;

/**
 * Created on 5/24/2018.
 */

public class ChangePasswordFragment extends BaseFragment {


    @BindView(R.id.edtOldPassword)
    AnyEditTextView edtOldPassword;
    @BindView(R.id.tilFullName)
    TextInputLayout tilFullName;
    @BindView(R.id.edtNewPassword)
    AnyEditTextView edtNewPassword;
    @BindView(R.id.tilNewPassword)
    TextInputLayout tilNewPassword;
    @BindView(R.id.edtConfirmPassword)
    AnyEditTextView edtConfirmPassword;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);


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
        titleBar.setSubHeading(getString(R.string.change_password));
    }


    public boolean isvalidated(){

        if (edtOldPassword.getText() == null || (edtOldPassword.getText().toString().isEmpty())) {
            edtOldPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtNewPassword.getText() == null || (edtNewPassword.getText().toString().isEmpty())) {
            edtNewPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtNewPassword.getText().toString().equals(edtOldPassword.getText().toString())) {
            edtNewPassword.setError(getString(R.string.samePassword));
            return false;
        } else if (edtNewPassword.getText().toString().length() < 6) {
            edtNewPassword.setError(getString(R.string.password_length_alert));
            return false;
        } else if (edtConfirmPassword.getText() == null || (edtConfirmPassword.getText().toString().isEmpty())) {
            edtConfirmPassword.setError(getString(R.string.enter_confirm_password));
            return false;
        } else if (edtConfirmPassword.getText().toString().length() < 6) {
            edtConfirmPassword.setError(getString(R.string.confirmpasswordLength));
            return false;
        } else if (!edtConfirmPassword.getText().toString().equals(edtNewPassword.getText().toString())) {
            edtConfirmPassword.setError(getString(R.string.conform_password_error));
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        if(isvalidated()){
          serviceHelper.enqueueCall(headerWebService.changePassword(edtOldPassword.getText().toString(),edtNewPassword.getText().toString()), CHANGEPASSWORD);
        }

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        super.ResponseSuccess(result, Tag, message);
        switch (Tag){
            case CHANGEPASSWORD:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.pass_changed));
                getDockActivity().popFragment();
                break;
        }
    }
}
