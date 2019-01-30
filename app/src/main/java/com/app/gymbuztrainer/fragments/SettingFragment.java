package com.app.gymbuztrainer.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.entities.CmsEnt;
import com.app.gymbuztrainer.entities.UserEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.helpers.DialogHelper;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.gymbuztrainer.global.WebServiceConstants.CMS;
import static com.app.gymbuztrainer.global.WebServiceConstants.LOGOUT;
import static com.app.gymbuztrainer.global.WebServiceConstants.TOGGLENOTIFICATION;

/**
 * Created on 5/24/2018.
 */

public class SettingFragment extends BaseFragment {


    @BindView(R.id.txtViewProfile)
    AnyTextView txtViewProfile;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.txtUnits)
    AnyTextView txtUnits;
    @BindView(R.id.cbLbs)
    CheckBox cbLbs;
    @BindView(R.id.cbKgs)
    CheckBox cbKgs;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.txtNotification)
    AnyTextView txtNotification;
    @BindView(R.id.toggleNotification)
    ToggleButton toggleNotification;
    @BindView(R.id.txtChangePassword)
    AnyTextView txtChangePassword;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    @BindView(R.id.txtRate)
    AnyTextView txtRate;
    @BindView(R.id.txtContactUs)
    AnyTextView txtContactUs;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    Unbinder unbinder;
    @BindView(R.id.txtAppVersion)
    AnyTextView txtAppVersion;
    @BindView(R.id.btn_fb)
    ImageView btnFb;
    @BindView(R.id.btn_insta)
    ImageView btnInsta;
    @BindView(R.id.btn_snapchat)
    ImageView btnSnapchat;
    @BindView(R.id.btn_twitter)
    ImageView btnTwitter;
    @BindView(R.id.btn_youtube)
    ImageView btnYoutube;


    private String facebookUrl = "";
    private String instaUrl = "";
    private String snapchatUrl = "";
    private String twitterUrl = "";
    private String youtubeUrl = "";
    private String aboutUs = "";
    private String phoneNumber = "";
    private String email = "";

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (prefHelper.getUser().getNotification() != null && prefHelper.getUser().getNotification().equals("0")) {
            toggleNotification.setChecked(false);
        } else {
            toggleNotification.setChecked(true);
        }

        serviceHelper.enqueueCall(headerWebService.getCMS(prefHelper.getUser().getGymID()), CMS);

        toggleNotificationListners();

        getAppVersion();
    }


    private void toggleNotificationListners() {

        toggleNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                serviceHelper.enqueueCall(headerWebService.ToggleNotification(b ? 1 : 0), TOGGLENOTIFICATION);

                UserEnt ent = prefHelper.getUser();
                ent.setNotification(b ? "1" : "0");
                prefHelper.putUser(ent);
            }
        });
    }

    private void getAppVersion() {

        String version = null;
        try {
            PackageInfo pInfo = getDockActivity().getPackageManager().getPackageInfo(getDockActivity().getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (version != null) {
            txtAppVersion.setText(getMainActivity().getResourceString(R.string.app_version) + " " + version);
        } else {
            txtAppVersion.setVisibility(View.GONE);
        }
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.settings));
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        super.ResponseSuccess(result, Tag, message);

        switch (Tag) {
            case CMS:
                CmsEnt cmsEnt = (CmsEnt) result;
                aboutUs = cmsEnt.getAbout() != null ? cmsEnt.getAbout() : "";
                phoneNumber = cmsEnt.getPhone() != null ? cmsEnt.getPhone() : "";
                email = cmsEnt.getEmail() != null ? cmsEnt.getEmail() : "";

                facebookUrl = cmsEnt.getFacebookURL() != null ? cmsEnt.getFacebookURL() : "";
                instaUrl = cmsEnt.getInstagramURL() != null ? cmsEnt.getInstagramURL() : "";
                twitterUrl = cmsEnt.getTwitterURL() != null ? cmsEnt.getTwitterURL() : "";
                youtubeUrl = cmsEnt.getYoutubeURL() != null ? cmsEnt.getYoutubeURL() : "";
                snapchatUrl = cmsEnt.getSnapChatURL() != null ? cmsEnt.getSnapChatURL() : "";

                break;

            case LOGOUT:
                prefHelper.setLoginStatus(false);
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().addDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                break;
        }
    }

    @OnClick({R.id.txtViewProfile, R.id.txtChangePassword, R.id.txtAboutUs, R.id.txtContactUs, R.id.txtLogout, R.id.txtRate, R.id.btn_fb, R.id.btn_insta, R.id.btn_snapchat, R.id.btn_twitter, R.id.btn_youtube})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.txtViewProfile:
                getDockActivity().replaceDockableFragment(ViewProfileFragment.newInstance(), ViewProfileFragment.class.getSimpleName());
                break;

            case R.id.txtChangePassword:
                getDockActivity().replaceDockableFragment(ChangePasswordFragment.newInstance(), ChangePasswordFragment.class.getSimpleName());
                break;

            case R.id.txtAboutUs:
                getDockActivity().replaceDockableFragment(AboutUsFragment.newInstance(aboutUs), AboutUsFragment.class.getSimpleName());
                break;

            case R.id.txtContactUs:
                getDockActivity().replaceDockableFragment(ContactusFragment.newInstance(email, phoneNumber), ContactusFragment.class.getSimpleName());
                break;

            case R.id.txtRate:
                final String appPackageName = getDockActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;

            case R.id.btn_fb:
                if (facebookUrl != null && !facebookUrl.equals("") && Patterns.WEB_URL.matcher(facebookUrl).matches()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.url_not_avaliable));
                }
                break;
            case R.id.btn_insta:
                if (instaUrl != null && !instaUrl.equals("") && Patterns.WEB_URL.matcher(facebookUrl).matches()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instaUrl)));
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.url_not_avaliable));
                }
                break;
            case R.id.btn_snapchat:
                if (snapchatUrl != null && !snapchatUrl.equals("") && Patterns.WEB_URL.matcher(facebookUrl).matches()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(snapchatUrl)));
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.url_not_avaliable));
                }
                break;
            case R.id.btn_twitter:
                if (twitterUrl != null && !twitterUrl.equals("") && Patterns.WEB_URL.matcher(facebookUrl).matches()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl)));
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.url_not_avaliable));
                }
                break;
            case R.id.btn_youtube:
                if (youtubeUrl != null && !youtubeUrl.equals("") && Patterns.WEB_URL.matcher(facebookUrl).matches()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)));
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.url_not_avaliable));
                }
                break;

            case R.id.txtLogout:


                final DialogHelper dialogHelper = new DialogHelper(getMainActivity());
                dialogHelper.initlogout(R.layout.dialog_logout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogHelper.hideDialog();
                        serviceHelper.enqueueCall(headerWebService.logout(AppConstants.Device_Type, FirebaseInstanceId.getInstance().getToken()), LOGOUT);

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogHelper.hideDialog();
                    }
                });

                dialogHelper.showDialog();


                break;
        }
    }


}
