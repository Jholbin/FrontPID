package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.cibertec.security.AuthResponse;
import com.cibertec.security.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller
public class LoginController {

    @Value("${backend.url}")  // Para tener configurado el backend URL en application.properties
    private String backendUrl;
    
   // Muestra el formulario de login
   @GetMapping("/login")
   public String showLoginForm() {
       return "login"; // Mapea a login.jsp
   }

    // Maneja el envío del formulario de login
    @PostMapping("/login")
    public String processLogin(String username, String password, Model model, HttpServletResponse response, HttpSession session) {
        // Crear un objeto para enviar las credenciales
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Usar RestTemplate para enviar el POST
        RestTemplate restTemplate = new RestTemplate();

        try {
            AuthResponse authResponse = restTemplate.postForObject(backendUrl + "/auth/login", loginRequest, AuthResponse.class);
            if (authResponse != null) {
                // Almacenar el token en la sesión del servidor
                session.setAttribute("token", authResponse.getToken());
                session.setAttribute("username", username);

                // Redirigir a la página de inicio (home.jsp)
                return "home";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }
        } catch (Exception e) {
            // Si hay error, mostrar un mensaje de error
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    // Verificar si el usuario está logueado antes de acceder a páginas protegidas
    @GetMapping("/protected")
    public String protectedPage(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");

        if (token == null) {
            return "redirect:/login";  // Redirigir al login si no hay token
        }

        model.addAttribute("message", "This is a protected page.");
        return "protectedPage";  // Página protegida
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // Invalidar la sesión actual
        session.invalidate();

        // Evitar caché del navegador
        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "no-cache");

        // Mensaje opcional que se puede mostrar en el login
        request.setAttribute("mensaje", "El usuario salió de sesión");
        
        // Redirigir al login
        return "login"; // JSP que se mostrará después del logout
    }
}
