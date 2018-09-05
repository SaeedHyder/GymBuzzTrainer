package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRequestSupportEnt {

    @SerializedName("requestSupportID")
    @Expose
    private Integer requestSupportID;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("machineExerciseID")
    @Expose
    private Integer machineExerciseID;
    @SerializedName("requestName")
    @Expose
    private String requestName;
    @SerializedName("floorID")
    @Expose
    private Integer floorID;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isAccepted")
    @Expose
    private Boolean isAccepted;
    @SerializedName("gymMachineID")
    @Expose
    private Integer gymMachineID;
    @SerializedName("acceptedTime")
    @Expose
    private String acceptedTime;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("machineExercise")
    @Expose
    private Object machineExercise;
    @SerializedName("gymMachine")
    @Expose
    private GymMachine gymMachine;
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

    public Integer getRequestSupportID() {
        return requestSupportID;
    }

    public void setRequestSupportID(Integer requestSupportID) {
        this.requestSupportID = requestSupportID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getMachineExerciseID() {
        return machineExerciseID;
    }

    public void setMachineExerciseID(Integer machineExerciseID) {
        this.machineExerciseID = machineExerciseID;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public Integer getFloorID() {
        return floorID;
    }

    public void setFloorID(Integer floorID) {
        this.floorID = floorID;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Integer getGymMachineID() {
        return gymMachineID;
    }

    public void setGymMachineID(Integer gymMachineID) {
        this.gymMachineID = gymMachineID;
    }

    public String getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(String acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getMachineExercise() {
        return machineExercise;
    }

    public void setMachineExercise(Object machineExercise) {
        this.machineExercise = machineExercise;
    }

    public GymMachine getGymMachine() {
        return gymMachine;
    }

    public void setGymMachine(GymMachine gymMachine) {
        this.gymMachine = gymMachine;
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
