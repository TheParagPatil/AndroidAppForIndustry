package com.oneroofit.it.Helper;

public class TeamHelperClass {
    String image;
    String title,description;

    public TeamHelperClass(){

    }

    public TeamHelperClass(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

