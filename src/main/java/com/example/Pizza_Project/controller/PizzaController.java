package com.example.Pizza_Project.controller;

import com.example.Pizza_Project.entity.Pizza;
import com.example.Pizza_Project.repository.CafeRepository;
import com.example.Pizza_Project.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    CafeRepository cafeRepository;

    @GetMapping("/pizzas/cafe_id={id}")
    public ResponseEntity<List<Pizza>> getPizzasByCafeId(@PathVariable Long id){
        return ResponseEntity.ok(cafeRepository.findById(id).get().getPizza_menu());
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

    @PutMapping("/pizza/{id}")
    public ResponseEntity<String> changePizza(
            @RequestBody Pizza pizza,
            @PathVariable Long id){
        pizza.setId(id);
        pizzaRepository.save(pizza);
        return ResponseEntity.ok("pizza was changed");
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<String> deletePizza(
            @RequestBody Pizza pizza,
            @PathVariable Long id
    ){
        pizzaRepository.deleteById(id);
        return ResponseEntity.ok("pizza was deleted");
    }

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> getAllPizzas(){
        return ResponseEntity.ok(pizzaRepository.findAll());
    }

    @GetMapping("/pizzas/name={pizza_name}")
    public ResponseEntity<List<Pizza>> findPizzaByNameContaining(
            @PathVariable(name = "pizza_name") String name)
    {
        return ResponseEntity.ok(pizzaRepository.findByNameContaining(name));
    }







}
