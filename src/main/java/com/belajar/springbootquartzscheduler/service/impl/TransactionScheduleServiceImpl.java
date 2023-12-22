package com.belajar.springbootquartzscheduler.service.impl;

import com.belajar.springbootquartzscheduler.constant.ExecutionStatus;
import com.belajar.springbootquartzscheduler.entity.TransactionSchedule;
import com.belajar.springbootquartzscheduler.job.TransactionScheduleJob;
import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleRequest;
import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleResponse;
import com.belajar.springbootquartzscheduler.repository.TransactionScheduleRepository;
import com.belajar.springbootquartzscheduler.service.TransactionScheduleService;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionScheduleServiceImpl implements TransactionScheduleService {

    private final TransactionScheduleRepository transactionScheduleRepository;

    private final Scheduler scheduler;

    @Override
    @Transactional
    public CreateTransactionScheduleResponse create(CreateTransactionScheduleRequest request) throws SchedulerException {
        TransactionSchedule transactionSchedule = TransactionSchedule.builder()
                .amount(request.getAmount())
                .fee(request.getFee())
                .total(request.getTotal())
                .executionAt(request.getExecutionAt())
                .executionStatus(ExecutionStatus.PENDING)
                .build();

        transactionSchedule = transactionScheduleRepository.save(transactionSchedule);

        JobDetail jobDetail = newJob(TransactionScheduleJob.class)
                .withIdentity("transaction-schedule-job-" + transactionSchedule.getId())
                .usingJobData("id", transactionSchedule.getId())
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger-transaction-schedule-job-" + transactionSchedule.getId())
                .startAt(new Date(transactionSchedule.getExecutionAt()))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        return CreateTransactionScheduleResponse.builder()
                .id(transactionSchedule.getId())
                .amount(transactionSchedule.getAmount())
                .fee(transactionSchedule.getFee())
                .total(transactionSchedule.getTotal())
                .executionAt(transactionSchedule.getExecutionAt())
                .executionStatus(transactionSchedule.getExecutionStatus())
                .build();
    }

}
