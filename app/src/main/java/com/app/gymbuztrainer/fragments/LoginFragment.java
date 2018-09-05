package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.UserEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.ui.views.AnyEditTextView;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.gymbuztrainer.global.WebServiceConstants.LOGIN;


public class LoginFragment extends BaseFragment {


    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.editField_guide)
    Guideline editFieldGuide;
    @BindView(R.id.glBox)
    Guideline glBox;
    @BindView(R.id.edtEmail)
    AnyEditTextView edtEmail;
    @BindView(R.id.edtPassword)
    AnyEditTextView edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btn_forgot_password)
    AnyTextView btnForgotPassword;
    @BindView(R.id.llBox)
    LinearLayout llBox;
    @BindView(R.id.scrollview_bigdaddy)
    ConstraintLayout scrollviewBigdaddy;
    Unbinder unbinder;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    public boolean isValidated() {

        if (edtEmail.testValidity()) {
            if (edtPassword.getText().toString().length() >= 6) {
                return true;
            } else {
                edtPassword.setError(getString(R.string.password_length));
            }
        }

        return false;
    }

    @OnClick({R.id.btnLogin, R.id.btn_forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnLogin:

                if (isValidated()) {
                    serviceHelper.enqueueCall(webService.login(edtEmail.getText().toString(), edtPassword.getText().toString(), FirebaseInstanceId.getInstance().getToken(), AppConstants.Device_Type), LOGIN);
                }

                break;

            case R.id.btn_forgot_password:
                UIHelper.showShortToastInCenter(getMainActivity(), getString(R.string.will_be_imp_beta));
                break;
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        super.ResponseSuccess(result, Tag, message);
        switch (Tag) {
            case LOGIN:
                UserEnt entity = (UserEnt) result;
                prefHelper.setLoginStatus(true);
                prefHelper.putUser(entity);
                prefHelper.set_TOKEN(entity.getAuthToken());
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeMenuFragment.newInstance(), "HomeMenuFragment");
                break;
        }
    }
}
