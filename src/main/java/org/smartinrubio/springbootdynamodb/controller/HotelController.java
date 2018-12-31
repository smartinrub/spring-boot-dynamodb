package org.smartinrubio.springbootdynamodb.controller;

import org.smartinrubio.springbootdynamodb.exception.HotelNotFoundException;
import org.smartinrubio.springbootdynamodb.model.Hotel;
import org.smartinrubio.springbootdynamodb.repository.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/table")
    public ResponseEntity createTable() {
        repository.createTable();
        return ResponseEntity.ok("Table Created!");
    }

    @GetMapping("/data")
    public ResponseEntity loadData() throws IOException {
        repository.loadData();
        return ResponseEntity.ok("Data Loaded");
    }

    @GetMapping
    public List<Hotel> readAll() {
        return Stream.generate(() -> repository.findAll().iterator().next()).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return repository.save(hotel);
    }

    @GetMapping("/{hotelId}")
    public Hotel readHotelById(@PathVariable("hotelId") String id) {
        return repository.findById(id).orElseThrow(HotelNotFoundException::new);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        return repository.save(hotel);
    }

    @GetMapping
    public List<Hotel> readHotelByName(@RequestParam("hotelName") String name) {
        return repository.findAllByName(name);
    }

    @DeleteMapping("/{hotelId}")
    public void deleteHotel(@PathVariable("hotelId") String id) {
        repository.deleteById(id);
    }


}
