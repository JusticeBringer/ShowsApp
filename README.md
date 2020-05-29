# ShowsApp
A java application for show attendance and hosting 

## Project idea

Make a java application for show attendance for normal users (named clients) and show hosting for specific users (named hosts). 
A user can log in into the app, see details about all available shows, buy a ticket at one or more shows (depends on client money) and he can refund the ticket (and get the money back). 
A host can see the list of shows and see what show does not have a host. If he wants to be the host he must have money to host it. Once a show is hosted another host cannot host it. A host can also cancel a show, which leads to its cancel and deleting it from the database. Project design is such that a show can initialy or not have a host, but, once its host wants to cancel the show, the show gets deleted from database table. 
All the data is given from MySQL database and live updated.

## Project design

We have a class Person and classes Client and Host inheritate that class.
We have a class Building and class Theatre inheritates that class.
We have a class Seat and a class Ticket and the class Show uses them for its constructor.

Project design goes like this: 
1) Logged in user is a client: He buys a ticket at a show which is hosted in a theatre which has a limited amount of seats and it has or not a host and the show is at a specific date. The client can refund the ticket after buying it, but cannot refund it after the event took place.
2) Logged in user is a host: He sees available list of shows that are open to be hosted and can choose one or more, depending on his budget. Later on, he can cancel the show and the entire event is deleted.

## Possible actions defined in GUI

1. Log in (as client or host)
2. Buy ticket (only client)
3. Refund ticket (only client)
4. Host show (only host)
5. Cancel show (only host)

## MySQL database queries

Except create, operations as read, update, delete were implemented.

### Read

1. Get clients from database
2. Get hosts from database
3. Get tickets from database
4. Get shows from database
5. Get theatres from database
6. See if user is in database for logging in

### Update

1. Update client money after buying a ticket
2. Update client money after refunding a ticket
3. Update host money after hosting a show
4. Update host money after cancelling a show
5. Update clients money after show has been cancelled (possible not working as intended)
6. Update available seats after client buys ticket at a show
7. Update available seats after client refunds a ticket at a show

### Delete

1. Delete ticket record after host cancelled the show
2. Delete show record after host cancelled the show

## Audit service

The audit is a comma separated value (csv) file, with 3 columns:
1. Action name
2. Timestamp
3. Thread name - thread that called a method

Every time a more important operation is made, it is reported in the audit.csv file. Example of its records can be seen [here](https://github.com/JusticeBringer/ShowsApp/blob/master/csvFiles/audit.csv).
 
## Services classes

For this specific project, following services classes have been used:

1. Database Service - methods used to extract data from the database
2. Login Service - methods used at the login panel
3. User Service - methods used for tracking and implementing user actions
4. Show Service - methods used for show related features
5. Audit Service - one single method, used for writing in the audit file

## GUI

Following images refer to the GUI of the application.

### GUI login panel

![Image of login panel](
https://github.com/JusticeBringer/ShowsApp/blob/master/appSS/login_panel.JPG)

### GUI client panel

![Image of client panel](
https://github.com/JusticeBringer/ShowsApp/blob/master/appSS/client_panel.JPG)

### GUI host panel

![Image of host panel](
https://github.com/JusticeBringer/ShowsApp/blob/master/appSS/host_panel.JPG)

### GUI live client demo

![Client video](
https://youtu.be/jHRGMr7S12E)

### GUI live host demo

![Client video](
https://youtu.be/xDSXZJoinKM)

## Database

The database used in this application was MySQL version 8.0.20. Next images are about the tables clients and theatres. At last, a video about the database.

### Clients table and queries used

![Image of clients table and queries](
https://github.com/JusticeBringer/ShowsApp/blob/master/appSS/clients_data.JPG)

### Theatres table and queries used

![Image of theatres table and queries](
https://github.com/JusticeBringer/ShowsApp/blob/master/appSS/theatres.JPG)

### Database video

![Database video](
https://youtu.be/YsBKeAJrFLY)

## The requirements

This project was based on given set of requirements (following is a Google translate text for the three phases)

### Phase 1

1. Definition of the system: 
  a. To create a list based on the chosen topic with at least 10 actions / queries that can be done within the system and a list with at least 8 types of objects. 
2. Implementation: to implement in the Java language an application based on those defined in point. The application will contain:. simple classes with private / protected attributes and access methods 
  a. at least 2 different collections capable of managing previously defined objects (List, Set, Map, etc.) of which at least one to be sorted - uni arrays will be used - / two-dimensional in case the collections are not browsed until the checkpoint date. 
  b. use inheritance for the creation of additional classes and their use in collections; 
  c. at least one service class to display operations 
  d. one main class from which calls to services are made

### Phase 2

Extend the project from the first stage by achieving persistence using files. Csv type 1 files (comma separated values) will be made for at least 4 of the classes defined in the first stage. - generic singleton services will be performed for writing and reading from files - at the start of the program the data will be uploaded from the files using the services created 2. Performing an audit service - a service will be performed to write to a CSV file each time one of the actions described in the first stage is performed. File structure: action_name, timestamp

### Phase 3

Replace the services performed in stage II with services that ensure persistence using the database using JDBC. - to perform services that expose operations such as create, read, update, delete for at least 4 of the defined classes - To create a graphical interface in which to display at least 5 of the initially defined actions. The interface will have at least 2 different screens that allow navigation between them. Swing or JSP will be used to create the graphical interface. Other frameworks can be used, but should be discussed beforehand in the laboratory. - Another column will be added to the file exported by the audit service: thread_name, representing the name of the thread that called the method.

## Technologies used

1. Java (vers. 11) + Java Swing for GUI
2. MySQL (vers. 8.0.20)

## Author

Arghire Gabriel (me)
