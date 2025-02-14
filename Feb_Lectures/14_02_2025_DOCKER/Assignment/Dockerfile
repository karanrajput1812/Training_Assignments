FROM openjdk:11-jdk-slim

WORKDIR /root/demo
COPY EmpManageApp.java /root/demo

RUN apt-get update && apt-get install -y wget && \
    wget https://jdbc.postgresql.org/download/postgresql-42.2.23.jar -P /root/demo

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH=$PATH:$JAVA_HOME/bin

EXPOSE 8080

# Run the application
# Compile the application
RUN javac -cp ".:postgresql-42.2.23.jar" EmpManageApp.java

# Run the application
CMD ["java", "-cp", ".:postgresql-42.2.23.jar", "EmpManageApp"]