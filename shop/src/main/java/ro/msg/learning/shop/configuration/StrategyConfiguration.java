package ro.msg.learning.shop.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundant;
import ro.msg.learning.shop.strategy.SingleLocation;

@Configuration
public class StrategyConfiguration {

    @Bean
    @ConditionalOnProperty(prefix="strategy", name="name", havingValue="singleLocation")
    public LocationStrategy singleLocation(){
        return new SingleLocation();
    }

    @Bean
    @ConditionalOnProperty(prefix="strategy",name="name", havingValue="mostAbundant")
    public LocationStrategy mostAbundant(){
        return new MostAbundant();
    }
}
