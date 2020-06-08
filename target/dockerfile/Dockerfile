FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/local/codeneuron
ADD  codeneuron-0.0.1-SNAPSHOT.jar /usr/local/codeneuron/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
