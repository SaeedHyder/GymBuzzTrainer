package com.app.gymbuztrainer.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 5/24/2018.
 */

public class ViewProfileFragment extends BaseFragment {


    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.txtName)
    AnyTextView txtName;
    @BindView(R.id.txtEmail)
    AnyTextView txtEmail;
    @BindView(R.id.txtAgeValue)
    AnyTextView txtAgeValue;
    @BindView(R.id.txtAge)
    AnyTextView txtAge;
    @BindView(R.id.llAge)
    LinearLayout llAge;
    @BindView(R.id.txtWeightValue)
    AnyTextView txtWeightValue;
    @BindView(R.id.txtWeight)
    AnyTextView txtWeight;
    @BindView(R.id.llWeight)
    LinearLayout llWeight;
    @BindView(R.id.txtHeightValue)
    AnyTextView txtHeightValue;
    @BindView(R.id.txtHeight)
    AnyTextView txtHeight;
    @BindView(R.id.llHeight)
    LinearLayout llHeight;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    Unbinder unbinder;

    private ImageLoader imageLoader;

    public static ViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private void setData() {

        if (prefHelper.getUser() != null) {
            if (prefHelper.getUser().getProfileImagePath() != null) {
                imageLoader.displayImage(prefHelper.getUser().getProfileImagePath(), civProfilePic);
            }
            txtName.setText(prefHelper.getUser().getFullName() != null ? prefHelper.getUser().getFullName() : "-");
            txtEmail.setText(prefHelper.getUser().getEmail() != null ? prefHelper.getUser().getEmail() : "-");
            txtHeightValue.setText(prefHelper.getUser().getHeight() != null ? prefHelper.getUser().getHeight() : "-");
            txtWeightValue.setText(prefHelper.getUser().getWeight() != null ? prefHelper.getUser().getWeight() : "-");
            txtAboutUs.setText(prefHelper.getUser().getAbout() != null ? prefHelper.getUser().getAbout() : "-");

            DateFormat format = new SimpleDateFormat(AppConstants.DATE_FORMAT_APP, Locale.ENGLISH);
            Date date = null;
            try {
                if (prefHelper.getUser().getDob() != null)
                    date = format.parse(prefHelper.getUser().getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                txtAgeValue.setText(String.valueOf(getAge(date)));
            } else {
                txtAgeValue.setText("-");
            }

        }

    }

    public static int getAge(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("You don't exist yet");
        }
        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
            age--;
        }
        return age;
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getMainActivity().getResourceString(R.string.profile1));
        titleBar.showRightButton(R.drawable.edit, true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(UpdateProfileFragment.newInstance(), UpdateProfileFragment.class.getSimpleName());
            }
        });
    }


}
