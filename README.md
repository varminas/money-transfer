# Money Transfer

It is a simple application which demonstrates money transfer between two accounts.

# Technologies

* Maven
* MicroProfile Framework: _OpenLiberty_
* REST with OpenApi
* H2 im-memory database
* JWT for security

## Run Backend application
    mvn clean package liberty:run-server

### Run Backend application with tests
    mvn clean install liberty:run-server

## Build and run UI
    npm ci
    npm run build
    npm run start

### Open UI in browser
    http://localhost:4200
    
### Connecting to the H2 Console:

Database is configured as in-memory

JDBC URL: __jdbc:h2:tcp://localhost/mem:banking__

### Test data

| No. | Username | Password |
|---|---|---|
| 1  | ob123 | ob123pwd |
| 2  | js456 | js456pwd |

## Docker

Application (Backend and UI) can be started within Docker containers.

### Build Docker containers and start them

    docker-compose up -d --build
    
Open the UI in the browser by the following link:

    http://localhost:80
    
Follow docker containers logs:

    docker-compose logs -f -- tail 100
    
Cleanup containers (delete volumes):

    docker-compose down -v
    
## Issues

* There is a problem by running backend docker container on __MacOS__ [Issue 48](https://github.com/OpenLiberty/guide-docker/issues/48)