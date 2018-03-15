package com.example.admin.ayuda.Model;


public class OldAgeHomeAppeal {

    public String picProof;
    public String description;
    public String address;
    public String financialNeeds;
    public String medicalNeeds;
    public String livelihoodNeeds;

    public OldAgeHomeAppeal() {
    }

    public OldAgeHomeAppeal(String picProof, String description, String address, String financialNeeds, String medicalNeeds, String livelihoodNeeds) {
        this.picProof = picProof;
        this.description = description;
        this.address = address;
        this.financialNeeds = financialNeeds;
        this.medicalNeeds = medicalNeeds;
        this.livelihoodNeeds = livelihoodNeeds;
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
}
