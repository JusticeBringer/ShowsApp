package service;

import repositories.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void updateMoneyToClient(int clID, double mon){
        userRepository.updateClientMoney(clID, mon);
    }
}
