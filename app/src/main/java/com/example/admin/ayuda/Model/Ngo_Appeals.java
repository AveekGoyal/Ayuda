package com.example.admin.ayuda.Model;

/**
 * Created by Admin on 24-Mar-18.
 */

public class Ngo_Appeals {
    public String adminEmail;
    public String adminOrgName;
    public String adminUserId;
    public String appealImageDp;
    public String appealName;
    public String appealTimestamp;


    public Ngo_Appeals() {
    }

    public Ngo_Appeals(String appealImageDp, String appealName, String appealTimestamp) {
        this.appealImageDp = appealImageDp;
        this.appealName = appealName;
        this.appealTimestamp = appealTimestamp;
    }

    public Ngo_Appeals(String adminEmail, String adminOrgName, String adminUserId, String appealImageDp, String appealName, String appealTimestamp) {
        this.adminEmail = adminEmail;
        this.adminOrgName = adminOrgName;
        this.adminUserId = adminUserId;
        this.appealImageDp = appealImageDp;
        this.appealName = appealName;
        this.appealTimestamp = appealTimestamp;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminOrgName() {
        return adminOrgName;
    }

    public void setAdminOrgName(String adminOrgName) {
        this.adminOrgName = adminOrgName;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAppealImageDp() {
        return appealImageDp;
    }

    public void setAppealImageDp(String appealImageDp) {
        this.appealImageDp = appealImageDp;
    }

    public String getAppealName() {
        return appealName;
    }

    public void setAppealName(String appealName) {
        this.appealName = appealName;
    }

    public String getAppealTimestamp() {
        return appealTimestamp;
    }

    public void setAppealTimestamp(String appealTimestamp) {
        this.appealTimestamp = appealTimestamp;
    }
}

