import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Slf4j
public class Resilience4JTest {
  RemoteService service;

  interface RemoteService {
    int process(int i);
  }

  @BeforeEach
  public void setUp() {
    service = Mockito.mock(RemoteService.class);
  }

  @Test
  void test_circuit_breaker() {
    CircuitBreakerConfig config =
        CircuitBreakerConfig.custom()
            .failureRateThreshold(20)
            .slidingWindowSize(5)
            .build();
    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);

    CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
    Function<Integer, Integer> decorated =
        CircuitBreaker.decorateFunction(circuitBreaker, service::process);

    Mockito.when(service.process(Mockito.any(Integer.class))).thenThrow(new RuntimeException());

    for (int i = 0; i < 10; i++) {
      try {
        decorated.apply(i);
      } catch (Exception ignore) {
        LOGGER.info("caught: ", ignore);
        System.out.println("caught: " + ignore);
      }
    }

    Mockito.verify(service, Mockito.times(5)).process(Mockito.any(Integer.class));
  }
}
