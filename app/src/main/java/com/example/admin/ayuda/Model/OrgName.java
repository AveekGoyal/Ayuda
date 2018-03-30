package com.example.admin.ayuda.Model;

/**
 * Created by asus on 3/30/2018.
 */

public class OrgName {

    public String emailId;
    public String orgName;

    public OrgName() {
    }

    public OrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return orgName;
    }
}
