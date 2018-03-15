package com.example.admin.ayuda.Model;

public class CommunityAppeal {

    public String picProof;
    public String cleaning;
    public String hunger;
    public String healthIssues;
    public String poverty;
    public String desc;
    public String contactNo;

    public CommunityAppeal() {
    }

    public CommunityAppeal(String picProof, String cleaning, String hunger, String healthIssues, String poverty, String desc, String contactNo) {
        this.picProof = picProof;
        this.cleaning = cleaning;
        this.hunger = hunger;
        this.healthIssues = healthIssues;
        this.poverty = poverty;
        this.desc = desc;
        this.contactNo = contactNo;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
