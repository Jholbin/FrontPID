package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cibertec.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegistroController {

    @Value("${backend.url}")
    private String backendUrl;

    @GetMapping("/register")
    public String showRegisterForm(HttpSession session, Model model) {
        // Verifica si hay un token en la sesión antes de mostrar el formulario de registro
        String token = (String) session.getAttribute("token");

        if (token == null) {
            return "redirect:/login";  // Redirige al login si no está logueado
        }

        return "register";  // Muestra el formulario de registro
    }

    @PostMapping("/register")
    public String processRegister(String username, String password, String firstname, String lastname, String dni, String email, String role, HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");

        if (token == null) {
            return "redirect:/login";  // Redirige al login si no está logueado
        }

        // Enviar datos de registro al backend
        User user = new User(username, password, firstname, lastname, dni, email, "ADMIN");

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Intentamos registrar al usuario
            restTemplate.postForObject(backendUrl + "/auth/register", user, Void.class);
            model.addAttribute("message", "Registration successful!");
            return "home";  // Redirige al home después del registro
        } catch (HttpClientErrorException.BadRequest e) {
            // Capturamos el error y extraemos el mensaje de error desde el backend
            String errorResponse = e.getResponseBodyAsString();

            // Si el backend envía un mensaje de error en el campo "token", lo mostramos
            if (errorResponse.contains("token")) {
                // Extraemos el mensaje de error (podría ser "El DNI ya está registrado", etc.)
                model.addAttribute("error", extractErrorMessage(errorResponse));
            } else {
                model.addAttribute("error", "Registration failed");
            }

            return "register";  // Regresamos al formulario de registro con el mensaje de error
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred");
            return "register";  // Regresamos al formulario de registro con un mensaje genérico
        }
    }

    private String extractErrorMessage(String errorResponse) {
        if (errorResponse.contains("token")) {
            // Extraemos el mensaje y lo formateamos para mejorar la experiencia del usuario
            String message = errorResponse.substring(errorResponse.indexOf(":") + 2, errorResponse.lastIndexOf("\""));
            
            if (message.contains("DNI")) {
                return "Este DNI ya está registrado. Por favor, elige otro.";
            } else if (message.contains("Email")) {
                return "Este correo electrónico ya está registrado. Intenta con otro.";
            } else if (message.contains("usuario")) {
                return "Este nombre de usuario ya está en uso. Elige otro.";
            }
            
            // Si no tiene uno de los errores específicos, retornamos el mensaje tal cual
            return message;
        }
        return "Ocurrió un error inesperado. Intenta nuevamente.";
    }
}
