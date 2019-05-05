# Money Transfer

It is a simple application which demonstrates money transfer between two accounts.

# Technologies

* Maven
* MicroProfile Framework: _OpenLiberty_
* REST with OpenApi
* H2 im-memory database
* JWT for sequrity

## Run Sample application
    mvn clean package liberty:run-server

### Run Sample application with tests
    mvn clean install liberty:run-server

### Open url's in browser
    http://localhost:9080
    
### Connecting to the H2 Console:

Database is configured as in-memory

JDBC URL: __jdbc:h2:tcp://localhost/mem:banking__

Sample SQL:

`
  create table account(id int primary key, number varchar(255), balance int);
  insert into account values (1, 'abc', 4);
  select * from account;
`
