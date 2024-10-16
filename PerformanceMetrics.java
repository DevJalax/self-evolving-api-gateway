public class PerformanceMetrics {
    private double responseTime;
    private double throughput;

    public PerformanceMetrics(double throughput, double responseTime) {
        this.throughput = throughput;
        this.responseTime = responseTime;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public double getThroughput() {
        return throughput;
    }
}

public class FitnessEvaluator {
    public double evaluateFitness(Chromosome chromosome, PerformanceMetrics metrics) {
        // Example fitness calculation: maximize throughput and minimize response time
        double fitness = (metrics.getThroughput() / metrics.getResponseTime()) * 1000;
        return fitness;
    }
}
