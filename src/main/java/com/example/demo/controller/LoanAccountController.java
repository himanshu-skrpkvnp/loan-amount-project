package com.example.demo.controller;

import com.example.demo.model.dto.LoanAccountDueResponseDTO;
import com.example.demo.service.LoanAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loanaccount")
@RequiredArgsConstructor
public class LoanAccountController {

    private final LoanAccountService loanAccountService;
    private static final Logger logger = LoggerFactory.getLogger(LoanAccountController.class);


    @GetMapping("/{accountNumber}")
    public LoanAccountDueResponseDTO getLoanAccount(@PathVariable Long accountNumber) {
        logger.info("Received request for loan account: {}", accountNumber);
        LoanAccountDueResponseDTO response = loanAccountService.processLoanAccount(accountNumber);
        logger.info("Returning response to source: {}", response);
        return response;
    }
}
