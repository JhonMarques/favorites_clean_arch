package com.br.api.favorites.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
public class CucumberSpringConfiguration {
}
