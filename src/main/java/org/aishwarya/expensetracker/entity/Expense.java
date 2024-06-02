package org.aishwarya.expensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expense")
public class Expense {

    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;

}
