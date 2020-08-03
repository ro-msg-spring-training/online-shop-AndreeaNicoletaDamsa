package ro.msg.learning.shop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundant;
import ro.msg.learning.shop.strategy.SingleLocation;

@Configuration
@RequiredArgsConstructor
public class StrategyConfiguration {


    private final ProductRepository productRepository;

    private final LocationRepository locationRepository;


    @Bean
    @ConditionalOnProperty(prefix = "strategy", name = "name", havingValue = "singleLocation")
    public LocationStrategy singleLocation() {
        return new SingleLocation(productRepository, locationRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "strategy", name = "name", havingValue = "mostAbundant")
    public LocationStrategy mostAbundant() {
        return new MostAbundant(locationRepository, productRepository);
    }
}
