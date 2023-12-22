package com.belajar.springbootquartzscheduler.model;

import com.belajar.springbootquartzscheduler.constant.ExecutionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateTransactionScheduleResponse {

    private Long id;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal total;

    private Long executionAt;

    private ExecutionStatus executionStatus;

}
