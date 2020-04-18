package com.example.demo.constant;

public enum MailContentType {

    PLAIN_TEXT("text/plain");

    private final String text;

    MailContentType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
