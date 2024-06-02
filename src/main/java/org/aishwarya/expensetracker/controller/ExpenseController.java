package org.aishwarya.expensetracker.controller;

import jakarta.persistence.EntityNotFoundException;
import org.aishwarya.expensetracker.dto.ExpenseIncomeDTO;
import org.aishwarya.expensetracker.entity.Expense;
import org.aishwarya.expensetracker.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseIncomeDTO expenseIncomeDTO){
        Expense createdExpense = expenseService.postExpense(expenseIncomeDTO);
        if(createdExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expense not created");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getExpenses(){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(expenseService.getExpenseById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable String id, @RequestBody ExpenseIncomeDTO expenseIncomeDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(expenseService.updateExpense(id, expenseIncomeDTO));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable String id){
        try{
            expenseService.deleteExpense(id);
            return ResponseEntity.status(HttpStatus.OK).body("Expense deleted successfully");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong!");
        }
    }




}
