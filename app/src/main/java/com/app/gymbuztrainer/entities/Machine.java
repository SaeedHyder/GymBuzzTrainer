package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Machine {


    @SerializedName("machineID")
    @Expose
    private Integer machineID;
    @SerializedName("machineCategoryId")
    @Expose
    private Integer machineCategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imagePath")
    @Expose
    private Object imagePath;
    @SerializedName("machineCategory")
    @Expose
    private Object machineCategory;
    @SerializedName("machineExercises")
    @Expose
    private Object machineExercises;
    @SerializedName("machineSettings")
    @Expose
    private Object machineSettings;
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


    public Integer getMachineID() {
        return machineID;
    }

    public void setMachineID(Integer machineID) {
        this.machineID = machineID;
    }

    public Integer getMachineCategoryId() {
        return machineCategoryId;
    }

    public void setMachineCategoryId(Integer machineCategoryId) {
        this.machineCategoryId = machineCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public Object getMachineCategory() {
        return machineCategory;
    }

    public void setMachineCategory(Object machineCategory) {
        this.machineCategory = machineCategory;
    }

    public Object getMachineExercises() {
        return machineExercises;
    }

    public void setMachineExercises(Object machineExercises) {
        this.machineExercises = machineExercises;
    }

    public Object getMachineSettings() {
        return machineSettings;
    }

    public void setMachineSettings(Object machineSettings) {
        this.machineSettings = machineSettings;
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
