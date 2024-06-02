package org.aishwarya.expensetracker.service.stats;

import lombok.extern.log4j.Log4j2;
import org.aishwarya.expensetracker.dto.GraphDTO;
import org.aishwarya.expensetracker.dto.StatsDTO;
import org.aishwarya.expensetracker.entity.Expense;
import org.aishwarya.expensetracker.entity.Income;
import org.aishwarya.expensetracker.repository.ExpenseRepository;
import org.aishwarya.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@Log4j2
public class StatsServiceImpl implements StatsService{

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public GraphDTO getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        log.warn("Expense List: {}", graphDTO.getExpenseList());
        //The minusDays method subtracts 30 days from the current date,
        // and the findByDateBetween method typically includes data
        // from the start date up to, but not including, the end date.
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        log.warn("Income List: {}", graphDTO.getIncomeList());
        return graphDTO;
    }

    public StatsDTO getStats(){
        StatsDTO statsDTO = new StatsDTO();

        Optional<Income> lastIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> lastExpense = expenseRepository.findFirstByOrderByDateDesc();

        lastIncome.ifPresent(statsDTO::setLatestIncome);
        lastExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setTotalIncome(incomeRepository.sumAllAmounts());
        statsDTO.setTotalExpense(expenseRepository.sumAllAmounts());

        statsDTO.setBalance(statsDTO.getTotalIncome() - statsDTO.getTotalExpense());

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);

        return statsDTO;
    }

}
