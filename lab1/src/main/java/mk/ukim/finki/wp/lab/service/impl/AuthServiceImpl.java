package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Roles;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchExceptions;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.Jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users login(String username, String password){
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public Users register(String username, String password, String repeatPassword, String firstName, String lastName, Roles role) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchExceptions();
        }

        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsernameAndPassword(username,password).isEmpty()) {
            throw new UsernameAlreadyExistsException(username);
        }

        Users user = new Users(username, password, firstName, lastName, role);
        return userRepository.save(user);
    }
}
