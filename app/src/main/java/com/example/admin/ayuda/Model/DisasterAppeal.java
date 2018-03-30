package com.example.admin.ayuda.Model;

public class DisasterAppeal {

    public String picProof;
    public String typeOfDisaster;
    public String description;
    public String needFood;
    public String needWater;
    public String needShelter;
    public String needClothing;
    public String needMedical;
    public String needRehab;
    public String contactNo;
    public String altContactNo;
    public String userId;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String timestamp;
    public String isAccepted;



    public DisasterAppeal() {
    }


    public DisasterAppeal(String picProof,String isAccepted, String typeOfDisaster, String description, String needFood, String needWater, String needShelter, String needClothing, String needMedical, String needRehab, String contactNo, String altContactNo, String userId, String timestamp, String appealFirstName, String appealLastName, String appealImageDp) {
        this.picProof = picProof;
        this.typeOfDisaster = typeOfDisaster;
        this.description = description;
        this.needFood = needFood;
        this.needWater = needWater;
        this.needShelter = needShelter;
        this.needClothing = needClothing;
        this.needMedical = needMedical;
        this.needRehab = needRehab;
        this.contactNo = contactNo;
        this.altContactNo = altContactNo;
        this.userId = userId;
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
        this.timestamp = timestamp;
        this.isAccepted = isAccepted;


    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }

    public String getTypeOfDisaster() {
        return typeOfDisaster;
    }

    public void setTypeOfDisaster(String typeOfDisaster) {
        this.typeOfDisaster = typeOfDisaster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getNeedFood() {
        return needFood;
    }

    public void setNeedFood(String needFood) {
        this.needFood = needFood;
    }

    public String getNeedWater() {
        return needWater;
    }

    public void setNeedWater(String needWater) {
        this.needWater = needWater;
    }

    public String getNeedShelter() {
        return needShelter;
    }

    public void setNeedShelter(String needShelter) {
        this.needShelter = needShelter;
    }

    public String getNeedClothing() {
        return needClothing;
    }

    public void setNeedClothing(String needClothing) {
        this.needClothing = needClothing;
    }

    public String getNeedMedical() {
        return needMedical;
    }

    public void setNeedMedical(String needMedical) {
        this.needMedical = needMedical;
    }

    public String getNeedRehab() {
        return needRehab;
    }

    public void setNeedRehab(String needRehab) {
        this.needRehab = needRehab;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAltContactNo() {
        return altContactNo;
    }

    public void setAltContactNo(String altContactNo) {
        this.altContactNo = altContactNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getAppealFirstName()
    {
        return appealFirstName;

    }

    public void setAppealFirstName(String appealFirstName) {
        this.appealFirstName = appealFirstName;
    }

    public String getAppealLastName() {
        return appealLastName;
    }

    public void setAppealLastName(String appealLastName) {
        this.appealLastName = appealLastName;
    }

    public String getAppealImageDp() {
        return appealImageDp;
    }

    public void setAppealImageDp(String appealImageDp) {
        this.appealImageDp = appealImageDp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }
}
