package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emi_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmiDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;

    private Double emiAmount;

    private boolean paidStatus;

    private boolean dueStatus;

    // Many EmiDetails belong to one LoanAccount
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_account_number")
    private LoanAccountEntity loanAccount;
}
