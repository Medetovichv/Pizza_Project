package com.example.Pizza_Project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void createCafeTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(
                "{\"name\":\"Pinoccio3\", \"city\" : \"Wabern\",\"address\" :\"Steinmühle 5\", \"email\": \"Pinoccio3@gmail.com\", \"phone\": \"017523566435\", \"open_at\": \"12:00\",\"close_at\" : \"21:00\"}",
                headers
        );

        String body =
                restTemplate.withBasicAuth("admin", "password").postForEntity(
                        "http://localhost:" + port + "/cafe",
                        request,
                        String.class
                ).getBody();


        assertEquals(body, "Cafe was registered");
    }

    @Test
    public void createPizzaTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(
                "{\"name\" : \"Margarita\", \"size\": \"XL\", \"ingredients\": \"Tomaten, Mozarello, Sauce\", \"price\" : 10}",
                headers
        );

        String body =
                restTemplate.withBasicAuth("admin", "password").postForEntity(
                        "http://localhost:" + port + "/pizza",
                        request,
                        String.class
                ).getBody();


        assertEquals(body, "New pizza was added");
    }

}
