package com.metroassesment.demo.controller;

import com.metroassesment.demo.dto.AccountRequest;
import com.metroassesment.demo.dto.CustomerResponse;
import com.metroassesment.demo.dto.ErrorResponse;
import com.metroassesment.demo.dto.SuccessResponse;
import com.metroassesment.demo.service.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccountRequest request) {

        if (request.getCustomerEmail() == null || request.getCustomerEmail().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Email is required field"));
        }

        Long id = service.createAndReturnId(request);

        SuccessResponse response = new SuccessResponse(
                id,
                201,
                "Customer account created");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {

        CustomerResponse response = service.getCustomer(id);

        if (response == null) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404, "Customer not found"));
        }

        return ResponseEntity.ok(response);
    }

}
