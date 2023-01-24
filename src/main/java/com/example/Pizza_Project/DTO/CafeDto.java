package com.example.Pizza_Project.DTO;

import com.example.Pizza_Project.entity.Cafe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CafeDto {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String email;
    private String phone;
    private String open_at;
    private String close_at;


    public Cafe toCafe(){
        Cafe cafe = new Cafe();
        cafe.setId(id);
        cafe.setCity(city);
        cafe.setName(name);
        cafe.setAddress(address);
        cafe.setEmail(email);
        cafe.setPhone(phone);
        cafe.setOpen_at(open_at);
        cafe.setClose_at(close_at);
        return cafe;
    }

    public static CafeDto fromCafe(Cafe cafe){
        CafeDto cafeDto = new CafeDto();
        cafeDto.setAddress(cafe.getAddress());
        cafeDto.setCity(cafe.getCity());
        cafeDto.setName(cafe.getName());
        cafeDto.setId(cafe.getId());
        cafeDto.setEmail(cafe.getEmail());
        cafeDto.setPhone(cafe.getPhone());
        cafeDto.setOpen_at(cafe.getOpen_at());
        cafeDto.setClose_at(cafe.getOpen_at());

        return cafeDto;
    }

}
