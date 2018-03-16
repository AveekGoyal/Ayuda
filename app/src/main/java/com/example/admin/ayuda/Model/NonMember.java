package com.example.admin.ayuda.Model;

public class NonMember {

    public String dpImage;
    public String firstName;
    public String lastName;
    public String gender;
    public String contactNumber;
    public String email;
    public String type;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;

    public NonMember() {
    }

    public NonMember(String appealFirstName, String appealLastName, String appealImageDp) {
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
    }

    public NonMember(String dpImage, String firstName, String lastName, String gender, String contactNumber, String email, String type) {
        this.dpImage = dpImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
        this.type = type;
    }

    public String getDpImage() {
        return dpImage;
    }

    public void setDpImage(String dpImage) {
        this.dpImage = dpImage;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
