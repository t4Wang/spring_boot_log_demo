package com.routz.springdemo.logdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello/{data}")
    public String home(@PathVariable String data) {
        logger.info("Into /hello/{}", data);
        logger.debug("DEBUG");
        if ("warn".equals(data)) {
            logger.warn("warn: {}", data);
            try (InputStream is = new FileInputStream(new File("/warn"))) {
                logger.info("Load File: warn");
            } catch(Exception e) {
                logger.error("error", e);
                return "error";
            }
        }
        return "Hello, visitor!";
    }

}
