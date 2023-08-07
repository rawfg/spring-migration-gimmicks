package ch.rcode.migration.gimmicks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class HelloWorldController {

    private final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @RolesAllowed("USER")
    @GetMapping("/hello")
    public String helloUser() {
        log.info("Hello World executed");
        return "Hello " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
