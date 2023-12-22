package com.belajar.springbootquartzscheduler.repository;

import com.belajar.springbootquartzscheduler.entity.TransactionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionScheduleRepository extends JpaRepository<TransactionSchedule, Long> {
}
