package service;

import model.individual.Person;
import repositories.UserRepository;

public class LoginService {

    private UserRepository userRepository;

    public LoginService() {
        userRepository = new UserRepository();
    }

    public boolean login(Person user) {
        Person result = userRepository.findUserByUsername(user.getUsername());

        if (result != null) {
            if (result.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}