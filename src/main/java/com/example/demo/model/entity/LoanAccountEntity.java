package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "loan_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanAccountEntity {

    @Id
    @Column(name = "loan_account_number")
    private Long loanAccountNumber;

     //One LoanAccount has many EmiDetails
    @OneToMany(mappedBy = "loanAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmiDetailEntity> emiDetails;

    @Override
    public String toString() {
        return "LoanAccountEntity{" +
                "loanAccountNumber=" + loanAccountNumber +
                '}';
    }
}
