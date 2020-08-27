package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by StuGud on 2020/8/24.
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
