package com.belajar.springbootquartzscheduler.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateTransactionScheduleRequest {

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    @PositiveOrZero
    private BigDecimal fee;

    @NotNull
    @PositiveOrZero
    private BigDecimal total;

    @NotNull
    @Positive
    private Long executionAt;

}
