# TELEWORK PLATFORM

## Introdution
Proxym's Telwork platform offer a solution for managing teleworking, compliance with health standards, optimising workspace and planning the presence of employees in the office according to configurable priority rules. 

## Features
The functionality of the system will be divided by the type of access of each actor.

**Admin's** main features are :
- Authenticate: In order to access the application's features, the admin must authenticate himself by entering his email and password.
- Create an account: To register users to our application, the admin must enter an email, and generate a password.
- Modify an account: the admin can change the roles of the users. 
- Create a pole: To create a cluster, the admin must enter the name of a pole. 
- Modify a pole: For each cluster, the admin must assign a pole manager. 
- Consult information: The administrator can consult the information of all users relating to our application.

**Pole manager's** main features are :
- Authenticate: In order to access the application's features, the pole manager must authenticate himself by entering his email and password.
- View schedules : the cluster manager can view all schedules of all developers. 
- Approve schedules: the cluster manager can approve the schedule of each developer by entering the schedule decision.
- Consult information : the manager can consult the information of all the users relating to our application. 

**Project manager's** main features are :
- Authenticate: In order to access the application's features the project manager must authenticate by entering their email and password.
- Configure schedules: The project manager can configure all schedules for all developers by entering the start date, end date and scheduling decision.
- Create Projects: The Project Manager can create all projects by entering the name of each project.
- Assign Developers : the Project Manager can assign developers to each project.
- View information : the project manager can view the information of all users relating to our application. 

**Developer's** main features are :
- Authenticate: In order to access the application's features, developers must authenticate themselves by entering their email and password.
- Consult information: the project manager can consult the information of all developers related to our application.

## Professional supervisor 
[Proxym Group](https://www.proxym-group.com/) is a global IT player recognised as a digital service provider in the fields of Smart Gov, Finance and Retail as well as an integrator of digital solutions.
With more than 600 successful digital projects and satisfied customers since its foundation in 2006 and more than 170 mobile software engineers in Europe, Africa and the Middle East, Proxym Group is at the heart of innovation.

## Development Environment

Following libraries were used during the development of this starter kit :
Spring Boot : Server side framework
Docker : Containerising framework
Postgres : SQL database managment system
Swagger : API documentation
JWT : Authentication mechanism for REST APIs

## Installtion 
Create the postgres Database using 
``` 
sudo -u postgres psql
postgres=# CREATE DATABASE [Database Name];
``` 
and run the serve.

For more details about database connexion, you can check this [Article](https://chartio.com/resources/tutorials/how-to-set-the-default-user-password-in-postgresql/)

## Throughout the entire application
Developers can find more information about this Application on my Github repository. I have written a series of two repositories on Spring Boot and Angular, here are the links:
- [Front-end developed using Angular 10](https://github.com/Rayen-cherni/Front-end-Telework-Platform)
- [Back-end developed using Spring boot](https://github.com/Rayen-cherni/Back-end-Telework-Platform)
