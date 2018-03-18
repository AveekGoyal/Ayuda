package com.example.admin.ayuda.Model;

public class CommunityAppeal {

    public String picProof;
    public String Cleaning;
    public String Hunger;
    public String HealthIssues;
    public String Poverty;
    public String description;
    public String ContactNo;
    public String userId;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String timestamp;


    public CommunityAppeal() {
    }



    public CommunityAppeal(String picProof, String Cleaning, String Hunger, String HealthIssues, String Poverty, String description, String ContactNo, String userId, String timestamp, String appealFirstName, String appealLastName, String appealImageDp) {
        this.picProof = picProof;
        this.Cleaning = Cleaning;
        this.Hunger = Hunger;
        this.HealthIssues = HealthIssues;
        this.Poverty = Poverty;
        this.description = description;
        this.ContactNo = ContactNo;
        this.userId = userId;
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
        this.timestamp = timestamp;

    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }

    public String getCleaning() {
        return Cleaning;
    }

    public void setCleaning(String Cleaning) {
        this.Cleaning = Cleaning;
    }

    public String getHunger() {
        return Hunger;
    }

    public void setHunger(String Hunger) {
        this.Hunger = Hunger;
    }

    public String getHealthIssues() {
        return HealthIssues;
    }

    public void setHealthIssues(String HealthIssues) {
        this.HealthIssues = HealthIssues;
    }

    public String getPoverty() {
        return Poverty;
    }

    public void setPoverty(String Poverty) {
        this.Poverty = Poverty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
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




}
