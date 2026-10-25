package com.amit.email_spam_detection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpamCheckResponse {
    private boolean spam;
    private String message;
    private String reason;
}