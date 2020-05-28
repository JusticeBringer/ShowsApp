package service;

import model.individual.Client;
import model.individual.Host;
import repositories.UserRepository;

public class LoginService {

    private UserRepository userRepository;
    private AuditService auditService = new AuditService();

    public LoginService() {
        userRepository = new UserRepository();
    }

    public boolean loginClient(Client client){
        auditService.writeInAuditFile("Client trying to login", Thread.currentThread().getName());

        String pswFromDB = userRepository.findUserByUsernameClient(client.getUsername());

        if (pswFromDB != null) {
            return pswFromDB.equals(client.getPassword());
        }
        return false;
    }
    public boolean loginHost(Host host){
        auditService.writeInAuditFile("Host trying to login", Thread.currentThread().getName());

        String pswFromDB = userRepository.findUserByUsernameHost(host.getUsername());

        if (pswFromDB != null) {
            return pswFromDB.equals(host.getPassword());
        }

        return false;
    }

}