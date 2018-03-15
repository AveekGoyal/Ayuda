package com.example.admin.ayuda.Model;



public class Members {
    public String firstName;
    public String lastName;
    public String gender;
    public String dpImage;
    public String email;
    public String moblileNo;
    public String orgName;
    public String memberId;
    public String address;
    public String city;
    public String state;
    public String pincode;

    public Members() {
    }

    public Members(String firstName, String lastName, String gender, String dpImage, String email, String moblileNo, String orgName, String memberId, String address, String city, String state, String pincode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dpImage = dpImage;
        this.email = email;
        this.moblileNo = moblileNo;
        this.orgName = orgName;
        this.memberId = memberId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDpImage() {
        return dpImage;
    }

    public void setDpImage(String dpImage) {
        this.dpImage = dpImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoblileNo() {
        return moblileNo;
    }

    public void setMoblileNo(String moblileNo) {
        this.moblileNo = moblileNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
