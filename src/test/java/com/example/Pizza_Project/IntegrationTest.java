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

        // тело запроса + заголовки + url
        HttpEntity<String> request = new HttpEntity<>(
                "{\"address\" :\"Steinmühle 5\", \"city\" : \"Wabern\", \"close_at\" : \"21:00\", \"name\":\"Pinoccio3\",\"open_at\": \"12:00\",\"phone\": \"017523566435\",\"email\": \"Pinoccio3@gmail.com\"}",

                headers
        );

        String body =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/cafe",
                        request,
                        String.class
                ).getBody();


        assertEquals(body, "cafe was registered");
    }

}
