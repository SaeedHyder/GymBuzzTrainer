package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("firstName")
    @Expose
    private Object firstName;
    @SerializedName("lastName")
    @Expose
    private Object lastName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("countryCode")
    @Expose
    private Integer countryCode;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("profileImagePath")
    @Expose
    private String profileImagePath;
    @SerializedName("roleID")
    @Expose
    private Integer roleID;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;

    @SerializedName("userDevices")
    @Expose
    private Object userDevices;
    @SerializedName("userAddresses")
    @Expose
    private Object userAddresses;
    @SerializedName("userGym")
    @Expose
    private Object userGym;
    @SerializedName("userOtherInfo")
    @Expose
    private Object userOtherInfo;
    @SerializedName("userExercises")
    @Expose
    private Object userExercises;
    @SerializedName("userLoginTokens")
    @Expose
    private Object userLoginTokens;
    @SerializedName("userSettings")
    @Expose
    private Object userSettings;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("updatedIp")
    @Expose
    private String updatedIp;
    @SerializedName("lastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Object getUserDevices() {
        return userDevices;
    }

    public void setUserDevices(Object userDevices) {
        this.userDevices = userDevices;
    }

    public Object getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Object userAddresses) {
        this.userAddresses = userAddresses;
    }

    public Object getUserGym() {
        return userGym;
    }

    public void setUserGym(Object userGym) {
        this.userGym = userGym;
    }

    public Object getUserOtherInfo() {
        return userOtherInfo;
    }

    public void setUserOtherInfo(Object userOtherInfo) {
        this.userOtherInfo = userOtherInfo;
    }

    public Object getUserExercises() {
        return userExercises;
    }

    public void setUserExercises(Object userExercises) {
        this.userExercises = userExercises;
    }

    public Object getUserLoginTokens() {
        return userLoginTokens;
    }

    public void setUserLoginTokens(Object userLoginTokens) {
        this.userLoginTokens = userLoginTokens;
    }

    public Object getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Object userSettings) {
        this.userSettings = userSettings;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(String updatedIp) {
        this.updatedIp = updatedIp;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
