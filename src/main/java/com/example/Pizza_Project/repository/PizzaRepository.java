package com.example.Pizza_Project.repository;

import com.example.Pizza_Project.entity.Cafe;
import com.example.Pizza_Project.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByCafe(Long cafeId);

}
