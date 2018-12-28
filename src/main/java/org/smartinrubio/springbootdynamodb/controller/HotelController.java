package org.smartinrubio.springbootdynamodb.controller;

import org.smartinrubio.springbootdynamodb.repository.HotelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/table")
    public ResponseEntity createHotelTable() {
        repository.createTable();
        return ResponseEntity.ok("Table Created!");
    }

}
