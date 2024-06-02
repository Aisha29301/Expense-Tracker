package org.aishwarya.expensetracker.service.income;

import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(ExpenseIncomeDTO expenseIncomeDTO);
    void deleteIncome(String id);
    Income getIncomeById(String id);
    Income updateIncome(String id, ExpenseIncomeDTO expenseIncomeDTO);
    List<Income> getAllIncomes();

}
