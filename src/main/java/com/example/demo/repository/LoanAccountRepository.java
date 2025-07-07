package com.example.demo.repository;

import com.example.demo.model.entity.LoanAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanAccountRepository extends JpaRepository<LoanAccountEntity, Long> {
}
