package com.belajar.springbootquartzscheduler.controller;

import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleRequest;
import com.belajar.springbootquartzscheduler.model.CreateTransactionScheduleResponse;
import com.belajar.springbootquartzscheduler.model.Response;
import com.belajar.springbootquartzscheduler.service.TransactionScheduleService;
import com.belajar.springbootquartzscheduler.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-schedules")
@RequiredArgsConstructor
public class TransactionScheduleController {

    private final TransactionScheduleService transactionScheduleService;

    @PostMapping
    public ResponseEntity<Response<CreateTransactionScheduleResponse>> create(@RequestBody @Valid CreateTransactionScheduleRequest request) throws SchedulerException {
        CreateTransactionScheduleResponse createTransactionScheduleResponse = transactionScheduleService.create(request);

        Response<CreateTransactionScheduleResponse> response = ResponseUtil.build(createTransactionScheduleResponse, null, null, HttpStatus.CREATED);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
