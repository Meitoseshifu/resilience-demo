package com.bobocode.notes.client;

import com.bobocode.notes.dto.PersonDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonServiceClient {
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    //private final CircuitBreakerFactory circuitBreakerFactory;

    /**
     *  No circuit breaker
     */
    /*public PersonDto getById(Long personId) { // todo not applicable in real project, created just to give an idea how it works under the hood
        List<ServiceInstance> instances = discoveryClient.getInstances("persons");
        ServiceInstance personServiceInstance = instances.get(0);
        String personByIdUrl = personServiceInstance.getUri() + String.format("/persons/{%s}", personId);
        return restTemplate.getForObject(personByIdUrl, PersonDto.class, personId);
    }*/

    /**
     * With circuit breaker
     */
    /*public PersonDto getById(Long personId) { // todo not applicable in real project, created just to give an idea how it works under the hood
        List<ServiceInstance> instances = discoveryClient.getInstances("persons");
        ServiceInstance personServiceInstance = instances.get(0);
        String personByIdUrl = personServiceInstance.getUri() + String.format("/persons/{%s}", personId);
        return circuitBreakerFactory.create("persons").run(
                () -> restTemplate.getForObject(personByIdUrl, PersonDto.class, personId)
        );
    }*/

    /**
     *  With circuit breaker and fallback
     */
    /*public PersonDto getById(Long personId) { // todo not applicable in real project, created just to give an idea how it works under the hood
        List<ServiceInstance> instances = discoveryClient.getInstances("persons");
        ServiceInstance personServiceInstance = instances.get(0);
        String personByIdUrl = personServiceInstance.getUri() + String.format("/persons/{%s}", personId);
        return circuitBreakerFactory.create("persons").run(
                () -> restTemplate.getForObject(personByIdUrl, PersonDto.class, personId), //circuit breaker
                throwable -> new PersonDto(personId, "HIDDEN", "HIDDEN")  //fallback
        );
    }*/

    /**
     *  Approach with annotations
     */
    @CircuitBreaker(name = "persons", fallbackMethod = "defaultPerson")
    public PersonDto getById(Long personId) { // todo not applicable in real project, created just to give an idea how it works under the hood
        List<ServiceInstance> instances = discoveryClient.getInstances("persons");
        ServiceInstance personServiceInstance = instances.get(0);
        String personByIdUrl = personServiceInstance.getUri() + String.format("/persons/{%s}", personId);
        return restTemplate.getForObject(personByIdUrl, PersonDto.class, personId);
    }

    private PersonDto defaultPerson(Long personId, Throwable throwable) {
        return new PersonDto(personId, "HIDDEN", "HIDDEN");
    }


}
