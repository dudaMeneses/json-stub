package nl.rabobank.powerofattorney.stub.service.powerOfAttorney;

import nl.rabobank.powerofattorney.stub.helper.PowerOfAttorneyHelper;
import nl.rabobank.powerofattorney.stub.repository.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.stub.service.PowerOfAttorneyService;
import nl.rabobank.powerofattorney.stub.service.exception.PowerOfAttorneyNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PowerOfAttorneyServiceFindByIdTest {

    @InjectMocks
    private PowerOfAttorneyService powerOfAttorneyService;

    @Mock
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Test
    public void whenHappyPath_shouldReturnPowerOfAttorneyDetails(){
        doReturn(Mono.just(PowerOfAttorneyHelper.create(0L))).when(powerOfAttorneyRepository).findById(anyLong());

        StepVerifier.create(powerOfAttorneyService.findById(0L))
                .assertNext(powerOfAttorney -> assertEquals(PowerOfAttorneyHelper.create(0L), powerOfAttorney))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenNoPowerOfAttorneys_shouldThrowPowerOfAttorneyNotFound(){
        doReturn(Mono.empty()).when(powerOfAttorneyRepository).findById(anyLong());

        StepVerifier.create(powerOfAttorneyService.findById(0L))
                .expectError(PowerOfAttorneyNotFound.class)
                .verify();
    }

}