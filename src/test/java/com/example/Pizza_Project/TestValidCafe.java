package com.example.Pizza_Project;

import com.example.Pizza_Project.controller.CafeController;
import com.example.Pizza_Project.controller.PizzaController;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc


public class TestValidCafe {


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
    public void createNewCafeTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Cafe was registered"));
    }

    @Test
    public void noNameIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
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
    public void noCityIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.city", Is.is("City is mandatory!"))
                )
                .andDo(print());
    }
    @Test
    public void noAddressIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.address", Is.is("Address can't be empty!"))
                )
                .andDo(print());
    }
    @Test
    public void noEmailIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\",\"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", Is.is("Email is mandatory!"))
                )
                .andDo(print());
    }
    @Test
    public void EmailBlankIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\",\"email\": \"Pinoccio3\",\"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", Is.is("Email address is not valid"))
                )
                .andDo(print());
    }
    @Test
    public void noPhoneNumberIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\",\"open_at\": \"12:00\",\"close_at\" : \"21:00\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.phone", Is.is("Phone is mandatory!"))
                )
                .andDo(print());
    }
    @Test
    public void noOpenTimeIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\",\"close_at\" : \"21:00\",\"phone\": \"017523566435\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.open_at", Is.is("this field can't be empty!"))
                )
                .andDo(print());
    }
    @Test
    public void noCloseTimeIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\",\"open_at\": \"12:00\",\"phone\": \"017523566435\"}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.close_at", Is.is("this field can't be empty!"))
                )
                .andDo(print());
    }

    @Test
    public void blankCafeIsError() throws Exception {
        mockMvc.perform(
                        post("/cafe").with(user("admin").password("password").roles("ADMIN")).with(csrf())
                                .content("{}")
                                .contentType("application/json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.name", Is.is("Name is mandatory!"))
                )
                .andExpect(jsonPath("$.city", Is.is("City is mandatory!"))
                )
                .andExpect(jsonPath("$.address", Is.is("Address can't be empty!"))
                )
                .andExpect(jsonPath("$.email", Is.is("Email is mandatory!"))
                )
                .andExpect(jsonPath("$.phone", Is.is("Phone is mandatory!"))
                )
                .andExpect(jsonPath("$.open_at", Is.is("this field can't be empty!"))
                )
                .andExpect(jsonPath("$.close_at", Is.is("this field can't be empty!"))
                )
                .andDo(print());
    }
}
