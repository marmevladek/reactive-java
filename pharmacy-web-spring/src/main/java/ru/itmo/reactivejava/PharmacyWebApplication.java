package ru.itmo.reactivejava;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;
import ru.itmo.reactivejava.generator.OrderGenerator;

@EnableWebFlux
@SpringBootApplication
public class PharmacyWebApplication implements CommandLineRunner {

    private final OrderGenerator orderGenerator;

    public PharmacyWebApplication(OrderGenerator orderGenerator) {
        this.orderGenerator = orderGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(PharmacyWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderGenerator.startGeneratingOrders();
    }
}
