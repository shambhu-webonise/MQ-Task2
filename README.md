# MQ-Task2 (Consumer/Subscriber Module)
This is a web project having subscribed with Message Queue(ActiveMQ). Server is integrated with all required configuration for Active MQ. Every time a new message entered by Producer (MQ-Task) is subscribed into it, write the content of message into a text file into user_home/NM/tmp directory. Each message is written separately with file name MQ generated messageID. 

##prerequisites
- Java 8
- Maven 3+
- App server (tomcat, tomee, glassfish, jetty etc)
- Active MQ 5.12.0 (currently tested with version 5.12, can work on other version)

##Setup & Run
####Without IDE
1. Clone the repository
2. navigate to the MQ-Task directory (project home directory)
3. Build by using maven (mvn clean install)
4. Rename warfile to "mq"
5. Run the app server
6. Deploy the war file to server
7. Open the User Home -> NM -> tmp to see the message file written there


####This can be import into the Eclipse or STS by following steps: 
1. clone the repository
2. import the project as maven project
3. integrate Tomcat to Eclipse.STS or any other app server
4. Run on sever
5. Open the User Home -> NM -> tmp to see the message file written there
