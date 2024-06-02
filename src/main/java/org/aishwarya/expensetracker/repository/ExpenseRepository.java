package org.aishwarya.expensetracker.repository;

import org.aishwarya.expensetracker.entity.Expense;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Aggregation({ "{ '$group': { '_id': null, 'totalAmount': { '$sum': '$amount' } } }" })
    Double sumAllAmounts();

    Optional<Expense> findFirstByOrderByDateDesc();

}
