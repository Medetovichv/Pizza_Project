package com.example.Pizza_Project.controller;

import com.example.Pizza_Project.DTO.CafeDto;
import com.example.Pizza_Project.entity.Cafe;
import com.example.Pizza_Project.repository.CafeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CafeController {

    @Autowired
    CafeRepository cafeRepository;

    @GetMapping("/cafes")
    public ResponseEntity<Iterable<CafeDto>> getAllCafes() {
        List<Cafe> list= cafeRepository.findAll();
        List<CafeDto> resList = new ArrayList<>();

        for (Cafe cafe : list) {
            CafeDto result = CafeDto.fromCafe(cafe);
            resList.add(result);
        }
        return ResponseEntity.ok(resList);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error ->
                        errors.put(
                                ((FieldError) error).getField(),
                                error.getDefaultMessage())
        );
        return errors;
    }
    @PostMapping("/cafe")
    public ResponseEntity<String> addCafe(
            @Valid @RequestBody Cafe cafe
    ){
        cafeRepository.save(cafe);
        return ResponseEntity.ok("Cafe was registered");
    }

    @PutMapping("/cafe/{id}")
    public ResponseEntity<String> updateCafe(
           @Valid  @RequestBody Cafe cafe,
            @PathVariable Long id
    ){
        cafe.setId(id);
        cafeRepository.save(cafe);
        return ResponseEntity.ok("Cafe was changed");
    }

    @DeleteMapping("/cafe/{id}")
    public ResponseEntity<String> deleteCafe(
            @Valid @RequestBody Cafe cafe,
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
