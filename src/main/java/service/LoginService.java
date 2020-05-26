package service;

import model.individual.Client;
import model.individual.Host;
import model.individual.Person;
import repositories.UserRepository;

public class LoginService {

    private UserRepository userRepository;

    public LoginService() {
        userRepository = new UserRepository();
    }

    public boolean loginClient(Client client){
        Client result = userRepository.findUserByUsernameClient(client.getUsername());
        if (result != null) {
            return true;
            //TODO uncomment below when loaded clients from database
            // return result.getPassword().equals(client.getPassword());
        }
        return false;
    }
    public boolean loginHost(Host host){
        Host result = userRepository.findUserByUsernameHost(host.getUsername());

        if (result != null) {
            return result.getPassword().equals(host.getPassword());
        }

        return false;

    }
}