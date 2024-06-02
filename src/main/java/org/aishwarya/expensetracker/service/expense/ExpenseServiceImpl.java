package org.aishwarya.expensetracker.service.expense;

import jakarta.persistence.EntityNotFoundException;
import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Expense;
import org.aishwarya.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense postExpense(ExpenseIncomeDTO expenseIncomeDTO){
        Expense expense = new Expense();
        return saveOrUpdateExpense(expense, expenseIncomeDTO);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Expense getExpenseById(String id){
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }else{
            throw new EntityNotFoundException("Expense not found with id: "+id);
        }
    }

    @Override
    public Expense updateExpense(String id, ExpenseIncomeDTO expenseIncomeDTO){
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return saveOrUpdateExpense(expense.get(), expenseIncomeDTO);
        }else{
            throw new EntityNotFoundException("Expense not found with id: "+id);
        }
    }

    @Override
    public void deleteExpense(String id){
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense not found with id: "+id);
        }
    }


    private Expense saveOrUpdateExpense(Expense expense, ExpenseIncomeDTO expenseIncomeDTO){
        expense.setTitle(expenseIncomeDTO.getTitle());
        expense.setDate(expenseIncomeDTO.getDate());
        expense.setDescription(expenseIncomeDTO.getDescription());
        expense.setAmount(expenseIncomeDTO.getAmount());
        expense.setCategory(expenseIncomeDTO.getCategory());
        return expenseRepository.save(expense);
    }
}

