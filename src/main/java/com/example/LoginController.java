package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yongyeon on 2016-08-07.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "loginForm")
    String loginForm() {
        return "loginForm";
    }
}
