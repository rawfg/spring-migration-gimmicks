package ch.rcode.migration.gimmicks;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class HelloWorldController {

    @RolesAllowed("USER")
    @GetMapping("/hello")
    public String helloUser() {
        return "Hello " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
