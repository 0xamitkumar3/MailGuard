package com.amit.email_spam_detection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailRequest {
    @NotBlank(message = "Email text cannot be empty")
    @Size(max = 10000, message = "Email text cannot exceed 10000 characters")
    private String emailText;
}
