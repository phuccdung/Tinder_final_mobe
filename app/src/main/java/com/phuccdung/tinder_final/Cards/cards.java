package com.phuccdung.tinder_final.Cards;

public class cards {
    String userId;
    String name;
    String profileImageUrl;
    String about;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public cards(String userId, String name, String profileImageUrl, String about) {
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.about = about;
    }
}
