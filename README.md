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

### Data

| No. | Username | Password |   |   |
|---|---|---|---|---|
| 1  | ob123 | ob123pwd |   |   |
| 2  | js456 | js456pwd |   |   |