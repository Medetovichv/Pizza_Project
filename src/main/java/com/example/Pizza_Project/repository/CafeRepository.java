package com.example.Pizza_Project.repository;

import com.example.Pizza_Project.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findByCityContaining(String city);
}
