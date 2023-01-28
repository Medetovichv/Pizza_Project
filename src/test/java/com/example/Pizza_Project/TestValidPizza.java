package com.example.Pizza_Project;

import com.example.Pizza_Project.controller.CafeController;
import com.example.Pizza_Project.controller.PizzaController;
import com.example.Pizza_Project.entity.Cafe;
import com.example.Pizza_Project.repository.CafeRepository;
import com.example.Pizza_Project.repository.PizzaRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WithMockUser(roles = "ADMIN")

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TestValidPizza {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PizzaController pizzaController;
    @Autowired
    CafeController cafeController;

    @MockBean
    PizzaRepository pizzaRepository;

    @MockBean
    CafeRepository cafeRepository;


    @Test
    public void createNewPizzaTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.
                        post("/pizza").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                        .content("{\"name\" : \"Margarita2\", \"size\": \"XXL\", \"ingredients\": \"Tomaten, Mozarello, Sauce, HartCheese\", \"price\" : 11}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("New pizza was added"));
    }

    @Test
    public void noNameIsError() throws Exception {
        mockMvc.perform(
                        post("/pizza").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"size\": \"XXL\",\"ingredients\": \"Tomaten, Mozarello, Sauce, HartCheese\", \"price\" : 11}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.name", Is.is("Name is mandatory!"))
                )
                .andDo(print())
        ;
    }

    @Test
    public void noIngredientsIsError() throws Exception {
        mockMvc.perform(
                        post("/pizza").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\" : \"Margarita2\", \"size\": \"XXL\",\"price\" : 11}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ingredients", Is.is("Write here ingredients!"))
                )
                .andDo(print());
    }

    @Test
    public void blankUserIsError() throws Exception {
        mockMvc.perform(
                        post("/pizza").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"size\": \"XXL\",\"price\" : 11}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.name", Is.is("Name is mandatory!"))
                )
                .andExpect(jsonPath("$.ingredients", Is.is("Write here ingredients!"))
                )
                .andDo(print());
    }
}
