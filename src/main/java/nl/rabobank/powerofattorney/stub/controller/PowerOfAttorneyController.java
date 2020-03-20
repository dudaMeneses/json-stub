package nl.rabobank.powerofattorney.stub.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.entity.PowerOfAttorney;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/power-of-attorneys", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerOfAttorneyController {

    @NonNull
    private PowerOfAttorneyService service;

    @GetMapping
    public Flux<Long> getPowerOfAttorneyIds(){
        return service.findAllIds();
    }

    @GetMapping(path = "/{id}")
    public Mono<PowerOfAttorney> findById(@PathVariable Long id){
        return service.findById(id);
    }

}
