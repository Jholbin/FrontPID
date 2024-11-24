package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";  // Redirige a la página de login
    }

    @GetMapping("/home")
    public String showRegisterForm(HttpSession session) {
        String token = (String) session.getAttribute("token");

        if (token == null) {
            return "redirect:/login";  // Redirige al home si no está logueado
        }

        return "home"; 
    }
    
}
