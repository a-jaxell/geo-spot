# GeoSpot - REST API for locations of interest

This was a school assignment to practice making a REST API using Spring Boot.


# Installing the project

Clone onto your computer using:
```shell
git clone <repository-address>
```

Preferably run this initially in an IDE like IntelliJ idea which makes it easier to first test and run.

## Database

This project was developed using a MySql version 8.2
This requires a MySQL database to run.

To run one with docker:

```shell
docker run --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<rootpassword> -p 3306:3306 -d mysql
```

You need to set environmental variables for user and password when running the application.

```shell
MYSQL_PASSWORD=<password>;MYSQL_USERNAME=<user>;
``` 

## API Documentation

Api documentation is available with the help of Postman [here](https://documenter.getpostman.com/view/27170679/2sA3JNaLBr)