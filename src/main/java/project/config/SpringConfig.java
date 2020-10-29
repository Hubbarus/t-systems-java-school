package project.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import project.utils.CartListWrapper;
import project.utils.PagingUtil;

import java.util.Collection;
import java.util.HashSet;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@ComponentScan("project")
public class SpringConfig {

    @Bean
    public ActiveMQConnectionFactory getMQConnectionFactory() {
        return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);
    }

    @Bean
    public PagingUtil getPagingUtil() {
        return new PagingUtil();
    }

    @Bean
    public CartListWrapper getCartListWrapper() {
        return new CartListWrapper();
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setPropertyCondition(skipCollections());
        return mapper;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    private Condition skipCollections() {
        return context -> !(context.getMapping().getSourceType().equals(Collection.class))
                || !(context.getMapping().getSourceType().equals(HashSet.class));
    }
}
