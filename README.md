This is a simple implementation of a servlet CRUD application, which
interacts with postgresql via hibernate and migrate DB using flyway.

Run Project:
1) log in as root to postgresql(sudo -i -u postgres):
     1.1) postservice=#  CREATE database postservice;
     1.2) $ psql -d template1 -U postgres
          template1=# create user username with password 'password';
          template1=# grant all privileges on database postservice to username;
     1.3) postservice=# GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to username;
          postservice=# GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to username;
          postservice=# GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to username;


2)   Execute the project by running:
     2.1) $mvn clean install
     2.2) $mvn flyway:migrate
     2.3) $java -jar HibernateCrudConsole-1.0-SNAPSHOT-jar-with-dependencies.jar
     
3)  To get data from a server the client sends the GET or POST request to 
    http://localhost:8080/post-service/users(posts or regions) with:
    create, update, list, delete(with parameter id, ?id=...) or find(with parameter id, ?id=...).
   

