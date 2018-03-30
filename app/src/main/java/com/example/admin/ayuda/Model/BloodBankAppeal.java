package com.example.admin.ayuda.Model;

public class BloodBankAppeal {

    public String picProof;
    public String patientName;
    public String familyMemberName;
    public String familyMemberContactNo;
    public String familyMemberAltContactNo;
    public String hospitalName;
    public String hospitalContactNo;
    public String hospitalAddress;
    public String plateletsCount;
    public String amountNeeded;
    public String bloodGroup;
    public String userId;
    public String timestamp;
    public String appealFirstName;
    public String appealLastName;
    public String appealImageDp;
    public String isAccepted;

    public BloodBankAppeal() {
    }

    public BloodBankAppeal(String picProof,String isAccepted, String patientName, String familyMemberName, String familyMemberContactNo, String familyMemberAltContactNo, String hospitalName, String hospitalContactNo, String hospitalAddress, String plateletsCount, String amountNeeded, String bloodGroup, String userId, String timestamp, String appealFirstName, String appealLastName, String appealImageDp) {
        this.picProof = picProof;
        this.patientName = patientName;
        this.familyMemberName = familyMemberName;
        this.familyMemberContactNo = familyMemberContactNo;
        this.familyMemberAltContactNo = familyMemberAltContactNo;
        this.hospitalName = hospitalName;
        this.hospitalContactNo = hospitalContactNo;
        this.hospitalAddress = hospitalAddress;
        this.plateletsCount = plateletsCount;
        this.amountNeeded = amountNeeded;
        this.bloodGroup = bloodGroup;
        this.userId = userId;
        this.timestamp = timestamp;
        this.appealFirstName = appealFirstName;
        this.appealLastName = appealLastName;
        this.appealImageDp = appealImageDp;
        this.isAccepted = isAccepted;

    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getFamilyMemberName() {
        return familyMemberName;
    }

    public void setFamilyMemberName(String familyMemberName) {
        this.familyMemberName = familyMemberName;
    }

    public String getFamilyMemberContactNo() {
        return familyMemberContactNo;
    }

    public void setFamilyMemberContactNo(String familyMemberContactNo) {
        this.familyMemberContactNo = familyMemberContactNo;
    }

    public String getFamilyMemberAltContactNo() {
        return familyMemberAltContactNo;
    }

    public void setFamilyMemberAltContactNo(String familyMemberAltContactNo) {
        this.familyMemberAltContactNo = familyMemberAltContactNo;
    }

    public String getHospitakName() {
        return hospitalName;
    }

    public void setHospitakName(String hospitakName) {
        this.hospitalName = hospitakName;
    }

    public String getHospitalContactNo() {
        return hospitalContactNo;
    }

    public void setHospitalContactNo(String hospitalContactNo) {
        this.hospitalContactNo = hospitalContactNo;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getPlateletsCount() {
        return plateletsCount;
    }

    public void setPlateletsCount(String plateletsCount) {
        this.plateletsCount = plateletsCount;
    }

    public String getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(String amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }
}
