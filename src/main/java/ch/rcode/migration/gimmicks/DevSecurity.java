package ch.rcode.migration.gimmicks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DevSecurity {

    @Configuration
    public class FirstConfiguration {

        @Configuration
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public class InnerConfiguration {

            private final Logger log = LoggerFactory.getLogger(InnerConfiguration.class);

            @Bean
            public SecurityFilterChain innerChain(HttpSecurity http) throws Exception {
                log.info("Bean innerChain built in InnerConfiguration");
                return http.build();
            }
        }
    }

    @Configuration
    public class SecondConfiguration {

        private final Logger log = LoggerFactory.getLogger(SecondConfiguration.class);

        @Bean
        public SecurityFilterChain secondChain(HttpSecurity http) throws Exception {
            log.info("Bean secondChain built in SecondConfiguration");
            return http.build();
        }
    }
}
