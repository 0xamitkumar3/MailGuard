
package com.amit.email_spam_detection.controller;

import com.amit.email_spam_detection.dto.EmailRequest;
import com.amit.email_spam_detection.dto.SpamCheckResponse;
import com.amit.email_spam_detection.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5175")
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
