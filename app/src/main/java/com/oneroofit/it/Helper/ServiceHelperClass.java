package com.oneroofit.it.Helper;

public class ServiceHelperClass {

    String title,description,image,small_desc;

    public ServiceHelperClass(){

    }

    public ServiceHelperClass(String image, String title, String description, String small_desc) {
        this.image = image;
        this.title = title;
        this.small_desc = small_desc;
        this.description = description;
    }

    public String  getImage() {
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

    public String getSmall_desc() {
        return small_desc;
    }

    public void setSmall_desc(String small_desc) {
        this.small_desc = small_desc;
    }
}
