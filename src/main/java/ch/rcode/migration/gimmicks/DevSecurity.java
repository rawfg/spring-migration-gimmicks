package ch.rcode.migration.gimmicks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class DevSecurity {

    @Configuration
    public class FirstConfiguration {

        @Configuration
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public class InnerConfiguration extends WebSecurityConfigurerAdapter {

            private final Logger log = LoggerFactory.getLogger(InnerConfiguration.class);

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                super.configure(http);
                log.info("configure executed in InnerConfiguration");

            }
        }
    }

    @Configuration
    public class SecondConfiguration extends WebSecurityConfigurerAdapter {

        private final Logger log = LoggerFactory.getLogger(SecondConfiguration.class);

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            log.info("configure executed in SecondConfiguration");

        }
    }
}
