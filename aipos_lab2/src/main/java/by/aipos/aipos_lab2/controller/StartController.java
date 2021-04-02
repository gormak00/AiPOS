package by.aipos.aipos_lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping(value = "/welcome")
    public String welcomePage() {
        return "start/welcomePage";
    }
}
