package repositories;

import model.event.Seat;
import model.event.Show;
import model.event.Ticket;
import model.individual.Client;
import model.individual.Host;
import model.structure.Theatre;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CSVRepository{
    private String csvFile;
    private static Integer writerAUD = 0;

    public CSVRepository(){
        this.csvFile = "";
    }

    public  List<Client> readClients() throws FileNotFoundException {
        List<Client> clients = new ArrayList<>();

        csvFile = "./csvFiles/clients.csv";

        Scanner sc = new Scanner(new File(csvFile));
        sc.useDelimiter(",");

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                Client client = new Client(Integer.parseInt(cls[0]), cls[1], cls[2], cls[3], cls[4], cls[5], Integer.parseInt(cls[6]), Integer.parseInt(cls[7]));
                clients.add(client);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public List<Host> readHosts() throws FileNotFoundException {
        List<Host> hosts = new ArrayList<>();

        csvFile = "./csvFiles/hosts.csv";

        Scanner sc = new Scanner(new File(csvFile));
        sc.useDelimiter(",");

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                Host host = new Host(Integer.parseInt(cls[0]), cls[1], cls[2], cls[3], cls[4], cls[5],
                                     Integer.parseInt(cls[6]), Integer.parseInt(cls[7]));
                hosts.add(host);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return hosts;
    }

    public List<Ticket> readTickets() throws  FileNotFoundException {
        List<Ticket> tickets = new ArrayList<>();

        csvFile = "./csvFiles/tickets.csv";

        Scanner sc = new Scanner(new File(csvFile));
        sc.useDelimiter(",");

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);


                Ticket ticket = new Ticket(Integer.parseInt(cls[0]), Integer.parseInt(cls[1]), Integer.parseInt(cls[2]),
                                           Integer.parseInt(cls[3]), Integer.parseInt(cls[4]), cls[5], cls[6]);
                tickets.add(ticket);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public List<Show> readShows() throws FileNotFoundException {
        List<Ticket> tickets = readTickets();
        List<Host> hosts = readHosts();
        List<Show> shows = new ArrayList<>();

        csvFile = "./csvFiles/shows.csv";

        Scanner sc = new Scanner(new File(csvFile));
        sc.useDelimiter(",");

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                Seat seat = new Seat(Integer.parseInt(cls[1]));

                Show show = new Show(Integer.parseInt(cls[0]), seat, tickets.get(0), hosts.get(0));
                shows.add(show);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return shows;
    }

    public List<Theatre> readTheatres() throws FileNotFoundException {
        List<Theatre> theatres = new ArrayList<>();

        csvFile = "./csvFiles/theatres.csv";

        Scanner sc = new Scanner(new File(csvFile));
        sc.useDelimiter(",");

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                List<Show> shows = readShows();

                Theatre theatre = new Theatre(Integer.parseInt(cls[0]), Double.parseDouble(cls[1]),
                                              Integer.parseInt(cls[2]), cls[3], cls[4], shows.get(0), cls[5]);
                theatres.add(theatre);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return theatres;
    }

    public void writeAudit(String numeActiune) {
        String whereWrite = "./csvFiles/audit.csv";

        try {
            FileWriter fw = new FileWriter(whereWrite, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            if (writerAUD == 0) {
                pw.println("nume_actiune,data");
            }

            Date date = new Date();

            pw.println(numeActiune + "," + date.toString());
            pw.flush();
            pw.close();

            writerAUD ++;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
