package com.example.demo.dto;

public enum ResponseType {
    ERROR("ERROR"),
    SUCCESS("SUCCESS");

    private final String text;

    ResponseType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
