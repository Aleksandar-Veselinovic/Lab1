package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccessingDataTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private AddressBookController bookController;

    private TestRestTemplate template;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateAddressBook() {
        System.out.println(port);
        template = new TestRestTemplate();
        JSONObject obj = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(obj.toString(), headers);
        String url = "http://localhost:8080/addressBooks";
        String book = template.postForObject(url, request, String.class);

        System.out.println(book);
    }
}