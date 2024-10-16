public class Chromosome {
    private String routingStrategy; // e.g., round-robin, random
    private String loadBalancingStrategy; // e.g., least-connection, weight-based
    private String cachingPolicy; // e.g., TTL-based, size-based

    public Chromosome(String routingStrategy, String loadBalancingStrategy, String cachingPolicy) {
        this.routingStrategy = routingStrategy;
        this.loadBalancingStrategy = loadBalancingStrategy;
        this.cachingPolicy = cachingPolicy;
    }

    public String getRoutingStrategy() {
        return routingStrategy;
    }

    public String getLoadBalancingStrategy() {
        return loadBalancingStrategy;
    }

    public String getCachingPolicy() {
        return cachingPolicy;
    }
}
