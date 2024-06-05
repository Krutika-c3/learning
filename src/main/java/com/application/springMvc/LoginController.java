package com.application.springMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String handleLogin(ModelMap model, @RequestParam String name,
                              @RequestParam String password) {
        if (loginService.notValidateUser(name, password)) {
            model.put("errorMessage", "Invalid Credentials !");
            return "login";
        }
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }
}

