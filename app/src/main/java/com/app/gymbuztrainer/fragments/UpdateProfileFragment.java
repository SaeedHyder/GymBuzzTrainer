package com.app.gymbuztrainer.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.activities.MainActivity;
import com.app.gymbuztrainer.entities.UserEnt;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.helpers.CameraHelper;
import com.app.gymbuztrainer.helpers.DatePickerHelper;
import com.app.gymbuztrainer.helpers.UIHelper;
import com.app.gymbuztrainer.ui.views.AnyEditTextView;
import com.app.gymbuztrainer.ui.views.AnyTextView;
import com.app.gymbuztrainer.ui.views.TitleBar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.app.gymbuztrainer.global.WebServiceConstants.EDITPROFILE;

/**
 * Created on 5/24/2018.
 */

public class UpdateProfileFragment extends BaseFragment implements MainActivity.ImageSetter {


    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;
    @BindView(R.id.ll_ProfileImage)
    RelativeLayout llProfileImage;
    @BindView(R.id.edtFullName)
    AnyEditTextView edtFullName;
    @BindView(R.id.edtEmail)
    AnyTextView edtEmail;
    @BindView(R.id.spGender)
    Spinner spGender;
    @BindView(R.id.llGender)
    LinearLayout llGender;
    @BindView(R.id.btn_dob)
    AnyTextView btnDob;
    @BindView(R.id.llDOB)
    LinearLayout llDOB;
    @BindView(R.id.edtAge)
    AnyEditTextView edtAge;
    @BindView(R.id.tilAge)
    TextInputLayout tilAge;
    @BindView(R.id.edtHeight)
    AnyEditTextView edtHeight;
    @BindView(R.id.edtWeight)
    AnyEditTextView edtWeight;
    @BindView(R.id.txt_textBox)
    AnyEditTextView txtTextBox;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    Unbinder unbinder;
    private String selectedDate = "";
    ArrayList genderList = new ArrayList();
    ArrayAdapter<String> genderAdapter;
    File profilePic;
    String profilePath = "";
    ArrayList<String> imagesCollection;

    ImageLoader imageLoader;


