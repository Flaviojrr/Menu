# Meal and Restaurant Orders CRUD Application 🍽️

## Overview
This is a Java web application built with JSP (JavaServer Pages) for creating, updating, and deleting (CRUD) meals and restaurant orders. The project uses Apache Tomcat 9 as the web server and HSQL as the embedded database.

## Requirements
Before running the application, ensure you have the following software installed:

* Java Development Kit (JDK) 8 or later
* <a href="https://tomcat.apache.org/download-90.cgi">Apache Tomcat 9</a>
* <a href="https://www.enterprisedb.com/downloads/postgres-postgresql-downloads"> PostgreSQL Database</a>
  
## Getting Started

Clone the repository:

```
git clone https://github.com/Flaviojrr/Menu
```

## Setting up the database and tables

1. You need to create locally at PostgreSQL a database named `VillaEleganza`.
2. Inside it, run the following query to create the `menu` table: 

```bash
CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    ingredients VARCHAR(255),
    type VARCHAR(255)
);
```

## Go to Eclipse IDE or similar

* We chose to use <a href="https://www.eclipse.org/downloads/packages/release/2023-09/r/eclipse-ide-enterprise-java-and-web-developers">Eclipse IDE for Enterprise Java and Web Developers</a> to make the project and tutorial.

1. Configure the Server in Eclipse IDE >> Servers >> New Server >> Apache Tomcat v9.0 Server >> Browse extracted zip directory. 
2. Alter the password in `DAOMenuItem.java` class as needed at your own machine 

...




