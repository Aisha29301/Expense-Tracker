package org.aishwarya.expensetracker.service.income;

import jakarta.persistence.EntityNotFoundException;
import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Income;
import org.aishwarya.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public Income postIncome(ExpenseIncomeDTO expenseIncomeDTO){
        Income income = new Income();
        return saveOrUpdateIncome(income, expenseIncomeDTO);
    }

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Income getIncomeById(String id){
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()) {
            return income.get();
        }else{
            throw new EntityNotFoundException("Income not found with id: "+id);
        }
    }

    @Override
    public void deleteIncome(String id){
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()){
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Income not found with id: "+id);
        }
    }

    @Override
    public Income updateIncome(String id, ExpenseIncomeDTO expenseIncomeDTO){
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()){
            return saveOrUpdateIncome(income.get(), expenseIncomeDTO);
        }else{
            throw new EntityNotFoundException("Income not found with id: "+id);
        }
    }

    public Income saveOrUpdateIncome(Income income, ExpenseIncomeDTO expenseIncomeDTO){
        income.setAmount(expenseIncomeDTO.getAmount());
        income.setCategory(expenseIncomeDTO.getCategory());
        income.setDescription(expenseIncomeDTO.getDescription());
        income.setTitle(expenseIncomeDTO.getTitle());
        income.setDate(expenseIncomeDTO.getDate());
        return incomeRepository.save(income);
    }

}