    public static UpdateProfileFragment newInstance() {
        return new UpdateProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMainActivity().setImageSetter(this);
        setGenderData();
        setData();

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getMainActivity().getResourceString(R.string.edit_profile1));
    }

    private void setData() {


        if (prefHelper.getUser() != null) {
            if (prefHelper.getUser().getProfileImagePath() != null) {
                imageLoader.displayImage(prefHelper.getUser().getProfileImagePath(), civProfilePic);
            }
            if (prefHelper.getUser().getFullName() != null)
                edtFullName.setText(prefHelper.getUser().getFullName());
            if (prefHelper.getUser().getEmail() != null)
                edtEmail.setText(prefHelper.getUser().getEmail());
            if (prefHelper.getUser().getHeight() != null)
                edtHeight.setText(prefHelper.getUser().getHeight());
            if (prefHelper.getUser().getWeight() != null)
                edtWeight.setText(prefHelper.getUser().getWeight());
            if (prefHelper.getUser().getDob() != null)
                btnDob.setText(prefHelper.getUser().getDob());
            if (prefHelper.getUser().getAbout() != null)
                txtTextBox.setText(prefHelper.getUser().getAbout());

            if (prefHelper.getUser().getGender() != null) {
                if (prefHelper.getUser().getGender() == 0) {
                    spGender.setSelection(1);
                } else {
                    spGender.setSelection(2);
                }
            } else {
                spGender.setSelection(0);
            }
        }

    }

    private boolean isValidated() {


        if (edtFullName.getText().toString().length() > 0 && !edtFullName.getText().toString().trim().equals("")) {
            if (edtHeight.getText().toString().length() > 0 && !edtHeight.getText().toString().trim().equals("")) {
                if (!edtHeight.getText().toString().trim().equals(".") && !(edtHeight.getText().toString().trim().contains(".."))) {
                    if (edtWeight.getText().toString().length() > 0 && !edtWeight.getText().toString().trim().equals("")) {
                        if (!edtWeight.getText().toString().trim().equals(".") && !(edtWeight.getText().toString().trim().contains(".."))) {
                            if (txtTextBox.getText().toString().length() > 0 && !txtTextBox.getText().toString().trim().equals("")) {
                                if (spGender.getSelectedItemPosition() != 0) {
                                    return true;
                                } else {
                                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.please_select_gender));
                                }
                            } else {
                                txtTextBox.setError(getMainActivity().getResourceString(R.string.please_enter_about));
                            }
                        }else{
                            edtWeight.setError(getMainActivity().getResourceString(R.string.wight_error));
                        }
                    } else {
                            edtWeight.setError(getMainActivity().getResourceString(R.string.please_enter_weight));
                        }
                    } else {
                        edtHeight.setError(getMainActivity().getResourceString(R.string.height_error));
                    }
                } else {
                    edtHeight.setError(getMainActivity().getResourceString(R.string.please_enter_height));
                }
            } else {
                edtFullName.setError(getMainActivity().getResourceString(R.string.please_enter_fullname));
            }


            return false;
        }

        private void setGenderData () {

            genderList = new ArrayList<String>();

            genderList.add(getMainActivity().getResourceString(R.string.select_gender));
            genderList.add(getMainActivity().getResourceString(R.string.male));
            genderList.add(getMainActivity().getResourceString(R.string.female));

            genderAdapter = new ArrayAdapter<String>(getDockActivity(), R.layout.spinner_item, genderList);

      /*  genderAdapter= new ArrayAdapter<String>(getDockActivity()
                , android.R.layout.simple_spinner_item, genderList) {
            @Override
            public boolean isEnabled(int position) {
                return !(position == 0);
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

        };*/
            genderAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spGender.setAdapter(genderAdapter);
            genderAdapter.notifyDataSetChanged();

        }

        @OnClick({R.id.civProfilePic, R.id.btnUpdate, R.id.btn_dob})
        public void onViewClicked (View view){
            switch (view.getId()) {

                case R.id.civProfilePic:
                    requestCameraPermission();
                    break;

                case R.id.btnUpdate:
                    if (isValidated()) {
                        MultipartBody.Part filePart = null;
                        if (profilePic != null) {
                            filePart = MultipartBody.Part.createFormData("file", profilePic.getName(), RequestBody.create(MediaType.parse("image"), profilePic));
                        }

                        RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), edtFullName.getText().toString() + "");
                        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), spGender.getSelectedItemPosition() == 1 ? "0" : "1");
                        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), btnDob.getText().toString() + "");
                        RequestBody height = RequestBody.create(MediaType.parse("text/plain"), edtHeight.getText().toString() + "");
                        RequestBody weight = RequestBody.create(MediaType.parse("text/plain"), edtWeight.getText().toString() + "");
                        RequestBody about = RequestBody.create(MediaType.parse("text/plain"), txtTextBox.getText().toString() + "");

                        serviceHelper.enqueueCall(headerWebService.editProfile(fullname, gender, dob, height, weight, about, filePart != null ? filePart : null), EDITPROFILE);

                    }
                    break;

                case R.id.btn_dob:
                    initDatePicker(btnDob);
                    break;

            }
        }

        @Override
        public void ResponseSuccess (Object result, String Tag, String message){
            super.ResponseSuccess(result, Tag, message);
            switch (Tag) {
                case EDITPROFILE:
                    UserEnt entity = (UserEnt) result;
                    prefHelper.putUser(entity);
                    UIHelper.showShortToastInCenter(getDockActivity(), getMainActivity().getResourceString(R.string.profile_updated));
                    getDockActivity().popFragment();
                    break;
            }
        }

        private void requestCameraPermission () {
            Dexter.withActivity(getDockActivity())
                    .withPermissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {

                            if (report.areAllPermissionsGranted()) {
                                CameraHelper.uploadPhotoDialog(getMainActivity());
                            }

                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                requestCameraPermission();

                            } else if (report.getDeniedPermissionResponses().size() > 0) {
                                requestCameraPermission();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).
                    withErrorListener(new PermissionRequestErrorListener() {
                        @Override
                        public void onError(DexterError error) {
                            UIHelper.showShortToastInCenter(getDockActivity(), "Grant Camera And Storage Permission to processed");
                            openSettings();
                        }
                    })

                    .onSameThread()
                    .check();


        }

        private void openSettings () {

            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            Uri uri = Uri.fromParts("package", getDockActivity().getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }


        public void checkPermission () {

            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA)
                    .onGranted(permissions -> {

                        CameraHelper.uploadPhotoDialog(getMainActivity());

                    })
                    .onDenied(permissions -> {
                        UIHelper.showShortToastInCenter(getDockActivity(), "Permission is required to access this feature");
                    })
                    .start();

        }


        private void initDatePicker ( final TextView textView){
            Calendar calendar = Calendar.getInstance();
            final DatePickerHelper datePickerHelper = new DatePickerHelper();
            datePickerHelper.initDateDialog(
                    getDockActivity(),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                    , "PreferredDate");
            datePickerHelper.setListener(new DatePickerHelper.OnDateSelectedListener() {
                @Override
                public void onDatePicked(Calendar date) {
                    selectedDate = new SimpleDateFormat(AppConstants.SERVER_DATE_FORMAT, Locale.ENGLISH)
                            .format(date.getTime());
                    textView.setText(new SimpleDateFormat(AppConstants.DateFormat_YMD, Locale.ENGLISH)
                            .format(date.getTime()));
                }
            });
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.set(Calendar.YEAR, c.get(Calendar.YEAR));
            datePickerHelper.setmaxDate(c.getTime().getTime());
            datePickerHelper.showDate();
        }

        @Override
        public void setImage (String imagePath){
            if (imagePath != null) {
                try {
                    profilePic = new Compressor(getDockActivity()).compressToFile(new File(imagePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //profilePic = new File(imagePath);
                ImageLoader.getInstance().displayImage("file:///" + imagePath, civProfilePic);
                profilePath = imagePath;
            }

        }

        @Override
        public void setFilePath (String filePath){

        }

        @Override
        public void setVideo (String videoPath){

        }
    }
