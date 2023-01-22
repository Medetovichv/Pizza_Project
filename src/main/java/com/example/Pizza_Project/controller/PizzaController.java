package com.example.Pizza_Project.controller;

import com.example.Pizza_Project.entity.Pizza;
import com.example.Pizza_Project.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/pizzas/cafe_id={id}")
    public ResponseEntity<Iterable<Pizza>> getPizzasByCafeId(@PathVariable Long id){
        return ResponseEntity.ok(pizzaRepository.findByCafeId(id));
    }

    @PostMapping("/pizza")
    public ResponseEntity<String> addPizza(@RequestBody Pizza pizza){
        pizzaRepository.save(pizza);
        return ResponseEntity.ok("New pizza was added");
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<Pizza> getById(@PathVariable Long id){
        return ResponseEntity.ok(pizzaRepository.findById(id).get());
    }

    @PutMapping("pizza/{id}")
    public ResponseEntity<String> changePizza(
            @RequestBody Pizza pizza,
            @PathVariable Long id){
        pizza.setId(id);
        pizzaRepository.save(pizza);
        return ResponseEntity.ok("pizza was changed");
    }






}
