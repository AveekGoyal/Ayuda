package com.example.admin.ayuda.Model;

/**
 * Created by asus on 3/21/2018.
 */

public class Story {

    public String storyImage;
    public String caption;

    public Story() {
    }

    public Story(String storyImage, String caption) {
        this.storyImage = storyImage;
        this.caption = caption;
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
}
