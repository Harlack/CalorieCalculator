package org.example.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty(message = "Name of the product should not be empty")
    private String name;
    @Min(value = 0, message = "Calories should not be less than 0")
    private Integer calories;
    @Min(value = 0, message = "Total fat should not be less than 0")
    private Integer totalFat;
    @Min(value = 0, message = "Cholesterol should not be less than 0")
    private Integer cholesterol;
    @Min(value = 0, message = "Sodium should not be less than 0")
    private Integer sodium;
    @Min(value = 0, message = "Total carbohydrates should not be less than 0")
    private Integer totalCarbohydrates;
    @Min(value = 0, message = "Protein should not be less than 0")
    private Integer protein;

}
