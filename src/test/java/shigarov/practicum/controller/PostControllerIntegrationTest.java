package shigarov.practicum.controller;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import shigarov.practicum.configuration.TestDataSourceConfiguration;
import shigarov.practicum.configuration.TestWebConfiguration;

@SpringJUnitConfig(classes = {TestDataSourceConfiguration.class, TestWebConfiguration.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:test-application.properties")
public class PostControllerIntegrationTest {
}
