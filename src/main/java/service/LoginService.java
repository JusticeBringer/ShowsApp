package service;

import model.individual.Client;
import model.individual.Host;
import repositories.UserRepository;

public class LoginService {

    private UserRepository userRepository;

    public LoginService() {
        userRepository = new UserRepository();
    }

    public boolean loginClient(Client client){
        String pswFromDB = userRepository.findUserByUsernameClient(client.getUsername());

        if (pswFromDB != null) {
            return pswFromDB.equals(client.getPassword());
        }
        return false;
    }
    public boolean loginHost(Host host){
        String pswFromDB = userRepository.findUserByUsernameHost(host.getUsername());

        if (pswFromDB != null) {
            return pswFromDB.equals(host.getPassword());
        }

        return false;

    }
}