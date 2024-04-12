# taxi fare from point to point
## Overview

at the moment the application calculates the cost of a taxi from the points I specify

## Prerequisites

- Docker installed on your machine.

## Getting Started

### Step 1: Creater docker-compose file

Copy my docker-compose.yml on your local machine

### Step 2: Navigate to the Project Directory

Change your current directory to the file directory

### Step 3: run!

run the command "docker-compose up"

### Step 4: copy the configuration files

folders will appear in your directory:
/config/prometheus.yml,
/ELK/logstash/config/logstash.yml,
/ELK/logstash/pipeline


1. delete the prometheus.yml folder and replace it with the corresponding file from my repository
2. delete the logstash.yml folder and replace it with the corresponding file from my repository
3. place the logstash.conf file in the pipline folder


## Additional Information

The application is available at http://localhost:8080/current-price
You can see all logs at http://localhost:5601
And metrics at http://localhost:3000

## Conclusion

This README provides a basic guide to running your application using Docker Compose. For more detailed information, refer to the official Docker documentation or the project's documentation.
