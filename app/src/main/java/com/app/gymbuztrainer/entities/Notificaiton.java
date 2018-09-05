package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificaiton {

    @SerializedName("notificationsID")
    @Expose
    private Integer notificationsID;
    @SerializedName("senderID")
    @Expose
    private Integer senderID;
    @SerializedName("recevierID")
    @Expose
    private Integer recevierID;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("actionID")
    @Expose
    private Integer actionID;
    @SerializedName("actionType")
    @Expose
    private Integer actionType;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("updatedIp")
    @Expose
    private Object updatedIp;
    @SerializedName("lastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;

    public Integer getNotificationsID() {
        return notificationsID;
    }

    public void setNotificationsID(Integer notificationsID) {
        this.notificationsID = notificationsID;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getRecevierID() {
        return recevierID;
    }

    public void setRecevierID(Integer recevierID) {
        this.recevierID = recevierID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getActionID() {
        return actionID;
    }

    public void setActionID(Integer actionID) {
        this.actionID = actionID;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(Object updatedIp) {
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
