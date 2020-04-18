package com.example.demo.model;

public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    private final String text;

    Role(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
