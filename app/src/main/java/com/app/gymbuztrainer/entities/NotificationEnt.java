package com.app.gymbuztrainer.entities;

import android.app.Notification;

import com.app.gymbuztrainer.helpers.DateHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saeedhyder on 9/15/2017.
 */

public class NotificationEnt {

    @SerializedName("notification")
    @Expose
    private Notificaiton notification;
    @SerializedName("senderName")
    @Expose
    private String senderName;
    @SerializedName("senderEmail")
    @Expose
    private String senderEmail;
    @SerializedName("receiverName")
    @Expose
    private String receiverName;
    @SerializedName("receiverEmail")
    @Expose
    private String receiverEmail;

    public Notificaiton getNotification() {
        return notification;
    }

    public void setNotification(Notificaiton notification) {
        this.notification = notification;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}