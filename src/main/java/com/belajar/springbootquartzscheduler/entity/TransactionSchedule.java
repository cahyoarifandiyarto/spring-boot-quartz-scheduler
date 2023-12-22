package com.belajar.springbootquartzscheduler.entity;

import com.belajar.springbootquartzscheduler.constant.ExecutionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_schedules")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_schedules_id_seq")
    @SequenceGenerator(name = "transaction_schedules_id_seq", sequenceName = "transaction_schedules_id_seq", allocationSize = 1)
    private Long id;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal total;

    private Long executionAt;

    @Enumerated(EnumType.STRING)
    private ExecutionStatus executionStatus;

}
