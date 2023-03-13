# krakenflex
KrakenFlex coding sample

Steps to run in local :

Download this project as ZIP or clone it using GitHub desktop.
If it is downloaded as ZIP, extract it.
Since this application is developed and compiled on Java 11, So Java 11 and Maven both have to be installed on the machine.
Once the application is downloaded in local make sure it's cleaned and compiled with "mvn clean install" to download all the required dependencies
Go to cmd prompt and point the current directory to the folder where this project is downloaded and run the below command to bring up the server

java -jar target/KrakenFlexTest-0.0.1-SNAPSHOT.jar src/main/java/com/kf/demo/KrakenFlexTestApplication.java

If everything goes well, you will see "Started KrakenFlexTestApplication ...." message on the console then hit the below url

http://localhost:8080/actuator/health in browser to check the health of the application

Below urls give the actual output based on the request type

1. http://localhost:8080/outages ---> lists all outage information after 2022-01-01 dated as per requirement.

2. http://localhost:8080/site-info/{site-id}  ---> displays the given site information with devices.
ex: http://localhost:8080/site-info/norwich-pear-tree

3. http://localhost:8080/site-outages/{site-id} --> posts the given outage information to backend.
ex: http://localhost:8080/site-outages/kingfisher
