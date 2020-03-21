package nl.rabobank.powerofattorney.stub.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.stub.model.projections.AccountProjection;
import nl.rabobank.powerofattorney.stub.service.AccountService;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "accounts")
@RequiredArgsConstructor
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @NonNull
    private AccountService service;

    @NonNull
    private SpelAwareProxyProjectionFactory projectionFactory;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Find account by its specific id",
            response = AccountProjection.class,
            responseContainer = "Mono"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account found"),
            @ApiResponse(code = 404, message = "Account not found"),
            @ApiResponse(code = 400, message = "Account is closed")
    })
    public Mono<AccountProjection> findById(@PathVariable String id){
        return service.findByExternalId(id)
                .map(account -> projectionFactory.createProjection(AccountProjection.class, account));
    }

}
