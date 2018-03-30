package com.example.admin.ayuda.Model;

/**
 * Created by asus on 3/31/2018.
 */

public class LeaderboardModel {

    public String rank;
    public String imageDp;
    public String orgName;
    public String points;

    public LeaderboardModel() {
    }

    public LeaderboardModel(String rank, String imageDp, String orgName, String points) {
        this.rank = rank;
        this.imageDp = imageDp;
        this.orgName = orgName;
        this.points = points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getImageDp() {
        return imageDp;
    }

    public void setImageDp(String imageDp) {
        this.imageDp = imageDp;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
