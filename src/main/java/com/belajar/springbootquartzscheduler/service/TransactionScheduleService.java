package com.belajar.springbootquartzscheduler.service;

import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleRequest;
import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleResponse;
import org.quartz.SchedulerException;

public interface TransactionScheduleService {

    CreateTransactionScheduleResponse create(CreateTransactionScheduleRequest request) throws SchedulerException;

}
