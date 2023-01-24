package com.example.Pizza_Project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cafe")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Cafe(String name, String city, String address, String email, String phone, String open_at, String close_at, List<Pizza> pizza_menu) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.open_at = open_at;
        this.close_at = close_at;
        this.pizza_menu = pizza_menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cafe cafe = (Cafe) o;
        return id != null && Objects.equals(id, cafe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", open_at='" + open_at + '\'' +
                ", close_at='" + close_at + '\'' +
                '}';
    }
}
