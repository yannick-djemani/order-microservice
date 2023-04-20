package com.codeimmig.OrderService.external.client;


import com.codeimmig.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    default  ResponseEntity<Void> fallback(Exception e ){
        throw new CustomException("Product Service is unavailable ", "UNAVAILABLE", 500);
    }
}
