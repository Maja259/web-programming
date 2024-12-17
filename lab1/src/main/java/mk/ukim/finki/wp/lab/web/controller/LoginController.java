package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        Users user=null;

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        try{
            user=authService.login(username, password);
            request.getSession().setAttribute("user", user);
            return "redirect:/events";
        }catch (RuntimeException e){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }
}
