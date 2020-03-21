package nl.rabobank.powerofattorney.stub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
public class BeansConfiguration {

    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory(){
        return new SpelAwareProxyProjectionFactory();
    }
}
