import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {
    
    @GetMapping("/performance")
    public PerformanceMetrics getPerformanceMetrics() {
        // Example: Return random metrics. In production, gather from Prometheus, etc.
        double throughput = Math.random() * 1000; // Random throughput
        double responseTime = Math.random() * 500; // Random response time (ms)
        return new PerformanceMetrics(throughput, responseTime);
    }
}
