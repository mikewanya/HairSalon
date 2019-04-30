## HairSalon Application
an app for a hair salon. The owner should be able to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist. 

**Specifications**
1. A salon employee can see list of all stylists.
2. A salon owner can a stylist, employee should see their details and list of all clients that belong to the stylist.
3. Employees can add new stylist
4. Employees can add clients to a stylist.
5. Employees can update stylist details.
6. Employees can update client details.
7. Employees can delete stylist.
8. Employees can delete client.

**Technologies Implemented**
1. Java 
2. Spark Java Web Framework
3. PostgreSQL for Database
4. Velocity Template Engine

**Set Up and Installation**
1. Download the project folders
> ```
>$ git clone https://github.com/mikewanya/hairsalon.git
>$ cd hairsalon
>```
2. Change postgres db login details in:
> ```
> src/main/java/DbDetails.java 
>```
3. Database instructions for PSQL
In PSQL:
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id SERIAL PRIMARY KEY, name VARCHAR, telno VARCHAR);
CREATE TABLE clients (id SERIAL PRIMARY KEY, name VARCHAR, telno VARCHAR, stylistId integer);

4. Launch
> ```
> $gradle run
>```
> open [http://localhost:4567](http://localhost:4567)

**License**
Mike Wanya
Licensed under [MIT Licence](License.txt) 