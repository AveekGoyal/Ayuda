package com.example.admin.ayuda.Model;

public class BloodBankAppeal {

    public String picProof;
    public String patientName;
    public String familyMemberName;
    public String familyMemberContactNo;
    public String familyMemberAltContactNo;
    public String hospitakName;
    public String hospitalContactNo;
    public String hospitalAddress;
    public String plateletsCount;
    public String amountNeeded;
    public String bloodGroup;

    public BloodBankAppeal() {
    }

    public BloodBankAppeal(String picProof, String patientName, String familyMemberName, String familyMemberContactNo, String familyMemberAltContactNo, String hospitakName, String hospitalContactNo, String hospitalAddress, String plateletsCount, String amountNeeded, String bloodGroup) {
        this.picProof = picProof;
        this.patientName = patientName;
        this.familyMemberName = familyMemberName;
        this.familyMemberContactNo = familyMemberContactNo;
        this.familyMemberAltContactNo = familyMemberAltContactNo;
        this.hospitakName = hospitakName;
        this.hospitalContactNo = hospitalContactNo;
        this.hospitalAddress = hospitalAddress;
        this.plateletsCount = plateletsCount;
        this.amountNeeded = amountNeeded;
        this.bloodGroup = bloodGroup;
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
        return hospitakName;
    }

    public void setHospitakName(String hospitakName) {
        this.hospitakName = hospitakName;
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
}
