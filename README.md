# krakenflex
KrakenFlex coding sample

Steps to run in local :

Download this project as ZIP or clone it using GitHub desktop.
If it is downloaded as ZIP, extract it.
Since this application is developed and compiled on Java 11, So Java 11 has to be installed on the machine.
Once the application is downloaded in local make sure it's cleaned and compiled with "mvn clean install" to download all the required dependencies
Once it's cleaned and installed run http://localhost:8080/actuator/health in browser to check the health of the application

Below urls give the actual output based on the request type

1. http://localhost:8080/outages ---> lists all outage information after 2022-01-01 dated as per requirement.

2. http://localhost:8080/site-info/{site-id}  ---> displays the given site information with devices.
ex: http://localhost:8080/site-info/norwich-pear-tree

3. http://localhost:8080/site-outages/{site-id} --> posts the given outage information to backend.
ex: http://localhost:8080/site-outages/kingfisher
