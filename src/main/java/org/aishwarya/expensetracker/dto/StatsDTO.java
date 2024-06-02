package org.aishwarya.expensetracker.dto;

import lombok.Data;
import org.aishwarya.expensetracker.entity.Expense;
import org.aishwarya.expensetracker.entity.Income;

@Data
public class StatsDTO {

    private Double totalIncome;
    private Double totalExpense;
    private Income latestIncome;
    private Expense latestExpense;
    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;

}
