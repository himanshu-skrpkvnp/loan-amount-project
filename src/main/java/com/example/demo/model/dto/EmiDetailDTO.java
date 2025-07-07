package com.example.demo.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmiDetailDTO {
    private String month;
    private Double emiAmount;
    private boolean paidStatus;
    private boolean dueStatus;
}
