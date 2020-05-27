package com.takeaway.movies;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieFilterApplicationIT {

    private static final String INCOMING_MESSAGE = "{\"name\":\"The Dark Knight\",\"rating\":10}";
    private static final String OUTGOING_MESSAGE = "{\"name\":\"The Dark Knight\",\"rating\":10,\"description\":\"A movie about Batman!\"}";


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


    //Example partial integration test - could be parameterized to get full coverage
    @Test
    public void goodMovieShouldNotBeFilteredAndGetCorrectDescription() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration.getCompleteConfiguration(
                        MovieFilterApplication.class))
                .run("--spring.cloud.function.definition=logIncoming|describeMovie|filter|logOutgoing")) {

            InputDestination source = context.getBean(InputDestination.class);
            OutputDestination target = context.getBean(OutputDestination.class);

            source.send(new GenericMessage<>(INCOMING_MESSAGE));
            assertThat(new String(target.receive().getPayload())).isEqualTo(OUTGOING_MESSAGE);
        }
    }

}
