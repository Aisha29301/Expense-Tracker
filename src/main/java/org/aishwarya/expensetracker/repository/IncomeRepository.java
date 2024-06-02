package org.aishwarya.expensetracker.repository;

import org.aishwarya.expensetracker.entity.Income;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends MongoRepository<Income,String> {
    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Aggregation({ "{ '$group': { '_id': null, 'totalAmount': { '$sum': '$amount' } } }" })
    Double sumAllAmounts();

    Optional<Income> findFirstByOrderByDateDesc();
}
