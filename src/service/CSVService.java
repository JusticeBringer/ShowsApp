package service;

import model.event.Show;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;
import repositories.CSVRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class CSVService {

    private CSVRepository csvRepository;

    public CSVService(){
        csvRepository = new CSVRepository();
    }

    public List<Client> getClients() throws FileNotFoundException {
        return csvRepository.readClients();
    }

    public List<Host> getHosts() throws FileNotFoundException {
        return csvRepository.readHosts();
    }

    public List<Show> getShows() throws FileNotFoundException {
        return csvRepository.readShows();
    }

    public List<Theatre> getTheatres() throws FileNotFoundException {
        return csvRepository.readTheatres();
    }

}
