package com.example.admin.ayuda.Model;


public class OldAgeHomeAppeal {

    public String picProof;
    public String description;
    public String address;
    public String financialNeeds;
    public String medicalNeeds;
    public String livelihoodNeeds;
    public String userId;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String timestamp;


    public OldAgeHomeAppeal() {
    }

    public OldAgeHomeAppeal(String picProof, String description, String address, String financialNeeds, String medicalNeeds, String livelihoodNeeds, String userId, String appealFirstName, String appealLastName, String appealImageDp, String timestamp) {
        this.picProof = picProof;
        this.description = description;
        this.address = address;
        this.financialNeeds = financialNeeds;
        this.medicalNeeds = medicalNeeds;
        this.livelihoodNeeds = livelihoodNeeds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFinancialNeeds() {
        return financialNeeds;
    }

    public void setFinancialNeeds(String financialNeeds) {
        this.financialNeeds = financialNeeds;
    }

    public String getMedicalNeeds() {
        return medicalNeeds;
    }

    public void setMedicalNeeds(String medicalNeeds) {
        this.medicalNeeds = medicalNeeds;
    }

    public String getLivelihoodNeeds() {
        return livelihoodNeeds;
    }

    public void setLivelihoodNeeds(String livelihoodNeeds) {
        this.livelihoodNeeds = livelihoodNeeds;
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
}


