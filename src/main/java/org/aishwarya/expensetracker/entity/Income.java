package org.aishwarya.expensetracker.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "income")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Income {
        @Id
        private String id;
        private String title;
        private String description;
        private String category;
        private LocalDate date;
        private Integer amount;
}
