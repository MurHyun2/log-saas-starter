
package com.oursaas.autolog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
@ConditionalOnWebApplication
public class AutoLogConfiguration {
    @Bean
    public Filter autoLogFilter() {
        return new AutoLogFilter();
    }
}
