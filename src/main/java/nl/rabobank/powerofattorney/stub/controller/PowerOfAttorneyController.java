package nl.rabobank.powerofattorney.stub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.projections.PowerOfAttorneyProjection;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "power-of-attorneys")
@RequestMapping(path = "/power-of-attorneys", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerOfAttorneyController {

    @NonNull
    private PowerOfAttorneyService service;

    @NonNull
    private SpelAwareProxyProjectionFactory projectionFactory;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Find all grant ids",
            response = String.class,
            responseContainer = "Flux"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Grants found"),
    })
    public Mono<List<String>> getPowerOfAttorneyIds(){
        return service.findAllIds();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Find specific grant",
            response = PowerOfAttorneyProjection.class,
            responseContainer = "Mono"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Grants found"),
    })
    public Mono<PowerOfAttorneyProjection> findById(@PathVariable String id){
        return service.findByExternalId(id)
                .map(powerOfAttorney -> projectionFactory.createProjection(PowerOfAttorneyProjection.class, powerOfAttorney));
    }

}
