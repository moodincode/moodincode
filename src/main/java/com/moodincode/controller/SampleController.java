package com.moodincode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leshang on 2018/5/9.
 */
@RestController
public class SampleController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}