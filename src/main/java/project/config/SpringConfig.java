package project.config;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashMap;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@ComponentScan("project")
public class SpringConfig {

    private final ApplicationContext context;

    @Autowired
    public SpringConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        Condition skipCollections = new Condition() {
            @Override
            public boolean applies(MappingContext context) {
                return !(context.getMapping().getSourceType().equals(Collection.class))
                        || !(context.getMapping().getSourceType().equals(HashMap.class));
            }
        };
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                //.setPropertyCondition(Conditions.not(Conditions.isType(Map.class), Conditions.isType(Collection.class)));
                .setPropertyCondition(skipCollections);
        return mapper;
    }
}
