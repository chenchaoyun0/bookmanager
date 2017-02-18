package com.sttx.bookmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexHomeController {
    @RequestMapping("/")
    public String indexHome() {
        return "forward:/book/selectBookPages";
    }
}
