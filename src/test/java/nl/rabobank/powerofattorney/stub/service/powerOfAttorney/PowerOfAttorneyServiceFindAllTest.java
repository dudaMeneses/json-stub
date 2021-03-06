package nl.rabobank.powerofattorney.stub.service.powerOfAttorney;

import nl.rabobank.powerofattorney.stub.helper.PowerOfAttorneyHelper;
import nl.rabobank.powerofattorney.stub.repository.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PowerOfAttorneyServiceFindAllTest {

    @InjectMocks
    private PowerOfAttorneyService powerOfAttorneyService;

    @Mock
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Test
    public void whenHappyPath_shouldReturnIds(){
        doReturn(Flux.just(PowerOfAttorneyHelper.create("0L"), PowerOfAttorneyHelper.create("1L"))).when(powerOfAttorneyRepository).findAll();

        StepVerifier.create(powerOfAttorneyService.findAllIds())
                .expectNext(List.of("0L", "1L"))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenNoPowerOfAttorneys_shouldReturnEmptyList(){
        doReturn(Flux.empty()).when(powerOfAttorneyRepository).findAll();

        StepVerifier.create(powerOfAttorneyService.findAllIds())
                .expectNext(List.of())
                .expectComplete()
                .verify();
    }

}