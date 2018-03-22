package com.example.admin.ayuda.Model;

/**
 * Created by HP on 3/19/2018.
 */

public class News {

    public String newsDescription;
    public String newsHeadline;
    private String picProof;
    public String newsOrgName;
    public String newsOrgDp;

    public News(String newsDescription, String newsHeadline, String picProof, String newsOrgName, String newsOrgDp) {
        this.newsDescription = newsDescription;
        this.newsHeadline = newsHeadline;
        this.picProof = picProof;
        this.newsOrgName = newsOrgName;
        this.newsOrgDp = newsOrgDp;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }
    public String getNewsOrgName() {
        return newsOrgName;
    }

    public void setNewsOrgName(String newsOrgName) {
        this.newsOrgName = newsOrgName;
    }



    public String getNewsOrgDp() {
        return newsOrgDp;
    }

    public void setNewsOrgDp(String newsOrgDp) {
        this.newsOrgDp = newsOrgDp;
    }





    public News() {
    }


}
