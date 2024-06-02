package org.aishwarya.expensetracker.controller;

import jakarta.persistence.EntityNotFoundException;
import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Income;
import org.aishwarya.expensetracker.service.income.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody ExpenseIncomeDTO expenseIncomeDTO) {
        Income createdIncome = incomeService.postIncome(expenseIncomeDTO);
        if (createdIncome != null) {
            return ResponseEntity.ok(createdIncome);
        } else {
            return ResponseEntity.badRequest().body("Income not created");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable String id) {
        try{
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable String id, @RequestBody ExpenseIncomeDTO expenseIncomeDTO) {
        try{
            return ResponseEntity.ok(incomeService.updateIncome(id, expenseIncomeDTO));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable String id) {
        try{
            incomeService.deleteIncome(id);
            return ResponseEntity.ok("Income deleted successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
        }
    }
}
