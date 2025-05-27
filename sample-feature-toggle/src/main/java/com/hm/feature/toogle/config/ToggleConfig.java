package com.hm.feature.toogle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

/**
 * ToggleConfig.
 *
 * @author huwenfeng
 */
@Configuration
public class ToggleConfig {
    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }
}
