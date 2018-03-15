package com.example.admin.ayuda.Model;


public class ChildAbuseAppeals {

    public String picProof;
    public String desc;
    public String physicalAbuse;
    public String sexualAbuse;
    public String psychologicalAbuse;
    public String abandon;
    public String childLabour;
    public String childMarriage;
    public String approxAge;
    public String gender;

    public ChildAbuseAppeals() {
    }

    public ChildAbuseAppeals(String picProof, String desc, String physicalAbuse, String sexualAbuse, String psychologicalAbuse, String abandon, String childLabour, String childMarriage, String approxAge, String gender) {
        this.picProof = picProof;
        this.desc = desc;
        this.physicalAbuse = physicalAbuse;
        this.sexualAbuse = sexualAbuse;
        this.psychologicalAbuse = psychologicalAbuse;
        this.abandon = abandon;
        this.childLabour = childLabour;
        this.childMarriage = childMarriage;
        this.approxAge = approxAge;
        this.gender = gender;
    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getAbandon() {
        return abandon;
    }

    public void setAbandon(String abandon) {
        this.abandon = abandon;
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

    public String getApproxAge() {
        return approxAge;
    }

    public void setApproxAge(String approxAge) {
        this.approxAge = approxAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
