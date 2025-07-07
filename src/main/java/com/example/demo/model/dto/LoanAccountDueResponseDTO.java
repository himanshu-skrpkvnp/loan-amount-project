package com.example.demo.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanAccountDueResponseDTO {
    private String loanAccountNumber;
    private String dueDate;    // You can use LocalDate too
    private Double emiAmount;
}
