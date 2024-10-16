import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public GeneticAlgorithm geneticAlgorithm() {
        // Initialize the population with some random strategies
        List<Chromosome> initialPopulation = Arrays.asList(
                new Chromosome("round-robin", "least-connection", "ttl"),
                new Chromosome("random", "weight-based", "none"),
                new Chromosome("sticky", "random", "size-based")
        );
        return new GeneticAlgorithm(initialPopulation);
    }

    @Bean
    public ApiGatewayService apiGatewayService(GeneticAlgorithm geneticAlgorithm, RouteLocatorBuilder builder) {
        return new ApiGatewayService(geneticAlgorithm, builder);
    }
}
