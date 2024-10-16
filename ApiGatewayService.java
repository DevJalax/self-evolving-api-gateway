import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayService {
    
    private final GeneticAlgorithm geneticAlgorithm;
    private final RouteLocatorBuilder builder;
    private final FitnessEvaluator fitnessEvaluator = new FitnessEvaluator();
    
    public ApiGatewayService(GeneticAlgorithm geneticAlgorithm, RouteLocatorBuilder builder) {
        this.geneticAlgorithm = geneticAlgorithm;
        this.builder = builder;
    }

    @Bean
    public RouteLocator dynamicRoutes(RouteLocatorBuilder builder) {
        // Create routes dynamically based on the evolved chromosome
        Chromosome evolvedStrategy = geneticAlgorithm.getPopulation().get(0); // Best chromosome

        return builder.routes()
                .route("service_route", r -> r.path("/service1/**")
                        .uri("http://localhost:8081")
                        .filters(f -> f.addRequestHeader("Host", evolvedStrategy.getRoutingStrategy()))
                )
                .build();
    }

    @Scheduled(fixedRate = 60000) // Run every 1 minute
    public void updateRoutes() {
        // Fetch latest performance metrics
        PerformanceMetrics metrics = new PerformanceMetrics(Math.random() * 1000, Math.random() * 500);
        
        // Evolve strategies based on performance
        geneticAlgorithm.evolve(metrics);

        // Dynamically update routes based on the evolved strategy
        dynamicRoutes(builder);
    }
}
