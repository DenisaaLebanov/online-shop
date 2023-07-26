package ro.msg.learning.shop.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.OrderStrategyInterface;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class StrategyConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "single-location")
    public OrderStrategyInterface getSingleLocationStrategy(final ProductService productService){
        return new SingleLocationStrategy(productService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "most-abundant")
    public OrderStrategyInterface getMostAbundantStrategy(final ProductService productService){
        return new MostAbundantStrategy(productService);
    }
}