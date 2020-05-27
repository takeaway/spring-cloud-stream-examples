package com.takeaway.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieFilterApplicationIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
        // Test whether Spring is able to start up without issues
    }

    @Test
    void healthTest() {
        webTestClient.get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("status")
                .isEqualTo("UP");
    }
}
