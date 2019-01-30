package com.app.gymbuztrainer.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class ContactusFragment extends BaseFragment {


    @BindView(R.id.anyTextView)
    AnyTextView anyTextView;
    @BindView(R.id.ivPhone)
    ImageView ivPhone;
    @BindView(R.id.viewPhone)
    View viewPhone;
    @BindView(R.id.txtPhone)
    AnyTextView txtPhone;
    @BindView(R.id.ivEmail)
    ImageView ivEmail;
    @BindView(R.id.viewEmail)
    View viewEmail;
    @BindView(R.id.txEmail)
    AnyTextView txEmail;
    Unbinder unbinder;

    private static String PhoneNumber = "";
    private static String Email = "";

    public static ContactusFragment newInstance(String email, String phoneNumber) {

        Email = email;
        PhoneNumber = phoneNumber;
        return new ContactusFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contactus, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtPhone.setText(PhoneNumber.equals("") ? "-" : PhoneNumber);
        txEmail.setText(Email.equals("") ? "-" : Email);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.contact_us));
    }

    private void openEmail(String email) {
        try {
            final Intent emailIntent = new Intent(Intent.ACTION_SEND);

            /* Fill it with Data */
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.write_your_comments));

            /* Send it off to the Activity-Chooser */
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (Exception e) {
            e.printStackTrace();
            UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.something_error));
        }
    }

    private void openPhone(String number) {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .onGranted(permissions -> {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + number));
                        startActivity(callIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.something_error));
                    }
                    // CameraHelper.uploadPhotoDialog(getMainActivity());

                })
                .onDenied(permissions -> {
                    UIHelper.showShortToastInCenter(getDockActivity(), "Permission is required to access this feature");
                })
                .start();

    }



    @OnClick({R.id.txtPhone, R.id.txEmail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtPhone:
                openPhone(txtPhone.getText().toString());
                break;
            case R.id.txEmail:
                openEmail(txEmail.getText().toString());
                break;
        }
    }
}
