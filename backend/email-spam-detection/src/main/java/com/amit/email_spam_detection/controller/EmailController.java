
package com.amit.email_spam_detection.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.email_spam_detection.dto.EmailRequest;
import com.amit.email_spam_detection.dto.SpamCheckResponse;
import com.amit.email_spam_detection.service.EmailService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = {
    "http://localhost:5175",
    "https://mail-guard-eight.vercel.app"
})
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email/health")
    public String healthCheck() {
        return "Email Spam Detection API is running!";
    }

    @PostMapping("/email/check")
    public SpamCheckResponse checkEmail(
            @Valid @RequestBody EmailRequest request) {
        return emailService.checkEmail(request.getEmailText());
    }
}
