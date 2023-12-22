package com.belajar.springbootquartzscheduler.job;

import com.belajar.springbootquartzscheduler.constant.ExecutionStatus;
import com.belajar.springbootquartzscheduler.entity.TransactionSchedule;
import com.belajar.springbootquartzscheduler.exception.ErrorException;
import com.belajar.springbootquartzscheduler.repository.TransactionScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TransactionScheduleJob implements Job {

    private final TransactionScheduleRepository transactionScheduleRepository;

    @Override
    @Transactional
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long id = jobExecutionContext.getJobDetail().getJobDataMap().getLong("id");

        TransactionSchedule transactionSchedule = transactionScheduleRepository.findById(id)
                .orElseThrow(() -> new ErrorException(HttpStatus.NOT_FOUND));
        transactionSchedule.setExecutionStatus(ExecutionStatus.SUCCESSED);

        transactionScheduleRepository.save(transactionSchedule);
    }

}
