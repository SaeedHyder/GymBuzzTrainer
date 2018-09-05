package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSetting {

    @SerializedName("userSettingID")
    @Expose
    private Integer userSettingID;
    @SerializedName("settingName")
    @Expose
    private String settingName;
    @SerializedName("settingValue")
    @Expose
    private String settingValue;
    @SerializedName("userID")
    @Expose
    private Integer userID;

    public Integer getUserSettingID() {
        return userSettingID;
    }

    public void setUserSettingID(Integer userSettingID) {
        this.userSettingID = userSettingID;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
