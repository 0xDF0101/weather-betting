package com.project.weatherbetting.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/region/{code}")
    public String regionPage() {
        return "forward:/region.html";
    }
}
