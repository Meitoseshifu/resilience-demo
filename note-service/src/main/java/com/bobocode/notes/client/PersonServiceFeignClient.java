package com.bobocode.notes.client;

import com.bobocode.notes.dto.PersonDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@FeignClient(name="persons")
public interface PersonServiceFeignClient {

    /**
     *  Real world approach with annotations
     *  Resilience4j also implements Bulkhead, Retry, TimeLimiter
     */
    @GetMapping("/persons/{id}")
    @CircuitBreaker(name = "persons", fallbackMethod = "defaultPerson")
    //@Bulkhead(name="todoBulk")
    //@Retry(name="todoRetry")
    //@TimeLimiter(name="todoTL")
    PersonDto getById(@PathVariable Long id);

    default PersonDto defaultPerson(Long personId, Throwable throwable) {
        return new PersonDto(personId, "HIDDEN", "HIDDEN");
    }


}
