package com.example.demo.integration;

import com.example.demo.controller.LoanAccountController;
import com.example.demo.model.dto.LoanAccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ExternalLoanAccountClient {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LoanAccountController.class);

    private static final String EXTERNAL_API_URL = "http://demo9993930.mockable.io/loanaccount/1";

    public LoanAccountResponseDTO fetchLoanAccountFromExternalApi(Long accountNumber) {
        LoanAccountResponseDTO loanAccountResponseDTO = null;
        try {
            loanAccountResponseDTO = restTemplate.getForObject(EXTERNAL_API_URL, LoanAccountResponseDTO.class, accountNumber);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return loanAccountResponseDTO;
    }
}
