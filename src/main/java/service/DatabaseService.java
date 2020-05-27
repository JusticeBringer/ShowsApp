package service;

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import repositories.DatabaseRepository;

import java.util.List;

public class DatabaseService {
    private DatabaseRepository databaseRepository;

    public DatabaseService(){
        databaseRepository = new DatabaseRepository();
    }

    public List<Client> getDBClients(){
        return databaseRepository.databaseClients();
    }
    public List<Host> getDBHosts(){
        return databaseRepository.databaseHosts();
    }
    public List<Show> getDBShows(){
        return databaseRepository.databaseShows();
    }
    public List<Theatre> getDBTheatres(){
        return databaseRepository.databaseTheatres();
    }

}
