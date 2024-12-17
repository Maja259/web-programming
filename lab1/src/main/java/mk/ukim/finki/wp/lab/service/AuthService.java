package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Roles;


public interface AuthService {
    Users login(String username, String password);
    Users register(String username,String password, String repeatPassword, String firstName, String lastName, Roles role);
}
