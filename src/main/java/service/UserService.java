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
    public void updateMoneyToHost(int hID, double mon){
        userRepository.updateHostMoney(hID, mon);
    }
    public void updateSeatsToShows(int getNrShowNrToBuy, int seatsNewNumber) {
        userRepository.updateSeatsAtShow(getNrShowNrToBuy, seatsNewNumber);
    }

}
