package com.amit.email_spam_detection.dto;

public class SpamCheckResponse {

    private boolean spam;
    private String message;
    private String reason;

    public SpamCheckResponse() {
    }

    public SpamCheckResponse(boolean spam, String message, String reason) {
        this.spam = spam;
        this.message = message;
        this.reason = reason;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}