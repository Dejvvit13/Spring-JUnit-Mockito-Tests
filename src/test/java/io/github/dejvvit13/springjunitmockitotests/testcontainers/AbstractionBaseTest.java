package io.github.dejvvit13.springjunitmockitotests.testcontainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractionBaseTest {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER;


    static {
        MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest");
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void setPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }
}
