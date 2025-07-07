package com.example.demo.service;

import com.example.demo.integration.ExternalLoanAccountClient;
import com.example.demo.model.dto.EmiDetailDTO;
import com.example.demo.model.dto.LoanAccountDueResponseDTO;
import com.example.demo.model.dto.LoanAccountResponseDTO;
import com.example.demo.model.entity.EmiDetailEntity;
import com.example.demo.model.entity.LoanAccountEntity;
import com.example.demo.repository.LoanAccountRepository;
import com.example.demo.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LoanAccountService {

    private final ExternalLoanAccountClient externalClient;
    private static final Logger logger = LoggerFactory.getLogger(LoanAccountService.class);
    private final LoanAccountRepository loanAccountRepository;

    public LoanAccountDueResponseDTO processLoanAccount(Long accountNumber) {
        // Call external API
        LoanAccountResponseDTO response = externalClient.fetchLoanAccountFromExternalApi(accountNumber);
        logger.debug("Received external response: {}", response);

        saveLoanAccount(response);

        // buisness logic to chk if emistatus is  paid or not

        if( response.getEmiDetails() != null && !response.getEmiDetails().isEmpty() ){
            for (EmiDetailDTO emi : response.getEmiDetails()) {
                if (!emi.isPaidStatus()) {

                    String formattedDate = DateUtils.getNextMonthMidDate(emi.getMonth());
                    logger.info("Found unpaid EMI. Due date calculated: {}", formattedDate);
                    return LoanAccountDueResponseDTO.builder()
                            .loanAccountNumber(response.getLoanAccountNumber().toString())
                            .dueDate(formattedDate)
                            .emiAmount(emi.getEmiAmount())
                            .build();
                }
            }
        }


        logger.info("No unpaid EMI found. Returning fallback response.");
        return LoanAccountDueResponseDTO.builder()
                .loanAccountNumber(response.getLoanAccountNumber().toString())
                .dueDate("N/A")
                .emiAmount(0.0)
                .build();
    }

    private void saveLoanAccount(LoanAccountResponseDTO dto) {

        LoanAccountEntity loanAccountEntity = LoanAccountEntity.builder()
                .loanAccountNumber(dto.getLoanAccountNumber())
                .build();

        List<EmiDetailEntity> emiDetails = dto.getEmiDetails().stream()
                .map(emiDTO -> {
                    EmiDetailEntity emiEntity = EmiDetailEntity.builder()
                            .month(emiDTO.getMonth())
                            .emiAmount(emiDTO.getEmiAmount())
                            .paidStatus(emiDTO.isPaidStatus())
                            .dueStatus(emiDTO.isDueStatus())
                            .loanAccount(loanAccountEntity)
                            .build();
                    return emiEntity;
                })
                .collect(Collectors.toList());


        loanAccountEntity.setEmiDetails(emiDetails);


        loanAccountRepository.save(loanAccountEntity);
    }

}
