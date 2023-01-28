package com.example.Pizza_Project;

import com.example.Pizza_Project.controller.CafeController;
import com.example.Pizza_Project.controller.PizzaController;
import com.example.Pizza_Project.repository.CafeRepository;
import com.example.Pizza_Project.repository.PizzaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles="ADMIN")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    CafeController cafeController;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PizzaController pizzaController;


    @Test
    public void addTwoCafes() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/cafe")
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/cafe")
                                .content("{\"name\":\"Pinoccio1\", \"city\" : \"Köln\", \"cafe\" : 2 ,\"address\" :\"Steinmühle 1\", \"email\": \"Pinoccio1@gmail.com\", \"phone\": \"017523560005\", \"open_at\": \"1ß:00\",\"close_at\" : \"22:00\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void addTwoPizzas() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/pizza")
                                .content("{\"name\" : \"Margarita2\", \"size\": \"XXL\", \"cafe\" : 1 ,\"ingredients\": \"Tomaten, Mozarello, Sauce, HartCheese\", \"price\" : 11}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/pizza")
                                .content("{\"name\" : \"Margarita\", \"size\": \"XL\", \"ingredients\": \"Tomaten, Mozarello, Sauce\", \"price\" : 10}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}
