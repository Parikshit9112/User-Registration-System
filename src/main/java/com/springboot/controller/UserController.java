package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value="/dashboard")
     public String userDashboard() {
        System.out.println("Dashboard page loding");
         return "user/dashboard";
     }
}
