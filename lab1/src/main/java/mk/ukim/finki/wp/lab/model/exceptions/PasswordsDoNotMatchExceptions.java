package mk.ukim.finki.wp.lab.model.exceptions;

public class PasswordsDoNotMatchExceptions extends RuntimeException {
    public PasswordsDoNotMatchExceptions() {
        super("Passwords do not match");
    }
}
