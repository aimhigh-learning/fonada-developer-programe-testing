package com.fonada.srana.test.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sandeep.rana
 */
@RequestMapping("/home")
@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public void welcome() {
        log.info("Start up....");
    }
}
