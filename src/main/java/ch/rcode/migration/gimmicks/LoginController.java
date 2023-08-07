package ch.rcode.migration.gimmicks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public ModelAndView login() {
        log.info("Log executed");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
}
