package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialMediaLink {

    @SerializedName("socialMediaID")
    @Expose
    private Integer socialMediaID;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("socialMediaImage")
    @Expose
    private String socialMediaImage;

    public Integer getSocialMediaID() {
        return socialMediaID;
    }

    public void setSocialMediaID(Integer socialMediaID) {
        this.socialMediaID = socialMediaID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSocialMediaImage() {
        return socialMediaImage;
    }

    public void setSocialMediaImage(String socialMediaImage) {
        this.socialMediaImage = socialMediaImage;
    }
}
