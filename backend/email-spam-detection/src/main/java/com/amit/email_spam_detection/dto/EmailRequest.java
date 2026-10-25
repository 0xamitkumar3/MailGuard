package com.amit.email_spam_detection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailRequest {

    @NotBlank(message = "Email text cannot be empty")
    @Size(max = 10000, message = "Email text cannot exceed 10000 characters")
    private String emailText;

    public EmailRequest() {
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }
}