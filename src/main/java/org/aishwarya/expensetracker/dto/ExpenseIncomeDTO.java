package org.aishwarya.expensetracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseIncomeDTO {

    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;

}
