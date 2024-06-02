package org.aishwarya.expensetracker.dto;

import lombok.Data;
import org.aishwarya.expensetracker.entity.Expense;
import org.aishwarya.expensetracker.entity.Income;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;
    private List<Income> incomeList;
}
