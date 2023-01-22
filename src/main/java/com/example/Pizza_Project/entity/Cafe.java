package com.example.Pizza_Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String address;

    private String email;

    private String phone;
    private String open_at;

    private String close_at;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cafe")
    private List<Pizza> pizza_menu;


}
