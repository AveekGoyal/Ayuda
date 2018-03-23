package com.example.admin.ayuda.Model;

/**
 * Created by asus on 3/21/2018.
 */

public class Story {

    public String storyImage;
    public String caption;
    public String orgName;
    public String firstName;
    public String lastName;
    public String imageDp;
    public String timestamp;

    public Story() {
    }

    public Story(String storyImage, String caption, String orgName, String firstName, String lastName, String imageDp, String timestamp) {
        this.storyImage = storyImage;
        this.caption = caption;
        this.orgName = orgName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageDp = imageDp;
        this.timestamp = timestamp;
    }

    public String getStoryImage() {
        return storyImage;
    }

    public void setStoryImage(String storyImage) {
        this.storyImage = storyImage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getImageDp() {
        return imageDp;
    }

    public void setImageDp(String imageDp) {
        this.imageDp = imageDp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
