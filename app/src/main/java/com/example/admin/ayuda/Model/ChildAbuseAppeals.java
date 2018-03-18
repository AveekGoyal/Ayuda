package com.example.admin.ayuda.Model;


public class ChildAbuseAppeals {

    public String picProof;
    public String description;
    public String physicalAbuse;
    public String sexualAbuse;
    public String psychologicalAbuse;
    public String childAbandon;
    public String childLabour;
    public String childMarriage;
    public String childApproxAge;
    public String gender;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String timestamp;



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

    public ChildAbuseAppeals() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ChildAbuseAppeals(String picProof, String description, String physicalAbuse, String sexualAbuse, String psychologicalAbuse, String childAbandon, String childLabour, String childMarriage, String childApproxAge, String gender, String appealFirstName, String appealLastName, String appealImageDp, String timestamp) {
        this.picProof = picProof;
        this.description = description;
        this.physicalAbuse = physicalAbuse;
        this.sexualAbuse = sexualAbuse;
        this.psychologicalAbuse = psychologicalAbuse;
        this.childAbandon = childAbandon;
        this.childLabour = childLabour;
        this.childMarriage = childMarriage;
        this.childApproxAge = childApproxAge;
        this.gender = gender;

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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhysicalAbuse() {
        return physicalAbuse;
    }

    public void setPhysicalAbuse(String physicalAbuse) {
        this.physicalAbuse = physicalAbuse;
    }

    public String getSexualAbuse() {
        return sexualAbuse;
    }

    public void setSexualAbuse(String sexualAbuse) {
        this.sexualAbuse = sexualAbuse;
    }

    public String getPsychologicalAbuse() {
        return psychologicalAbuse;
    }

    public void setPsychologicalAbuse(String psychologicalAbuse) {
        this.psychologicalAbuse = psychologicalAbuse;
    }

    public String getChildAbandon() {
        return childAbandon;
    }

    public void setChildAbandon(String childAbandon) {
        this.childAbandon = childAbandon;
    }

    public String getChildLabour() {
        return childLabour;
    }

    public void setChildLabour(String childLabour) {
        this.childLabour = childLabour;
    }

    public String getChildMarriage() {
        return childMarriage;
    }

    public void setChildMarriage(String childMarriage) {
        this.childMarriage = childMarriage;
    }

    public String getChildApproxAge() {
        return childApproxAge;
    }

    public void setChildApproxAge(String childApproxAge) {
        this.childApproxAge = childApproxAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

        }
