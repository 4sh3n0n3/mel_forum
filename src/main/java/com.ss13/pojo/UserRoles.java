package com.ss13.pojo;

public enum UserRoles {
    MODERATOR("MODERATOR"),
    USER("USER");

    private final String text;

    private UserRoles(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
