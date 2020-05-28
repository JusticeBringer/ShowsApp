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


 
