mvn liberty:stop-server && mvn clean install && mvn liberty:start-server
# tail -f 20 backendServices/target/liberty/wlp/usr/servers/backendServer/logs/messages.log
