package com.example.Pizza_Project.controller;

import com.example.Pizza_Project.entity.Cafe;
import com.example.Pizza_Project.repository.CafeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CafeController {

    @Autowired
    CafeRepository cafeRepository;

    @GetMapping("/cafes")
    public ResponseEntity<Iterable<Cafe>> getAllCafes() {
        return ResponseEntity.ok(cafeRepository.findAll());
    }

    @PostMapping("/cafe")
    public ResponseEntity<String> addCafe(
            @RequestBody Cafe cafe
    ){
        cafeRepository.save(cafe);
        return ResponseEntity.ok("Cafe was registered");
    }

    @PutMapping("/cafe/{id}")
    public ResponseEntity<String> updateCafe(
            @RequestBody Cafe cafe,
            @PathVariable Long id
    ){
        cafe.setId(id);
        cafeRepository.save(cafe);
        return ResponseEntity.ok("Cafe was changed");
    }

    @DeleteMapping("/cafe/{id}")
    public ResponseEntity<String> deleteCafe(
            @RequestBody Cafe cafe,
            @PathVariable Long id
    ){
        cafeRepository.delete(cafeRepository.findById(id).get());

        return ResponseEntity.ok("Cafe was deleted");
    }

    @GetMapping("/cafe/full/{id}")
    public ResponseEntity<Cafe> getFullCafeInfo(@PathVariable Long id){
        return ResponseEntity.ok(cafeRepository.findById(id).get());
    }

    @GetMapping("/cafes/address={city}")
    public ResponseEntity<List<Cafe>> findingCafeByCity(@PathVariable String city){

        return ResponseEntity.ok(cafeRepository.findByCityContaining(city));
    }







}
