package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccessingDataTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private AddressBookController bookController;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testCreateAddressBook() {
        JSONObject obj = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(obj.toString(), headers);
        String url = "http://localhost:" + port + "/addressBooks";
        String response = template.postForObject(url, request, String.class);

        assert(response.contains("addressBooks/1"));

        url = "http://localhost:" + port + "/buddies?id=1&name=Bob&number=567";
        response = template.postForObject(url, request, String.class);

        assert(response.contains("Bob"));
        assert(response.contains("567"));

        url = "http://localhost:" + port + "/buddyInfoes";
        response = template.getForObject(url, String.class);
        assert(response.contains("Bob"));
        assert(response.contains("567"));

        url = "http://localhost:" + port + "/addressBooks/1/buddies/1";
        template.delete(url);

        url = "http://localhost:" + port + "/addressBooks/1/buddies";
        response = template.getForObject(url, String.class);
        assert(!response.contains("Bob"));
        assert(!response.contains("567"));
    }
}