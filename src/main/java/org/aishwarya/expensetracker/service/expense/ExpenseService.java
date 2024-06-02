package org.aishwarya.expensetracker.service.expense;

import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseIncomeDTO expenseIncomeDTO);
    List<Expense> getAllExpenses();
    Expense getExpenseById(String id);
    Expense updateExpense(String id, ExpenseIncomeDTO expenseIncomeDTO);
    void deleteExpense(String id);

}
