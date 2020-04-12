# wfm-kafka-spike project

This project is part of the spike to research how to consume events from Talkdesk's Kafka.

The authentication credentials are not included in the project source and will be transferred through secure means,
e.g. Keybase.

In order to execute the project with the correct credentials, the following command line, that sets some environment
variables with the required values, may be used:

```
KAFKA_USERNAME=<the username> \
    KAFKA_PASSWORD=<the password> \
    TRUSTSTORE=<the path to the keystore> \
    mvn clean quarkus:dev
```
