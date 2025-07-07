package com.example.demo.integration;

import com.example.demo.model.dto.LoanAccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ExternalLoanAccountClient {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String EXTERNAL_API_URL = "http://demo9993930.mockable.io/loanaccount/1";

    public LoanAccountResponseDTO fetchLoanAccountFromExternalApi(Long accountNumber) {
        return restTemplate.getForObject(EXTERNAL_API_URL, LoanAccountResponseDTO.class, accountNumber);
    }
}
