package com.example.demo.service;

import com.example.demo.integration.ExternalLoanAccountClient;
import com.example.demo.model.dto.EmiDetailDTO;
import com.example.demo.model.dto.LoanAccountDueResponseDTO;
import com.example.demo.model.dto.LoanAccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanAccountService {

    private final ExternalLoanAccountClient externalClient;

    public LoanAccountDueResponseDTO processLoanAccount(Long accountNumber) {
        // Call external API
        LoanAccountResponseDTO response = externalClient.fetchLoanAccountFromExternalApi(accountNumber);

        // buisness logic to chk if emistatus is  paid or not

        if( response.getEmiDetails() != null && !response.getEmiDetails().isEmpty() ){
            for (EmiDetailDTO emi : response.getEmiDetails()) {
                if (!emi.isPaidStatus()) {
                    return LoanAccountDueResponseDTO.builder()
                            .loanAccountNumber(response.getLoanAccountNumber().toString())
                            .dueDate(emi.getMonth())
                            .emiAmount(emi.getEmiAmount())
                            .build();
                }
            }
        }



        return LoanAccountDueResponseDTO.builder()
                .loanAccountNumber(response.getLoanAccountNumber().toString())
                .dueDate("N/A")
                .emiAmount(0.0)
                .build();
    }
}
