package com.example.demo.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanAccountResponseDTO {
    private Long loanAccountNumber;
    private List<EmiDetailDTO> emiDetails;
}
