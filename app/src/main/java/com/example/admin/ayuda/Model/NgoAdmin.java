package com.example.admin.ayuda.Model;



public class NgoAdmin {
    public String orgName;
    public String imageDp;
    public String headName;
    public String panNumber;
    public String gender;
    public String email;
    public  String mobileNumber;
    public String websiteLink;
    public String ngoAddress;
    public String ngoPinCode;
    public String ngoState;
    public String ngoCity;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;

    public NgoAdmin() {
    }

    public NgoAdmin(String appealFirstName, String appealLastName, String appealImageDp) {
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
    }

    public NgoAdmin(String orgName, String imageDp, String headName, String panNumber, String gender, String email, String mobileNumber, String websiteLink, String ngoAddress, String ngoPinCode, String ngoState, String ngoCity) {
        this.orgName = orgName;
        this.imageDp = imageDp;
        this.headName = headName;
        this.panNumber = panNumber;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.websiteLink = websiteLink;
        this.ngoAddress = ngoAddress;
        this.ngoPinCode = ngoPinCode;
        this.ngoState = ngoState;
        this.ngoCity = ngoCity;
    }

    public NgoAdmin(String orgName, String imageDp, String headName, String panNumber, String gender, String email, String mobileNumber, String websiteLink, String ngoAddress, String ngoPinCode, String ngoState, String ngoCity, String appealFirstName, String appealLastName, String appealImageDp) {
        this.orgName = orgName;
        this.imageDp = imageDp;
        this.headName = headName;
        this.panNumber = panNumber;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.websiteLink = websiteLink;
        this.ngoAddress = ngoAddress;
        this.ngoPinCode = ngoPinCode;
        this.ngoState = ngoState;
        this.ngoCity = ngoCity;
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getImageDp() {
        return imageDp;
    }

    public void setImageDp(String imageDp) {
        this.imageDp = imageDp;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getNgoAddress() {
        return ngoAddress;
    }

    public void setNgoAddress(String ngoAddress) {
        this.ngoAddress = ngoAddress;
    }

    public String getNgoPinCode() {
        return ngoPinCode;
    }

    public void setNgoPinCode(String ngoPinCode) {
        this.ngoPinCode = ngoPinCode;
    }

    public String getNgoState() {
        return ngoState;
    }

    public void setNgoState(String ngoState) {
        this.ngoState = ngoState;
    }

    public String getNgoCity() {
        return ngoCity;
    }

    public void setNgoCity(String ngoCity) {
        this.ngoCity = ngoCity;
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
}
