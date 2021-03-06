package com.phuccdung.tinder_final.Chat;

public class Chat {
    private String message;
    private Boolean currentUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Boolean currentUser) {
        this.currentUser = currentUser;
    }

    public Chat(String message, Boolean currentUser) {
        this.message = message;
        this.currentUser = currentUser;
    }
}
