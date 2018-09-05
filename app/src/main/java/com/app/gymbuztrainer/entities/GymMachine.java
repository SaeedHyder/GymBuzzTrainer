package com.app.gymbuztrainer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GymMachine {

    @SerializedName("gymMachineId")
    @Expose
    private Integer gymMachineId;
    @SerializedName("gymId")
    @Expose
    private Integer gymId;
    @SerializedName("machineId")
    @Expose
    private Integer machineId;
    @SerializedName("machineNumber")
    @Expose
    private Object machineNumber;
    @SerializedName("qrCode")
    @Expose
    private Object qrCode;
    @SerializedName("imagePath")
    @Expose
    private Object imagePath;
    @SerializedName("zoneID")
    @Expose
    private Integer zoneID;
    @SerializedName("floorID")
    @Expose
    private Integer floorID;
    @SerializedName("machinePoint")
    @Expose
    private Object machinePoint;
    @SerializedName("gym")
    @Expose
    private Object gym;
    @SerializedName("machine")
    @Expose
    private Machine machine;
    @SerializedName("floor")
    @Expose
    private Object floor;
    @SerializedName("zone")
    @Expose
    private Zone zone;
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


    public Integer getGymMachineId() {
        return gymMachineId;
    }

    public void setGymMachineId(Integer gymMachineId) {
        this.gymMachineId = gymMachineId;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Object getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Object machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Object getQrCode() {
        return qrCode;
    }

    public void setQrCode(Object qrCode) {
        this.qrCode = qrCode;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getZoneID() {
        return zoneID;
    }

    public void setZoneID(Integer zoneID) {
        this.zoneID = zoneID;
    }

    public Integer getFloorID() {
        return floorID;
    }

    public void setFloorID(Integer floorID) {
        this.floorID = floorID;
    }

    public Object getMachinePoint() {
        return machinePoint;
    }

    public void setMachinePoint(Object machinePoint) {
        this.machinePoint = machinePoint;
    }

    public Object getGym() {
        return gym;
    }

    public void setGym(Object gym) {
        this.gym = gym;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Object getFloor() {
        return floor;
    }

    public void setFloor(Object floor) {
        this.floor = floor;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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
