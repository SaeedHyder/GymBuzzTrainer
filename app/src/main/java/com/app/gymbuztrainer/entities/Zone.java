package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zone {


    @SerializedName("zoneID")
    @Expose
    private Integer zoneID;
    @SerializedName("zoneName")
    @Expose
    private String zoneName;
    @SerializedName("floorID")
    @Expose
    private Integer floorID;
    @SerializedName("southCoordinate")
    @Expose
    private Object southCoordinate;
    @SerializedName("westCoordinate")
    @Expose
    private Object westCoordinate;
    @SerializedName("northCoordinate")
    @Expose
    private Object northCoordinate;
    @SerializedName("eastCoordinate")
    @Expose
    private Object eastCoordinate;
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


    public Integer getZoneID() {
        return zoneID;
    }

    public void setZoneID(Integer zoneID) {
        this.zoneID = zoneID;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getFloorID() {
        return floorID;
    }

    public void setFloorID(Integer floorID) {
        this.floorID = floorID;
    }

    public Object getSouthCoordinate() {
        return southCoordinate;
    }

    public void setSouthCoordinate(Object southCoordinate) {
        this.southCoordinate = southCoordinate;
    }

    public Object getWestCoordinate() {
        return westCoordinate;
    }

    public void setWestCoordinate(Object westCoordinate) {
        this.westCoordinate = westCoordinate;
    }

    public Object getNorthCoordinate() {
        return northCoordinate;
    }

    public void setNorthCoordinate(Object northCoordinate) {
        this.northCoordinate = northCoordinate;
    }

    public Object getEastCoordinate() {
        return eastCoordinate;
    }

    public void setEastCoordinate(Object eastCoordinate) {
        this.eastCoordinate = eastCoordinate;
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
