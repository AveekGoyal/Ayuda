package com.example.admin.ayuda.Model;

public class CommunityAppeal {

    public String picProof;
    public String cleaning;
    public String hunger;
    public String healthIssues;
    public String poverty;
    public String description;
    public String contactNo;
    public String userId;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String timestamp;
    public String isAccepted;


    public CommunityAppeal() {
    }

    public CommunityAppeal(String picProof,String isAccepted, String cleaning, String hunger, String healthIssues, String poverty, String description, String contactNo, String userId, String appealFirstName, String appealLastName, String appealImageDp, String timestamp) {
        this.picProof = picProof;
        this.cleaning = cleaning;
        this.hunger = hunger;
        this.healthIssues = healthIssues;
        this.poverty = poverty;
        this.description = description;
        this.contactNo = contactNo;
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

    public String getCleaning() {
        return cleaning;
    }

    public void setCleaning(String cleaning) {
        this.cleaning = cleaning;
    }

    public String getHunger() {
        return hunger;
    }

    public void setHunger(String hunger) {
        this.hunger = hunger;
    }

    public String getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(String healthIssues) {
        this.healthIssues = healthIssues;
    }

    public String getPoverty() {
        return poverty;
    }

    public void setPoverty(String poverty) {
        this.poverty = poverty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppealFirstName() {
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
