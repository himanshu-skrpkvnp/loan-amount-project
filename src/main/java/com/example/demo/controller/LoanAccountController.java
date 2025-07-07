package com.example.demo.controller;

import com.example.demo.model.dto.LoanAccountDueResponseDTO;
import com.example.demo.model.dto.LoanAccountResponseDTO;
import com.example.demo.service.LoanAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loanaccount")
@RequiredArgsConstructor
public class LoanAccountController {

    private final LoanAccountService loanAccountService;



    @GetMapping("/{accountNumber}")
    public LoanAccountDueResponseDTO getLoanAccount(@PathVariable Long accountNumber) {
        return loanAccountService.processLoanAccount(accountNumber);
    }
}
