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
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                Seat seat = new Seat(Integer.parseInt(cls[1]));
                Show show;

                switch (count) {
                    case 0:
                        show = new Show(Integer.parseInt(cls[0]), seat, tickets.get(0));
                        show.setHasHost(false);
                        break;
                    case 1:
                        show = new Show(Integer.parseInt(cls[0]), seat, tickets.get(1), hosts.get(0));
                        break;
                    case 2:
                        show = new Show(Integer.parseInt(cls[0]), seat, tickets.get(2), hosts.get(1));
                        break;
                    default:
                        show = new Show(Integer.parseInt(cls[0]), seat, tickets.get(3));
                        show.setHasHost(false);
                        break;
                }

                count ++;

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

        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] cls = line.split(splitBy);

                List<Show> shows = readShows();
                Theatre theatre;

                switch (count) {
                    case 0:
                        theatre = new Theatre(Integer.parseInt(cls[0]), Double.parseDouble(cls[1]),
                                Integer.parseInt(cls[2]), cls[3], cls[4], shows.get(0), cls[5]);
                        shows.get(count).setHasHost(false);
                        break;
                    case 1:
                        theatre = new Theatre(Integer.parseInt(cls[0]), Double.parseDouble(cls[1]),
                                Integer.parseInt(cls[2]), cls[3], cls[4], shows.get(1), cls[5]);
                        break;
                    case 2:
                        theatre = new Theatre(Integer.parseInt(cls[0]), Double.parseDouble(cls[1]),
                                Integer.parseInt(cls[2]), cls[3], cls[4], shows.get(2), cls[5]);
                        break;
                    default:
                        theatre = new Theatre(Integer.parseInt(cls[0]), Double.parseDouble(cls[1]),
                                Integer.parseInt(cls[2]), cls[3], cls[4], shows.get(3), cls[5]);
                        shows.get(count).setHasHost(false);
                        break;
                }

                //adding the show to the list of hosted shows by the theatre
                List<String> thHost = theatre.getShowsHosted();
                thHost.add(shows.get(count).getTicket().getShowName());
                theatre.setShowsHosted(thHost);

                theatres.add(theatre);

                count ++;
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
